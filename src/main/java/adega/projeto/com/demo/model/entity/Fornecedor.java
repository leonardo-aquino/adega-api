package adega.projeto.com.demo.model.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Table
@Entity(name = "fornecedor")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Fornecedor {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "nome", length = (100), nullable = false)
    private String nome;

    // um fornecedor só tem apenas 1 endereço
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_endereco")
    @JsonIgnore
    private Endereco endereco;

    @Column(name = "telefone", length = (20),nullable = false)
    private String telefone;

    @Column(name = "email", length = (100),unique = true, nullable = false)
    private String email;

    // um fornecedor pode ter vários produtos
    @ManyToMany(mappedBy = "fornecedores")
    private List<Produto> produtos;
}
