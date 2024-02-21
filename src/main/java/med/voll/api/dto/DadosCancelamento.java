package med.voll.api.dto;

import jakarta.validation.constraints.NotNull;

public record DadosCancelamento(
        @NotNull
        Long idConsulta,
        @NotNull
        MotivoCancelamento motivoCancelamento
) {
}
