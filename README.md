# Gerenciador de Pet Shop

Este é um projeto de um sistema para gerenciamento de um Pet Shop. Ele inclui funcionalidades para o gerenciamento de clientes, animais de estimação e serviços oferecidos pelo pet shop.

## Tecnologias utilizadas

- Java
- Spring Framework
- Spring Boot
- Spring Data JPA
- H2 Database
- RESTful API
- Postman
- Ajax

## Funcionalidades

### Requisições via Postman

- **Listar todos os clientes:**
  - Endpoint: `GET /client/clinets` findAll()
  - Descrição: Retorna todos os clientes cadastrados.
  
- **Buscar cliente por ID:**
  - Endpoint: `GET /client/id/{id}`
  - Descrição: Retorna os detalhes de um cliente com base no ID fornecido.
  
- **Buscar clientes por tipo de animal:**
  - Endpoint: `GET /client/byTipoAnimal/{tipoAnimal}`
  - Descrição: Retorna os clientes que possuem animais do tipo especificado.
  
- **Criar um novo cliente:**
  - Endpoint: `POST /client/user`
  - Descrição: Cria um novo cliente com base nos dados fornecidos.
  - Utilização: Enviar uma requisição POST no Postman com os dados do cliente no corpo da requisição.

### Formulário de Cadastro de Cliente

Além das requisições via Postman, o sistema oferece um formulário web para cadastrar novos clientes:

- **Cadastro de Cliente:**
  - Página HTML: `index.html` (por enquanto)
  - Descrição: Um formulário web que permite inserir os dados de um novo cliente e submetê-los para o backend.

## Estrutura do Projeto

O projeto está organizado em camadas:

- **Controller**: Responsável pelo tratamento das requisições HTTP com o Postman.
- **Service**: Lógica de negócio e processamento das operações.
- **Repository**: Interação com o banco de dados através do Spring Data JPA.
- **Model**: Entidades e classes de domínio do sistema.

Projeto ainda em andamento para fins de estudo e prática!


