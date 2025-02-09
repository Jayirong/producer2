package com.anemona.producer2.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.anemona.producer2.DTO.AlertaDTO;
import com.anemona.producer2.DTO.EstadoVitalDTO;
import com.anemona.producer2.config.Producer2Config;
import com.anemona.producer2.model.HistoricoMensaje;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class Producer2Service {
    
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${aneback.url}")
    private String anebackUrl;

    @Scheduled(fixedRate = 30000)
    public void generarHistorico() {
        try {
            log.info("Iniciando generacion de historico");
            LocalDateTime fechaFin = LocalDateTime.now();
            LocalDateTime fechaInicio = fechaFin.minusMinutes(5);

            //obtenemos estados y alertas
            List<EstadoVitalDTO> estadosVitales = obtenerEstadosVitales();
            List<AlertaDTO> alertas = obtenerAlertas();

            HistoricoMensaje historico = new HistoricoMensaje();
            historico.setFechaInicio(fechaInicio);
            historico.setFechaFin(fechaFin);
            historico.setEstadosVitales(estadosVitales);
            historico.setAlertas(alertas);
            historico.setTotalEstadosVitales(estadosVitales.size());
            historico.setTotalAlertas(alertas.size());

            enviarHistorico(historico);
            log.info("Histórico generado y enviado exitosamente");
        } catch (Exception e) {
            log.error("Error generando historico: {}", e.getMessage());
        }
    }

    private List<EstadoVitalDTO> obtenerEstadosVitales() {
        String url = anebackUrl + "/api/estadoVitales/all";
        return restTemplate.exchange(
            url,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<List<EstadoVitalDTO>>() { }
            ).getBody();
    }

    private List<AlertaDTO> obtenerAlertas() {
        String url = anebackUrl + "/api/alertas/all";
        return restTemplate.exchange(
            url,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<List<AlertaDTO>>() { }
            ).getBody();
    }

    private void enviarHistorico(HistoricoMensaje historico) {
        try {
            rabbitTemplate.convertAndSend(
                Producer2Config.EXCHANGE_HISTORICO,
                Producer2Config.ROUTING_KEY,
                historico
            );
            log.info("Historicon enviado a RabbitMQ >:)");
        } catch (Exception e) {
            log.error("Error enviando historico a RabbitMQ: {}", e.getMessage());
        }
    }

}
