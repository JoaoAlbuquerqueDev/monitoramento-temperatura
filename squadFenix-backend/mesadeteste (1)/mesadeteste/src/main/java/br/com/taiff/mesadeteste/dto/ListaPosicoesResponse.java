package br.com.taiff.mesadeteste.dto;

import br.com.taiff.mesadeteste.model.Posicao;
import br.com.taiff.mesadeteste.model.Teste;

import java.util.List;
import java.util.stream.Collectors;

public class ListaPosicoesResponse {

    private  Long id;

    private String descricao;

    private int eixoX;


    private int eixoY;


    private int eixoZ;


    private float rotacao;


    private float tempo;


    private Teste produto;



    public ListaPosicoesResponse(Posicao posicao) {
        id = posicao.getId();
        eixoX =posicao.getEixoX();
        eixoY = posicao.getEixoY();
        eixoZ = posicao.getEixoZ();
        rotacao = posicao.getRotacao();
        tempo = posicao.getTempo();
        produto = posicao.getTeste();




    }

    public static List<ListaPosicoesResponse> toModel(List<Posicao> posicao) {

        return posicao.stream().map(ListaPosicoesResponse::new).collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
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
