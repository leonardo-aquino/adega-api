package adega.projeto.com.demo.model.entity;

import adega.projeto.com.demo.model.enums.Cargo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "funcionario")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Funcionario implements UserDetails {

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

    @Column(name = "senha")
    private String senha;

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
    @Column(name = "data_admissao", updatable = false)
    private LocalDate dataAdmissao;

    @Column(name = "data_demissao")
    private LocalDate dataDemissao;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + cargo.name()));
    }

    @Override
    public @Nullable String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.cpf;
    }

    @Override
    public boolean isAccountNonExpired() { return true; }
    @Override
    public boolean isAccountNonLocked() { return true; }
    @Override
    public boolean isCredentialsNonExpired() { return true; }
    @Override
    public boolean isEnabled() { return true; }

}
