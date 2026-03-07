package adega.projeto.com.demo.model.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.Type;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "funcionario")
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "nome", nullable = false, length = (50))
    private String nome;

    @Column(name = "sobrenome", nullable = false, length = (100))
    private String sobreNome;

    @Column(name = "salario", precision = 10, scale = 2)
    private BigDecimal salario;

    @ElementCollection
    @CollectionTable(name = "funcionario_cargos", joinColumns = @JoinColumn(name = "funcionario_id"))
    @Column(name = "cargo")
    private List<String> cargo;

    // Vários funcionarios pode ter o mesmo Endereço
    @JoinColumn(name = "id_endereco")
    @ManyToOne
    private Endereco endereco;

    // um funcionário pode realizar várias vendas
    @OneToMany(mappedBy = "funcionario")
    private List<Venda> vendas;
}
