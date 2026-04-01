package adega.projeto.com.demo.controller.dto;

import adega.projeto.com.demo.exceptions.IdIncorretoException;
import adega.projeto.com.demo.model.entity.Venda;
import adega.projeto.com.demo.repository.FuncionarioRepository;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public record VendaDTO(@NotBlank(message = "funcionario id não pode estar nulo") String funcionarioId,
                       @NotEmpty(message = "Itens não pode estar vazio")
                       @Size(min =1,message = "é nessesario pelo menos 1 item") List<ItemVendaDTO> itens) {

    public Venda toEntity(FuncionarioRepository funcionarioRepository) {
        var venda = new Venda();
        venda.setFuncionario(funcionarioRepository.findById(UUID.fromString(funcionarioId))
                .orElseThrow(() -> new IdIncorretoException("Não existe Funcionário com esse ID " + funcionarioId)));
        venda.setItens(new ArrayList<>()); // lista vazia, preenchida no service
        return venda;
    }
}