package org.primeiroprojetocursooo.projetobancodedados2biblioteca.DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
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

    //METODO VERIFICAR DESCRIÇÂO
    public boolean existeDescricao(String descricao) {
        EntityManager em = JPAUtil.getEntityManager();
        try{
            String jpql = "SELECT c FROM Categoria c WHERE LOWER(c.descricao)= LOWER(:descricao)";
            TypedQuery<Categoria> query = em.createQuery(jpql, Categoria.class);
            query.setParameter("descricao", descricao);
            return !query.getResultList().isEmpty(); // se a lista não estiver vazia, a categoria existe
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("Erro Ao Verificar");
        }finally {
            em.close();
        }
    }
    //METODO BUSCAR POR DESCRIÇÂO
    public List<Categoria> buscarPorDescricao(String descricao) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            String jpql = "SELECT c FROM Categoria c WHERE LOWER(c.descricao) LIKE LOWER(:descricao)";
            TypedQuery<Categoria> query = em.createQuery(jpql, Categoria.class);
            query.setParameter("descricao", "%" + descricao + "%");
            return query.getResultList();
        }catch (Exception e){
            throw new RuntimeException("Erro Ao Procurar Por Descricao");
        }
        finally {
            em.close();
        }
    }
}
