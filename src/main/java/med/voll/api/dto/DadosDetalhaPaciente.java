package med.voll.api.dto;

import med.voll.api.dto.endereco.Endereco;
import med.voll.api.entity.Paciente;

public record DadosDetalhaPaciente(Long id,
                                   String nome,
                                   String email,
                                   String cpf,
                                   String telefone,
                                   Endereco endereco) {
    public DadosDetalhaPaciente(Paciente paciente){
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getCpf(), paciente.getTelefone(), paciente.getEndereco());
    }
}
