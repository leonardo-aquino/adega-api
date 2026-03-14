package adega.projeto.com.demo.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private List<ItemVenda> itens;

    @ManyToOne
    @JoinColumn(name = "id_funcionario")
    private Funcionario funcionario;

}
