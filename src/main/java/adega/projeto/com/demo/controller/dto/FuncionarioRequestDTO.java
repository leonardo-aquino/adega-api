package adega.projeto.com.demo.controller.dto;

import adega.projeto.com.demo.model.entity.Funcionario;
import adega.projeto.com.demo.model.enums.Cargo;
import adega.projeto.com.demo.repository.FuncionarioRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record RequestFuncionarioDTO(String nome,
                                    String sobreNome,
                                    BigDecimal salairo,
                                    Cargo cargo,
                                    String Endereco,
                                    List<String> vendas) {

    public static Funcionario toEntity(RequestFuncionarioDTO dto, FuncionarioRepository repository){
        Funcionario funcionario = new Funcionario();
        funcionario.setNome(dto.nome);
        funcionario.setSobreNome(dto.sobreNome);
        funcionario.setSalario(dto.salairo);
        funcionario.setEndereco(repository.findBy(UUID.fromString(dto.Endereco)));
    }
}
