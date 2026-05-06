
package br.com.taiff.mesadeteste.controller;

import br.com.taiff.mesadeteste.dto.ListaPosicoesResponse;

import br.com.taiff.mesadeteste.model.Posicao;
import br.com.taiff.mesadeteste.repository.PosicaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@CrossOrigin("*")
@RestController
public class ListaPosicaoController {

    @Autowired
    private PosicaoRepository posicaoRepository;

    @GetMapping("/posicoes")

    public List<ListaPosicoesResponse>listar(){
        List<Posicao> posicao = posicaoRepository.findAll();
        return ListaPosicoesResponse.toModel(posicao);
    }
}


