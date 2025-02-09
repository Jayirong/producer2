package com.anemona.producer2.DTO;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class AlertaDTO {
    private Long id_alerta;
    private String descripcion_alerta;
    private int nivel_alerta;
    private String parametro_alterado;
    private LocalDateTime fecha_alerta;
    private Long id_paciente;
    private long id_estado_vital;
    private boolean visto;
}
