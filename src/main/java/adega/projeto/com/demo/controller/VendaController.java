package adega.projeto.com.demo.controller;

import adega.projeto.com.demo.controller.common.Location;
import adega.projeto.com.demo.controller.dto.VendaDTO;
import adega.projeto.com.demo.model.entity.Venda;
import adega.projeto.com.demo.repository.FuncionarioRepository;
import adega.projeto.com.demo.service.VendaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;

@RestController
@RequestMapping("vendas")
@RequiredArgsConstructor
public class VendaController implements Location {

private final FuncionarioRepository funcionarioRepository;
private final VendaService vendaService;

    @PostMapping
    @PreAuthorize("hasAnyRole('DONO','GERENTE','FUNCIONARIO')")
    public ResponseEntity<Void> salvarVenda(@Valid @RequestBody VendaDTO dto) {
        Venda venda = dto.toEntity(funcionarioRepository);
        var vendaSalva = vendaService.salvar(venda, dto.itens());

        URI location = location(vendaSalva.getId());
        return ResponseEntity.created(location).build();
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAnyRole('DONO','GERENTE')")
    public ResponseEntity<Venda> buscarVenda(@PathVariable String id){
        return ResponseEntity.status(HttpStatus.OK).body(vendaService.buscarVenda(id));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('DONO','GERENTE')")
    public ResponseEntity<Page<Venda>> buscarTodos(
            @RequestParam(value = "funcionario-id", required = false) String id,
            @RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
            @RequestParam(value = "tamanho-pagina", defaultValue = "10") Integer tamanhoPagina,
            @RequestParam(value = "inicio", required = false)
            @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss") LocalDateTime inicio,
            @RequestParam(value = "fim", required = false)
            @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss") LocalDateTime fim) {

        return ResponseEntity.ok(vendaService.buscarTodos(id, pagina, tamanhoPagina, inicio, fim));
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('DONO')")
    public ResponseEntity<Void> deletar(@PathVariable String id){
        vendaService.deletar(id);
        return ResponseEntity.ok().build();
    }

  @PutMapping("{id}")
  @PreAuthorize("hasAnyRole('DONO','GERENTE')")
  public ResponseEntity<Void> atualizar(@PathVariable String id, @Valid @RequestBody VendaDTO dto) {
        vendaService.atualizar(id,dto);
        return ResponseEntity.ok().build();
  }

}
