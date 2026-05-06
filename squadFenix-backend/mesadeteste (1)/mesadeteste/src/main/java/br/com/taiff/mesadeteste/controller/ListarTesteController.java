package br.com.taiff.mesadeteste.controller;

import br.com.taiff.mesadeteste.dto.ListaTesteResponse;
import br.com.taiff.mesadeteste.model.Teste;
import br.com.taiff.mesadeteste.repository.TesteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping(value = "/testes")
public class ListarTesteController {

    @Autowired
    TesteRepository testeRepository;

    @GetMapping
    public List<ListaTesteResponse>listar(){
        List<Teste> testeList = testeRepository.findAll();

        return ListaTesteResponse.toModel(testeList);
    }


}
