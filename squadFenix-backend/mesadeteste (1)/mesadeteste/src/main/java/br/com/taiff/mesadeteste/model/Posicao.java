package br.com.taiff.mesadeteste.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Posicao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int eixoX;

    @Column(nullable = false)
    private int eixoY;
    
    @Column(nullable = false)
    private int eixoZ;
    
    @Column(nullable = false)
    private float rotacao;
    
    @Column(nullable = false)
    private float tempo;

    @JsonIgnore
    @ManyToOne
    private Teste teste;
    


    public Posicao( int eixoX, int eixoY, int eixoZ, float rotacao, float tempo, Teste teste) {

        this.eixoX = eixoX;
        this.eixoY = eixoY;
        this.eixoZ = eixoZ;
        this.rotacao = rotacao;
        this.tempo = tempo;
        this.teste = teste;

    }

    @Deprecated
    public Posicao() {
    }

    public int getEixoX() {
        return eixoX;
    }

    public Long getId() {
        return id;
    }


    public int getEixoY() {
        return eixoY;
    }

    public int getEixoZ() {
        return eixoZ;
    }

    public float getRotacao() {
        return rotacao;
    }

    public float getTempo() {
        return tempo;
    }

    public Teste getTeste() {
        return teste;
    }




}

