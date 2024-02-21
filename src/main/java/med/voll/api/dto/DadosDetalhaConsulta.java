package med.voll.api.dto;

import med.voll.api.entity.Consulta;

import java.time.LocalDateTime;

public record DadosDetalhaConsulta(Long id, Long idMedico, Long idPaciente, LocalDateTime data) {
    public DadosDetalhaConsulta(Consulta consulta) {
        this(consulta.getId(), consulta.getMedico().getId(), consulta.getPaciente().getId(), consulta.getData());
    }
}
