package adega.projeto.com.demo.controller.dto;

import adega.projeto.com.demo.model.entity.Endereco;
import adega.projeto.com.demo.model.entity.Funcionario;
import adega.projeto.com.demo.model.entity.Venda;
import adega.projeto.com.demo.model.enums.Cargo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public record FuncionarioResponseDTO(String nome,
                                     String id,
                                     String sobrenome,
                                     String cpf,
                                     BigDecimal salario,
                                     Cargo cargo,
                                     Endereco endereco,
                                     List<Venda> vendas,
                                     String idade,
                                     String dataAdmissao,
                                     LocalDate dataDemissao) {

    public static FuncionarioResponseDTO toDTO(Funcionario funcionario){
        DateTimeFormatter dataFormatada = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        FuncionarioResponseDTO dto = new FuncionarioResponseDTO(
                funcionario.getNome(),
                funcionario.getId().toString(),
                funcionario.getSobrenome(),
                funcionario.getCpf(),
                funcionario.getSalario(),
                funcionario.getCargo(),
                funcionario.getEndereco(),
                funcionario.getVendas(),
                funcionario.getIdade(),
                funcionario.getDataAdmissao().format(dataFormatada),
               funcionario.getDataDemissao());
        return dto;
    }

}
