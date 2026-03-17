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


public record FornecedorDTO(

        @NotBlank(message = "Nome não pode ser nulo ou vazio")
        String nome,

        @NotBlank(message = "endereco não pode ser nulo ou vazio")
        String endereco,

        @NotBlank(message = "Telefone não pode ser nulo ou vazio")
        String telefone,

        @NotBlank(message = "Email não pode ser nulo ou vazio")
        @Email(message = "Email invalido! ")
        String email,

        List<Produto>produtos) {

    public  Fornecedor toEntity(FornecedorDTO dto,EnderecoRepository enderecoRepository){
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setEmail(dto.email);
        fornecedor.setNome(dto.nome);
        fornecedor.setTelefone(dto.telefone);

        Endereco enderecoEncontrado = enderecoRepository.findById(UUID.fromString(dto.endereco))
                .orElseThrow(()-> new IdIncorretoException("Id do Indereço não achado, " +
                        "entrar em contato com o servidor"));
        fornecedor.setEndereco(enderecoEncontrado);
        fornecedor.setProdutos(dto.produtos);
        return fornecedor;

    }
}
