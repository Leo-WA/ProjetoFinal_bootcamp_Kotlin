# Microserviço de Autenticação para Academia

## Descrição
Este projeto implementa um microserviço de autenticação para uma academia, utilizando Kotlin e Spring Boot. O serviço gerencia o registro de usuários, controla o status dos pagamentos de mensalidades e notifica a empresa sobre pagamentos atrasados.

## Funcionalidades

- **Registro de Usuários**: Permite a criação de novos usuários com informações básicas como nome, email e senha.
- **Gerenciamento de Pagamentos**: Mantém o controle sobre os pagamentos dos usuários, incluindo datas de vencimento e status de pagamento.
- **Notificação de Atrasos**: Identifica usuários com pagamentos atrasados e notifica a administração da academia.

## Tecnologias Utilizadas

- **Kotlin**: Linguagem de programação utilizada para o desenvolvimento do serviço.
- **Spring Boot**: Framework para facilitar a configuração e o desenvolvimento de aplicações em Java/Kotlin.
- **Spring Data JPA**: Para a persistência dos dados em um banco de dados.
- **Spring Security**: Para a autenticação e segurança dos usuários.

## Estrutura do Projeto

- **User (Entidade)**: Representa os usuários da academia.
- **Payment (Entidade)**: Representa os registros de pagamento dos usuários.
- **UserRepository (Repositório)**: Interface para operações de banco de dados relacionadas aos usuários.
- **PaymentRepository (Repositório)**: Interface para operações de banco de dados relacionadas aos pagamentos.
- **AuthenticationService (Serviço)**: Lida com a lógica de negócios para registro e autenticação dos usuários.
- **UserController (Controlador REST)**: Controlador que expõe endpoints REST para o gerenciamento de usuários.

## Configuração e Instalação

**Pré-requisitos**: JDK 11+, Maven ou Gradle.

**Instalação**: Clone o repositório e execute o projeto usando um IDE de sua escolha ou pela linha de comando com Maven/Gradle.

## Uso

Para registrar um novo usuário, faça uma requisição POST para `/users/register` com um payload contendo `name`, `email` e `password`.

## Segurança

Este projeto utiliza uma função simplificada para a criptografia de senhas. Para um ambiente de produção, é recomendado implementar um método mais robusto.

## Contribuições

Contribuições são bem-vindas. Por favor, envie um Pull Request ou abra uma Issue para discussões relacionadas a melhorias ou correções.
