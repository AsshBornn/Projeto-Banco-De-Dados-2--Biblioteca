package org.primeiroprojetocursooo.projetobancodedados2biblioteca.services;
import org.primeiroprojetocursooo.projetobancodedados2biblioteca.DAO.CategoriaDAO;
import org.primeiroprojetocursooo.projetobancodedados2biblioteca.entity.Categoria;

import java.util.List;

public class CategoriaService extends GenericService<Categoria> {
    public CategoriaService() {
        super(new CategoriaDAO(Categoria.class));
    }
    @Override
    public void salvar(Categoria entidade) {
        super.salvar(entidade);
    }
    @Override
    public void atualizar(Categoria entidade) {
        super.atualizar(entidade);
    }
    @Override
    public void excluir(Long id) {
        super.excluir(id);
    }
    @Override
    public Categoria buscarPorId(Long id) {
        return super.buscarPorId(id);
    }
    @Override
    public List<Categoria> listar() {
        return super.listar();
    }
}
