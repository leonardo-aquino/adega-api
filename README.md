# 🍷 Adega API – Sistema de Gestão de Funcionários, Produtos e Vendas

## 📌 Sobre o projeto
Este projeto é uma **API RESTful** desenvolvida em Spring Boot para gerenciar funcionários, fornecedores, produtos e vendas de uma adega. O foco é aplicar boas práticas de segurança com **JWT**, controle de acesso por **roles** e uma sólida **arquitetura em camadas**.

---

## 🚀 Tecnologias utilizadas
* **Java 17**
* **Spring Boot 3**
* **Spring Security + JWT**
* **Hibernate / JPA**
* **PostgreSQL**
* **Lombok**
* **Postman** (para testes)

---

## 📂 Domínio do Sistema

### 🔑 Papéis (Roles)
* **DONO** → Acesso total (CRUD completo).
* **GERENTE** → Acesso administrativo e consultas.
* **FUNCIONARIO** → Acesso restrito (consultas e criação de vendas).

### 🍷 Tipos de Produto
* **Vinhos:** `VINHO_TINTO`, `VINHO_BRANCO`, `VINHO_ROSE`, `ESPUMANTE`, `PORTO`
* **Cerveja:** `CERVEJA`
* **Destilados:** `LICOR`, `VODKA`, `GIN`, `RUM`, `WHISKY`, `CONHAQUE`, `TEQUILA`, `CACHACA`

---

## 🔒 Segurança com JWT
* Autenticação via CPF e senha.
* Retorna um **token JWT** válido por 24h.
* Header em requisições protegidas: `Authorization: Bearer <token>`.
* Controle de acesso com `@PreAuthorize` nos controllers.
* Senhas criptografadas com **BCrypt**.

---

## 📋 Exemplos de Requisição

### 🔑 Login
`POST /auth/login`
```json
{
  "cpf": "12345678901",
  "senha": "suaSenhaSegura"
}
🏠 Criar Endereço
POST /enderecos

JSON
{
  "numero": "123",
  "rua": "Rua das Flores",
  "cep": "12345-678",
  "bairro": "Centro"
}
📦 Criar Fornecedor
POST /fornecedores

JSON
{
  "nome": "Distribuidora Vinhos Ltda",
  "endereco": "40265808-0948-4637-bcd6-e9a8fd89f563",
  "telefone": "11987654321",
  "email": "contato@vinhosltda.com",
  "produtos": []
}
👤 Criar Funcionário
POST /funcionarios

JSON
{
  "nome": "Carlos",
  "sobrenome": "Silva",
  "cpf": "12345678901",
  "senha": "senhaSegura",
  "salario": 2500.00,
  "cargo": "FUNCIONARIO",
  "endereco": "40265808-0948-4637-bcd6-e9a8fd89f563",
  "idade": "30"
}
🍷 Criar Produto
POST /produtos

JSON
{
  "nome": "Vinho Tinto Reserva",
  "tipoProduto": "VINHO_TINTO",
  "teorAlcoolico": 13.5,
  "volume": "750",
  "vencimento": "01/12/2027",
  "preco": 89.90,
  "fornecedores": ["40265808-0948-4637-bcd6-e9a8fd89f563"],
  "quantidade": 50
}
💰 Criar Venda
POST /vendas

JSON
{
  "funcionarioId": "40265808-0948-4637-bcd6-e9a8fd89f563",
  "itens": [
    {
      "produtoId": "123e4567-e89b-12d3-a456-426614174000",
      "quantidade": 2
    },
    {
      "produtoId": "987e6543-e21b-12d3-a456-426614174999",
      "quantidade": 1
    }
  ]
}
⚠️ Tratamento de Erros
Exemplo de resposta para um Request inválido (Status 422):

Corpo enviado:

JSON
{
  "nome": "",
  "cpf": "11111111111",
  "salario": -500,
  "cargo": "INVALIDO"
}
Resposta do sistema:

JSON
{
  "status": 422,
  "mensagem": "Erro de validação",
  "erros": [
    { "campo": "nome", "erro": "Nome não pode ser nulo ou vazio" },
    { "campo": "salario", "erro": "Salário deve ser positivo" },
    { "campo": "cargo", "erro": "Cargo inválido" }
  ]
}
🏗️ Arquitetura em Camadas
Entity: Modelagem de domínio e tabelas.

Repository: Persistência e consultas dinâmicas com Specifications.

Service: Lógica de negócio, regras e validações.

Controller: Endpoints REST e controle de segurança.

DTOs: Padronização da entrada/saída de dados.

Validator: Regras de negócio aplicadas antes da persistência.

Exception Handler: Tratamento centralizado para respostas HTTP limpas.

📈 Próximos passos
[ ] Desenvolver o front-end utilizando React ou Angular.

[ ] Criar documentação interativa com Swagger/OpenAPI.

[ ] Realizar o deploy na nuvem com AWS/Azure.

💡 Motivação
Este projeto foi desenvolvido como um estudo prático focado em segurança de APIs REST e gestão de acessos hierárquicos. Serve como portfólio para demonstrar domínio em Spring Security, JWT e arquitetura de software profissional no ecossistema Java.

Desenvolvido por Leonardo Nascimento
