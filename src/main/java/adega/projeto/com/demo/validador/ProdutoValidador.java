package adega.projeto.com.demo.validador;

import adega.projeto.com.demo.exceptions.FornecedorInexistenteException;
import adega.projeto.com.demo.model.entity.Fornecedor;
import adega.projeto.com.demo.model.entity.Produto;
import adega.projeto.com.demo.repository.FornecedorRepository;
import adega.projeto.com.demo.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ProdutoValidador {

    private final ProdutoRepository repository;
    private final FornecedorRepository fornecedorRepository;


    @Transactional
    public void validar(Produto produto, List<UUID> fornecedoresIds){
        Optional<Produto> produtoOptional = repository.findByNomeAndTipoProdutoAndTeorAlcoolico(
                produto.getNome(), produto.getTipoProduto(), produto.getTeorAlcoolico());

        if (produtoOptional.isPresent()){
            var produtoExistente = produtoOptional.get();
            produtoExistente.adicionarQuantidade(produto.getQuantidade());
            repository.save(produtoExistente);
        } else {
            // salva produto sem fornecedores
            produto.setFornecedores(null);
            Produto produtoSalvo = repository.save(produto);

            repository.flush();

            List<Fornecedor> fornecedores = fornecedoresIds.stream()
                    .map(id -> fornecedorRepository.findById(id)
                            .orElseThrow(() -> new FornecedorInexistenteException("Fornecedor com id "+ id +" não existe")))
                    .toList();

            produtoSalvo.setFornecedores(new ArrayList<>(fornecedores));
            repository.save(produtoSalvo);
        }
    }
}