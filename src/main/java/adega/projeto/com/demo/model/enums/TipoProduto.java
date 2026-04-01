package adega.projeto.com.demo.model.enums;

import adega.projeto.com.demo.exceptions.TipoProdutoInvalidoException;

import java.util.Arrays;

public enum TipoProduto {
    VINHO_TINTO("vinho tinto"),
    VINHO_BRANCO("vinho branco"),
    VINHO_ROSE("vinho rose"),
    ESPUMANTE("espumante"),
    PORTO("porto"),
    CERVEJA("cerveja"),
    LICOR("licor"),
    VODKA("vodka"),
    GIN("gin"),
    RUM("rum"),
    WHISKY("whisky"),
    CONHAQUE("conhaque"),
    TEQUILA("tequila"),
    CACHACA("cachaca");

    private final String descricao;

    TipoProduto(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public static TipoProduto toEnum(String valor) {
        return Arrays.stream(TipoProduto.values())
                .filter(tp -> tp.descricao.equalsIgnoreCase(valor))
                .findFirst()
                .orElseThrow(() -> new TipoProdutoInvalidoException(
                        "Tipo Produto inválido. Válidos são: " + Arrays.toString(TipoProduto.values())
                ));
    }
}