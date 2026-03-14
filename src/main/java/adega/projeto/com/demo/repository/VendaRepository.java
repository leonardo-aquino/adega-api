package adega.projeto.com.demo.repository;

import adega.projeto.com.demo.model.entity.Produto;
import adega.projeto.com.demo.model.entity.Venda;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface VendaRepository extends JpaRepository<Venda, UUID> {
}
