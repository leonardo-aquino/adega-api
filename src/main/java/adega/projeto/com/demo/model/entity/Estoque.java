package adega.projeto.com.demo.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "estoque")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Estoque {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    // em um estoque pode ter vários produtos
    @OneToMany(mappedBy = "estoque")
    private List<Produto> produtos;


    @Column(name = "localizacao")
    private String localizacao;
}
