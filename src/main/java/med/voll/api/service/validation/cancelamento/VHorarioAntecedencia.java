package med.voll.api.service.validation.cancelamento;

import med.voll.api.dto.DadosCancelamento;
import med.voll.api.infra.exception.ValidacaoException;
import med.voll.api.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
@Component("ValidadorHorarioAntecedenciaCancelamento")
public class VHorarioAntecedencia implements VCancelamento{
    @Autowired
    private ConsultaRepository repository;
    @Override
    public void validar(DadosCancelamento dados){
        var consulta = repository.getReferenceById(dados.idConsulta());
        var agora = LocalDateTime.now();
        var diferencaEmHoras = Duration.between(agora, consulta.getData()).toHours();
        if(diferencaEmHoras < 24){
            throw new ValidacaoException("Consulta somente pode ser cancelada com antecedência mínima de 24h!");

        }
    }
}
