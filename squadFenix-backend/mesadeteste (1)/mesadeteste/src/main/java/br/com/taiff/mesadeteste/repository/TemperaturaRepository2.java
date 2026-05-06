package br.com.taiff.mesadeteste.repository;

import br.com.taiff.mesadeteste.model.Temperatura;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemperaturaRepository2 extends PagingAndSortingRepository<Temperatura,Long >, JpaSpecificationExecutor<Temperatura> {
}
