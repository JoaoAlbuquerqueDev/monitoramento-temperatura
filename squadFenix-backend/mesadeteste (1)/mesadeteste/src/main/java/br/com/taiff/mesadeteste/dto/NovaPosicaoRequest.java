package br.com.taiff.mesadeteste.dto;

import br.com.taiff.mesadeteste.model.Posicao;
import br.com.taiff.mesadeteste.model.Teste;


import javax.persistence.EntityManager;

public class NovaPosicaoRequest {

    private int eixoX;
    private int eixoY;
    private int eixoZ;
    private String descricao;


    private float rotacao;

    private float tempo;

    private Long teste;



    public NovaPosicaoRequest(String descricao,int eixoX, int eixoY, int eixoZ, float rotacao, float tempo, Long teste) {
        this.descricao = descricao;
        this.eixoX = eixoX;
        this.eixoY = eixoY;
        this.eixoZ = eixoZ;
        this.rotacao = rotacao;
        this.tempo = tempo;
        this.teste = teste;

    }


    public Posicao toModel(EntityManager entityManager) {

        if (teste == null) {
            throw new IllegalArgumentException("Teste é obrigatório");
        }

        Teste a = entityManager.find(Teste.class, teste);

        if (a == null) {
            throw new RuntimeException("Teste não encontrado");
        }

        return new Posicao(eixoX, eixoY, eixoZ, rotacao, tempo, a);
    }
}

