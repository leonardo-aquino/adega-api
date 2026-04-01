package adega.projeto.com.demo.model.entity;

import adega.projeto.com.demo.model.enums.TipoProduto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "produto")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "nome", length = 100, nullable = false)
    private String nome;

    @Column(name = "tipo_produto", nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoProduto tipoProduto;

    @Column(name = "teor_alcoolico", nullable = false,precision = 5, scale = 2)
    private BigDecimal teorAlcoolico;

    @Column(name = "volume", nullable = false)
    private Integer volume;

    @Future
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate vencimento;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "produto_fornecedor",
            joinColumns = @JoinColumn(name = "produto_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "fornecedor_id", referencedColumnName = "id")
    )
    private List<Fornecedor> fornecedores;

    @Column(name = "quantidade")
    private Integer quantidade;

    @Column(name = "preco", precision = 10, scale = 2)
    private BigDecimal preco;

    @OneToMany(mappedBy = "produto")
    @JsonManagedReference
    private List<ItensVenda> itensVenda = new ArrayList<>();

    public void adicionarQuantidade(Integer quantidade){
        this.quantidade += quantidade;
    }

    public  void removerQuantidade(Integer quantidade){
        this.quantidade -= quantidade;
    }

}
