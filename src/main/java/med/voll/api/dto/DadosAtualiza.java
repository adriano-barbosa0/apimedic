package med.voll.api.dto;

import jakarta.validation.constraints.NotNull;
import med.voll.api.dto.endereco.DadosEndereco;

public record DadosAtualiza(
                                    @NotNull
                                    Long id,
                                    String nome,
                                    String telefone,
                                    DadosEndereco endereco
) {
}

