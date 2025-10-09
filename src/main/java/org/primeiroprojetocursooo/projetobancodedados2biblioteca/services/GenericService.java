package org.primeiroprojetocursooo.projetobancodedados2biblioteca.services;

import org.primeiroprojetocursooo.projetobancodedados2biblioteca.DAO.GenericDAO;

import java.util.List;

public class GenericService<T> {
    private GenericDAO<T> dao;

    public GenericService(GenericDAO<T> dao) {
        this.dao = dao;
    }

    public void salvar(T entidade) { dao.salvar(entidade); }
    public void atualizar(T entidade) { dao.atualizar(entidade); }
    public void excluir(Long id) { dao.excluir(id); }
    public T buscarPorId(Long id) { return dao.buscarPorId(id); }
    public List<T> listar() { return dao.listar(); }
}
