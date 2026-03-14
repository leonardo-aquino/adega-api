package adega.projeto.com.demo.repository;

import adega.projeto.com.demo.model.entity.ItemVenda;
import adega.projeto.com.demo.model.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ItemVendaRepository extends JpaRepository<ItemVenda, UUID> {
}
