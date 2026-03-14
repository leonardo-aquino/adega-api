package adega.projeto.com.demo.service;

import adega.projeto.com.demo.controller.dtos.EnderecoDTO;
import adega.projeto.com.demo.exceptions.IdIncorretoException;
import adega.projeto.com.demo.model.entity.Endereco;
import adega.projeto.com.demo.repository.EnderecoRepository;
import adega.projeto.com.demo.repository.specs.EnderecoSpecs;
import adega.projeto.com.demo.validador.EnderecoValidador;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;
    private final EnderecoValidador validador;

    public Endereco salvarEndereco (Endereco endereco){
        validador.validar(endereco);
        return enderecoRepository.save(endereco);
    }

    public Page<Endereco> buscarEnderecos(Integer pagina,
                                          Integer tamanhoPagina,
                                          String numero,
                                          String rua,
                                          String cep,
                                          String bairro) {

        Specification<Endereco> specs = Specification
                .where(((root, query, cb) -> cb.conjunction()));

        if (cep != null){
            specs = specs.and(EnderecoSpecs.cepEqual(cep));
        }
        if (rua != null && !rua.isEmpty()) {
            specs = specs.and(EnderecoSpecs.ruaLike(rua));
        }
        if (numero != null && !numero.isEmpty()) {
            specs = specs.and(EnderecoSpecs.numeroLike(numero));
        }
        if (bairro != null && !bairro.isEmpty()) {
            specs = specs.and(EnderecoSpecs.bairroLike(bairro));
        }


        Pageable pageRequest = PageRequest.of(pagina,tamanhoPagina);
       return enderecoRepository.findAll(specs,pageRequest);
    }

    public Endereco obterPorId(String id){
        Optional<Endereco> optionalEndereco = enderecoRepository.findById(UUID.fromString(id));
        if (optionalEndereco.isEmpty()){
            throw new IdIncorretoException("id não encontrado! ");
        }
        return optionalEndereco.get();
    }

    public Void atualizarEndereco(String id, EnderecoDTO enderecoDTO) {
        Optional<Endereco> optionalEndereco = enderecoRepository.findById(UUID.fromString(id));

        Endereco endereco = optionalEndereco
                .map(e -> {
                    e.setRua(enderecoDTO.rua());
                    e.setCep(enderecoDTO.cep());
                    e.setNumero(enderecoDTO.numero());
                    e.setFornecedores(enderecoDTO.fornecedores());
                    e.setFuncionario(enderecoDTO.funcionarios());
                    e.setBairro(enderecoDTO.bairro());
                    return e;
                }).orElseThrow(() -> new IdIncorretoException("id não encontrado! "));

        enderecoRepository.save(endereco);
        return null;
    }

    public void deletarEndereco(String id) {
        Optional<Endereco> optionalEndereco = enderecoRepository.findById(UUID.fromString(id));
        if (optionalEndereco.isEmpty()){
            throw new IdIncorretoException("id não encontrado! ");
        }
       enderecoRepository.delete(optionalEndereco.get());
    }
}
