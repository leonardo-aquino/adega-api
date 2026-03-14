package adega.projeto.com.demo.repository.specs;

import adega.projeto.com.demo.model.entity.Endereco;
import org.springframework.data.jpa.domain.Specification;


public class EnderecoSpecs {

    public static Specification<Endereco> cepEqual (String cep){
        return (root, query, cb) -> cb.equal(root.get("cep"),cep);
    }
    public static Specification<Endereco> ruaLike(String rua) {
        return (root, query, cb) -> cb.like(cb.lower(root.get("rua")), "%" + rua.toLowerCase() + "%");
    }

    public static Specification<Endereco> numeroLike(String numero) {
        return (root, query, cb) -> cb.like(root.get("numero"), "%"+ numero +"%");
    }

    public static Specification<Endereco> bairroLike(String bairro) {
        return (root, query, cb) -> cb.like(cb.lower(root.get("bairro")), "%" + bairro.toLowerCase() + "%");
    }
}
