package adega.projeto.com.demo.repository;

import adega.projeto.com.demo.model.entity.Funcionario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface FuncionarioRepository extends JpaRepository<Funcionario, UUID> {

    boolean existsByCpf(String cpf);

    Page<Funcionario> findAll(Specification<Funcionario> specs, Pageable pageable);

    Optional<Funcionario> findByCpf(String cpf);
}
