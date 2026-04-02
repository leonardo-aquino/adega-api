package adega.projeto.com.demo.service;

import adega.projeto.com.demo.exceptions.CargoInvalidoException;
import adega.projeto.com.demo.exceptions.IdIncorretoException;
import adega.projeto.com.demo.model.entity.Funcionario;
import adega.projeto.com.demo.model.enums.Cargo;
import adega.projeto.com.demo.repository.FuncionarioRepository;
import adega.projeto.com.demo.repository.specs.FuncionarioSpecs;
import adega.projeto.com.demo.validador.FuncionarioValidador;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;
    private final FuncionarioValidador validador;
    private final PasswordEncoder encoder;

    @Transactional
    public Funcionario salvarFuncionario(Funcionario funcionario) {
        validador.validar(funcionario);
        var senha = funcionario.getSenha();
        funcionario.setSenha(encoder.encode(senha));
       return funcionarioRepository.save(funcionario);
    }

    public Funcionario buscarPorId(String id){
        return funcionarioRepository.findById(UUID.fromString(id))
                .orElseThrow(()-> new IdIncorretoException("Não Existe Funcionario com esse ID!"));
    }

    public Page<Funcionario> buscarTodos(Integer pagina, Integer tamanhoPagina,
                                         String nome, String sobrenome, String cpf, BigDecimal salario, String cargo,
                                         String idade, LocalDate dataAdmissao, LocalDate dataDemissao){

        Specification<Funcionario> specs = Specification
                .where((((root, query, cb) -> cb.conjunction())));

        if (nome != null){
            specs = specs.and(FuncionarioSpecs.nomeLike(nome));
        }
        if (sobrenome != null){
            specs = specs.and(FuncionarioSpecs.sobrenomeLike(sobrenome));
        }
        if (cpf != null){
            specs = specs.and(FuncionarioSpecs.cpfEqual(cpf));
        }
        if (salario != null){
            specs = specs.and(FuncionarioSpecs.salarioEqual(salario));
        }

        if(cargo != null) {
            if (!cargo.equalsIgnoreCase("DONO")
                    && !cargo.equalsIgnoreCase("GERENTE")
                    && !cargo.equalsIgnoreCase("FUNCIONARIO")) {
                throw new CargoInvalidoException("Cargo inválido");
            }
        }

        if (cargo != null){
            specs = specs.and(FuncionarioSpecs.cargoLike(cargo));
        }
        if (idade != null){
            specs = specs.and(FuncionarioSpecs.idadeEqual(idade));
        }
        if (dataAdmissao != null){
            specs = specs.and(FuncionarioSpecs.dataAdmissaoEqual(dataAdmissao));
        }
        if (dataDemissao != null){
            specs = specs.and(FuncionarioSpecs.dataDemissaoEqual(dataDemissao));
        }

        Pageable pageable = PageRequest.of(pagina,tamanhoPagina);

        return funcionarioRepository.findAll(specs,pageable);
    }

    @Transactional
    public void atualizar(Funcionario funcionario,String id){
      Funcionario funcionarioExistente = funcionarioRepository.findById(UUID.fromString(id))
               .orElseThrow(()-> new IdIncorretoException("Nenhum Funcionario com esse ID "+ id));
        funcionarioExistente.setCpf(funcionario.getCpf());
        funcionarioExistente.setIdade(funcionario.getIdade());
        funcionarioExistente.setVendas(funcionario.getVendas());
        funcionarioExistente.setNome(funcionario.getNome());
        funcionarioExistente.setSalario(funcionario.getSalario());
        funcionarioExistente.setEndereco(funcionario.getEndereco());
        funcionarioExistente.setCargo(funcionario.getCargo());
        funcionarioExistente.setDataAdmissao(funcionario.getDataAdmissao());
        funcionarioExistente.setDataDemissao(funcionario.getDataDemissao());

        funcionarioRepository.save(funcionarioExistente);
    }

    @Transactional
    public void deletar(String id){
        Funcionario funcionarioExistente = funcionarioRepository.findById(UUID.fromString(id))
                .orElseThrow(()-> new IdIncorretoException("Nenhum Funcionario com esse ID " + id));

        funcionarioRepository.delete(funcionarioExistente);
    }

}
