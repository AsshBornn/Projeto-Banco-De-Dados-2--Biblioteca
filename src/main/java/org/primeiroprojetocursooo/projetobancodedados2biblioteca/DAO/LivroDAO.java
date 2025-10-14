package org.primeiroprojetocursooo.projetobancodedados2biblioteca.DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.primeiroprojetocursooo.projetobancodedados2biblioteca.entity.Categoria;
import org.primeiroprojetocursooo.projetobancodedados2biblioteca.entity.Livro;
import org.primeiroprojetocursooo.projetobancodedados2biblioteca.entity.enums.LivroStatus;
import org.primeiroprojetocursooo.projetobancodedados2biblioteca.util.JPAUtil;

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
    public Livro buscarPorId(Integer id) {
        return super.buscarPorId(id);
    }
    //UPDATE

    @Override
    public void atualizar(Livro entidade) {
        super.atualizar(entidade);
    }

    //DELETE
    @Override
    public void excluir(Integer id) {
        super.excluir(id);
    }
    //METODO VERIFICAR TITULO
    public boolean existeTITULO(String titulo) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            String jpql = "SELECT l FROM Livro l WHERE LOWER(l.titulo)= LOWER(:titulo)";
            TypedQuery<Livro> query = em.createQuery(jpql, Livro.class);
            query.setParameter("titulo", titulo);
            return !query.getResultList().isEmpty();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro Ao Verificar");
        } finally {
            em.close();
        }
    }
}