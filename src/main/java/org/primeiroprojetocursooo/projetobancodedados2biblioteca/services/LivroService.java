package org.primeiroprojetocursooo.projetobancodedados2biblioteca.services;
import org.primeiroprojetocursooo.projetobancodedados2biblioteca.DAO.LivroDAO;
import org.primeiroprojetocursooo.projetobancodedados2biblioteca.entity.Categoria;
import org.primeiroprojetocursooo.projetobancodedados2biblioteca.entity.Livro;
import org.primeiroprojetocursooo.projetobancodedados2biblioteca.entity.enums.LivroStatus;

import java.util.List;

public class LivroService extends GenericService<Livro> {
    private LivroDAO livroDAO;

    public LivroService() {
        super(new LivroDAO(Livro.class));//Passa para o GenericService
        this.livroDAO = (LivroDAO) super.getDao();//pega a mesma instancia para pode utilizar metodos especificos da classe LivroDAO sem criar outra instancia
    }


    @Override
    public void salvar(Livro entidade) {//verifica se a entidade e o titulo não é nulo ou é apenas espaços vazios
        if(entidade == null|| entidade.getTitulo() == null || entidade.getTitulo().isBlank()){
            throw new IllegalArgumentException("Livro Invalido");
        }
        if(livroDAO.existeLivro(entidade.getTitulo())){//VERIFICA SE A JA ESTA CADASTRADO
            throw new IllegalArgumentException("Livro Ja cadastrado");
        }
        if(entidade.getPreco() == null || entidade.getPreco() < 0){// VERIFICA SE O PREÇO É VALIDO
            throw new IllegalArgumentException("Preço inválido");
        }
        entidade.setStatus(LivroStatus.DISPONIVEL);//MARCA O LIVRO COMO DISPONIVEL
        super.salvar(entidade);
    }
    @Override
    public void atualizar(Livro entidade) {
        if(entidade == null|| entidade.getTitulo() == null || entidade.getTitulo().isBlank()){
            throw new IllegalArgumentException("Livro Invalido");//VERIFICA SE A ENTIDADE É NULO EXISTE TITULO E NÃO É APENAS CARACTERES EM BRANCO
        }
        //Verifica se o livro existe
        if (livroDAO.buscarPorId((entidade.getId())) == null) {
            throw new IllegalArgumentException("Livro Não Encontrado");
        }
        //VALIDAÇÃO SE NÃO EXISTE OUTRO LIVRO COM MESMO TITULO
        Livro livroExistente = livroDAO.buscarPorTituloExato(entidade.getTitulo());
        if(livroExistente != null && !livroExistente.getId().equals(entidade.getId())){
            throw new IllegalArgumentException("Título já cadastrado para outro livro");
        }
        if(entidade.getPreco() == null || entidade.getPreco() < 0){//VERIFICA O PREÇO
            throw new IllegalArgumentException("Preco Invalido");
        }
        super.atualizar(entidade);
    }

    @Override
    public void excluir(Integer id) {
        Livro livro = livroDAO.buscarPorId(id);//BUSCA O LIVRO
        if (livro == null) {//VERIFICA SE NÃO É NULO
            throw new RuntimeException("Livro não encontrado");
        }

        // Verifica se o livro está locado
        if (livro.getStatus() == LivroStatus.LOCADO) {
            throw new RuntimeException("Não é possível excluir um livro que está locado");
        }

        // Verifica se o livro tem locações ativas
        if (!livro.getLocacoes().isEmpty()) {
            throw new RuntimeException("Não é possível excluir um livro que possui locações ativas");
        }

        super.excluir(id); // só exclui se passar nas regras acima
    }


    @Override
    public Livro buscarPorId(Integer id) {
        return super.buscarPorId(id);
    }

    @Override
    public List<Livro> listar() {
        return super.listar();
    }
    public List<Livro> buscarLivroPorTitulo(String titulo) {
        if(titulo == null || titulo.isEmpty()|| titulo.isBlank()){
            return List.of();
        }
        return livroDAO.buscarLivroPorTitulo(titulo);
    }
    public List<Livro> buscarLivrosDisponiveis() {
        return livroDAO.buscarLivrosDisponiveis();
    }
    public List<Livro> buscarLivrosLocados() {
        return livroDAO.buscarLivrosLocados();
    }
}
