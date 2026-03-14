package adega.projeto.com.demo.controller.dtos;

import adega.projeto.com.demo.exceptions.IdIncorretoException;
import adega.projeto.com.demo.model.entity.Endereco;
import adega.projeto.com.demo.model.entity.Fornecedor;
import adega.projeto.com.demo.model.entity.Produto;
import adega.projeto.com.demo.repository.EnderecoRepository;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.List;
import java.util.UUID;


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
