package adega.projeto.com.demo.controller;

import adega.projeto.com.demo.controller.common.Location;
import adega.projeto.com.demo.controller.dto.FuncionarioRequestDTO;
import adega.projeto.com.demo.controller.dto.FuncionarioResponseDTO;
import adega.projeto.com.demo.model.entity.Funcionario;
import adega.projeto.com.demo.repository.EnderecoRepository;
import adega.projeto.com.demo.service.FuncionarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDate;

@RestController
@RequestMapping("/funcionarios")
@RequiredArgsConstructor
public class FuncionarioController implements Location {

    private final FuncionarioService funcionarioService;
    private final EnderecoRepository enderecoRepository;

    @PostMapping
    @PreAuthorize("hasRole('DONO')")
    public ResponseEntity<Void> criarFuncionario(@RequestBody @Valid FuncionarioRequestDTO dto){
         Funcionario funcionario = dto.toEntity(dto,enderecoRepository) ;
        var funcionarioCriado = funcionarioService.salvarFuncionario(funcionario);
        URI location = location(funcionarioCriado.getId());
        return ResponseEntity.created(location).build();
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAnyRole('DONO','GERENTE')")
    public ResponseEntity<FuncionarioResponseDTO> buscarPorId(@PathVariable("id") String id){
        Funcionario funcionario = funcionarioService.buscarPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(FuncionarioResponseDTO.toDTO(funcionario));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('DONO','GERENTE')")
    public ResponseEntity<Page<FuncionarioResponseDTO>> buscarTodos(
            @RequestParam(value = "pagina",defaultValue = "0",required = false) Integer pagina,
            @RequestParam(value = "tamanho-pagina",defaultValue = "10",required = false) Integer tamanhoPagina,
            @RequestParam(value = "nome",required = false) String nome,
            @RequestParam(value = "sobrenome",required = false) String sobrenome,
            @RequestParam(value = "cpf",required = false) String cpf,
            @RequestParam(value = "salario",required = false)BigDecimal salario,
            @RequestParam(value = "cargo",required = false)String cargo,
            @RequestParam(value = "idade",required = false) String idade,
            @RequestParam(value = "data-admissao", required = false)@DateTimeFormat(pattern = "dd/MM/yyyy" ) LocalDate dataAdmissao,
            @RequestParam(value = "data-demissao",required = false) @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate dataDemissao){

        Page<Funcionario> funcionarios = funcionarioService.buscarTodos(pagina,
                tamanhoPagina, nome, sobrenome, cpf, salario, cargo, idade, dataAdmissao, dataDemissao);

        return ResponseEntity.status(HttpStatus.OK).body(funcionarios.map(FuncionarioResponseDTO::toDTO));
    }


    @PutMapping("{id}")
    @PreAuthorize("hasRole('DONO')")
    public ResponseEntity<Void> atualizarPorId(@PathVariable("id") String id,
                                               @RequestBody @Valid FuncionarioRequestDTO dto){
        Funcionario funcionario = dto.toEntity(dto,enderecoRepository);
        funcionarioService.atualizar(funcionario,id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('DONO')")
    public ResponseEntity<Void> deletarPorId(@PathVariable("id") String id){
        funcionarioService.deletar(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
