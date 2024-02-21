package med.voll.api.dto;

import med.voll.api.entity.Consulta;

import java.time.LocalDateTime;

public record DadosListagemConsultas(
        Long id,
        String medico,
        String paciente,
        LocalDateTime data

) {
    public DadosListagemConsultas(Consulta consulta){
        this(consulta.getId(), consulta.getMedico().getNome(), consulta.getPaciente().getNome(), consulta.getData());
    }
}
