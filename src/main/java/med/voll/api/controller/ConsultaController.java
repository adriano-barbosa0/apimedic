package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.dto.DadosAgendamento;
import med.voll.api.dto.DadosCancelamento;
import med.voll.api.dto.DadosListagemConsultas;

import med.voll.api.repository.ConsultaRepository;
import med.voll.api.service.AgendamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consultas")
@SecurityRequirement(name = "bearer-key")
public class ConsultaController {
    @Autowired
    private AgendamentoService agendamento;
    @Autowired
    private ConsultaRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity agendar(@RequestBody @Valid DadosAgendamento dados){
        var dto = agendamento.agendar(dados);
        return ResponseEntity.ok(dto);

    }
    @DeleteMapping
    @Transactional
    public ResponseEntity cancelar(@RequestBody @Valid DadosCancelamento dados){
        agendamento.cancelar(dados);
        return ResponseEntity.noContent().build();

    }
    @GetMapping
    public Page<DadosListagemConsultas> listar(Pageable paginacao) {
        return repository.findAll(paginacao).map(DadosListagemConsultas::new);
    }

}
