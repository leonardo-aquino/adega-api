🍷 Adega API – Sistema de Gestão de Funcionários, Produtos e Vendas
📌 Sobre o projeto
Este projeto é uma API RESTful desenvolvida em Spring Boot para gerenciar funcionários, fornecedores, produtos e vendas de uma adega.
O foco é aplicar boas práticas de segurança com JWT, controle de acesso por roles e arquitetura em camadas.

🚀 Tecnologias utilizadas
• 	Java 17
• 	Spring Boot 3
• 	Spring Security + JWT
• 	Hibernate / JPA
• 	PostgreSQL
• 	Lombok
• 	Postman (para testes)

📂 Domínio do Sistema

🔑 Papéis (Roles)
• 	 → acesso total (CRUD completo).
• 	 → acesso administrativo e consultas.
• 	 → acesso restrito (consultas e criação de vendas).
🍷 Tipos de Produto
• 	Vinhos: , , , , 
• 	Cerveja: 
• 	Destilados: , , , , , , , 

🔒 Segurança com JWT
• 	Login em  com CPF e senha.
• 	Retorna um token JWT válido por 24h.
• 	Header em requisições protegidas: Authorization: Bearer <seu_token>
•   Roles controlam acesso com @PreAuthorize nos controllers.
•   Senhas são criptografadas com BCrypt.

📌 Endpoints principais
(Resumo em tabelas já incluído anteriormente)

📌 Exemplos de requisição

🔑 Login
POST /auth/login
Content-Type: application/json

{
  "cpf": "25746310808",
  "senha": "123"
}

🏠 Criar Endereço
POST /enderecos
Authorization: Bearer <token>
Content-Type: application/json


{
  "numero": "123",
  "rua": "Rua das Flores",
  "cep": "12345-678",
  "bairro": "Centro"
}



📦 Criar Fornecedor
POST /fornecedores
Authorization: Bearer <token>
Content-Type: application/json


{
  "nome": "Distribuidora Vinhos Ltda",
  "endereco": "40265808-0948-4637-bcd6-e9a8fd89f563",
  "telefone": "11987654321",
  "email": "contato@vinhosltda.com",
  "produtos": []
}



👤 Criar Funcionário
POST /funcionarios
Authorization: Bearer <token>
Content-Type: application/json


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
Authorization: Bearer <token>
Content-Type: application/json


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
Authorization: Bearer <token>
Content-Type: application/json


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



⚠️ Tratamento de erros – Exemplo
Request inválido:
{
  "nome": "",
  "cpf": "11111111111",
  "salario": -500,
  "cargo": "INVALIDO"
}


Resposta:
{
  "status": 422,
  "mensagem": "Erro de validação",
  "erros": [
    { "campo": "nome", "erro": "Nome não pode ser nulo ou vazio" },
    { "campo": "salario", "erro": "Salário deve ser positivo" },
    { "campo": "cargo", "erro": "Cargo inválido" }
  ]
}



🏗️ Arquitetura em camadas
- Entity → modelo de domínio.
- Repository → persistência e consultas dinâmicas com Specifications.
- Service → lógica de negócio e validações.
- Controller → endpoints REST com segurança.
- DTOs → entrada/saída de dados.
- Validator → regras de negócio antes de persistir.
- Exception Handler → tratamento centralizado de erros.

⚙️ Configuração
- Banco de dados: PostgreSQL.
- Credenciais via variáveis de ambiente (DATASOURCE_URL, DATASOURCE_USERNAME, DATASOURCE_PASSWORD).
- Hibernate configurado para update.
- SQL exibido no console para debug.

📈 Próximos passos
- Desenvolver o front-end da aplicação utilizando React ou Angular, criando uma interface moderna e intuitiva.
- Integrar o front-end com a API para oferecer uma experiência completa ao usuário.
- Criar documentação com Swagger/OpenAPI para facilitar o uso da API.
- Realizar o deploy na nuvem com AWS, garantindo escalabilidade e alta disponibilidade.


💡 Motivação
Este projeto foi desenvolvido como estudo prático de segurança em APIs REST e gestão de usuários com diferentes níveis de permissão.
A ideia é mostrar domínio em Spring Security, JWT e boas práticas de arquitetura, servindo como portfólio para oportunidades na área de backend.



