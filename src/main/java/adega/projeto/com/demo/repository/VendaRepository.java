package adega.projeto.com.demo.repository;

import adega.projeto.com.demo.model.entity.Produto;
import adega.projeto.com.demo.model.entity.Venda;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface VendaRepository extends JpaRepository<Venda, UUID>, JpaSpecificationExecutor<Venda> {

    Page<Venda> findByFuncionarioId(UUID funcionarioId, Pageable pageable);

    Page<Venda> findByFuncionarioIdAndHoraVendaBetween(UUID funcionarioId, LocalDateTime inicio, LocalDateTime fim, Pageable pageable);

    Page<Venda> findByHoraVendaBetween(LocalDateTime inicio, LocalDateTime fim, Pageable pageable);


}
