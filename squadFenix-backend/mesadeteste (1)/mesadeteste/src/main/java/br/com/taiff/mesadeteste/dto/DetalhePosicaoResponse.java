package br.com.taiff.mesadeteste.dto;

import br.com.taiff.mesadeteste.model.Posicao;
import br.com.taiff.mesadeteste.model.Teste;



public class DetalhePosicaoResponse {


    private int eixoX;


    private int eixoY;


    private int eixoZ;


    private float rotacao;


    private float tempo;


    private Teste produto;

    public DetalhePosicaoResponse(Posicao posicao) {
        eixoX =posicao.getEixoX();
        eixoY = posicao.getEixoY();
        eixoZ = posicao.getEixoZ();
        rotacao = posicao.getRotacao();
        tempo = posicao.getTempo();
        produto = posicao.getTeste();
    }

    public int getEixoX() {
        return eixoX;
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

    public Teste getProduto() {
        return produto;
    }
}
