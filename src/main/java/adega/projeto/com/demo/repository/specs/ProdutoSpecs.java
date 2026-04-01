package adega.projeto.com.demo.repository.specs;

import adega.projeto.com.demo.model.entity.Produto;
import adega.projeto.com.demo.model.enums.TipoProduto;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.time.LocalDate;


public class ProdutoSpecs {

    public static Specification<Produto> nomeLike (String nome){
        return (root, query, cb) -> cb.like(root.get("nome"), "%"+ nome +"%");
    }
    public static Specification<Produto> tipoProdutoLike(TipoProduto tipoProduto) {
        return (root, query, cb) -> cb.equal(root.get("tipoProduto"),tipoProduto);
    }

    public static Specification<Produto> teorAlcoolicoEqual(BigDecimal teorAlcoolico) {
        return (root, query, cb) -> cb.equal(root.get("teorAlcoolico"),teorAlcoolico);
    }

    public static Specification<Produto> volumeEqual(String volume) {
        return (root, query, cb) -> cb.equal((root.get("volume")), volume);
    }

    public static Specification<Produto> vencimentoMenorOuIgual(LocalDate vencimento) {
        return (root, query, cb) -> cb.lessThanOrEqualTo(root.get("vencimento"), vencimento);
    }

    public static Specification<Produto> quantidadeEqual(Integer quantidade) {
        return (root, query, cb) -> cb.equal((root.get("quantidade")), quantidade);
    }

}
