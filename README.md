# APIMedic

APIMedic é uma API para gerenciamento de um sistema de consultório médico, construída com Java e Spring Boot. A API oferece funcionalidades de login com token, CRUD de pacientes e médicos, agendamento e cancelamento de consultas, além de várias validações para garantir a integridade dos dados.

## Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.0**
- **MySQL**
- **Flyway** (versionamento do banco de dados)
- **JUnit** (testes unitários)
- **Mockito** (testes unitários)
- **Swagger** (documentação)
- **Postman** (testes da API)
- **JPA** (persistência de dados)
- **Spring Security** (segurança, com tokens de acesso limitados a 30 minutos)

## Funcionalidades

- **Tela de Login:** Implementação de autenticação com token.
- **CRUD de Pacientes e Médicos:** Permite criar, ler, atualizar e excluir (delete lógico) consultas, pacientes e médicos.
- **Agendamento de Consultas:** Para agendar não é necessário digitar o nome do médico, mas sim sua especialidade, assim trará todos os médicos com aquela especialidade com horários disponíveis para o respectivo dia da consulta.
- **Cancelamento de Consultas:** Consultas podem ser canceladas com 24 horas de antecedência por médicos e pacientes.
- **Validações:** Verificação se o médico está ativo antes de agendar, se o paciente não tem outra consulta agendada no mesmo horário, etc.

## Instalação e Configuração

1. **Clone o repositório:**
    ```sh
    git clone https://github.com/seuusuario/apimedic.git
    cd apimedic
    ```

2. **Configure o banco de dados MySQL:**
    - Crie um banco de dados no MySQL.
    - Atualize as credenciais do banco de dados em `src/main/resources/application.properties`.

3. **Execute a aplicação:**
    ```sh
    ./mvnw spring-boot:run
    ```

4. **Acesse a documentação da API:**
    - Abra o navegador e vá para `http://localhost:8080/swagger-ui.html` para ver a documentação do Swagger.

## Testes

- **JUnit e Mockito:** Os testes unitários estão implementados usando JUnit e Mockito.

## Contribuindo

Se você quiser contribuir com este projeto, siga os passos abaixo:

1. **Fork este repositório.**
2. **Crie um branch para sua feature:**
    ```sh
    git checkout -b minha-feature
    ```
3. **Commit suas mudanças:**
    ```sh
    git commit -m 'Adiciona minha feature'
    ```
4. **Push para o branch:**
    ```sh
    git push origin minha-feature
    ```
5. **Abra um Pull Request.**

## Licença

Este projeto está licenciado sob a Licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

## Contato

Para mais informações, entre em contato com Adriano Barbosa (adrianobarbosa3001@gmail.com).
