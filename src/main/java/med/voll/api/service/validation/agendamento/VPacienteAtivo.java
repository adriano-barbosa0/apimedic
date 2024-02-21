package med.voll.api.service.validation.agendamento;

import med.voll.api.dto.DadosAgendamento;
import med.voll.api.infra.exception.ValidacaoException;
import med.voll.api.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VPacienteAtivo implements IVAgendamento {
    @Autowired
    private PacienteRepository repository;
    public void validar(DadosAgendamento dados){
        var pacienteAtivo = repository.findAtivoById(dados.idPaciente());
        if(!pacienteAtivo){
            throw new ValidacaoException("Consulta não pode ser agendada com paciente excluído");
        }
    }
}
