package adega.projeto.com.demo.controller.dto;

import adega.projeto.com.demo.model.entity.Produto;
import adega.projeto.com.demo.model.enums.TipoProduto;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record ProdutoRequestDTO(
                                @NotBlank(message = "Nome não pode ser nulo ou vazio")
                                String nome,

                                @NotNull(message = "Tipo do Produto não pode ser nulo")
                                TipoProduto tipoProduto,

                                @NotNull(message = "O teor alcoólico é obrigatório")
                                @DecimalMin(value = "0.0", inclusive = true, message = "O teor alcoólico não pode ser negativo")
                                @DecimalMax(value = "70.0", inclusive = true, message = "O teor alcoólico não pode ultrapassar 70%")
                                BigDecimal teorAlcoolico,

                                @NotBlank(message = "O volume é obrigatório")
                                @Pattern(regexp = "^[0-9]+$", message = "O volume deve ser informado apenas com números, sem pontos ou vírgulas e em ML")
                                String volume,

                                @Future
                                @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
                                LocalDate vencimento,

                                @NotNull
                                BigDecimal preco,

                                @NotNull(message = "Fornecedor não pode ser nulo")
                                List<UUID> fornecedores,

                                @Positive(message = "quantidade não pode ser negativa")
                                @Min(1)
                                Integer quantidade
                                ) {

    public Produto toEntity(ProdutoRequestDTO dto){
        Produto produto = new Produto();
        produto.setTipoProduto(dto.tipoProduto);
        produto.setNome(dto.nome);
//        produto.setEstoque(estoqueRepository.findById(dto.estoque).orElseThrow(()-> new EstoqueInexistenteException("Estoque com id "+ dto.estoque+" não existe")));
        produto.setQuantidade(dto.quantidade);
        produto.setPreco(dto.preco);
        produto.setVolume(Integer.parseInt(dto.volume()));
        produto.setVencimento(dto.vencimento);
        produto.setItensVenda(null);
        produto.setTeorAlcoolico(dto.teorAlcoolico);
        return produto;
    }


}
