package br.com.taiff.mesadeteste.dto;

import java.time.LocalDateTime;
import java.util.Date;


import br.com.taiff.mesadeteste.model.Temperatura;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public class NovaTemperaturaRequest {


    private int t1;


    private int t2;
    

    private int t3;
    

    private int tAmbiente;
    
    private LocalDateTime dataTeste;

    public NovaTemperaturaRequest(int t1, int t2, int t3, int tAmbiente, LocalDateTime dataTeste) {
        this.t1 = t1;
        this.t2 = t2;
        this.t3 = t3;
        this.tAmbiente = tAmbiente;
        this.dataTeste = dataTeste;
    }

    public Temperatura toModel() {
        return new Temperatura(
                this.t1,
                this.t2,
                this.t3,
                this.tAmbiente,
                this.dataTeste
        		);
    }

    public Specification<Temperatura> toSpec() {
        return (root,query, builder)->{
            return builder.and();
        };
    }
    
 
}

