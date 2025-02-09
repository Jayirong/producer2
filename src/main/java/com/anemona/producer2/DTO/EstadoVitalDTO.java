package com.anemona.producer2.DTO;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class EstadoVitalDTO {
    private Long id_estado;
    private int frecuencia_cardiaca;
    private int presion_arterial_sis;
    private int presion_arterial_dias;
    private float saturacion_oxigeno;
    private LocalDateTime fecha_registro_ev;
    private Long id_paciente;
}
