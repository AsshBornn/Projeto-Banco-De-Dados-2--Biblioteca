package org.primeiroprojetocursooo.projetobancodedados2biblioteca.services;

import org.primeiroprojetocursooo.projetobancodedados2biblioteca.DAO.GenericDAO;
import org.primeiroprojetocursooo.projetobancodedados2biblioteca.DAO.LivroDAO;
import org.primeiroprojetocursooo.projetobancodedados2biblioteca.entity.Livro;

import java.util.List;

public class LivroService extends GenericService<Livro> {
    public LivroService() {
        super(new LivroDAO(Livro.class));
    }
    @Override
    public void salvar(Livro entidade) {
        super.salvar(entidade);
    }
    @Override
    public void atualizar(Livro entidade) {
        super.atualizar(entidade);
    }

    @Override
    public void excluir(Long id) {
        super.excluir(id);
    }

    @Override
    public Livro buscarPorId(Long id) {
        return super.buscarPorId(id);
    }

    @Override
    public List<Livro> listar() {
        return super.listar();
    }
}
