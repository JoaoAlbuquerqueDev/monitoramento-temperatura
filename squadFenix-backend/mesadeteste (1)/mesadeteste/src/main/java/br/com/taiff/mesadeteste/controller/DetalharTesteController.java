package br.com.taiff.mesadeteste.controller;


import br.com.taiff.mesadeteste.dto.DetalharTesteResponse;
import br.com.taiff.mesadeteste.model.Teste;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RestController
public class DetalharTesteController {

    @PersistenceContext
    EntityManager entityManager;

    @GetMapping(value = "/teste/{id}")
    public ResponseEntity<?>buscarTeste(@PathVariable("id") Long id){

         Teste buscarteste = entityManager.find(Teste.class, id);

          if(buscarteste == null){

              return ResponseEntity.notFound().build();
          }

          DetalharTesteResponse detalharTesteResponse = new DetalharTesteResponse(buscarteste);

          return ResponseEntity.ok(detalharTesteResponse);





    }


}
