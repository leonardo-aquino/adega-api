package adega.projeto.com.demo.repository;

import adega.projeto.com.demo.model.entity.Funcionario;
import adega.projeto.com.demo.model.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FuncionarioRepository extends JpaRepository<Funcionario, UUID> {
}
