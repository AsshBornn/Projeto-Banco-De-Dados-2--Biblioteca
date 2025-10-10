# Projeto-Banco-De-Dados-2--Biblioteca


Projeto Spring Boot com PostgreSQL, Hibernate e JPA
Este é um projeto desenvolvido em Java utilizando o framework Spring Boot, com integração ao banco de dados PostgreSQL através do Hibernate e JPA para mapeamento objeto-relacional.

## 📋 Pré-requisitos
Antes de executar o projeto, certifique-se de ter instalado:

+ Java 17 ou superior

+ Maven 3.6 ou superior

+ PostgreSQL 12 ou superior

+ Git

## 🚀 Tecnologias Utilizadas

+ Java 17

+ Spring Boot 3.x

+ Spring Data JPA

+ Hibernate

+ PostgreSQL

+ Maven

+ Spring Web

## 📦 Estrutura do Projeto
text
</config></pre>src/
├── main/
│   ├── java/
│   │   └── com/
│   │       └── exemplo/
│   │           └── projeto/
│   │               ├── ProjetoApplication.java
│   │
<pre><config>
  

## 📚 Sistema de Gerenciamento de Biblioteca
Um sistema CRUD (Create, Read, Update, Delete) para gerenciamento de biblioteca desenvolvido com Java Spring Boot, PostgreSQL, Hibernate e JPA.

## 📋 Funcionalidades
✅ Cadastro de Livros - Adicionar novos livros ao acervo

📖 Consulta de Livros - Listar e buscar livros disponíveis

✏️ Atualização de Livros - Modificar informações dos livros

🗑️ Exclusão de Livros - Remover livros do acervo

👥 Gerenciamento de Autores - CRUD completo de autores

🔍 Busca Avançada - Pesquisar livros por título, autor, gênero

## 🚀 Tecnologias Utilizadas
+ Java 17

+ Spring Boot 3.x

+ Spring Data JPA

+ Hibernate

+ PostgreSQL

+ Maven

+ Spring Web

+ Spring Validation

## 📦 Estrutura do Projeto
text
src/
├── main/
│   ├── java/
│   │   └── com/
│   │       └── biblioteca/
│   │           ├── BibliotecaApplication.java
│   │           ├── controller/
│   │           │   ├── LivroController.java
│   │           │   └── AutorController.java
│   │           ├── model/
│   │           │   ├── Livro.java
│   │           │   └── Autor.java
│   │           ├── repository/
│   │           │   ├── LivroRepository.java
│   │           │   └── AutorRepository.java
│   │           ├── service/
│   │           │   ├── LivroService.java
│   │           │   └── AutorService.java
│   │           └── dto/
│   │               ├── LivroDTO.java
│   │               └── AutorDTO.java
│   └── resources/
│       ├── application.properties
│       └── data.sql
└── test/
    └── java/
        └── com/
            └── biblioteca/
## 🗃️ Modelo de Dados
  
Entidade Livro
### java
- id: Long
- titulo: String
- isbn: String
- anoPublicacao: Integer
- editora: String
- genero: String
- disponivel: Boolean
- autor: Autor
Entidade Autor
java
- id: Long
- nome: String
- nacionalidade: String
- dataNascimento: LocalDate
- livros: List<Livro>
⚙️ Configuração do Projeto
1. Clone o repositório
bash
git clone https://github.com/seu-usuario/biblioteca-crud.git
cd biblioteca-crud
2. Configuração do Banco de Dados
Crie um banco de dados PostgreSQL chamado biblioteca_db e configure o application.properties:

properties
# application.properties
spring.datasource.url=jdbc:postgresql://localhost:5432/biblioteca_db
spring.datasource.username=seu-usuario
spring.datasource.password=sua-senha

# JPA/Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

# Server
server.port=8080
3. Instalação e Execução
bash
# Compilar o projeto
mvn clean compile

# Executar a aplicação
mvn spring-boot:run

# Ou executar o JAR
mvn clean package
java -jar target/biblioteca-0.0.1-SNAPSHOT.jar
📡 Endpoints da API
Livros
Método	Endpoint	Descrição
GET	/api/livros	Listar todos os livros
GET	/api/livros/{id}	Buscar livro por ID
GET	/api/livros/search?titulo={titulo}	Buscar livros por título
POST	/api/livros	Cadastrar novo livro
PUT	/api/livros/{id}	Atualizar livro
DELETE	/api/livros/{id}	Excluir livro
Autores
Método	Endpoint	Descrição
GET	/api/autores	Listar todos os autores
POST	/api/autores	Cadastrar novo autor
PUT	/api/autores/{id}	Atualizar autor
DELETE	/api/autores/{id}	Excluir autor
🧪 Testando a API
Exemplo de requisição para criar um livro:
bash
curl -X POST http://localhost:8080/api/livros \
  -H "Content-Type: application/json" \
  -d '{
    "titulo": "Dom Casmurro",
    "isbn": "978-85-7232-144-9",
    "anoPublicacao": 1899,
    "editora": "Livraria Garnier",
    "genero": "Romance",
    "disponivel": true,
    "autorId": 1
  }'
Exemplo de requisição para criar um autor:
bash
curl -X POST http://localhost:8080/api/autores \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "Machado de Assis",
    "nacionalidade": "Brasileira",
    "dataNascimento": "1839-06-21"
  }'
🗂️ Script do Banco de Dados
O arquivo data.sql na pasta src/main/resources contém dados iniciais para teste:

sql
INSERT INTO autor (nome, nacionalidade, data_nascimento) VALUES 
('Machado de Assis', 'Brasileira', '1839-06-21'),
('Clarice Lispector', 'Brasileira', '1920-12-10'),
('Jorge Amado', 'Brasileira', '1912-08-10');
🧪 Testes
Para executar os testes unitários e de integração:

bash
mvn test
👥 Contribuição
Faça o fork do projeto

Crie uma branch para sua feature (git checkout -b feature/AmazingFeature)

Commit suas mudanças (git commit -m 'Add some AmazingFeature')

Push para a branch (git push origin feature/AmazingFeature)

Abra um Pull Request

📄 Licença
Este projeto está sob a licença MIT. Veja o arquivo LICENSE para mais detalhes.

📞 Contato
Seu Nome - seu.email@example.com

Link do Projeto: https://github.com/seu-usuario/biblioteca-crud
