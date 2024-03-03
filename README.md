# Gerenciador de Pet Shop

Este é um projeto de um sistema para gerenciamento de um Pet Shop. Ele inclui funcionalidades para o gerenciamento de clientes, produtos e serviços oferecidos pelo pet shop.

## Tecnologias utilizadas

- Java
- Spring Framework
- Spring Boot
- Spring Data JPA
- PostgreSQL
- RESTful API
- Postman
- Ajax

## Funcionalidades

### Requisições Client via Postman

- **Listar todos os clientes:**  Endpoint: `GET /client/clients` findAll()
  
- **Buscar cliente por ID:** - Endpoint: `GET /client/id/{id}`  
  
- **Buscar clientes por tipo de animal:**  - Endpoint: `GET /client/byTipoAnimal/{tipoAnimal}`

- **Criar um novo cliente:** - Endpoint: `POST /client/user`
  
- **Atualizar um cliente existente:** - Endpoint: `PUT /client/edit/{id}`
  
- **Excluir um cliente:** Endpoint: `DELETE /client/idDelete/{id}`

- ### Requisições de serviços via Postman

- **Listar todos os serviços oferecidos pelo petshop:**  Endpoint: `GET /tarefa/allServices` findAll()
  
- **Buscar serviço por ID:** - Endpoint: `GET /tarefa/id/{id}`  

- **Criar um novo serviço:** - Endpoint: `POST /tarefa/createServ`
  
- **Atualizar um serviço existente:** - Endpoint: `PUT /tarefa/edit/{id}`
  
- **Excluir um serviço:** Endpoint: `DELETE /tarefa/delete/{id}`

- ### Requisições de Produtos via Postman

- **Listar todos os produtos oferecidos pelo petshop:**  Endpoint: `GET /product/allProducts` findAll()
  
- **Buscar produto por ID:** - Endpoint: `GET /product/id/{id}`  

- **Criar um novo produto:** - Endpoint: `POST /product/newProduct`
  
- **Atualizar um produto existente:** - Endpoint: `PUT /product/edit/{id}`
  
- **Excluir um produto:** Endpoint: `DELETE /product/deleteProduct/{id}`

- - ### Requisições da Ordem de Serviço via Postman

- **Listar todas as Ordens de Serviço oferecidos pelo petshop:**  Endpoint: `GET /order/findAll` findAll()
  
- **Buscar Ordem de Serviço por ID:** - Endpoint: `GET /order/findOrderById/{id}`  

- **Criar uma nova Ordem de Serviço:** - Endpoint: `POST /order/createOrder/{clientId}`
  
- **Atualizar uma Ordem de Serviço existente:** - Endpoint: `PUT /order/updateOrderById/{id}`
  
- **Excluir uma Ordem de Serviço:** Endpoint: `DELETE /order/deleteOrderById/{id}`


### Formulário de Cadastro de Cliente

Além das requisições via Postman, o sistema oferece um formulário web para cadastrar novos clientes, excluir e altera-los: 

## Estrutura do Projeto

O projeto está organizado em camadas:

- **Controller**: Responsável pelo tratamento das requisições HTTP com o Postman.
- **Service**: Lógica de negócio e processamento das operações.
- **Repository**: Interação com o banco de dados através do Spring Data JPA.
- **Model**: Entidades e classes de domínio do sistema.

Projeto ainda em andamento para fins de estudo e prática!

