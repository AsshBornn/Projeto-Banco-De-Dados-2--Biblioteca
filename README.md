# 📚 Sistema de Biblioteca — Java + JPA Clássico

> Um projeto educacional em **Java 17** utilizando **JPA (Jakarta Persistence API)** com foco em **persistência manual e arquitetura em camadas**.  
> Desenvolvido **sem Spring Boot**, para compreender profundamente o uso do `EntityManager` e o padrão **DAO (Data Access Object)**.

---

## 🚀 Objetivo do Projeto

Criar um **sistema de backend robusto** para gerenciar uma biblioteca, contemplando:

- 👤 Cadastro de **Usuários**
- 📘 Cadastro de **Livros** e **Categorias**
- 🔄 Gerenciamento de **Locações (empréstimos)**
- 💳 Controle de **Pagamentos** associados às locações
- 🧩 Implementação de uma **arquitetura limpa e desacoplada** (Entidade → DAO → Serviço)

---

## 🏛️ Entidades e Relacionamentos

O sistema é formado por cinco entidades principais:

| Entidade  | Descrição | Relacionamentos |
|------------|------------|----------------|
| **Usuario** | Representa o cliente da biblioteca. | 1 → * Locacoes |
| **Categoria** | Gênero do livro (ex: Ficção, Técnico). | 1 → * Livros |
| **Livro** | Item locado, com preço e status. | * → * Locacoes / 1 → Categoria |
| **Locacao** | Representa o empréstimo. | * → Livros / 1 → Usuario / 1 → 1 Pagamento |
| **Pagamento** | Registro financeiro da locação. | 1 → 1 Locacao |

### 🔗 Diagrama (Simplificado)

+-----------+ 1..* 1 +-----------+
| Usuario |----------->| Locacao |
+-----------+ +-----------+
| ^
| | 1..1
| |
+-----------+ 1..* 1 | |
| Categoria |----------->| Livro |
+-----------+ +-----------+
| ^
| | 1..*
| |
1..*| | 1
+-----------+ 1..1 | |
| Pagamento |<-------------+ |
+-----------+ |
|
+ (tb_locacao_livro)

yaml
Copiar código

---

## 💡 Insights Arquiteturais

### 🔹 1. Arquitetura em Camadas

| Camada | Função |
|--------|--------|
| **entity** | Modela as tabelas via anotações JPA (`@Entity`, `@Table`, `@Id`). |
| **dao** | Camada de persistência. CRUD e consultas JPQL. |
| **service** | Lógica de negócio e validação. |
| **util** | Classes auxiliares, como `JPAUtil` para gerenciar `EntityManager`. |

---

### 🔹 2. Padrão **Generic DAO** e **Generic Service**

- `GenericDAO<T>`: Implementa CRUD básico.  
- `GenericService<T, Y>`: Consome o DAO e expõe operações à aplicação.

**Benefício:**  
Evita repetição de código — cada entidade (Livro, Usuario etc.) herda os métodos genéricos e só implementa comportamentos específicos.

---

### 🔹 3. Mapeamentos JPA

- `@OneToMany / @ManyToOne`: (Usuario → Locacao, Categoria → Livro)  
- `@ManyToMany`: (Locacao ↔ Livro via `@JoinTable`)  
- `@OneToOne`: (Locacao ↔ Pagamento)  
- `FetchType.EAGER`: Usado estrategicamente em carregamentos automáticos.

---

### 🔹 4. Lógica de Negócio nos Services

Exemplos:

- 📧 `UsuarioService`: valida e-mails duplicados.  
- 📘 `LivroService`: define status inicial como `DISPONIVEL`.  
- 💰 `Pagamento`: calcula valor total via `locacao.getValorTotal()`.

---

## 🛠️ Tecnologias e Dependências

| Tecnologia | Função |
|-------------|--------|
| **Java 17** | Linguagem principal |
| **Maven** | Gerenciador de dependências |
| **JPA (Jakarta Persistence API)** | Persistência de dados |
| **Hibernate** | Implementação do JPA |
| **PostgreSQL** | Banco de dados |
| **SLF4J** | Logging de queries SQL |

---

## ⚙️ Como Executar o Projeto

### 🧩 Pré-requisitos

- Java 17  
- Maven  
- PostgreSQL (localhost:5432)

### 🗄️ Banco de Dados

Crie o banco **biblioteca** no PostgreSQL:

```sql
CREATE DATABASE biblioteca;
Configuração padrão (persistence.xml):

makefile
Copiar código
Usuário: postgres
Senha: 549276183
⚠️ Atenção: por segurança, altere essa senha antes de publicar o projeto.

🏗️ Build do Projeto
bash
Copiar código
mvn clean install
Isso fará o download das dependências e criará os artefatos.

▶️ Execução
Execute o método main() da classe:

Copiar código
org.primeiroprojetocursooo.projetobancodedados2biblioteca.Main
Isso abrirá o menu interativo no console, com todas as operações CRUD disponíveis.
A aplicação encerra apenas com a opção 0 - Sair.

📖 Exemplos de Fluxo (Main.java)
🔁 1. Cadastrar uma Locação
Solicita um Usuário

Solicita um ou mais Livros

Altera status dos livros para LOCADO

Cria uma Locacao, define LOCADA, e salva via LocacaoService

💳 2. Registrar um Pagamento
Seleciona uma Locação pendente

Cria um Pagamento associado

Calcula automaticamente o valor total

Atualiza status da locação para FINALIZADA

🧠 Conclusão
Este projeto demonstra uma aplicação clássica e didática do JPA, aplicando boas práticas como:

Separação de responsabilidades

Padrões de projeto (DAO, Service)

Uso limpo e manual do EntityManager

Persistência relacional completa e controlada

💬 Um ótimo ponto de partida para quem deseja dominar a base do JPA e Hibernate antes de avançar para frameworks automatizados como Spring Boot.

✨ Autor
Erick Geovane
📧 erickgeovane2002@gmail.com
💻 LinkedIn | GitHub

🧩 “Entender a base é o primeiro passo para dominar o avançado.”
