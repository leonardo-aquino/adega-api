package adega.projeto.com.demo.model.entity;

import adega.projeto.com.demo.controller.dto.ItemVendaDTO;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Table
@Entity(name = "venda")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToMany(mappedBy = "venda", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<ItensVenda> itens = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "id_funcionario")
    private Funcionario funcionario;

    @CreationTimestamp
    private LocalDateTime horaVenda;

    @Column(name = "valor_total",  precision = 10, scale = 2)
    private BigDecimal valorTotal;
}
