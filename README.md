Markdown# 🎫 Sistema de Gestão de Chamados — API REST

[![Java](https://img.shields.io/badge/Java-17-orange.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Spring Security](https://img.shields.io/badge/Spring%20Security-JWT-blue.svg)](https://spring.io/projects/spring-security)
[![Swagger](https://img.shields.io/badge/Swagger-OpenAPI%203.0-green.svg)](http://localhost:8080/swagger-ui.html)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

API REST completa para gerenciamento e acompanhamento de chamados de TI e Helpdesk interno. O sistema conta com controle de acesso por perfis, categorização de chamados, histórico de comentários, paginação e filtros dinâmicos, validação de dados e documentação interativa com Swagger.

---

## 📌 Principais Recursos

- 🔐 **Autenticação & Autorização:** Controle de acesso por perfil (`SOLICITANTE`, `ATENDENTE`, `ADMIN`) via tokens JWT (*Stateless*).
- 📋 **Gestão de Chamados:** Abertura, atribuição de atendente, alteração de prioridades e ciclo de vida do chamado (`ABERTO`, `EM_ANDAMENTO`, `RESOLVIDO`, `CANCELADO`).
- 💬 **Interações & Comentários:** Histórico e registro de updates dentro de cada chamado.
- ⚡ **Paginação e Filtros Dinâmicos:** Busca parametrizada por status, prioridade e categoria com ordenação customizada.
- 🛡️ **Tratamento Global de Exceções:** Respostas JSON padronizadas para erros de validação (HTTP 400) e recursos não encontrados (HTTP 404).
- 📜 **Documentação OpenAPI 3.0:** Interface Swagger UI com suporte a autenticação Bearer Token para testes diretos pelo navegador.
- 🌱 **Data Seeding Automático:** Carga inicial de dados fictícios para testes imediatos da API.

---

## 🛠️ Tecnologias Utilizadas

- **Linguagem:** Java 17
- **Framework:** Spring Boot 3.x (Spring Web, Spring Data JPA, Spring Validation)
- **Segurança:** Spring Security + JJWT (Java JWT 0.12.x)
- **Banco de Dados:** H2 Database (em memória para ambiente de desenvolvimento)
- **Documentação:** Springdoc OpenAPI / Swagger UI
- **Testes:** JUnit 5 & Mockito
- **Build Tool:** Maven

---

## 📐 Modelo de Dados (ERD)

```mermaid
erDiagram
    USUARIO {
        Long id PK
        String nome
        String email
        String senha
        Perfil perfil
    }

    CATEGORIA {
        Long id PK
        String nome
    }

    CHAMADO {
        Long id PK
        String titulo
        String descricao
        Prioridade prioridade
        StatusChamado status
        LocalDateTime dataCriacao
        LocalDateTime dataAtualizacao
        Long solicitante_id FK
        Long atendente_id FK
        Long categoria_id FK
    }

    COMENTARIO {
        Long id PK
        String mensagem
        LocalDateTime dataCriacao
        Long autor_id FK
        Long chamado_id FK
    }

    USUARIO ||--o{ CHAMADO : "solicita / atende"
    USUARIO ||--o{ COMENTARIO : "escreve"
    CATEGORIA ||--o{ CHAMADO : "classifica"
    CHAMADO ||--o{ COMENTARIO : "possui"
🚀 Como Executar o ProjetoPré-requisitosJava JDK 17 ou superior instalado.Git instalado.Passo a PassoClonar o repositório:Bashgit clone [https://github.com/davimnds/sistema-gestao-chamados.git](https://github.com/davimnds/sistema-gestao-chamados.git)
cd sistema-gestao-chamados
Compilar e Executar a aplicação:PowerShell# No Windows:
.\mvnw.cmd spring-boot:run

# No Linux/Mac:
./mvnw spring-boot:run
Acessar o Swagger UI:Abra no navegador: http://localhost:8080/swagger-ui.htmlAcessar o H2 Console (Opcional):URL: http://localhost:8080/h2-consoleJDBC URL: jdbc:h2:mem:chamados_dbUser: sa | Password: (em branco)🔑 Dados Fictícios de Teste (Data Seeder)A aplicação popula automaticamente o banco de dados na inicialização com as seguintes contas (senhas criptografadas com BCrypt):PerfilE-mailSenhaFunçãoADMINadmin@email.com123456Gerenciamento total do sistemaATENDENTEatendente@email.com123456Atendimento e atualização de chamadosSOLICITANTEcarlos@email.com123456Abertura e consulta de chamados próprios🧪 Executando os Testes UnitáriosPara rodar os testes unitários das regras de negócio (JUnit 5 + Mockito):PowerShell.\mvnw.cmd test
📂 Coleção de Testes (Postman / Insomnia)A coleção de requisições prontas está disponível no diretório docs/:docs/sistema-gestao-chamados.postman_collection.json📄 LicençaEste projeto está sob a licença MIT.
