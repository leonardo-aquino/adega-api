package adega.projeto.com.demo.controller.dto;

import adega.projeto.com.demo.exceptions.IdIncorretoException;
import adega.projeto.com.demo.model.entity.Endereco;
import adega.projeto.com.demo.model.entity.Funcionario;
import adega.projeto.com.demo.model.entity.Venda;
import adega.projeto.com.demo.model.enums.Cargo;
import adega.projeto.com.demo.repository.EnderecoRepository;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public record FuncionarioRequestDTO(

        @NotBlank(message = "Nome não pode ser nulo ou vazio")
        @Size(min = 3,max = 15,message = "Nome deve ter no minimo 2 caracters e no máximo 15")
        String nome,

        @NotBlank(message = "Nome não pode ser nulo ou vazio")
        @Size(min = 3,max = 70,message = "Sobrenome deve ter no minimo 3 caracters e no máximo 70")
        String sobrenome,

        @NotBlank(message = "CPF não pode ser nulo ou vazio")
        @CPF(message = "CPF invalido")
        @Pattern(regexp = "\\d+", message = "O CPF deve conter apenas números")
        String cpf,

        @NotBlank(message = "senha não pode ser nula")
        String senha,

        @NotNull(message = "Salário é obrigatório")
        @Positive(message = "Salário deve ser positivo")
        @DecimalMin(value = "1000.00", inclusive = true)
        BigDecimal salario,

        @NotNull(message = "Cargo não pode ser nulo")
        Cargo cargo,

        @NotNull(message = "Endereço não pode ser nulo")
        String endereco,

        @NotBlank(message = "Idade não pode ser nula ou vazia")
        String idade) {

    public  Funcionario toEntity(FuncionarioRequestDTO dto, EnderecoRepository repository){
        Funcionario funcionario = new Funcionario();
        funcionario.setNome(dto.nome);
        funcionario.setSobrenome(dto.sobrenome);
        funcionario.setSenha(dto.senha);
        funcionario.setSalario(dto.salario);
        funcionario.setIdade(dto.idade);
        funcionario.setCpf(dto.cpf);
        Optional<Endereco> enderecoEncontrado = repository.findById(UUID.fromString(dto.endereco));
        if (enderecoEncontrado.isEmpty()){
            throw new IdIncorretoException("Endereço não encontrado");
        }
        funcionario.setEndereco(enderecoEncontrado.get());
        funcionario.setCargo(dto.cargo);
        return funcionario;
    }
}
