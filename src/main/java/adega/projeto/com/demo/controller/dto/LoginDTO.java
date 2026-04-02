package adega.projeto.com.demo.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.br.CPF;

public record LoginDTO(
        @NotBlank(message = "CPF não pode ser nulo ou vazio")
        @CPF(message = "CPF inválido")
        @Pattern(regexp = "\\d+", message = "O CPF deve conter apenas números")
        String cpf,

        @NotBlank(message = "Senha não pode ser nula ou vazia")
        String senha
) {}