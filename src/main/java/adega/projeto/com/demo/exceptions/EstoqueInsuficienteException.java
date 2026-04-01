package adega.projeto.com.demo.exceptions;

public class EstoqueInsuficienteException extends RuntimeException {
    public EstoqueInsuficienteException(String message) {
        super(message);
    }
}
