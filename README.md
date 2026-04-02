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
• 	DONO → acesso total (CRUD completo).
• 	GERENTE → acesso administrativo e consultas.
• 	FUNCIONARIO → acesso restrito (consultas e criação de vendas).
🍷 Tipos de Produto
• 	Vinhos: VINHO_TINTO, VINHO_BRANCO, VINHO_ROSE, ESPUMANTE, PORTO
• 	Cerveja: CERVEJA
• 	Destilados: LICOR, VODKA, GIN, RUM, WHISKY, CONHAQUE, TEQUILA, CACHACA

🔒 Segurança com JWT
• 	Login em  com CPF e senha.
• 	Retorna um token JWT válido por 24h.
• 	Header em requisições protegidas:

• 	Roles controlam acesso com  nos controllers.
• 	Senhas são criptografadas com BCrypt.

📌 Endpoints principais
(Resumo em tabelas já incluído anteriormente)

📋 Exemplos de requisição
🔑 Login



🏠 Criar Endereço



📦 Criar Fornecedor



👤 Criar Funcionário



🍷 Criar Produto



💰 Criar Venda



⚠️ Tratamento de erros – Exemplo
Request inválido:

Resposta:


🏗️ Arquitetura em camadas
• 	Entity → modelo de domínio.
• 	Repository → persistência e consultas dinâmicas com Specifications.
• 	Service → lógica de negócio e validações.
• 	Controller → endpoints REST com segurança.
• 	DTOs → entrada/saída de dados.
• 	Validator → regras de negócio antes de persistir.
• 	Exception Handler → tratamento centralizado de erros.

⚙️ Configuração
• 	Banco de dados: PostgreSQL.
• 	Credenciais via variáveis de ambiente (, , ).
• 	Hibernate configurado para .
• 	SQL exibido no console para debug.

📈 Próximos passos
• 	Desenvolver o front-end da aplicação utilizando React ou Angular, criando uma interface moderna e intuitiva.
• 	Integrar o front-end com a API para oferecer uma experiência completa ao usuário.
• 	Criar documentação com Swagger/OpenAPI para facilitar o uso da API.
• 	Realizar o deploy na nuvem com AWS, garantindo escalabilidade e alta disponibilidade.

💡 Motivação
Este projeto foi desenvolvido como estudo prático de segurança em APIs REST e gestão de usuários com diferentes níveis de permissão.
A ideia é mostrar domínio em Spring Security, JWT e boas práticas de arquitetura, servindo como portfólio para oportunidades na área de backend.
