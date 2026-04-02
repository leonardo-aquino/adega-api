package adega.projeto.com.demo.controller.common;

import adega.projeto.com.demo.controller.dto.errosDTO.ErroCampo;
import adega.projeto.com.demo.controller.dto.errosDTO.ErroResposta;
import adega.projeto.com.demo.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErroResposta metodoInvalidExeption(MethodArgumentNotValidException e){
        List<FieldError> fieldErrors = e.getFieldErrors(); // método para retornar quais campos deu erro na anotação valid no DTO
        List<ErroCampo> listaErros = fieldErrors
                .stream() // mapeando a lista fieldErros para a lista do tipo ErroCampo
                .map(fe -> new ErroCampo(fe.getField(), fe.getDefaultMessage()))
                .collect(Collectors.toList());
        return new ErroResposta(HttpStatus.UNPROCESSABLE_ENTITY.value(),
                "Erro de validação",listaErros);
    }


    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(IdIncorretoException.class)
    public ErroResposta idInvalido(IdIncorretoException e){
        ErroResposta erro = new ErroResposta(HttpStatus.NOT_FOUND.value(),e.getMessage(), List.of());
        return erro;
    }


    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(EntidadeJaCriadaException.class)
    public ErroResposta idInvalido(EntidadeJaCriadaException e){
        ErroResposta erro = new ErroResposta(HttpStatus.CONFLICT.value(),e.getMessage(), List.of());
        return erro;
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CargoInvalidoException.class)
    public ErroResposta cargoInvalido(CargoInvalidoException e){
        ErroResposta erro = new ErroResposta(HttpStatus.BAD_REQUEST.value(),e.getMessage(), List.of());
        return erro;
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(FornecedorInexistenteException.class)
    public ErroResposta FornecedorInexistenteException(FornecedorInexistenteException e){
        ErroResposta erro = new ErroResposta(HttpStatus.BAD_REQUEST.value(),e.getMessage(), List.of());
        return erro;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(TipoProdutoInvalidoException.class)
    public ErroResposta TipoProdutoInvalidoException(TipoProdutoInvalidoException e){
        ErroResposta erro = new ErroResposta(HttpStatus.BAD_REQUEST.value(),e.getMessage(), List.of());
        return erro;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(EstoqueInsuficienteException.class)
    public ErroResposta EstoqueInsuficienteException(EstoqueInsuficienteException e){
        ErroResposta erro = new ErroResposta(HttpStatus.BAD_REQUEST.value(),e.getMessage(), List.of());
        return erro;
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(AuthorizationDeniedException .class)
    public ErroResposta AuthorizationDeniedException(AuthorizationDeniedException e){
        ErroResposta erro = new ErroResposta(HttpStatus.FORBIDDEN.value(),"Você não tem autorização para executar esta tarefa!", List.of());
        return erro;
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(BadCredentialsException.class)
    public ErroResposta BadCredentialsException(BadCredentialsException e){
        ErroResposta erro = new ErroResposta(HttpStatus.UNAUTHORIZED.value(),"Nenhum Funcionario com esse cpf ou senha", List.of());
        return erro;
    }


    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErroResposta erroGenerico(Exception e) {
        return new ErroResposta(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Erro inesperado no servidor", List.of());
    }
}
