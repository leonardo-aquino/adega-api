package adega.projeto.com.demo.service;

import adega.projeto.com.demo.controller.dto.ItemVendaDTO;
import adega.projeto.com.demo.controller.dto.VendaDTO;
import adega.projeto.com.demo.exceptions.EstoqueInsuficienteException;
import adega.projeto.com.demo.exceptions.IdIncorretoException;
import adega.projeto.com.demo.model.entity.ItensVenda;
import adega.projeto.com.demo.model.entity.Produto;
import adega.projeto.com.demo.model.entity.Venda;
import adega.projeto.com.demo.repository.FuncionarioRepository;
import adega.projeto.com.demo.repository.ProdutoRepository;
import adega.projeto.com.demo.repository.VendaRepository;
import adega.projeto.com.demo.validador.VendaValidador;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VendaService {

    private final VendaRepository vendaRepository;
    private final ProdutoRepository produtoRepository;
    private final VendaValidador validador;
    private final FuncionarioRepository funcionarioRepository;


    @Transactional
    public Venda salvar(Venda venda, List<ItemVendaDTO> itensDTO) {
        if (venda.getItens() == null) {
            venda.setItens(new ArrayList<>()); // lista de ItensVenda
        }

        for (ItemVendaDTO itemDTO : itensDTO) {
            UUID uuid = UUID.fromString(itemDTO.produtoId());
            Produto produto = produtoRepository.findById(uuid)
                    .orElseThrow(() -> new IdIncorretoException("Produto não encontrado: " + itemDTO.produtoId()));

            if (produto.getQuantidade() < itemDTO.quantidade()) {
                throw new EstoqueInsuficienteException("Estoque insuficiente para o produto: " + produto.getNome());
            }

            produto.removerQuantidade(itemDTO.quantidade());
            produtoRepository.save(produto);

            // cria entidade ItensVenda
            ItensVenda itemVenda = new ItensVenda();
            itemVenda.setProduto(produto);
            itemVenda.setVenda(venda);
            itemVenda.setQuantidade(itemDTO.quantidade());
            itemVenda.setPrecoUnitario(produto.getPreco()); // se tiver campo preco

            // adiciona na lista de ItensVenda da venda
            venda.getItens().add(itemVenda);
        }
        BigDecimal total = venda.getItens().stream()
                .map(item -> item.getPrecoUnitario().multiply(BigDecimal.valueOf(item.getQuantidade())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        venda.setValorTotal(total);
      return  vendaRepository.save(venda);
    }

    public Venda buscarVenda(String id) {
        Venda venda = vendaRepository.findById(UUID.fromString(id)).orElseThrow(()-> new IdIncorretoException("Não existe Venda com esse ID"));
        return venda;
    }

    public Page<Venda> buscarTodos(String id,
                                   Integer pagina,
                                   Integer tamanhoPagina,
                                   LocalDateTime inicio,
                                   LocalDateTime fim) {
        Pageable pageable = PageRequest.of(pagina, tamanhoPagina);

        if (id != null && inicio != null && fim != null) {
            return vendaRepository.findByFuncionarioIdAndHoraVendaBetween(
                    UUID.fromString(id), inicio, fim, pageable);
        }

        if (inicio != null && fim != null) {
            return vendaRepository.findByHoraVendaBetween(inicio, fim, pageable);
        }

        if (id != null) {
            return vendaRepository.findByFuncionarioId(UUID.fromString(id), pageable);
        }

        return vendaRepository.findAll(pageable);
    }

    @Transactional
    public void deletar(String id) {
        vendaRepository.delete(vendaRepository.findById(UUID.fromString(id))
                .orElseThrow(()-> new IdIncorretoException("Não existe Venda com esse ID "+ id)));
    }

    @Transactional
    public void atualizar(String id, VendaDTO dto) {
        Venda venda = validador.existsVenda(id);

        venda.setFuncionario(
                funcionarioRepository.findById(UUID.fromString(dto.funcionarioId()))
                        .orElseThrow(() -> new IdIncorretoException("Não existe funcionário com esse ID " + dto.funcionarioId()))
        );

        venda.getItens().clear();

        for (ItemVendaDTO itemDTO : dto.itens()) {
            UUID uuid = UUID.fromString(itemDTO.produtoId());
            Produto produto = produtoRepository.findById(uuid)
                    .orElseThrow(() -> new IdIncorretoException("Produto não encontrado: " + itemDTO.produtoId()));

            if (produto.getQuantidade() < itemDTO.quantidade()) {
                throw new EstoqueInsuficienteException("Estoque insuficiente para o produto: " + produto.getNome());
            }

            produto.removerQuantidade(itemDTO.quantidade());
            produtoRepository.save(produto);

            ItensVenda itemVenda = new ItensVenda();
            itemVenda.setProduto(produto);
            itemVenda.setVenda(venda);
            itemVenda.setQuantidade(itemDTO.quantidade());
            itemVenda.setPrecoUnitario(produto.getPreco());

            venda.getItens().add(itemVenda);
        }

        BigDecimal total = venda.getItens().stream()
                .map(item -> item.getPrecoUnitario().multiply(BigDecimal.valueOf(item.getQuantidade())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        venda.setValorTotal(total);

        vendaRepository.save(venda);
    }
}
