🚀 README: Projeto de Sistema de Biblioteca (Java + JPA Clássico)
Este documento descreve a arquitetura, os objetivos e os principais insights do projeto de sistema de gerenciamento de biblioteca, desenvolvido em Java com foco em persistência de dados usando JPA (Jakarta Persistence API) e uma arquitetura em camadas.

O projeto foi intencionalmente desenvolvido sem as automações do Spring Boot (como Spring Data JPA) para focar no aprendizado dos mecanismos internos do JPA, como o gerenciamento manual do EntityManager e a implementação do padrão Data Access Object (DAO).

🎯 1. Objetivo do Projeto
O objetivo principal é criar um sistema de backend robusto para gerenciar as operações de uma biblioteca. Isso inclui:

Cadastro de Usuários, Livros e Categorias.

Gerenciamento do ciclo de vida de Locações (empréstimos) de livros.

Controle de Pagamentos associados a cada locação.

Implementação de uma arquitetura limpa, desacoplada e de fácil manutenção, separando as responsabilidades em camadas distintas (Entidade, DAO, Serviço).

🏛️ 2. Entidades Principais e Relacionamentos
O núcleo do sistema é definido por cinco entidades principais que se relacionam para formar o modelo de dados:

Usuario: O cliente da biblioteca. (Possui muitas Locacoes).

Categoria: O gênero do livro (ex: "Ficção", "Técnico"). (Possui muitos Livros).

Livro: O item a ser locado, com preço e status. (Pertence a uma Categoria e pode estar em muitas Locacoes).

Locacao: A transação central. Representa o empréstimo de um ou mais livros por um usuário. (Pertence a um Usuario, possui muitos Livros e tem um Pagamento).

Pagamento: O registro financeiro vinculado a uma locação. (Pertence a uma Locacao).

Diagrama de Relacionamentos (Simplificado):

+-----------+ 1..* 1 +-----------+
|  Usuario  |----------->|  Locacao  |
+-----------+            +-----------+
                           |   ^
                           |   | 1..1
                           |   |
+-----------+ 1..* 1   |   |
| Categoria |----------->|  Livro    |
+-----------+            +-----------+
                           |   ^
                           |   | 1..*
                           |   |
                         1..*|   | 1
+-----------+ 1..1         |   |
| Pagamento |<-------------+   |
+-----------+                |
                             |
                             + (tb_locacao_livro)
💡 3. Principais Insights e Padrões de Arquitetura
A análise do código revela uma arquitetura madura que aplica diversos padrões de design para garantir baixo acoplamento, alta coesão e reutilização de código.

Insight 1: Arquitetura em Camadas (Layered Architecture)
O projeto é estritamente dividido em camadas, cada uma com uma responsabilidade clara:

entity: Camada de Domínio. Contém os POJOs (Plain Old Java Objects) que mapeiam as tabelas do banco de dados usando anotações JPA (@Entity, @Table, @Id, etc.).

DAO (Data Access Object): Camada de Persistência. É a única camada que se comunica diretamente com o banco de dados. Responsável por todas as operações CRUD (Create, Read, Update, Delete) e consultas JPQL.

services: Camada de Negócio. Orquestra as operações e contém toda a lógica de negócio e validação (ex: "um usuário não pode ter e-mail duplicado", "um livro novo sempre começa como DISPONIVEL").

util: Camada de Utilidade. Fornece classes auxiliares, como o JPAUtil para gerenciar o EntityManager.

Insight 2: Padrão Generic DAO e Generic Service
Este é o insight arquitetural mais importante do projeto.

GenericDAO<T>: Uma classe genérica que implementa todos os métodos CRUD básicos (salvar, atualizar, excluir, buscarPorId, listar).

GenericService<T, Y>: Uma classe genérica que consome um GenericDAO e expõe esses métodos para a camada de aplicação.

Benefício: Os DAOs (LivroDAO, UsuarioDAO, etc.) e os Serviços (LivroService, UsuarioService, etc.) herdam toda a funcionalidade CRUD, evitando repetição de código. Eles só precisam implementar os métodos que são específicos de sua entidade (ex: LivroDAO.buscarPorTitulo()).

Insight 3: Mapeamento Relacional (JPA) Detalhado
O projeto demonstra um uso correto e completo dos mapeamentos do JPA para modelar o domínio:

@OneToMany / @ManyToOne: Relação padrão (ex: Usuario -> Locacao, Categoria -> Livro).

@ManyToMany: Usado na relação Locacao <-> Livro, que corretamente gera uma tabela de junção (@JoinTable(name = "tb_locacao_livro")).

@OneToOne: Usado na relação Locacao <-> Pagamento, garantindo que cada locação tenha um único pagamento.

FetchType.EAGER: Usado estrategicamente para carregar dados relacionados (ex: carregar os livros de uma Categoria ou as locações de um Usuario) automaticamente.

Insight 4: Lógica de Negócio e Validação nos Services
A camada de Serviço é usada corretamente para aplicar regras de negócio antes de persistir os dados:

Validação: Os métodos isValid() em cada serviço (UsuarioService, CategoriaService, etc.) verificam se os dados estão corretos (ex: campos em branco) e se violam regras de unicidade (ex: UsuarioService.existeEmail()).

Regras de Negócio: A lógica é encapsulada onde faz sentido.

Ex 1: LivroService, ao salvar, define automaticamente o status como LivroStatus.DISPONIVEL.

Ex 2: Locacao possui o método getValorTotal(), que calcula o custo total com base nos dias e no preço dos livros.

Ex 3: Pagamento, no método setLocacao(), chama locacao.getValorTotal() para persistir o valor calculado no banco.

🛠️ 4. Tecnologias e Dependências
Conforme definido no pom.xml, as principais tecnologias são:

Java 17

Maven (para gerenciamento de dependências)

JPA (Jakarta Persistence API): A especificação (jakarta.persistence-api).

Hibernate: A implementação do JPA (hibernate-core).

PostgreSQL: O driver de banco de dados (postgresql).

SLF4J: Para logging das queries SQL no console.

⚙️ 5. Como Executar o Projeto
Este é um projeto Maven e usa o persistence.xml para configuração.

Pré-requisitos:

Java 17 (conforme pom.xml).

Maven.

PostgreSQL rodando (preferencialmente em localhost:5432).

Configurar o Banco de Dados:

Conforme o persistence.xml, você precisa criar um banco de dados no PostgreSQL chamado biblioteca.

O usuário deve ser postgres e a senha 549276183.

⚠️ AVISO DE SEGURANÇA: Esta senha está exposta no persistence.xml. Recomenda-se alterar essa senha ou usar variáveis de ambiente antes de compartilhar o projeto.

Construir o Projeto (Build):

Navegue até a pasta raiz do projeto (onde está o pom.xml) e execute:

Bash

mvn clean install
Isso baixará todas as dependências (Hibernate, driver PostgreSQL, etc.).

Executar a Aplicação:

O projeto é uma aplicação de console. Para rodar, basta executar o método main da classe org.primeiroprojetocursooo.projetobancodedados2biblioteca.Main.

Isso iniciará o menu interativo no console, permitindo que você chame todos os serviços de CRUD e regras de negócio.

Observação: A aplicação só termina quando você escolhe a opção '0 - Sair'. Para liberar os recursos do banco de dados corretamente, a chamada JPAUtil.close() deve ser executada quando a aplicação for encerrada.

🚀 6. Exemplos de Uso (Fluxo do Main.java)
O arquivo Main.java fornecido é uma aplicação de console completa que demonstra como os serviços são orquestrados para executar as regras de negócio. Os fluxos mais importantes são:

Fluxo 1: Cadastrar uma Locação (Transação complexa)
O método cadastrarLocacao() demonstra uma transação que envolve múltiplas entidades:

Solicita um Usuário (buscado via USUARIO_SERVICE).

Solicita um ou mais Livros (buscados via LIVRO_SERVICE).

Para cada livro selecionado, o status do livro é alterado para LivroStatus.LOCADO.

Cria uma nova Locacao, define seu status para LocacaoStatus.LOCADA, associa o usuário e os livros.

Salva a Locacao (via LOCACAO_SERVICE). O JPA/Hibernate gerencia a atualização dos livros e a inserção na tabela de junção (tb_locacao_livro).

Fluxo 2: Registrar um Pagamento (Transação de finalização)
O método cadastrarPagamento() demonstra o fechamento de um ciclo de negócio:

Lista as locações pendentes e solicita o ID de uma Locacao (via LOCACAO_SERVICE).

Cria um novo objeto Pagamento.

Ao usar pagamento.setLocacao(locacao), a lógica interna da entidade Pagamento é acionada, calculando o valor total com base nos dias e preço dos livros da locação.

Salva o Pagamento (via PAGAMENTO_SERVICE).

O status da Locacao original é então atualizado para LocacaoStatus.FINALIZADA e salvo (via LOCACAO_SERVICE), completando o processo.
