package adega.projeto.com.demo.controller.dto.errosDTO;

import java.util.List;

public record ErroResposta(int status, String mensagem, List<ErroCampo> erros) {
}
