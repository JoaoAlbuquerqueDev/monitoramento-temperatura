package br.com.taiff.mesadeteste.repository;

import br.com.taiff.mesadeteste.model.Teste;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TesteRepository extends JpaRepository<Teste, Long> {

    Optional<Teste> findById(Long id);
}


