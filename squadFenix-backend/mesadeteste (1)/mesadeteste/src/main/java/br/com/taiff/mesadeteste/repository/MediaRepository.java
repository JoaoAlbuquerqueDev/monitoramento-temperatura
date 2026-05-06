package br.com.taiff.mesadeteste.repository;


import java.util.List;

import javax.persistence.EntityManager;

import br.com.taiff.mesadeteste.model.Janela;
import org.springframework.stereotype.Repository;

import br.com.taiff.mesadeteste.model.Temperatura;

@Repository
public class MediaRepository {
	
	private final EntityManager em;
	
	public MediaRepository(EntityManager em) {
		this.em = em;
	}
    
	public Janela findJanela(Long id,Long in, Long fin) {
		Long n1 = in;
		Long n2 = fin;
		Long x = id;
		
		float acumt1 = 0;
		float acumt2 = 0;
		float acumt3 = 0;
	    float acumt4 = 0;
		

		String query = "select T from Temperatura T where T.id between :in and :fin" ;

		var q = em.createQuery(query, Temperatura.class)
				.setParameter("in", n1)
				.setParameter("fin", n2);
		
		List<Temperatura> lista = q.getResultList();
				
		for (Temperatura t : lista) {
			acumt1 =acumt1 + t.getT1();
			acumt2 = acumt2 + t.getT2();
			acumt3 = acumt3 + t.getT3();
			acumt4 =acumt4 + t.getTAmbiente();
		}
		
		int qtd = lista.size();
		if(qtd == 0) {
			return new Janela(id,0,0,0,0);
		}
		float mediaT1 = acumt1 / qtd;
		float mediaT2 = acumt2 / qtd;
		float mediaT3 = acumt3 / qtd;
		float mediaTA = acumt4/qtd;
		 
		Janela janela = new Janela(x, mediaT1, mediaT2, mediaT3, mediaTA);
		
		acumt1 = 0;
		acumt2 = 0;
		acumt3 = 0;
		acumt4 = 0;
		                             
		return janela;
	}

}
