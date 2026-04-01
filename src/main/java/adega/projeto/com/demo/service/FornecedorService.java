package adega.projeto.com.demo.service;

import adega.projeto.com.demo.controller.dto.FornecedorDTO;
import adega.projeto.com.demo.controller.dto.FornecedorDTOResponse;
import adega.projeto.com.demo.exceptions.IdIncorretoException;
import adega.projeto.com.demo.model.entity.Fornecedor;
import adega.projeto.com.demo.repository.EnderecoRepository;
import adega.projeto.com.demo.repository.FornecedorRepository;
import adega.projeto.com.demo.repository.specs.FornecedorSpecs;
import adega.projeto.com.demo.validador.FornecedorValidador;
import jakarta.transaction.Transactional;
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
public class FornecedorService {

    private final FornecedorValidador validador;
    private final FornecedorRepository fornecedorRepository;
    private final EnderecoRepository enderecoRepository;

    @Transactional
    public void salvarFornecedor(Fornecedor fornecedor){
        validador.validar(fornecedor);
        fornecedorRepository.save(fornecedor);
    }

    public FornecedorDTOResponse buscarPorId(String id) {
        Optional<Fornecedor> existeFornecedor = fornecedorRepository.findById(UUID.fromString(id));
        if (existeFornecedor.isEmpty()){
            throw new IdIncorretoException("Não foi achado nenhum fornecedor com esse ID!");
        }
        Fornecedor fornecedor = existeFornecedor.get();
        return FornecedorDTOResponse.toDTO(fornecedor);

    }

    public Page<Fornecedor> buscarEnderecos(Integer pagina,
                                            Integer tamanhoPagina,
                                            String nome,
                                            String telefone,
                                            String email) {


        Specification <Fornecedor> specs = Specification
                .where(((root, query, cb) -> cb.conjunction()));

        if (nome != null && !nome.isEmpty()){

            specs = FornecedorSpecs.nomeLike(nome);
        }

        if (telefone != null && !telefone.isEmpty()){

            specs = FornecedorSpecs.telefoneEqual(telefone);
        }

        if (email != null && !email.isEmpty()){

            specs = FornecedorSpecs.emailLike(email);
        }

        Pageable pageable = PageRequest.of(pagina,tamanhoPagina);
        return fornecedorRepository.findAll(specs,pageable);
    }

    @Transactional
    public void deletarFornecedor(String id) {

        Optional<Fornecedor> existsFornecedor = fornecedorRepository.findById(UUID.fromString(id));
        if (existsFornecedor.isEmpty()){
            throw new IdIncorretoException("Id não encontrado");
        }
        fornecedorRepository.delete(existsFornecedor.get());
    }

    @Transactional
    public void atualizarFornecedor(String id, FornecedorDTO dto) {
        Optional<Fornecedor> exitsFornecedor = fornecedorRepository.findById(UUID.fromString(id));
        if (exitsFornecedor.isEmpty()){
            throw new IdIncorretoException("Fornecedor não existe");
        }
        Fornecedor fornecedor = exitsFornecedor.get();
        fornecedor.setProdutos(dto.produtos());
        fornecedor.setTelefone(dto.telefone());
        fornecedor.setEndereco(enderecoRepository.findById(UUID.fromString(dto.endereco())).
                orElseThrow(()-> new IdIncorretoException("Id incorreto")));
        fornecedor.setNome(dto.nome());
        fornecedor.setEmail(dto.email());

        fornecedorRepository.save(fornecedor);
    }
}
