package adega.projeto.com.demo.model.entity;

import adega.projeto.com.demo.model.enums.Cargo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "funcionario")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "nome", nullable = false, length = (50))
    private String nome;

    @Column(name = "sobrenome", nullable = false, length = (100))
    private String sobrenome;

    @Column(name = "cpf",nullable = false)
    private String cpf;

    @Column(name = "salario", precision = 10, scale = 2)
    private BigDecimal salario;

    @Enumerated(EnumType.STRING)
    @Column(name = "cargo", nullable = false, length = 50)
    private Cargo cargo;

    // Vários funcionarios pode ter o mesmo Endereço
    @JoinColumn(name = "id_endereco")
    @ManyToOne
    private Endereco endereco;

    // um funcionário pode realizar várias vendas
    @OneToMany(mappedBy = "funcionario")
    @JsonIgnore
    private List<Venda> vendas = new ArrayList<>();

    @Column(name = "idade")
    private String idade;

    @CreationTimestamp
    @Column(name = "data_admissao", nullable = false, updatable = false)
    private LocalDate dataAdmissao;

    @Column(name = "data_demissao")
    private LocalDate dataDemissao;
}
