package adega.projeto.com.demo.controller.dto;


import adega.projeto.com.demo.model.entity.Fornecedor;
import adega.projeto.com.demo.model.entity.Produto;
import adega.projeto.com.demo.model.enums.TipoProduto;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

public record ProdutoResponseDTO(
                                UUID id,
                                String nome,
                                TipoProduto tipoProduto,
                                BigDecimal teorAlcoolico,
                                String volume,
                                String vencimento,
                                BigDecimal preco,
                                List<Fornecedor> fornecedores,
//                                Estoque estoque,
                                Integer quantidade
                                ) {

    public static ProdutoResponseDTO toDTO (Produto produto){
        DateTimeFormatter dataFormatada = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        ProdutoResponseDTO dto = new ProdutoResponseDTO(
                produto.getId(),
                produto.getNome(), produto.getTipoProduto(),
                produto.getTeorAlcoolico(),produto.getVolume().toString(), (produto.getVencimento().format(dataFormatada)),produto.getPreco(),
                produto.getFornecedores()/*,produto.getEstoque()*/, produto.getQuantidade()
        );
        return dto;
    }
}
