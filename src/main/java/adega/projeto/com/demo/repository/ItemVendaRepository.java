package adega.projeto.com.demo.repository;

import adega.projeto.com.demo.model.entity.ItensVenda;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ItemVendaRepository extends JpaRepository<ItensVenda, UUID> {
}
