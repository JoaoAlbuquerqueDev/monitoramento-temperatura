package br.com.taiff.mesadeteste.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Teste {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String modelo;


    private LocalDate dataTeste;


       @OneToOne(cascade = {CascadeType.DETACH})
       @JoinColumn(name="ZEROPECA_ID")
       private ZeroPeca zeroPeca;


       @OneToMany(mappedBy = "teste")
       List<Posicao> posicoes = new ArrayList<>();


    public Teste(String nome, String modelo, LocalDate dataTeste, ZeroPeca zeroPeca) {
        this.nome = nome;
        this.modelo = modelo;
        this.dataTeste = dataTeste;
        this.zeroPeca = zeroPeca;
    }
     @Deprecated
    public Teste() {
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
