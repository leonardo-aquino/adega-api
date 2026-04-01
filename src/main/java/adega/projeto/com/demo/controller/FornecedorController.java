package adega.projeto.com.demo.controller;

import adega.projeto.com.demo.controller.dto.FornecedorDTO;
import adega.projeto.com.demo.controller.dto.FornecedorDTOResponse;
import adega.projeto.com.demo.model.entity.Fornecedor;
import adega.projeto.com.demo.repository.EnderecoRepository;
import adega.projeto.com.demo.service.FornecedorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fornecedores")
@RequiredArgsConstructor
public class FornecedorController{

    private final EnderecoRepository enderecoRepository;
    private final FornecedorService fornecedorService;

    @PostMapping
    public ResponseEntity<Void> criarFornecedor(@RequestBody @Valid FornecedorDTO fornecedorDTO){
        Fornecedor fornecedor = fornecedorDTO.toEntity(fornecedorDTO,enderecoRepository);
        fornecedorService.salvarFornecedor(fornecedor);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("{id}")
    public ResponseEntity<FornecedorDTOResponse> buscarPorId(@PathVariable("id") String id){
       return ResponseEntity.status(HttpStatus.OK).body(fornecedorService.buscarPorId(id));
    }

    @GetMapping
    public ResponseEntity<Page<FornecedorDTOResponse>> buscarFornecedor(@RequestParam(value = "pagina", defaultValue = "0")Integer pagina,
                                                            @RequestParam(value = "tamanho-pagina", defaultValue = "10")Integer tamanhoPagina,
                                                            @RequestParam(value = "nome", required = false)String nome,
                                                            @RequestParam(value = "telefone", required = false)String telefone,
                                                            @RequestParam(value = "email", required = false)String email
    ) {
        Page<Fornecedor> fornecedores = fornecedorService.buscarEnderecos(pagina, tamanhoPagina,nome,telefone,email);
        Page<FornecedorDTOResponse> resultado = fornecedores.map(FornecedorDTOResponse::toDTO);
        return ResponseEntity.status(HttpStatus.OK).body(resultado);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletarFornecedor(@PathVariable("id") String id){
        fornecedorService.deletarFornecedor(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> atualizarFornecedor(@PathVariable("id") String id,
                                                    @RequestBody @Valid FornecedorDTO dto){
        fornecedorService.atualizarFornecedor(id,dto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
