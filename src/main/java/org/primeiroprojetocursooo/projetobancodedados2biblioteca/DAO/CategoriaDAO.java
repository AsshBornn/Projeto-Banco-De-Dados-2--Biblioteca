package org.primeiroprojetocursooo.projetobancodedados2biblioteca.DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.primeiroprojetocursooo.projetobancodedados2biblioteca.entity.Categoria;
import org.primeiroprojetocursooo.projetobancodedados2biblioteca.util.JPAUtil;

import java.util.List;

/**
 * DAO específico para a entidade Categoria.
 *
 * <p>
 * Estende o GenericDAO para herdar operações CRUD básicas e adiciona métodos
 * específicos de Categoria, como busca por descrição ou verificação de existência.
 * </p>
 */
public class CategoriaDAO extends GenericDAO<Categoria> {

    /**
     * Construtor que passa a classe Categoria para o GenericDAO.
     *
     * @param clazz Classe da entidade Categoria
     */
    public CategoriaDAO(Class<Categoria> clazz) {
        super(clazz);
    }

    /**
     * Verifica se já existe uma categoria com a descrição fornecida.
     *
     * <p>
     * Boas práticas:
     * - Usa LOWER() para tornar a comparação case-insensitive.
     * - Fecha o EntityManager no bloco finally.
     * - Captura exceções e fornece feedback.
     * </p>
     *
     * @param descricao Descrição da categoria a verificar
     * @return true se já existir, false caso contrário
     */
    public boolean existeDescricao(String descricao) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            String jpql = "SELECT c FROM Categoria c WHERE LOWER(c.descricao) = LOWER(:descricao)";
            TypedQuery<Categoria> query = em.createQuery(jpql, Categoria.class);
            query.setParameter("descricao", descricao);
            // Retorna true se a lista não estiver vazia
            return !query.getResultList().isEmpty();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao verificar a existência da categoria", e);
        } finally {
            em.close(); // garante fechamento do EntityManager
        }
    }

    /**
     * Busca categorias cuja descrição contenha a string fornecida.
     *
     * <p>
     * Boas práticas:
     * - Usa LIKE e LOWER() para busca case-insensitive e parcial.
     * - Fecha o EntityManager no bloco finally.
     * </p>
     *
     * @param descricao Parte da descrição a ser buscada
     * @return Lista de categorias que correspondem ao critério
     */
    public List<Categoria> buscarPorDescricao(String descricao) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            String jpql = "SELECT c FROM Categoria c WHERE LOWER(c.descricao) LIKE LOWER(:descricao)";
            TypedQuery<Categoria> query = em.createQuery(jpql, Categoria.class);
            query.setParameter("descricao", "%" + descricao + "%"); // busca parcial
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao procurar categorias por descrição", e);
        } finally {
            em.close(); // garante fechamento do EntityManager
        }
    }
}
