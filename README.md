# Gerenciador de Pet Shop

Sistema para gerenciamento de clientes, produtos e serviços de um Pet Shop. Oferece API RESTful para interações com o banco de dados e um formulário web.

## Tecnologias

- **Java** | **Spring Boot** | **PostgreSQL** 
- **Postman** | **Ajax** | **JUnit** | **Mockito**

## Funcionalidades

- **Clientes**: Cadastro, edição, exclusão e busca (por ID ou tipo de animal).
- **Serviços**: Gerenciamento completo de serviços.
- **Produtos**: Cadastro, edição, listagem e exclusão.
- **Ordens de Serviço**: Gerenciamento de ordens associadas a clientes.

## Exemplos de Endpoints

- **Clientes**:  
  `GET /client/clients` – Lista todos os clientes  
  `POST /client/user` – Cria um novo cliente

- **Serviços**:  
  `GET /tarefa/allServices` – Lista todos os serviços  
  `POST /tarefa/createServ` – Cria um novo serviço

- **Produtos**:  
  `GET /product/allProducts` – Lista todos os produtos  
  `POST /product/newProduct` – Cria um novo produto

- **Ordens de Serviço**:  
  `GET /order/findAll` – Lista todas as ordens de serviço  
  `POST /order/createOrder/{clientId}` – Cria uma nova ordem de serviço

## Estrutura do Projeto

O projeto segue o padrão **MVC**:

## Como Rodar

1. Clone este repositório:  
   `git clone https://github.com/MateusGildev/GerenciadorPetshop.git`
   
2. Navegue até a pasta do projeto:  
   `cd GerenciadorPetshop`

3. Execute a aplicação:  
   `./mvnw spring-boot:run`

4. Acesse a API em [http://localhost:8080](http://localhost:8080)


