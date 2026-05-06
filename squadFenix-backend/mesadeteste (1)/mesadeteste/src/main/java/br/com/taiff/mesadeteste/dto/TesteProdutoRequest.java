package br.com.taiff.mesadeteste.dto;

import br.com.taiff.mesadeteste.model.Teste;
import br.com.taiff.mesadeteste.model.ZeroPeca;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

public class TesteProdutoRequest {

    @NotBlank
    private String nome;

    @NotBlank
    private String modelo;

    private LocalDate dataTeste;

    private Long idZeroPeca;

    public TesteProdutoRequest(String nome, String modelo, LocalDate dataTeste, Long idZeroPeca) {
        this.nome = nome;
        this.modelo = modelo;
        this.dataTeste = dataTeste;
        this.idZeroPeca = idZeroPeca;
    }

    public Teste toModel(EntityManager entityManager) {

        ZeroPeca a = entityManager.find(ZeroPeca.class, idZeroPeca);

        return new Teste(this.nome, this.modelo, this.dataTeste, a);

    }


}
