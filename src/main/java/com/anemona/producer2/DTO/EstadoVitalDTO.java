package com.anemona.producer2.DTO;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Data;

@Data
public class EstadoVitalDTO {
    private Long id_estado;
    private int frecuencia_cardiaca;
    private int presion_arterial_sis;
    private int presion_arterial_dias;
    private float saturacion_oxigeno;
    private LocalDate fecha_registro_ev;
    private LocalTime hora_registro_ev;
    private Long id_paciente;
}
