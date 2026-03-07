package adega.projeto.com.demo.model.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Table
@Entity(name = "venda")
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToMany(mappedBy = "venda", cascade = CascadeType.ALL)
    private List<ItemVenda> itens;

    @ManyToOne
    private Funcionario funcionario;

}
