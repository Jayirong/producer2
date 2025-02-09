package com.anemona.producer2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anemona.producer2.service.Producer2Service;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/api/producer2")
public class Producer2Controller {
    @Autowired
    private Producer2Service producer2Service;

    @PostMapping("/generar-historico")
    public ResponseEntity<?> generarHistorico() {
        try {
            producer2Service.generarHistorico();
            return ResponseEntity.ok("Historico generado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error generando historico: " + e.getMessage());
        }
    }
    
}
