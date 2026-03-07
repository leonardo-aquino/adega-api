package adega.projeto.com.demo.model.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "endereco")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "numero",nullable = false)
    private String numero;

    @Column(name = "rua", nullable = false)
    private String rua;

    @Column(name = "cep",nullable = false)
    private String cep;

    @Column(name = "bairro")
    private String bairro;

    // em um endereço pode ter muitos funcionários
    @OneToMany(mappedBy = "endereco")
    private List<Funcionario> funcionario;

    // em um endereço pode ter vários fornecedores
    @OneToMany(mappedBy = "endereco")
    private List<Fornecedor> fornecedores;
}
