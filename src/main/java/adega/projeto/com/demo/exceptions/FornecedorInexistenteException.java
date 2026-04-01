package adega.projeto.com.demo.exceptions;

public class FornecedorInexistenteException extends RuntimeException {
    public FornecedorInexistenteException(String message) {
        super(message);
    }
}
