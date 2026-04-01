package adega.projeto.com.demo.validador;

import adega.projeto.com.demo.exceptions.EntidadeJaCriadaException;
import adega.projeto.com.demo.model.entity.Funcionario;
import adega.projeto.com.demo.repository.FuncionarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FuncionarioValidador {

   private final FuncionarioRepository repository;

   public void validar(Funcionario funcionario){
       if (exitsFuncionario(funcionario)){
           throw new EntidadeJaCriadaException("Já existe um Funcionario com CPF na base de dados");
       }
   }

   public boolean exitsFuncionario(Funcionario funcionario){
       return repository.existsByCpf(funcionario.getCpf());
   }

}
