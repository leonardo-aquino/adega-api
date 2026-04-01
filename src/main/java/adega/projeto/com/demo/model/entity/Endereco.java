package adega.projeto.com.demo.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "endereco")
@AllArgsConstructor
@NoArgsConstructor
@Data
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
    @JsonIgnore
    private List<Funcionario> funcionario = new ArrayList<>();

    // em um endereço pode ter vários fornecedores
    @OneToMany(mappedBy = "endereco",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Fornecedor> fornecedores = new ArrayList<>();
}
