package adega.projeto.com.demo.controller.common;

import adega.projeto.com.demo.controller.dtos.errosDTO.ErroCampo;
import adega.projeto.com.demo.controller.dtos.errosDTO.ErroResposta;
import adega.projeto.com.demo.exceptions.EntidadeJaCriadaException;
import adega.projeto.com.demo.exceptions.IdIncorretoException;
import org.springframework.http.HttpStatus;
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
}
