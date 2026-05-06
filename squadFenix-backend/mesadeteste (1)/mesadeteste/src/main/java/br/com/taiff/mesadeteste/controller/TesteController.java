
package br.com.taiff.mesadeteste.controller;

import br.com.taiff.mesadeteste.dto.TesteProdutoRequest;
import br.com.taiff.mesadeteste.model.Teste;
import br.com.taiff.mesadeteste.repository.TesteRepository;
import br.com.taiff.mesadeteste.repository.ZeroPecaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
@CrossOrigin("*")
@RestController
@RequestMapping("/gravarteste")
public class TesteController {

     @Autowired
     TesteRepository produtoRepository;

     @PersistenceContext
     EntityManager entityManager;


    @PostMapping
    public ResponseEntity<?>gravarProduto(@RequestBody @Valid TesteProdutoRequest request){

        Teste teste = request.toModel(entityManager);

        produtoRepository.save(teste);

        return ResponseEntity.ok().build();

    }

}


