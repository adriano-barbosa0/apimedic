package med.voll.api.service.validation.agendamento;

import med.voll.api.dto.DadosAgendamento;
import med.voll.api.infra.exception.ValidacaoException;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
@Component
public class VHorarioFuncionamento implements IVAgendamento {
    public void validar(DadosAgendamento dados){
        var dataConsulta = dados.data();
        var domingo = dataConsulta.getDayOfWeek().equals((DayOfWeek.SUNDAY));
        var antesAbertura = dataConsulta.getHour() < 7;
        var depoisEncerramento = dataConsulta.getHour() > 18;
        if(domingo || antesAbertura || depoisEncerramento){
            throw  new ValidacaoException(("Consulta fora do horário de funcionamento da clínica"));
        }
    }
}
