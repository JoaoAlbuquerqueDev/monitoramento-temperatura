package br.com.taiff.mesadeteste.dto;

import br.com.taiff.mesadeteste.model.Posicao;
import br.com.taiff.mesadeteste.model.Teste;
import br.com.taiff.mesadeteste.model.ZeroPeca;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DetalharTesteResponse {

    private String nome;

    private String modelo;


    private LocalDate dataTeste;

    private ZeroPeca zeroPeca;


   private List<Posicao>posicoes;


    public DetalharTesteResponse(Teste teste) {
        this.nome = teste.getNome();
        this.modelo = teste.getModelo();
        this.dataTeste = teste.getDataTeste();
        this.zeroPeca = teste.getZeroPeca();
        this.posicoes = teste.getPosicoes();
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
