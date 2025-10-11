package org.primeiroprojetocursooo.projetobancodedados2biblioteca.services;
import org.primeiroprojetocursooo.projetobancodedados2biblioteca.DAO.CategoriaDAO;
import org.primeiroprojetocursooo.projetobancodedados2biblioteca.entity.Categoria;

import java.util.List;

public class CategoriaService extends GenericService<Categoria> {

    private CategoriaDAO categoriaDAO; // DAO específico
    public CategoriaService() {
        super(new CategoriaDAO(Categoria.class)); // passa para GenericService
        this.categoriaDAO = (CategoriaDAO) super.getDao(); // pega a mesma instância para não criar duas instancias do DAO
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
    public void excluir(Integer id) {
        super.excluir(id);
    }
    @Override
    public Categoria buscarPorId(Integer id) {
        return super.buscarPorId(id);
    }
    @Override
    public List<Categoria> listar() {
        return super.listar();
    }

}
