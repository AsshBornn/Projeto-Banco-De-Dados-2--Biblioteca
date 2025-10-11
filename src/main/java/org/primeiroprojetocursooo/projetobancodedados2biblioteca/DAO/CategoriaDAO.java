package org.primeiroprojetocursooo.projetobancodedados2biblioteca.DAO;

import jakarta.persistence.EntityManager;
import org.primeiroprojetocursooo.projetobancodedados2biblioteca.entity.Categoria;
import org.primeiroprojetocursooo.projetobancodedados2biblioteca.entity.Usuario;
import org.primeiroprojetocursooo.projetobancodedados2biblioteca.util.JPAUtil;

import java.util.List;

public class CategoriaDAO extends GenericDAO<Categoria> {


    public CategoriaDAO(Class<Categoria> clazz) {
        super(clazz);
    }
    //CREATE
    @Override
    public void salvar(Categoria entidade) {
        super.salvar(entidade);
    }
    //READ
    @Override
    public List<Categoria> listar() {
        return super.listar();
    }
    //READ
    @Override
    public Categoria buscarPorId(Integer id) {
        return super.buscarPorId(id);
    }
    //UPDATE
    @Override
    public void atualizar(Categoria entidade) {
        super.atualizar(entidade);
    }
    //DELETE
    @Override
    public void excluir(Integer id) {
        super.excluir(id);
    }

}
