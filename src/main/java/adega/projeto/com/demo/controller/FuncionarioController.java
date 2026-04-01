package adega.projeto.com.demo.controller;

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
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@RestController
@RequestMapping("/funcionarios")
@RequiredArgsConstructor
public class FuncionarioController {

    private final FuncionarioService funcionarioService;
    private final EnderecoRepository enderecoRepository;

    @PostMapping
    public ResponseEntity<Void> criarFuncionario(@RequestBody @Valid FuncionarioRequestDTO dto){
         Funcionario funcionario = dto.toEntity(dto,enderecoRepository) ;
        funcionarioService.salvarFuncionario(funcionario);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("{id}")
    public ResponseEntity<FuncionarioResponseDTO> buscarPorId(@PathVariable("id") String id){
        Funcionario funcionario = funcionarioService.buscarPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(FuncionarioResponseDTO.toDTO(funcionario));
    }

    @GetMapping
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
    public ResponseEntity<Void> atualizarPorId(@PathVariable("id") String id,
                                               @RequestBody @Valid FuncionarioRequestDTO dto){
        Funcionario funcionario = dto.toEntity(dto,enderecoRepository);
        funcionarioService.atualizar(funcionario,id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable("id") String id){
        funcionarioService.deletar(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
