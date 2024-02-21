package med.voll.api.service.validation.agendamento;

import med.voll.api.dto.DadosAgendamento;
import med.voll.api.infra.exception.ValidacaoException;
import med.voll.api.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VPacienteSemConsultaDia implements IVAgendamento {
    @Autowired
    private ConsultaRepository repository;
    public void validar(DadosAgendamento dados){
        var primeiroHorario = dados.data().withHour(7);
        var ultimoHorario = dados.data().withHour(18);
        var pacienteSemConsultaDia = repository.existsByPacienteIdAndDataBetween(dados.idPaciente(), primeiroHorario, ultimoHorario);
        if(pacienteSemConsultaDia){
            throw new ValidacaoException("Paciente já possui uma consulta agendada nesse dia");
        }
    }
}
