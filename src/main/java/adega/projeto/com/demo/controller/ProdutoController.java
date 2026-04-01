package adega.projeto.com.demo.controller;

import adega.projeto.com.demo.controller.dto.ProdutoRequestDTO;
import adega.projeto.com.demo.controller.dto.ProdutoResponseDTO;
import adega.projeto.com.demo.model.entity.Produto;
import adega.projeto.com.demo.service.ProdutoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@RestController
@RequestMapping("produtos")
@RequiredArgsConstructor
public class ProdutoController{

    private final ProdutoService service;


    @PostMapping
    public ResponseEntity<Void> criarProduto(@RequestBody @Valid ProdutoRequestDTO dto){
        Produto produto = dto.toEntity(dto);
        service.salvar(produto,dto.fornecedores());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> atualizarProduto(@PathVariable("id") String id,@RequestBody ProdutoRequestDTO dto){
        Produto produto = dto.toEntity(dto);
        service.atualizar(id,produto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable String id){
        service.deletar(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("{id}")
    public ResponseEntity<ProdutoResponseDTO> buscarPorId(@PathVariable String id){
       return ResponseEntity.status(HttpStatus.OK).body(service.buscarPorId(id)) ;
    }

    @GetMapping
    public ResponseEntity<Page<ProdutoResponseDTO>> buscarTodos(
            @RequestParam(value = "pagina",defaultValue = "0")Integer pagina,
            @RequestParam(value = "tamanho-pagina",defaultValue = "10")Integer tamanhoPagina,
            @RequestParam(value = "nome",required = false)String nome,
            @RequestParam(value = "tipo-produto",required = false)String tipoProduto,
            @RequestParam(value = "teor-alcoolico",required = false)BigDecimal teorAlcoolico,
            @RequestParam(value = "volume",required = false)String volume,
            @RequestParam(value = "vencimento",required = false)@DateTimeFormat(pattern = "dd/MM/yyyy" )LocalDate vencimento,
            @RequestParam(value = "quantidade",required = false)Integer quantidade){

        Page<Produto> produtos = service.buscarTodos(pagina, tamanhoPagina,
                nome, tipoProduto, teorAlcoolico, volume, vencimento, quantidade);
        return ResponseEntity.status(HttpStatus.OK).body(produtos.map(ProdutoResponseDTO::toDTO));

    }
}
