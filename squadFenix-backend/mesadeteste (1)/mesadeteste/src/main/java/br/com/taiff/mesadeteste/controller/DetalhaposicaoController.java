
package br.com.taiff.mesadeteste.controller;


import br.com.taiff.mesadeteste.dto.DetalhePosicaoResponse;
import br.com.taiff.mesadeteste.model.Posicao;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
@CrossOrigin("*")
@RestController
public class DetalhaposicaoController {

    @PersistenceContext
    private EntityManager manager;

    @GetMapping(value = "/posicao/{id}")
    public ResponseEntity<?>detalhe(@PathVariable("id") Long id){
        Posicao posicaobuscada = manager.find(Posicao.class, id);

         if(posicaobuscada == null){
             return ResponseEntity.notFound().build();
         }

          DetalhePosicaoResponse detalhePosicaoResponse = new DetalhePosicaoResponse(posicaobuscada);

         return ResponseEntity.ok(detalhePosicaoResponse);
    }
}

