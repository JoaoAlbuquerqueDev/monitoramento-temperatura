package br.com.taiff.mesadeteste.dto;

import br.com.taiff.mesadeteste.model.Posicao;
import br.com.taiff.mesadeteste.model.Teste;
import br.com.taiff.mesadeteste.model.ZeroPeca;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListaTesteResponse {

    private Long id;

    private String nome;

    private String modelo;


    private LocalDate dataTeste;


    private ZeroPeca zeroPeca;

    List<Posicao> posicoes = new ArrayList<>();


    public ListaTesteResponse(Teste teste) {
        this.id = teste.getId();
        this.nome = teste.getNome();
        this.modelo = teste.getModelo();
        this.dataTeste = teste.getDataTeste();
        this.zeroPeca = teste.getZeroPeca();
        this.posicoes = teste.getPosicoes();
    }

    public static List<ListaTesteResponse> toModel(List<Teste> testeList) {

      return testeList.stream().map(ListaTesteResponse::new).collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getModelo() {
        return modelo;
    }

    public LocalDate getDataTeste() {
        return dataTeste;
    }

    public ZeroPeca getZeroPeca() {
        return zeroPeca;
    }

    public List<Posicao> getPosicoes() {
        return posicoes;
    }
}
