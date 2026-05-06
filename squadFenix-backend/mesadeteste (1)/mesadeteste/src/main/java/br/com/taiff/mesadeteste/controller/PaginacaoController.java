package br.com.taiff.mesadeteste.controller;

import br.com.taiff.mesadeteste.dto.NovaTemperaturaRequest;
import br.com.taiff.mesadeteste.model.Temperatura;
import br.com.taiff.mesadeteste.repository.TemperaturaRepository2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaginacaoController {

    @Autowired
    private TemperaturaRepository2 temperaturaRepository2;

    @GetMapping(value = "/paginacao-simples")
    public String paginacaoSimples(){
        Page<Temperatura> temperaturaPage = temperaturaRepository2.findAll(PageRequest.of(0,20
        ));
        return temperaturaPage.getContent().toString();
    }


     @GetMapping(value = "/paginacao-com-parametros")
    public String paginacaoComParametros(Pageable pageable){
         Page<Temperatura> temperaturaPage = temperaturaRepository2.findAll(pageable);
         return temperaturaPage.getContent().toString();

     }

      @GetMapping(value = "/query-dimamica")
     public String paginacaoComParametrosEOrdenacao(NovaTemperaturaRequest novaTemperaturaRequest){

          return temperaturaRepository2.findAll(novaTemperaturaRequest.toSpec()).toString();

      }


}
