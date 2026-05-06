package br.com.taiff.mesadeteste.dto;

import br.com.taiff.mesadeteste.model.Temperatura;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListaTemperaturaResponse {
    private Long id;


    private float t1;

    private float t2;


    private float t3;


    private float tAmbiente;


    private LocalDateTime dataTeste;




    public ListaTemperaturaResponse(Temperatura temperatura) {
        id = temperatura.getId();
        t1 = temperatura.getT1();
        t2 = temperatura.getT2();
        t3 = temperatura.getT3();
        tAmbiente = temperatura.getTAmbiente();
        dataTeste = temperatura.getDataTeste();
    }

    public ListaTemperaturaResponse() {
    }

    public static List<ListaTemperaturaResponse> toModel(List<Temperatura>temperatura){
        return temperatura.stream().map(ListaTemperaturaResponse::new).collect(Collectors.toList());
      }

    public Long getId() {
        return id;
    }

    public float getT1() {
        return t1;
    }

    public float getT2() {
        return t2;
    }

    public float getT3() {
        return t3;
    }

    public float gettAmbiente() {
        return tAmbiente;
    }

    public LocalDateTime getDataTeste() {
        return dataTeste;
    }


}

