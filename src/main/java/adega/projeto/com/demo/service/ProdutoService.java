package adega.projeto.com.demo.service;

import adega.projeto.com.demo.controller.dto.ProdutoResponseDTO;
import adega.projeto.com.demo.exceptions.IdIncorretoException;
import adega.projeto.com.demo.model.entity.Produto;
import adega.projeto.com.demo.model.enums.TipoProduto;
import adega.projeto.com.demo.repository.ProdutoRepository;
import adega.projeto.com.demo.repository.specs.ProdutoSpecs;
import adega.projeto.com.demo.validador.ProdutoValidador;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoValidador validador;
    private final ProdutoRepository produtoRepository;

    @Transactional
    public void salvar(Produto produto, List<UUID> fornecedoresIds){
        validador.validar(produto, fornecedoresIds);
    }

    @Transactional
    public void atualizar(String id, Produto produto) {
       Produto produtoExistente = produtoRepository.findById(UUID.fromString(id))
               .orElseThrow(()-> new IdIncorretoException("Não existe produto com esse id"+ id));

       produtoExistente.setFornecedores(produto.getFornecedores());
       produtoExistente.setNome(produto.getNome());
       produtoExistente.setVolume(produto.getVolume());
       produtoExistente.setVencimento(produto.getVencimento());
       produtoExistente.setTipoProduto(produto.getTipoProduto());
       produtoExistente.setTeorAlcoolico(produto.getTeorAlcoolico());
       produtoExistente.setQuantidade(produto.getQuantidade());

       produtoRepository.save(produtoExistente);
    }

    @Transactional
    public void deletar(String id){
        Produto produtoExistente = produtoRepository.findById(UUID.fromString(id))
                .orElseThrow(()-> new IdIncorretoException("Não existe produto com esse id"+ id));
        produtoRepository.delete(produtoExistente);
    }

    public ProdutoResponseDTO buscarPorId(String id){
        Produto produtoExistente = produtoRepository.buscarComFornecedores(UUID.fromString(id))
                .orElseThrow(()-> new IdIncorretoException("Não existe produto com esse id "+ id));
        return ProdutoResponseDTO.toDTO(produtoExistente);
    }

    public Page<Produto> buscarTodos(
                            Integer pagina,
                            Integer tamanhoPagina,
                            String nome,
                            String tipoProduto,
                            BigDecimal teorAlcoolico,
                            String volume,
                            LocalDate vencimento,
                            Integer quantidade) {

        Specification<Produto> specs = Specification
                .where(((root, query, cb) -> cb.conjunction()));

        if (nome != null && !nome.isEmpty()){
            specs = ProdutoSpecs.nomeLike(nome);
        }
        if (tipoProduto != null ){
            specs = ProdutoSpecs.tipoProdutoLike(TipoProduto.toEnum(tipoProduto));
        }
        if (teorAlcoolico!= null){
            specs = ProdutoSpecs.teorAlcoolicoEqual(teorAlcoolico);
        }
        if (volume != null){
            specs = ProdutoSpecs.volumeEqual(volume);
        }
        if (vencimento != null){
            specs = ProdutoSpecs.vencimentoMenorOuIgual(vencimento);
        }
        if (quantidade != null){
            specs = ProdutoSpecs.quantidadeEqual(quantidade);
        }

        Pageable pageable =PageRequest.of(pagina,tamanhoPagina);
        return produtoRepository.findAll(specs,pageable);
    }
}