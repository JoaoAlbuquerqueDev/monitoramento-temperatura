package br.com.taiff.mesadeteste.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.taiff.mesadeteste.dto.ListaZeroPecaResponse;
import br.com.taiff.mesadeteste.model.ZeroPeca;
import br.com.taiff.mesadeteste.repository.ZeroPecaRepository;

@CrossOrigin("*")
@RestController
public class ListaZeroPecaController {
	@Autowired
    private ZeroPecaRepository zeroPecaRepositor;

    @GetMapping("/zeroPeca")

    public List<ListaZeroPecaResponse>listar(){
        List<ZeroPeca> zeroPeca = zeroPecaRepositor.findAll();
        return ListaZeroPecaResponse.toModel(zeroPeca);
    }
}
