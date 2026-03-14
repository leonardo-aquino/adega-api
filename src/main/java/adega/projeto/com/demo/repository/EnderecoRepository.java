package adega.projeto.com.demo.repository;

import adega.projeto.com.demo.model.entity.Endereco;
import adega.projeto.com.demo.model.entity.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EnderecoRepository extends JpaRepository<Endereco, UUID> {
    Page<Endereco> findAll(Specification<Endereco> specs, Pageable pageRequest);

    boolean existsByNumeroAndRuaAndCepAndBairro(String numero,String rua, String cep,String bairro);
}
