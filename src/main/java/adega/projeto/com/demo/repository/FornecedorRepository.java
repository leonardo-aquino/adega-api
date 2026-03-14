package adega.projeto.com.demo.repository;

import adega.projeto.com.demo.model.entity.Endereco;
import adega.projeto.com.demo.model.entity.Fornecedor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FornecedorRepository extends JpaRepository<Fornecedor, UUID> {

    boolean existsByNomeAndEnderecoAndTelefoneAndEmail(String nome, Endereco endereco, String telefone, String email);

    Page<Fornecedor> findAll(Specification<Fornecedor> specs, Pageable pageable);
}
