package med.voll.api.dto;

import med.voll.api.dto.endereco.Endereco;
import med.voll.api.entity.Medico;

public record DadosDetalhaMedico(Long id,
                                 String nome,
                                 String email,
                                 String crm,
                                 String telefone,
                                 Especialidade especialidade,
                                 Endereco endereco) {
    public DadosDetalhaMedico(Medico medico){
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getTelefone(), medico.getEspecialidade(), medico.getEndereco());
    }
}
