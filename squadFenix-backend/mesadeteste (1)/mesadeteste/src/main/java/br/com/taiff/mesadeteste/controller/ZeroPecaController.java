
package br.com.taiff.mesadeteste.controller;

import br.com.taiff.mesadeteste.dto.ZeroPecaRequest;
import br.com.taiff.mesadeteste.model.ZeroPeca;
import br.com.taiff.mesadeteste.repository.ZeroPecaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

@CrossOrigin("*")
@RestController
@RequestMapping("/zeropeca")
public class ZeroPecaController {

	@Autowired
	private ZeroPecaRepository zeropecaRepository;

   ;

	@PostMapping
	public ResponseEntity<?> zeroPeca(@RequestBody @Valid ZeroPecaRequest request) {

		ZeroPeca zeroPeca = request.toModel();

		zeropecaRepository.save(zeroPeca);

		return ResponseEntity.ok().build();
	}

}
