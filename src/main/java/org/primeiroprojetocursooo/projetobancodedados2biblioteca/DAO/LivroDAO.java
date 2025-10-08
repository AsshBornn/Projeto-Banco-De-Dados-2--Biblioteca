package org.primeiroprojetocursooo.projetobancodedados2biblioteca.DAO;

import org.primeiroprojetocursooo.projetobancodedados2biblioteca.entity.Livro;

import java.util.List;

public class LivroDAO extends GenericDAO<Livro> {

    public LivroDAO(Class<Livro> clazz) {
        super(clazz);
    }
    //CREATE
    @Override
    public void salvar(Livro entidade) {
        super.salvar(entidade);
    }
    //READ
    @Override
    public List<Livro> listar() {
        return super.listar();
    }
    //READ
    @Override
    public Livro buscarPorId(Long id) {
        return super.buscarPorId(id);
    }
    //UPDATE

    @Override
    public void atualizar(Livro entidade) {
        super.atualizar(entidade);
    }
    //DELETE
    @Override
    public void excluir(Long id) {
        super.excluir(id);
    }
}
