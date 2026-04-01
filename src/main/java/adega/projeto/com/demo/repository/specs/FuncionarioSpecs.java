package adega.projeto.com.demo.repository.specs;

import adega.projeto.com.demo.model.entity.Funcionario;
import adega.projeto.com.demo.model.enums.Cargo;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.time.LocalDate;

public class FuncionarioSpecs {

    public static Specification<Funcionario> nomeLike(String nome){
        return (root, query, cb) -> cb.like(cb.lower(root.get("nome")),"%"+nome.toLowerCase()+"%");
    }

    public static Specification<Funcionario> sobrenomeLike(String sobrenome){
        return (root, query, cb) -> cb.like(cb.lower(root.get("sobrenome")),"%"+sobrenome.toLowerCase()+"%");
    }

    public static Specification<Funcionario> cpfEqual(String cpf){
        return (root, query, cb) -> cb.equal(root.get("cpf"),cpf);
    }

    public static Specification<Funcionario> salarioEqual(BigDecimal salario){
        return (root, query, cb) -> cb.equal(root.get("salario"),salario);
    }

    public static Specification<Funcionario> cargoLike(String cargo){
        return (root, query, cb) -> cb.like(cb.upper(root.get("cargo")),"%"+cargo+"%");
    }

    public static Specification<Funcionario> idadeEqual(String idade){
        return (root, query, cb) -> cb.equal(root.get("idade"),idade);
    }

    public static Specification<Funcionario> dataAdmissaoEqual(LocalDate dataAdmissao){
        return (root, query, cb) -> cb.equal(root.get("dataAdmissao"),dataAdmissao);
    }

    public static Specification<Funcionario> dataDemissaoEqual(LocalDate dataDemissao) {
        return (root, query, cb) -> cb.equal(root.get("dataDemissao"),dataDemissao);
    }
}
