package adega.projeto.com.demo.validador;

import adega.projeto.com.demo.exceptions.EntidadeJaCriadaException;
import adega.projeto.com.demo.model.entity.Fornecedor;
import adega.projeto.com.demo.repository.FornecedorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FornecedorValidador {

    private final FornecedorRepository repository;

    public void validar(Fornecedor fornecedor){
       if (exitsEndereco(fornecedor)){
           throw new EntidadeJaCriadaException("Fornecedor ja Criado");
       }
    }

    public boolean exitsEndereco (Fornecedor fornecedor){
         return repository.existsByNomeAndEnderecoAndTelefoneAndEmail(
                 fornecedor.getNome(),
                 fornecedor.getEndereco(),
                 fornecedor.getTelefone(),
                 fornecedor.getEmail());
    }
}
