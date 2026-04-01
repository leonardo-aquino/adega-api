package adega.projeto.com.demo.controller.dto;

import adega.projeto.com.demo.model.entity.Endereco;
import adega.projeto.com.demo.model.entity.Fornecedor;
import adega.projeto.com.demo.model.entity.Produto;

import java.util.List;


public record FornecedorDTOResponse(

        String id,
        String nome,
        String telefone,
        String email,
        Endereco endereco,
        List<Produto>produtos) {


    public static FornecedorDTOResponse toDTO (Fornecedor fornecedor){
        FornecedorDTOResponse fornecedorDTO = new FornecedorDTOResponse(
                fornecedor.getId().toString(),
                fornecedor.getNome(),
                fornecedor.getTelefone(),
                fornecedor.getEmail(),
                fornecedor.getEndereco(),
                fornecedor.getProdutos());
        return fornecedorDTO;
    }
}
