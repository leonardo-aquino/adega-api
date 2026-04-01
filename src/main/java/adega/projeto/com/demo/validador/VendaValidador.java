package adega.projeto.com.demo.validador;

import adega.projeto.com.demo.exceptions.IdIncorretoException;
import adega.projeto.com.demo.model.entity.Venda;
import adega.projeto.com.demo.repository.VendaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class VendaValidador {

    private final VendaRepository vendaRepository;

    public Venda existsVenda(String id){
       return vendaRepository.findById(UUID.fromString(id)).orElseThrow(()-> new IdIncorretoException("Não existe Venda com esse ID "+ id));
    }
}
