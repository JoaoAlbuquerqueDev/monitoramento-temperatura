package br.com.taiff.mesadeteste.model;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Temperatura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private float t1;

    @Column(nullable = false)
    private float t2;

    @Column(nullable = false)
    private float t3;

    @Column(nullable = false)
    private float tAmbiente;

    private LocalDateTime dataTeste;

    public Temperatura() {
    }

    public Temperatura(float t1, float t2, float t3, float tAmbiente, LocalDateTime dataTeste) {
        this.t1 = t1;
        this.t2 = t2;
        this.t3 = t3;
        this.tAmbiente = tAmbiente;
        this.dataTeste = dataTeste;
    }

    // GETTERS
    public Long getId() { return id; }
    public float getT1() { return t1; }
    public float getT2() { return t2; }
    public float getT3() { return t3; }
    public float getTAmbiente() { return tAmbiente; }
    public LocalDateTime getDataTeste() { return dataTeste; }

    // SETTERS 
    public void setT1(float t1) { this.t1 = t1; }
    public void setT2(float t2) { this.t2 = t2; }
    public void setT3(float t3) { this.t3 = t3; }
    public void setTAmbiente(float d) { this.tAmbiente = d; }
    public void setDataTeste(LocalDateTime dataTeste) { this.dataTeste = dataTeste; }

    @Override
    public String toString() {
        return "Temperatura{" +
                "id=" + id +
                ", t1=" + t1 +
                ", t2=" + t2 +
                ", t3=" + t3 +
                ", tAmbiente=" + tAmbiente +
                ", dataTeste=" + dataTeste +
                '}';
    }
}
