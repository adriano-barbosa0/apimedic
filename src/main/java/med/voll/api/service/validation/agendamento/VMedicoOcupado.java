package med.voll.api.service.validation.agendamento;

import med.voll.api.dto.DadosAgendamento;
import med.voll.api.infra.exception.ValidacaoException;
import med.voll.api.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VMedicoOcupado implements IVAgendamento{
    @Autowired
    private ConsultaRepository repository;
    public void validar(DadosAgendamento dados){
        var medicoOcupado = repository.existsByMedicoIdAndDataAndMotivoCancelamentoIsNull(dados.idMedico(), dados.data());
        if(medicoOcupado){
            throw new ValidacaoException("Médico já possui outra consulta agendada nesse mesmo horário");
        }
    }
}
