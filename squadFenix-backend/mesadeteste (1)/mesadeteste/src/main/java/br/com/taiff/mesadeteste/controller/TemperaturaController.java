package br.com.taiff.mesadeteste.controller;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.taiff.mesadeteste.dto.NovaTemperaturaRequest;
import br.com.taiff.mesadeteste.model.Temperatura;
import br.com.taiff.mesadeteste.repository.TemperaturaRepository;


@RestController
@RequestMapping("/temperatura")
@CrossOrigin(origins = "http://localhost:3000")
public class TemperaturaController {

    @Autowired
    private TemperaturaRepository repository;
    private volatile boolean rodando = true;

    @PostMapping
    public ResponseEntity<?> gravarTemperatura(@RequestBody @Valid NovaTemperaturaRequest request) {

        Temperatura temperatura = request.toModel();
        repository.save(temperatura);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/ultima")
    public ResponseEntity<?> ultimaTemperatura() {
        Temperatura t = repository.findTopByOrderByIdDesc();

        if (t == null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(t);
    }
    @PostMapping("/gerar-lote") // gera todos os valores de uma vez
    public ResponseEntity<String> gerarLote() {

        List<Temperatura> lista = new ArrayList<>();

        double tBase = 70;

        for (int i = 0; i < 1807; i++) {

            Temperatura t = new Temperatura();

            double variacao;
            
            if (i < 30) {
                // aquecendo
                variacao = i * 1.2;
            } else if (i < 60) {
                // estabilizado
            	variacao = 36 + Math.sin(i * 0.2) * 2; 
            } else {
                //  resfriando
            	variacao = Math.max(0, 36 - ((i - 60) * 1.1));
            }

            double ruido = (Math.random() * 2 - 1); // -1 até +1
            double ambiente = 23 + Math.sin(i * 0.1) * 2;
            double pico = (i == 40) ? 15 : 0;
            
            float baseValor = (float)(tBase + variacao + ruido);

            t.setT1(Math.random() < 0.03 ? 0 : baseValor + (float)pico);
            t.setT2(Math.random() < 0.03 ? 0 : baseValor + 5);
            t.setT3(Math.random() < 0.03 ? 0 : baseValor + 10);
            t.setTAmbiente((float)(ambiente + Math.random()));

            t.setDataTeste(LocalDateTime.now());
            lista.add(t);
        }
        repository.saveAll(lista); //  salvar lista

        return ResponseEntity.ok("20 temperaturas geradas");
    }

    @PostMapping("/stream-simulacao")
    public ResponseEntity<String> streamSimulacao() throws InterruptedException {
    	rodando = true;
        double tBase = 40;
        
        new Thread(() -> {
        	for (int i = 0; i < 100 && rodando; i++) {
                try {
                    Temperatura t = new Temperatura();
                    double variacao;
                    
                    if (i < 30) {
                        // aquecendo
                        variacao = i * 1.2;
                    } else if (i < 60) {
                        // estabilizado
                    	variacao = 36 + Math.sin(i * 0.2) * 2; 
                    } else {
                        //  resfriando
                    	variacao = Math.max(0, 36 - ((i - 60) * 1.1));
                    }

                    double ambiente = 23 + Math.sin(i * 0.1) * 2;
      
                    
                    float baseValor = (float)(tBase + variacao);

                    t.setT1(Math.random() < 0.03 ? 0 : baseValor);
                    t.setT2(Math.random() < 0.03 ? 0 : baseValor + 5);
                    t.setT3(Math.random() < 0.03 ? 0 : baseValor + 10);
                    t.setTAmbiente((float)(ambiente + Math.random()));

                    t.setDataTeste(LocalDateTime.now());

                    repository.save(t);
                    Thread.sleep(1000);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();    

        return ResponseEntity.ok("Simulação Inciada");
    }
    
    @PostMapping("/parar")
    public ResponseEntity<?> pararSimulacao() {
        rodando = false;
        return ResponseEntity.ok("Simulação parada");
    }
}






