# Order Service

Este projeto é um **serviço de pedidos** em **Spring Boot**, que:
- Recebe pedidos (de um sistema externo A),
- Armazena em um banco H2 (in-memory, para teste),
- Calcula o valor total dos itens do pedido,
- Fornece um endpoint para que outro sistema (B) consulte e atualize o status dos pedidos.

---

## Sumário
1. [Descrição Geral](#descrição-geral)
2. [Tecnologias e Versões](#tecnologias-e-versões)
3. [Pré-requisitos](#pré-requisitos)
4. [Como Executar](#como-executar)
5. [Endpoints Principais](#endpoints-principais)
6. [Validação e Tratamento de Erros](#validação-e-tratamento-de-erros)
7 [Possíveis Melhorias](#possíveis-melhorias)

---

## Descrição Geral
Este serviço expõe operações para:
1. **Criar um novo pedido** (*Create an Order*): Recebe um `externalOrderId` único e uma lista de valores de produtos.
2. **Listar todos os pedidos** (*List Orders*): Retorna pedidos armazenados, com `status` e `totalValue`.
3. **Marcar pedido como “enviado ao B”** (*Mark as Sent*): Atualiza o `status` de um pedido específico.

O banco de dados utilizado é o **H2** em memória, configurado no arquivo `application.properties`.  
Foi adicionada uma **unique constraint** em `externalOrderId` para evitar duplicações de pedidos.

---

## Tecnologias e Versões
- **Java**: 17
- **Spring Boot**: 3.4.2
- **Maven**: para gerenciamento de dependências
- **H2 Database** (banco em memória)
- **Spring Data JPA** para persistência
- **Spring Boot Validation** (Bean Validation)
- **REST** para comunicação HTTP

---

## Pré-requisitos
- **Java 17** instalado
- **Maven** (3.x ou superior) instalado

Se estiver usando uma IDE como IntelliJ ou Eclipse, basta importar o projeto Maven que ela se encarrega das dependências.

---

## Endpoints

### 1. Criar Pedido
POST /api/orders Content-Type: application/json

{ "externalOrderId": "A-123", "productValues": [12.50, 7.45, 3.10] }

**Finalidade**: Cria um novo pedido com ID externo `A-123` e soma os valores de `[12.50, 7.45, 3.10]`.

---

### 2. Listar Todos os Pedidos
GET /api/orders

**Finalidade**: Retorna todos os pedidos cadastrados.

---

### 3. Marcar Pedido como “enviado”
POST /api/orders/{externalOrderId}/mark-sent

---
**Finalidade**: Atualiza o `status` do pedido para `SENT_TO_B`.

---

## Validação e Tratamento de Erros

1. **Validação**
    - Usa anotações do Bean Validation (`@NotBlank`, `@NotNull`) no DTO (`CreateOrderRequest`).
    - Se algum campo estiver inválido, a aplicação gera **400 Bad Request** com um corpo JSON indicando os erros de campo.

2. **Erro de Integridade**
    - Se `externalOrderId` já existir, o banco lança exceção de violação de integridade.
    - A aplicação captura e retorna **409 Conflict**.

3. **GlobalExceptionHandler**
    - Classe que intercepta exceções como `MethodArgumentNotValidException` ou `DataIntegrityViolationException` e converte em respostas HTTP adequadas (400 ou 409).

---

## Possíveis Melhorias
- **Segurança**: Adicionar autenticação/authorization (Spring Security) para expor endpoints protegidos.
- **Paginação**: Caso haja grande volume de dados, incluir paginação em `GET /api/orders`.
- **Observabilidade**: Integrar Spring Boot Actuator para health checks, métricas (Micrometer/Prometheus).
- **Docker e Deploy**: Criar um Dockerfile, empacotar e subir em containers. Configurar CI/CD para facilitar o deploy.
- **Testes Unitários/Integração**: Garantir cobertura de testes usando Spring Boot Test + banco H2 para simular cenários.




