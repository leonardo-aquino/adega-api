package adega.projeto.com.demo.validador;

import adega.projeto.com.demo.exceptions.EntidadeJaCriadaException;
import adega.projeto.com.demo.model.entity.Endereco;
import adega.projeto.com.demo.repository.EnderecoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EnderecoValidador {

    private final EnderecoRepository repository;

    public void validar(Endereco endereco){
       if (exitsEndereco(endereco)){
           throw new EntidadeJaCriadaException("Endereço ja Criado");
       }
    }

    public boolean exitsEndereco (Endereco endereco){
         return repository.existsByNumeroAndRuaAndCepAndBairro(endereco.getNumero(),
                endereco.getRua(),
                endereco.getCep(),
                endereco.getBairro());

    }
}
