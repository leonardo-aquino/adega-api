package adega.projeto.com.demo.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "item_venda")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItensVenda {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    @JsonBackReference
    private Produto produto;

    @ManyToOne
    @JoinColumn(name = "venda_id")
    @JsonBackReference
    private Venda venda;

    @Column(nullable = false)
    private Integer quantidade;

    @Column(name = "preco_unitario", precision = 10, scale = 2, nullable = false)
    private BigDecimal precoUnitario;

}
