package br.com.taiff.mesadeteste.repository;

import br.com.taiff.mesadeteste.model.Posicao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PosicaoRepository extends JpaRepository<Posicao, Long> {	
}


