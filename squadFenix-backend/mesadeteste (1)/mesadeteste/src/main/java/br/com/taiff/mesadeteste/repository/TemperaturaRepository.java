package br.com.taiff.mesadeteste.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.taiff.mesadeteste.model.Temperatura;

@Repository
public interface TemperaturaRepository extends JpaRepository<Temperatura, Long> {

	Temperatura findTopByOrderByIdDesc();


}
