
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
