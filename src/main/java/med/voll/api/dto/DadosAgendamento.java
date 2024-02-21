package med.voll.api.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DadosAgendamento(
        @JsonAlias("medico_id") Long idMedico,
        @NotNull
        @JsonAlias("paciente_id") Long idPaciente,
        @NotNull
        @Future
        LocalDateTime data,
        Especialidade especialidade
) {
}
