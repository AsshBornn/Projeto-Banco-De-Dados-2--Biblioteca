package org.primeiroprojetocursooo.projetobancodedados2biblioteca;

import org.primeiroprojetocursooo.projetobancodedados2biblioteca.entity.*;
import org.primeiroprojetocursooo.projetobancodedados2biblioteca.entity.enums.*;
import org.primeiroprojetocursooo.projetobancodedados2biblioteca.services.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Classe principal do sistema de Biblioteca.
 * <p>
 * Contém menus para gerenciar categorias, livros, usuários, locações e pagamentos.
 * Este Main funciona como uma aplicação console, utilizando Services para persistência via JPA.
 * Boas práticas:
 * - Uso de Service para manipular entidades (DAO encapsulado).
 * - Scanner e loops de menu controlados.
 * - Tratamento simples de exceções, especialmente datas e IDs inválidos.
 */
public class Main {

    // Scanner compartilhado para toda a aplicação
    private static final Scanner SCANNER = new Scanner(System.in);

    // Serviços (cada Service encapsula operações CRUD e consultas)
    private static final CategoriaService CATEGORIA_SERVICE = new CategoriaService();
    private static final LivroService LIVRO_SERVICE = new LivroService();
    private static final UsuarioService USUARIO_SERVICE = new UsuarioService();
    private static final LocacaoService LOCACAO_SERVICE = new LocacaoService();
    private static final PagamentoService PAGAMENTO_SERVICE = new PagamentoService();

    public static void main(String[] args) {

        while (true) {
            // Construção do menu principal
            System.out.print("""
                    ==== MENU PRINCIPAL ====
                    1 - Categoria
                    2 - Livro
                    3 - Usuário
                    4 - Locação
                    5 - Pagamento
                    0 - Sair
                    Escolha: """);

            int opcao;
            try {
                opcao = Integer.parseInt(SCANNER.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida! Digite um número.");
                continue;
            }

            switch (opcao) {
                case 1 -> menuCategoria();
                case 2 -> menuLivro();
                case 3 -> menuUsuario();
                case 4 -> menuLocacao();
                case 5 -> menuPagamento();
                case 0 -> {
                    System.out.println("Saindo...");
                    return;
                }
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    // ================= MENU CATEGORIA =================

    /**
     * Menu para operações de Categoria.
     */
    private static void menuCategoria() {
        System.out.print("""
                ==== MENU CATEGORIA ====
                1 - Listar
                2 - Cadastrar
                3 - Atualizar
                4 - Excluir
                5 - Buscar por descrição
                Escolha: """);

        int opcao = Integer.parseInt(SCANNER.nextLine());

        switch (opcao) {
            case 1 -> listarCategorias();
            case 2 -> cadastrarCategoria();
            case 3 -> atualizarCategoria();
            case 4 -> excluirCategoria();
            case 5 -> buscarCategoriaPorDescricao();
            default -> System.out.println("Opção inválida!");
        }
    }

    private static void listarCategorias() {
        List<Categoria> categorias = CATEGORIA_SERVICE.listar();
        System.out.println("\n--- Categorias ---");
        categorias.forEach(c -> System.out.println(c.getId() + " - " + c.getDescricao()));
    }

    private static void cadastrarCategoria() {
        System.out.print("Descrição: ");
        String descricao = SCANNER.nextLine();

        Categoria categoria = new Categoria();
        categoria.setDescricao(descricao);

        CATEGORIA_SERVICE.salvar(categoria);
        System.out.println("Categoria cadastrada com sucesso!");
    }

    private static void atualizarCategoria() {
        listarCategorias();
        System.out.print("ID da Categoria para atualizar: ");
        int id = Integer.parseInt(SCANNER.nextLine());

        Categoria categoria = CATEGORIA_SERVICE.buscarPorId(id);
        if (categoria == null) {
            System.out.println("Categoria não encontrada!");
            return;
        }

        System.out.print("Nova descrição: ");
        categoria.setDescricao(SCANNER.nextLine());

        CATEGORIA_SERVICE.atualizar(categoria);
        System.out.println("Categoria atualizada com sucesso!");
    }

    private static void excluirCategoria() {
        listarCategorias();
        System.out.print("ID da Categoria para excluir: ");
        int id = Integer.parseInt(SCANNER.nextLine());

        CATEGORIA_SERVICE.excluir(id);
        System.out.println("Categoria excluída com sucesso!");
    }

    private static void buscarCategoriaPorDescricao() {
        System.out.print("Digite a descrição: ");
        String descricao = SCANNER.nextLine();

        List<Categoria> categorias = CATEGORIA_SERVICE.buscarPorDescricao(descricao);
        categorias.forEach(c -> System.out.println(c.getId() + " - " + c.getDescricao()));
    }

    // ================= MENU LIVRO =================

    private static void menuLivro() {
        System.out.print("""
                ==== MENU LIVRO ====
                1 - Listar
                2 - Cadastrar
                3 - Atualizar
                4 - Excluir
                5 - Buscar por título
                Escolha: """);

        int opcao = Integer.parseInt(SCANNER.nextLine());

        switch (opcao) {
            case 1 -> listarLivros();
            case 2 -> cadastrarLivro();
            case 3 -> atualizarLivro();
            case 4 -> excluirLivro();
            case 5 -> buscarLivroPorTitulo();
            default -> System.out.println("Opção inválida!");
        }
    }

    private static void listarLivros() {
        List<Livro> livros = LIVRO_SERVICE.listar();
        System.out.println("\n--- Livros ---");
        livros.forEach(l -> System.out.println(l.getId() + " - " + l.getTitulo() + " [" + l.getStatus() + "]"));
    }

    private static void cadastrarLivro() {
        System.out.print("Título: ");
        String titulo = SCANNER.nextLine();

        System.out.print("Valor por dia: ");
        double preco = Double.parseDouble(SCANNER.nextLine());

        listarCategorias();
        System.out.print("ID da Categoria: ");
        int idCategoria = Integer.parseInt(SCANNER.nextLine());

        Categoria categoria = CATEGORIA_SERVICE.buscarPorId(idCategoria);

        Livro livro = new Livro();
        livro.setTitulo(titulo);
        livro.setCategoria(categoria); // Relacionamento ManyToOne
        livro.setStatus(LivroStatus.DISPONIVEL); // Enum de status
        livro.setPreco(preco);

        LIVRO_SERVICE.salvar(livro);
        System.out.println("Livro cadastrado com sucesso!");
    }

    private static void atualizarLivro() {
        listarLivros();
        System.out.print("ID do Livro: ");
        int id = Integer.parseInt(SCANNER.nextLine());

        Livro livro = LIVRO_SERVICE.buscarPorId(id);
        if (livro == null) {
            System.out.println("Livro não encontrado!");
            return;
        }

        System.out.print("Novo título: ");
        livro.setTitulo(SCANNER.nextLine());

        listarCategorias();
        System.out.print("Nova Categoria ID: ");
        int idCategoria = Integer.parseInt(SCANNER.nextLine());

        livro.setCategoria(CATEGORIA_SERVICE.buscarPorId(idCategoria));
        LIVRO_SERVICE.atualizar(livro);

        System.out.println("Livro atualizado com sucesso!");
    }

    private static void excluirLivro() {
        listarLivros();
        System.out.print("ID do Livro: ");
        int id = Integer.parseInt(SCANNER.nextLine());

        LIVRO_SERVICE.excluir(id);
        System.out.println("Livro excluído com sucesso!");
    }

    private static void buscarLivroPorTitulo() {
        System.out.print("Digite o título: ");
        String titulo = SCANNER.nextLine();

        List<Livro> livros = LIVRO_SERVICE.buscarPorTitulo(titulo);
        livros.forEach(l -> System.out.println(l.getId() + " - " + l.getTitulo() + " [" + l.getStatus() + "]"));
    }

    // ================= MENU USUÁRIO =================

    private static void menuUsuario() {
        System.out.print("""
                ==== MENU USUÁRIO ====
                1 - Listar
                2 - Cadastrar
                3 - Atualizar
                4 - Excluir
                Escolha: """);

        int opcao = Integer.parseInt(SCANNER.nextLine());

        switch (opcao) {
            case 1 -> listarUsuarios();
            case 2 -> cadastrarUsuario();
            case 3 -> atualizarUsuario();
            case 4 -> excluirUsuario();
            default -> System.out.println("Opção inválida!");
        }
    }

    private static void listarUsuarios() {
        List<Usuario> usuarios = USUARIO_SERVICE.listar();
        System.out.println("\n--- Usuários ---");
        usuarios.forEach(u -> System.out.println(u.getId() + " - " + u.getNome() + " (" + u.getEmail() + ")"));
    }

    private static void cadastrarUsuario() {
        System.out.print("Nome: ");
        String nome = SCANNER.nextLine();

        System.out.print("E-mail: ");
        String email = SCANNER.nextLine();

        Usuario usuario = new Usuario();
        usuario.setNome(nome);
        usuario.setEmail(email);

        USUARIO_SERVICE.salvar(usuario);
        System.out.println("Usuário cadastrado com sucesso!");
    }

    private static void atualizarUsuario() {
        listarUsuarios();
        System.out.print("ID do Usuário: ");
        int id = Integer.parseInt(SCANNER.nextLine());

        Usuario usuario = USUARIO_SERVICE.buscarPorId(id);
        if (usuario == null) {
            System.out.println("Usuário não encontrado!");
            return;
        }

        System.out.print("Novo nome: ");
        usuario.setNome(SCANNER.nextLine());

        System.out.print("Novo e-mail: ");
        usuario.setEmail(SCANNER.nextLine());

        USUARIO_SERVICE.atualizar(usuario);
        System.out.println("Usuário atualizado com sucesso!");
    }

    private static void excluirUsuario() {
        listarUsuarios();
        System.out.print("ID do Usuário: ");
        int id = Integer.parseInt(SCANNER.nextLine());

        USUARIO_SERVICE.excluir(id);
        System.out.println("Usuário excluído com sucesso!");
    }

    // ================= MENU LOCAÇÃO =================

    /**
     * Menu para operações de Locação.
     */
    private static void menuLocacao() {
        System.out.print("""
                ==== MENU LOCAÇÃO ====
                1 - Listar
                2 - Cadastrar
                3 - Finalizar Locação
                4 - Excluir
                5 - Buscar por status
                6 - Buscar por período
                Escolha: """);

        int opcao = Integer.parseInt(SCANNER.nextLine());

        switch (opcao) {
            case 1 -> listarLocacoes();
            case 2 -> cadastrarLocacao();
            case 3 -> finalizarLocacao();
            case 4 -> excluirLocacao();
            case 5 -> buscarLocacoesPorStatus();
            case 6 -> buscarLocacoesPorPeriodo();
            default -> System.out.println("Opção inválida!");
        }
    }

    /**
     * Lista todas as locações e seus livros.
     */
    private static void listarLocacoes() {
        List<Locacao> locacoes = LOCACAO_SERVICE.listar();
        System.out.println("\n--- Locações ---");

        locacoes.forEach(locacao -> {
            String livros = locacao.getLivros().stream()
                    .map(Livro::getTitulo)
                    .collect(Collectors.joining(", "));

            String usuario = locacao.getUsuario() != null ? locacao.getUsuario().getNome() : "N/D";
            String dataLocacao = locacao.getDataLocacao() != null ? locacao.getDataLocacao().toString() : "N/D";
            String dataDevolucao = locacao.getDataDevolucao() != null ? locacao.getDataDevolucao().toString() : "N/D";

            System.out.println(locacao.getId() + " - " + usuario + " - " + livros +
                    " [" + locacao.getStatus() + "] - Locação: " + dataLocacao +
                    " - Devolução: " + dataDevolucao);
        });
    }

    /**
     * Cadastra uma locação associando livros e usuário.
     */
    private static void cadastrarLocacao() {
        listarUsuarios();
        System.out.print("ID do Usuário: ");
        int idUsuario = Integer.parseInt(SCANNER.nextLine());

        Usuario usuario = USUARIO_SERVICE.buscarPorId(idUsuario);
        if (usuario == null) {
            System.out.println("Usuário não encontrado!");
            return;
        }

        listarLivros();
        System.out.print("IDs dos livros (separados por vírgula): ");
        String[] ids = SCANNER.nextLine().split(",");

        Locacao locacao = new Locacao();
        locacao.setUsuario(usuario);
        locacao.setStatus(LocacaoStatus.LOCADA);

        for (String s : ids) {
            int livroId = Integer.parseInt(s.trim());
            Livro livro = LIVRO_SERVICE.buscarPorId(livroId);
            if (livro != null) {
                locacao.getLivros().add(livro);
                livro.setStatus(LivroStatus.LOCADO); // atualiza o status do livro
            } else {
                System.out.println("Livro com ID " + livroId + " não encontrado!");
            }
        }

        // Captura a data de devolução com validação
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataDevolucao = null;
        while (dataDevolucao == null) {
            System.out.print("Digite a Data de Devolução (dd/MM/yyyy): ");
            try {
                dataDevolucao = LocalDate.parse(SCANNER.nextLine(), formatter);
            } catch (Exception e) {
                System.out.println("Data inválida! Digite no formato dd/MM/yyyy.");
            }
        }

        locacao.setDataLocacao(LocalDate.now());
        locacao.setDataDevolucao(dataDevolucao);

        LOCACAO_SERVICE.salvar(locacao);
        System.out.println("Locação cadastrada com sucesso!");
    }

    /**
     * Finaliza a locação alterando a data de devolução para hoje.
     */
    private static void finalizarLocacao() {
        listarLocacoes();
        System.out.print("ID da Locação para finalizar: ");
        int id = Integer.parseInt(SCANNER.nextLine());

        Locacao locacao = LOCACAO_SERVICE.buscarPorId(id);
        if (locacao == null) {
            System.out.println("Locação não encontrada!");
            return;
        }

        locacao.setDataDevolucao(LocalDate.now());
        LOCACAO_SERVICE.atualizar(locacao);
        System.out.println("Locação finalizada com sucesso!");
    }

    private static void excluirLocacao() {
        listarLocacoes();
        System.out.print("ID da Locação para excluir: ");
        int id = Integer.parseInt(SCANNER.nextLine());

        LOCACAO_SERVICE.excluir(id);
        System.out.println("Locação excluída com sucesso!");
    }

    private static void buscarLocacoesPorStatus() {
        System.out.println("""
                Digite:
                1 - Locadas
                2 - Finalizadas
                3 - Atrasadas""");

        int opcao = Integer.parseInt(SCANNER.nextLine());
        List<Locacao> locacoes;

        switch (opcao) {
            case 1 -> locacoes = LOCACAO_SERVICE.buscarLocacoes(LocacaoStatus.LOCADA);
            case 2 -> locacoes = LOCACAO_SERVICE.buscarLocacoes(LocacaoStatus.FINALIZADA);
            case 3 -> locacoes = LOCACAO_SERVICE.buscarLocacoes(LocacaoStatus.ATRASADA);
            default -> {
                System.out.println("Opção inválida!");
                return;
            }
        }

        locacoes.forEach(locacao -> {
            String livros = locacao.getLivros().stream()
                    .map(Livro::getTitulo)
                    .collect(Collectors.joining(", "));
            String usuario = locacao.getUsuario() != null ? locacao.getUsuario().getNome() : "N/D";
            String dataLocacao = locacao.getDataLocacao() != null ? locacao.getDataLocacao().toString() : "N/D";
            String dataDevolucao = locacao.getDataDevolucao() != null ? locacao.getDataDevolucao().toString() : "N/D";

            System.out.println(locacao.getId() + " - " + usuario + " - " + livros +
                    " [" + locacao.getStatus() + "] - Locação: " + dataLocacao +
                    " - Devolução: " + dataDevolucao);
        });
    }

    private static void buscarLocacoesPorPeriodo() {
        System.out.print("Digite a data de início (dd/MM/yyyy): ");
        LocalDate dataInicio = LocalDate.parse(SCANNER.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        System.out.print("Digite a data de fim (dd/MM/yyyy): ");
        LocalDate dataFim = LocalDate.parse(SCANNER.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        List<Locacao> locacoes = LOCACAO_SERVICE.buscarLocacaoPorPeriodo(dataInicio, dataFim);

        locacoes.forEach(locacao -> {
            String livros = locacao.getLivros().stream()
                    .map(Livro::getTitulo)
                    .collect(Collectors.joining(", "));
            String usuario = locacao.getUsuario() != null ? locacao.getUsuario().getNome() : "N/D";
            String dataLocacao = locacao.getDataLocacao() != null ? locacao.getDataLocacao().toString() : "N/D";
            String dataDevolucao = locacao.getDataDevolucao() != null ? locacao.getDataDevolucao().toString() : "N/D";

            System.out.println(locacao.getId() + " - " + usuario + " - " + livros +
                    " [" + locacao.getStatus() + "] - Locação: " + dataLocacao +
                    " - Devolução: " + dataDevolucao);
        });
    }

    // ================= MENU PAGAMENTO =================

    private static void menuPagamento() {
        System.out.print("""
                ==== MENU PAGAMENTO ====
                1 - Listar
                2 - Cadastrar
                3 - Atualizar
                4 - Buscar por período
                5 - Buscar por usuário
                Escolha: """);

        int opcao = Integer.parseInt(SCANNER.nextLine());

        switch (opcao) {
            case 1 -> listarPagamentos();
            case 2 -> cadastrarPagamento();
            case 3 -> atualizarPagamento();
            case 4 -> buscarPagamentoPorPeriodo();
            case 5 -> buscarPagamentoPorUsuario();
            default -> System.out.println("Opção inválida!");
        }
    }

    private static void listarPagamentos() {
        List<Pagamento> pagamentos = PAGAMENTO_SERVICE.listar();
        System.out.println("\n--- Pagamentos ---");
        pagamentos.forEach(p -> {
            String usuario = p.getLocacao().getUsuario() != null ? p.getLocacao().getUsuario().getNome() : "N/D";
            System.out.println(p.getId() + " - Usuário: " + usuario +
                    " - Valor: " + p.getValor() +
                    " - Data: " + p.getDataPagamento());
        });
    }

    private static void cadastrarPagamento() {
        System.out.println("Locações pendentes de pagamento:");
        LOCACAO_SERVICE.listar().forEach(l ->
                System.out.println(l.getId() + " - " + l.getUsuario().getNome() +
                        " - Livros: " + l.getLivros().stream()
                        .map(Livro::getTitulo)
                        .collect(Collectors.joining(", ")))
        );

        System.out.print("ID da Locação: ");
        int idLocacao = Integer.parseInt(SCANNER.nextLine());
        Locacao locacao = LOCACAO_SERVICE.buscarPorId(idLocacao);

        if (locacao == null) {
            System.out.println("Locação não encontrada!");
            return;
        }

        Pagamento pagamento = new Pagamento();
        pagamento.setLocacao(locacao);
        pagamento.setDataPagamento(LocalDate.now());

        PAGAMENTO_SERVICE.salvar(pagamento);

        locacao.setStatus(LocacaoStatus.FINALIZADA); // Marca locação como finalizada
        LOCACAO_SERVICE.atualizar(locacao);

        System.out.println("Pagamento registrado com sucesso!");
        System.out.println("Valor do pagamento: R$ " + pagamento.getValor());
    }

    private static void atualizarPagamento() {
        listarPagamentos();
        System.out.print("ID do Pagamento: ");
        int id = Integer.parseInt(SCANNER.nextLine());

        Pagamento pagamento = PAGAMENTO_SERVICE.buscarPorId(id);
        if (pagamento == null) {
            System.out.println("Pagamento não encontrado!");
            return;
        }

        System.out.print("Nova data de pagamento (dd/MM/yyyy): ");
        LocalDate novaData;
        try {
            novaData = LocalDate.parse(SCANNER.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } catch (Exception e) {
            System.out.println("Formato de data inválido!");
            return;
        }

        pagamento.setDataPagamento(novaData);
        PAGAMENTO_SERVICE.atualizar(pagamento);
        System.out.println("Data do pagamento atualizada com sucesso!");
    }

    private static void buscarPagamentoPorPeriodo() {
        System.out.print("Digite a data de início (dd/MM/yyyy): ");
        LocalDate dataInicio = LocalDate.parse(SCANNER.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        System.out.print("Digite a data de fim (dd/MM/yyyy): ");
        LocalDate dataFim = LocalDate.parse(SCANNER.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        List<Pagamento> pagamentos = PAGAMENTO_SERVICE.buscarPorPeriodo(dataInicio, dataFim);

        pagamentos.forEach(p -> {
            String usuario = p.getLocacao().getUsuario() != null ? p.getLocacao().getUsuario().getNome() : "N/D";
            String livros = p.getLocacao().getLivros().stream()
                    .map(Livro::getTitulo)
                    .collect(Collectors.joining(", "));
            System.out.println(p.getId() + " - " + usuario +
                    " - Livros: " + livros +
                    " - Valor: " + p.getValor() +
                    " - Data Pagamento: " + (p.getDataPagamento() != null ? p.getDataPagamento() : "N/D"));
        });
    }

    private static void buscarPagamentoPorUsuario() {
        System.out.print("Digite o ID do usuário: ");
        int idUsuario = Integer.parseInt(SCANNER.nextLine());
        Usuario usuario = USUARIO_SERVICE.buscarPorId(idUsuario);

        if (usuario == null) {
            System.out.println("Usuário não encontrado!");
            return;
        }

        List<Pagamento> pagamentos = PAGAMENTO_SERVICE.buscarPagamentosPorUsuario(usuario);

        pagamentos.forEach(p -> {
            String livros = p.getLocacao().getLivros().stream()
                    .map(Livro::getTitulo)
                    .collect(Collectors.joining(", "));
            System.out.println(p.getId() + " - " + usuario.getNome() +
                    " - Livros: " + livros +
                    " - Valor: " + p.getValor() +
                    " - Data Pagamento: " + (p.getDataPagamento() != null ? p.getDataPagamento() : "N/D"));
        });
    }
}
