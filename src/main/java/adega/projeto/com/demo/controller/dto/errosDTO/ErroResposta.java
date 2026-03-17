package adega.projeto.com.demo.controller.dtos.errosDTO;

import java.util.List;

public record ErroResposta(int status, String mensagem, List<ErroCampo> erros) {
}
