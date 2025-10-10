package org.primeiroprojetocursooo.projetobancodedados2biblioteca.DAO;

import jakarta.persistence.EntityManager;
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
    //METODO EXPECIFICO PARA VERIFICAR SE JA EXISTE UM LIVRO IGUAL
    public boolean existeLivro(String titulo) {
        EntityManager em = JPAUtil.getEntityManager();
        try{//SELECT PARA VERIFICAR SE EXISTE UM LIVRO COM TITULO IGUAL E CONTAR SEM CASESENSITIVE
            String jpql="SELECT COUNT(l) from Livro l WHERE LOWER(l.titulo) = LOWER(:titulo)";
            Long count = em.createQuery(jpql,long.class)//monta o typedQuery
                    .setParameter("titulo",titulo)//Liga o Valor ao Parametro
                    .getSingleResult();//Retorna o Resultado
            return count > 0;
    }finally{
        em.close();}
    }
    //METODO EXPECIFICO PARA BUSCAR POR  LISTA DE TITULOS
    public List<Livro> buscarLivroPorTitulo(String titulo) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            // Busca parcial, ignorando maiúsculas/minúsculas
            String jpql = "SELECT l FROM Livro l WHERE LOWER(l.titulo) LIKE LOWER(:titulo)";
            return em.createQuery(jpql, Livro.class)
                    .setParameter("titulo", "%" + titulo + "%") // permite qualquer parte do título
                    .getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar Livro por Título", e);
        } finally {
            em.close();
        }
    }
    //METODO EXPECIFICIO PARA BUSCAR PELO TITULO EXATO PARA VALIDAÇÃO
    public Livro buscarPorTituloExato(String titulo) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            String jpql = "SELECT l FROM Livro l WHERE LOWER(l.titulo) = LOWER(:titulo)";
            return em.createQuery(jpql, Livro.class)
                    .setParameter("titulo", titulo)
                    .getResultStream()
                    .findFirst()
                    .orElse(null);
        } finally {
            em.close();
        }
    }
    //METODO ESPECIFICO BUSCAR LIVRO DISPONIVEL
    public List<Livro> buscarLivrosDisponiveis() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            // JPQL: seleciona livros cujo status seja DISPONIVEL
            String jpql = "SELECT l FROM Livro l WHERE l.status = :status";
            return em.createQuery(jpql, Livro.class)
                    .setParameter("status", LivroStatus.DISPONIVEL)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    //METODO ESPECIFICO BUSCAR LIVRO LOCADO
    public List<Livro> buscarLivrosLocados() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            String jpql = "SELECT l FROM Livro l WHERE l.status = :status";
            return em.createQuery(jpql, Livro.class)
                    .setParameter("status", LivroStatus.LOCADO)
                    .getResultList();
        } finally {
            em.close();
        }
    }
}
