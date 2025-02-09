package com.anemona.producer2.model;

import java.time.LocalDateTime;
import java.util.List;

import com.anemona.producer2.DTO.AlertaDTO;
import com.anemona.producer2.DTO.EstadoVitalDTO;

import lombok.Data;

@Data
public class HistoricoMensaje {
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private List<EstadoVitalDTO> estadosVitales;
    private List<AlertaDTO> alertas;
    private int totalEstadosVitales;
    private int totalAlertas;
}
