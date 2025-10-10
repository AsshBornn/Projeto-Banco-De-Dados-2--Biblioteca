package org.primeiroprojetocursooo.projetobancodedados2biblioteca;

import org.primeiroprojetocursooo.projetobancodedados2biblioteca.entity.*;
import org.primeiroprojetocursooo.projetobancodedados2biblioteca.entity.enums.*;
import org.primeiroprojetocursooo.projetobancodedados2biblioteca.services.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner sc = new Scanner(System.in);
    private static final CategoriaService categoriaService = new CategoriaService();
    private static final LivroService livroService = new LivroService();
    private static final UsuarioService usuarioService = new UsuarioService();
    private static final LocacaoService locacaoService = new LocacaoService();
    private static final PagamentoService pagamentoService = new PagamentoService();

    public static void main(String[] args) {
        while(true) {
            System.out.println("\n==== MENU PRINCIPAL ====");
            System.out.println("1 - Categoria");
            System.out.println("2 - Livro");
            System.out.println("3 - Usuário");
            System.out.println("4 - Locação");
            System.out.println("5 - Pagamento");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");
            int opc = Integer.parseInt(sc.nextLine());

            switch(opc) {
                case 1 -> menuCategoria();
                case 2 -> menuLivro();
                case 3 -> menuUsuario();
                case 4 -> menuLocacao();
                case 5 -> menuPagamento();
                case 0 -> { System.out.println("Saindo..."); return; }
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    // ================= MENU CATEGORIA =================
    private static void menuCategoria() {
        System.out.println("\n==== MENU CATEGORIA ====");
        System.out.println("1 - Listar");
        System.out.println("2 - Cadastrar");
        System.out.println("3 - Atualizar");
        System.out.println("4 - Excluir");
        System.out.print("Escolha: ");
        int opc = Integer.parseInt(sc.nextLine());

        switch(opc) {
            case 1 -> listarCategorias();
            case 2 -> cadastrarCategoria();
            case 3 -> atualizarCategoria();
            case 4 -> excluirCategoria();
            default -> System.out.println("Opção inválida!");
        }
    }

    private static void listarCategorias() {
        List<Categoria> categorias = categoriaService.listar();
        System.out.println("\n--- Categorias ---");
        categorias.forEach(c -> System.out.println(c.getId() + " - " + c.getDescricao()));
    }

    private static void cadastrarCategoria() {
        System.out.print("Descrição: ");
        String desc = sc.nextLine();
        Categoria c = new Categoria();
        c.setDescricao(desc);
        categoriaService.salvar(c);
        System.out.println("Categoria cadastrada!");
    }

    private static void atualizarCategoria() {
        listarCategorias();
        System.out.print("ID da Categoria para atualizar: ");
        int id = Integer.parseInt(sc.nextLine());
        Categoria c = categoriaService.buscarPorId(id);
        if(c == null) { System.out.println("Categoria não encontrada"); return; }
        System.out.print("Nova descrição: ");
        c.setDescricao(sc.nextLine());
        categoriaService.atualizar(c);
        System.out.println("Categoria atualizada!");
    }

    private static void excluirCategoria() {
        listarCategorias();
        System.out.print("ID da Categoria para excluir: ");
        int id = Integer.parseInt(sc.nextLine());
        categoriaService.excluir(id);
        System.out.println("Categoria excluída!");
    }

    // ================= MENU LIVRO =================
    private static void menuLivro() {
        System.out.println("\n==== MENU LIVRO ====");
        System.out.println("1 - Listar");
        System.out.println("2 - Cadastrar");
        System.out.println("3 - Atualizar");
        System.out.println("4 - Excluir");
        System.out.print("Escolha: ");
        int opc = Integer.parseInt(sc.nextLine());

        switch(opc) {
            case 1 -> listarLivros();
            case 2 -> cadastrarLivro();
            case 3 -> atualizarLivro();
            case 4 -> excluirLivro();
            default -> System.out.println("Opção inválida!");
        }
    }

    private static void listarLivros() {
        List<Livro> livros = livroService.listar();
        System.out.println("\n--- Livros ---");
        livros.forEach(l -> System.out.println(l.getId() + " - " + l.getTitulo() + " [" + l.getStatus() + "]"));
    }

    private static void cadastrarLivro() {
        System.out.print("Título: ");
        String titulo = sc.nextLine();
        System.out.print("Valor Por Dia: ");
        double preco = Double.parseDouble(sc.nextLine());
        listarCategorias();
        System.out.print("ID da Categoria: ");
        int idCat = Integer.parseInt(sc.nextLine());
        Categoria cat = categoriaService.buscarPorId(idCat);
        Livro l = new Livro();
        l.setTitulo(titulo);
        l.setCategoria(cat);
        l.setStatus(LivroStatus.DISPONIVEL);
        l.setPreco(preco);
        livroService.salvar(l);
        System.out.println("Livro cadastrado!");
    }

    private static void atualizarLivro() {
        listarLivros();
        System.out.print("ID do Livro: ");
        int id = Integer.parseInt(sc.nextLine());
        Livro l = livroService.buscarPorId(id);
        if(l == null) { System.out.println("Livro não encontrado"); return; }
        System.out.print("Novo título: ");
        l.setTitulo(sc.nextLine());
        listarCategorias();
        System.out.print("Nova Categoria ID: ");
        int idCat = Integer.parseInt(sc.nextLine());
        l.setCategoria(categoriaService.buscarPorId(idCat));
        livroService.atualizar(l);
        System.out.println("Livro atualizado!");
    }

    private static void excluirLivro() {
        listarLivros();
        System.out.print("ID do Livro: ");
        int id = Integer.parseInt(sc.nextLine());
        livroService.excluir(id);
        System.out.println("Livro excluído!");
    }

    // ================= MENU USUÁRIO =================
    private static void menuUsuario() {
        System.out.println("\n==== MENU USUÁRIO ====");
        System.out.println("1 - Listar");
        System.out.println("2 - Cadastrar");
        System.out.println("3 - Atualizar");
        System.out.println("4 - Excluir");
        System.out.print("Escolha: ");
        int opc = Integer.parseInt(sc.nextLine());

        switch(opc) {
            case 1 -> listarUsuarios();
            case 2 -> cadastrarUsuario();
            case 3 -> atualizarUsuario();
            case 4 -> excluirUsuario();
            default -> System.out.println("Opção inválida!");
        }
    }

    private static void listarUsuarios() {
        List<Usuario> usuarios = usuarioService.listar();
        System.out.println("\n--- Usuários ---");
        usuarios.forEach(u -> System.out.println(u.getId() + " - " + u.getNome() + " (" + u.getEmail() + ")"));
    }

    private static void cadastrarUsuario() {
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("E-mail: ");
        String email = sc.nextLine();
        Usuario u = new Usuario();
        u.setNome(nome);
        u.setEmail(email);
        usuarioService.salvar(u);
        System.out.println("Usuário cadastrado!");
    }

    private static void atualizarUsuario() {
        listarUsuarios();
        System.out.print("ID do Usuário: ");
        int id = Integer.parseInt(sc.nextLine());
        Usuario u = usuarioService.buscarPorId(id);
        if(u == null) { System.out.println("Usuário não encontrado"); return; }
        System.out.print("Novo nome: ");
        u.setNome(sc.nextLine());
        System.out.print("Novo e-mail: ");
        u.setEmail(sc.nextLine());
        usuarioService.atualizar(u);
        System.out.println("Usuário atualizado!");
    }

    private static void excluirUsuario() {
        listarUsuarios();
        System.out.print("ID do Usuário: ");
        int id = Integer.parseInt(sc.nextLine());
        usuarioService.excluir(id);
        System.out.println("Usuário excluído!");
    }

    // ================= MENU LOCAÇÃO =================
    private static void menuLocacao() {
        System.out.println("\n==== MENU LOCAÇÃO ====");
        System.out.println("1 - Listar");
        System.out.println("2 - Cadastrar");
        System.out.println("3 - Atualizar (finalizar)");
        System.out.println("4 - Excluir");
        System.out.print("Escolha: ");
        int opc = Integer.parseInt(sc.nextLine());

        switch(opc) {
            case 1 -> listarLocacoes();
            case 2 -> cadastrarLocacao();
            case 3 -> finalizarLocacao();
            case 4 -> excluirLocacao();
            default -> System.out.println("Opção inválida!");
        }
    }

    private static void listarLocacoes() {
        List<Locacao> locacoes = locacaoService.listar();
        System.out.println("\n--- Locações ---");
        locacoes.forEach(l -> {
            String livros = l.getLivros().stream().map(Livro::getTitulo).reduce((a,b)->a+", "+b).orElse("");
            String usuario = l.getUsuario() != null ? l.getUsuario().getNome() : "N/D";
            System.out.println(l.getId() + " - " + usuario + " - " + livros + " [" + l.getStatus() + "]");
        });
    }

    private static void cadastrarLocacao() {
        listarUsuarios();
        System.out.print("ID do Usuário: ");
        int idUsuario = Integer.parseInt(sc.nextLine());
        Usuario u = usuarioService.buscarPorId(idUsuario);
        if (u == null) {
            System.out.println("Usuário não encontrado");
            return;
        }
        listarLivros();
        System.out.print("IDs dos livros (separados por vírgula): ");
        String[] ids = sc.nextLine().split(",");

        Locacao loc = new Locacao();
        loc.setUsuario(u);

        for (String s : ids) {
            int livroId = Integer.parseInt(s.trim());
            Livro l = livroService.buscarPorId(livroId);
            if (l != null) {
                loc.getLivros().add(l);
                l.setStatus(LivroStatus.LOCADO); // atualiza o status do livro
            } else {
                System.out.println("Livro com ID " + livroId + " não encontrado!");
            }
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataDevolucao = null;
        while (dataDevolucao == null) {
            System.out.println("Digite a Data de Devolução (dd/MM/yyyy): ");
            String dataInput = sc.nextLine();
            try {
                dataDevolucao = LocalDate.parse(dataInput, formatter);
            } catch (Exception e) {
                System.out.println("Data inválida! Digite no formato dd/MM/yyyy.");
            }
        }
        loc.setDataDevolucao(dataDevolucao);
        loc.setDataLocacao(LocalDate.now());
        locacaoService.salvar(loc);
        System.out.println("Locação cadastrada com sucesso!");
    }


    private static void finalizarLocacao() {
        listarLocacoes();
        System.out.print("ID da Locação para finalizar: ");
        int id = Integer.parseInt(sc.nextLine());
        Locacao loc = locacaoService.buscarPorId(id);
        if(loc == null){ System.out.println("Locação não encontrada"); return; }
        loc.setDataDevolucao(LocalDate.now());
        locacaoService.atualizar(loc);
        System.out.println("Locação finalizada!");
    }

    private static void excluirLocacao() {
        listarLocacoes();
        System.out.print("ID da Locação para excluir: ");
        int id = Integer.parseInt(sc.nextLine());
        locacaoService.excluir(id);
        System.out.println("Locação excluída!");
    }

    // ================= MENU PAGAMENTO =================
    private static void menuPagamento() {
        System.out.println("\n==== MENU PAGAMENTO ====");
        System.out.println("1 - Listar");
        System.out.println("2 - Cadastrar");
        System.out.println("3 - Atualizar");
        System.out.print("Escolha: ");
        int opc = Integer.parseInt(sc.nextLine());

        switch(opc) {
            case 1 -> listarPagamentos();
            case 2 -> cadastrarPagamento();
            case 3 -> atualizarPagamento();
            default -> System.out.println("Opção inválida!");
        }
    }

    private static void listarPagamentos() {
        List<Pagamento> pagamentos = pagamentoService.listar();
        System.out.println("\n--- Pagamentos ---");
        pagamentos.forEach(p -> {
            String usuario = p.getLocacao().getUsuario() != null ? p.getLocacao().getUsuario().getNome() : "N/D";
            System.out.println(p.getId() + " - Usuário: " + usuario + " - Valor: " + p.getValor() + " - Data: " + p.getDataPagamento());
        });
    }

    private static void cadastrarPagamento() {
        // Listar locações pendentes
        List<Locacao> pendentes = locacaoService.listarLocacoesSemPagamento();
        System.out.println("Locações pendentes de pagamento:");
        pendentes.forEach(l ->
                System.out.println(l.getId() + " - " + l.getUsuario().getNome() + " - Livros: " + l.getLivros())
        );

        // Escolher a locação
        System.out.print("ID da Locação: ");
        int idLoc = Integer.parseInt(sc.nextLine());
        Locacao loc = locacaoService.buscarPorId(idLoc);

        if (loc == null) {
            System.out.println("Locação não encontrada");
            return;
        }

        // Criar e salvar pagamento
        Pagamento p = new Pagamento();
        p.setLocacao(loc);
        pagamentoService.salvar(p);

        // Mostrar valor imediatamente
        System.out.println("Pagamento registrado!");
        System.out.println("Valor do pagamento: R$ " + p.getValor());
    }


    private static void atualizarPagamento() {
        listarPagamentos(); // lista todos os pagamentos
        System.out.print("ID do Pagamento: ");
        int id = Integer.parseInt(sc.nextLine());

        Pagamento p = pagamentoService.buscarPorId(id);
        if (p == null) {
            System.out.println("Pagamento não encontrado");
            return;
        }

        System.out.print("Nova data de pagamento (dd/MM/yyyy): ");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate novaData;
        try {
            novaData = LocalDate.parse(sc.nextLine(), formatter);
        } catch (Exception e) {
            System.out.println("Formato de data inválido");
            return;
        }

        pagamentoService.atualizarDataPagamento(p, novaData);
        System.out.println("Data do pagamento atualizada com sucesso!");
    }
}
