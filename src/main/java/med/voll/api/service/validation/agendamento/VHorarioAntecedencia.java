package med.voll.api.service.validation.agendamento;

import med.voll.api.dto.DadosAgendamento;
import med.voll.api.infra.exception.ValidacaoException;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component("ValidadorHorarioAntecedenciaAgendamento")
public class VHorarioAntecedencia implements IVAgendamento{
    public void validar(DadosAgendamento dados){
        var dataConsulta = dados.data();
        var agora = LocalDateTime.now();
        var dfMinutos = Duration.between(agora, dataConsulta).toMinutes();
        if(dfMinutos < 30){
            throw  new ValidacaoException(("Consulta deve ser agendada com antecedência mínima de 30 minutos."));
        }
    }
}
