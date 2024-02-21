package med.voll.api.service.validation.agendamento;

import med.voll.api.dto.DadosAgendamento;
import med.voll.api.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VMedicoAtivo implements IVAgendamento{
    @Autowired
    private MedicoRepository repository;
    public void validar(DadosAgendamento dados){
        if(dados.idMedico() == null){
            return;
    }
    var medicoAtivo = repository.findAtivoById(dados.idMedico());
}}
