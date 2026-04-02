package adega.projeto.com.demo.controller;

import adega.projeto.com.demo.controller.common.Location;
import adega.projeto.com.demo.controller.dto.EnderecoDTO;
import adega.projeto.com.demo.model.entity.Endereco;
import adega.projeto.com.demo.service.EnderecoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/enderecos")
@RequiredArgsConstructor
public class EnderecoController implements Location{

    private final EnderecoService enderecoService;

    @PostMapping
    @PreAuthorize("hasAnyRole('DONO','GERENTE')")
    public ResponseEntity<EnderecoDTO> criarEndereco(@RequestBody @Valid EnderecoDTO enderecoDTO){
        Endereco endereco = enderecoDTO.toEntity(enderecoDTO);
        var enderecoCriado = enderecoService.salvarEndereco(endereco);

        URI location = location(enderecoCriado.getId());
        return ResponseEntity.created(location).body(EnderecoDTO.toDTO(enderecoCriado));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('DONO','GERENTE','FUNCIONARIO')")
    public ResponseEntity<Page<EnderecoDTO>> buscarEndereco(@RequestParam(value = "pagina", defaultValue = "0")Integer pagina,
                                                            @RequestParam(value = "tamanho-pagina", defaultValue = "10")Integer tamanhoPagina,
                                                            @RequestParam(value = "numero", required = false)String numero,
                                                            @RequestParam(value = "rua", required = false)String rua,
                                                            @RequestParam(value = "cep", required = false)String cep,
                                                            @RequestParam(value = "bairro", required = false)String bairro
                                                            ) {
        Page<Endereco> enderecos = enderecoService.buscarEnderecos(pagina, tamanhoPagina,numero,rua,cep,bairro);
        Page<EnderecoDTO> resultado = enderecos.map(EnderecoDTO::toDTO);
        return ResponseEntity.status(HttpStatus.OK).body(resultado);
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAnyRole('DONO','GERENTE','FUNCIONARIO')")
    public ResponseEntity<EnderecoDTO> buscarPorId(@PathVariable("id") String id) {
        Endereco endereco = enderecoService.obterPorId(id);
        EnderecoDTO resultado = EnderecoDTO.toDTO(endereco);
        return ResponseEntity.status(HttpStatus.OK).body(resultado);
    }

    @PutMapping("{id}")
    @PreAuthorize("hasAnyRole('DONO','GERENTE')")
    public ResponseEntity<Void> atualizarEndereco(@PathVariable("id") String id,
                                                  @RequestBody @Valid EnderecoDTO enderecoDTO){
        enderecoService.atualizarEndereco(id,enderecoDTO);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('DONO')")
    public ResponseEntity<Void> deletarEndereco(@PathVariable("id") String id){
        enderecoService.deletarEndereco(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
