package adega.projeto.com.demo.repository.specs;

import adega.projeto.com.demo.model.entity.Endereco;
import adega.projeto.com.demo.model.entity.Fornecedor;
import org.springframework.data.jpa.domain.Specification;


public class FornecedorSpecs {

    public static Specification<Fornecedor> nomeLike (String nome){
        return (root, query, cb) -> cb.like(cb.lower(root.get("nome")),"%"+ nome.toLowerCase() + "%") ;
    }
    public static Specification<Fornecedor> telefoneEqual(String telefone) {
        return (root, query, cb) -> cb.equal(root.get("telefone"), telefone);
    }

    public static Specification<Fornecedor> emailLike (String email){
        return (root, query, cb) -> cb.like(cb.lower(root.get("email")),"%"+ email.toLowerCase() + "%") ;
    }

}
