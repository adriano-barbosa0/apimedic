package med.voll.api.controller;

import med.voll.api.dto.*;
import med.voll.api.dto.endereco.DadosEndereco;
import med.voll.api.dto.endereco.Endereco;
import med.voll.api.entity.Medico;
import med.voll.api.repository.MedicoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class MedicoControllerTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private JacksonTester<DadosDetalhaMedico> dadosDetalhaMedicoJacksonTester;
    @Autowired
    private JacksonTester<DadosMedico> dadosMedicoJacksonTester;
    @MockBean
    private MedicoRepository repository;
    @Test
    @DisplayName("Deveria devolver codigo http 400 quando informacoes estao invalidas")
    @WithMockUser
    void cadastrarMedicoCenario1() throws Exception{
        var response = mvc
                .perform(post("/medicos"))
                .andReturn().getResponse();
        assertThat(response.getStatus())
                .isEqualTo(HttpStatus.BAD_REQUEST.value());
    }
    @Test
    @DisplayName("Deveria devolver codigo http 201 quando informacoes estao validas")
    @WithMockUser
    void cadastrarMedicoCenario2()throws Exception{
        var dadosCadastro = new DadosMedico(
                "Medico",
                "medico@voll.med",
                "12345678910",
                "123456",
                Especialidade.CARDIOLOGIA,
                new DadosEndereco("Rua A","Nilopolis","23007334","Rio de Janeiro","RJ",null,"33")
        );

        when(repository.save(any())).thenReturn(new Medico(dadosCadastro));

        var response = mvc.perform(post("/medicos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dadosMedicoJacksonTester.write(
                                dadosCadastro).getJson()))
                .andReturn().getResponse();
        var dadosDetalhamento = new DadosDetalhaMedico(
                null,
                dadosCadastro.nome(),
                dadosCadastro.email(),
                dadosCadastro.crm(),
                dadosCadastro.telefone(),
                dadosCadastro.especialidade(),
                new Endereco(dadosCadastro.endereco())
        );

        var jsonEsperado = dadosDetalhaMedicoJacksonTester.write(
                dadosDetalhamento
        ).getJson();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }
}


