package adega.projeto.com.demo.repository;

import adega.projeto.com.demo.model.entity.Produto;
import adega.projeto.com.demo.model.enums.TipoProduto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

public interface ProdutoRepository extends JpaRepository<Produto, UUID>, JpaSpecificationExecutor<Produto> {

    @Query("SELECT p FROM Produto p JOIN FETCH p.fornecedores WHERE p.id = :id")
    Optional<Produto> buscarComFornecedores(@Param("id") UUID id);

    Optional<Produto> findByNomeAndTipoProdutoAndTeorAlcoolico(String nome, TipoProduto tipoProduto, BigDecimal teorAlcoolico);

}
