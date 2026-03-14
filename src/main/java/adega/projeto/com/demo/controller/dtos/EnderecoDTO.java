package adega.projeto.com.demo.controller.dtos;

import adega.projeto.com.demo.model.entity.Endereco;
import adega.projeto.com.demo.model.entity.Fornecedor;
import adega.projeto.com.demo.model.entity.Funcionario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.List;

public record EnderecoDTO(

        String id,

        @NotBlank(message = "Número não pode ser nulo ou vazio")
        String numero,

        @NotBlank(message = "Rua não pode ser nulo ou vazio")
        String rua,

        @NotBlank(message = "Cep não pode ser nulo ou vazio")
        @Pattern(regexp = "\\d{5}-\\d{3}", message = "CEP deve estar no formato 00000-000")
        String cep,

        @NotBlank(message = "Bairro não pode ser nulo ou vazio")
        String bairro,

        List<Funcionario> funcionarios,
        List<Fornecedor> fornecedores) {

    public Endereco toEntity(EnderecoDTO dto){
        Endereco endereco = new Endereco();
        endereco.setBairro(dto.bairro);
        endereco.setCep(dto.cep);
        endereco.setNumero(dto.numero);
        endereco.setRua(dto.rua);
        endereco.setFuncionario(dto.funcionarios);
        endereco.setFornecedores(dto.fornecedores);
        return endereco;
    }

    public static EnderecoDTO toDTO(Endereco endereco){
        EnderecoDTO dto = new EnderecoDTO(endereco.getId().toString(),
                endereco.getNumero(),
                endereco.getRua(),
                endereco.getCep(),
                endereco.getBairro(),
                endereco.getFuncionario(),
                endereco.getFornecedores());
        return dto;



    }

}

