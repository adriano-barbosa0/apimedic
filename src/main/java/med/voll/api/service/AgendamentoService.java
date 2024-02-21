package med.voll.api.service;

import med.voll.api.dto.DadosAgendamento;
import med.voll.api.dto.DadosCancelamento;
import med.voll.api.dto.DadosDetalhaConsulta;
import med.voll.api.entity.Consulta;
import med.voll.api.entity.Medico;
import med.voll.api.infra.exception.ValidacaoException;
import med.voll.api.repository.ConsultaRepository;
import med.voll.api.repository.MedicoRepository;
import med.voll.api.repository.PacienteRepository;
import med.voll.api.service.validation.agendamento.IVAgendamento;
import med.voll.api.service.validation.cancelamento.VCancelamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendamentoService {
    @Autowired
    private ConsultaRepository consultaRepository;
    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private List<IVAgendamento> validadores;
    @Autowired
    private List<VCancelamento> validadorCancelamentos;
    public DadosDetalhaConsulta agendar(DadosAgendamento dados){
        if(!pacienteRepository.existsById(dados.idPaciente())){
            throw new ValidacaoException("Id do paciente não existe!");
        }
        if(dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico())){
            throw new ValidacaoException("Id do medico não existe!");
        }
        validadores.forEach(v -> v.validar((dados)));
        var paciente = pacienteRepository.getReferenceById(dados.idPaciente());
        var medico = escolherMedico(dados);
        if(medico == null){
            throw new ValidacaoException(("Não existe médico disponível no horário e data informados!"));
        }
        var consulta = new Consulta(null, medico, paciente, dados.data(), null);
        consultaRepository.save(consulta);
        return new DadosDetalhaConsulta(consulta);

    }

    private Medico escolherMedico(DadosAgendamento dados) {
        if(dados.idMedico() != null){
            return medicoRepository.getReferenceById(dados.idMedico());
        }
        if (dados.especialidade() == null){
            throw new ValidacaoException("Especialidade é obrigatória quando médico não for escolhido!");
        }
        return medicoRepository.medicoAleatorio(dados.especialidade(), dados.data());
    }

    public void cancelar(DadosCancelamento dados) {
        if(!consultaRepository.existsById(dados.idConsulta())){
            throw new ValidacaoException("Id da consulta informado não existe");
        }
        validadorCancelamentos.forEach(v -> v.validar(dados) );
        var consulta = consultaRepository.getReferenceById(dados.idConsulta());
        consulta.cancelar(dados.motivoCancelamento());
    }
}
