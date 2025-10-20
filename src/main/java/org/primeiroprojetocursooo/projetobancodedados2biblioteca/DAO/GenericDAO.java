package org.primeiroprojetocursooo.projetobancodedados2biblioteca.DAO;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.primeiroprojetocursooo.projetobancodedados2biblioteca.util.JPAUtil;

import java.util.List;

/**
 * DAO genérico para operações CRUD básicas.
 *
 * <p>
 * Este DAO pode ser estendido por classes específicas (ex: UsuarioDAO, LivroDAO)
 * para evitar duplicação de código.
 * </p>
 *
 * @param <T> Tipo da entidade JPA que será gerenciada.
 */
public class GenericDAO<T> {

    /**
     * Classe da entidade gerenciada pelo DAO.
     * É usada para operações genéricas, como find e consultas dinâmicas.
     */
    private final Class<T> clazz;

    /**
     * Construtor que recebe a classe da entidade.
     *
     * @param clazz Classe da entidade
     */
    public GenericDAO(Class<T> clazz) {
        this.clazz = clazz;
    }

    /**
     * Persiste uma nova entidade no banco de dados.
     *
     * Boas práticas:
     * - Sempre usar transações.
     * - Fechar o EntityManager no bloco finally.
     *
     * @param entidade Entidade a ser salva
     */
    public void salvar(T entidade) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();  // inicia transação
            em.persist(entidade);         // persiste a entidade
            em.getTransaction().commit(); // confirma operação
        } catch (Exception e) {
            em.getTransaction().rollback(); // desfaz transação em caso de erro
            e.printStackTrace();            // exibe stack trace
        } finally {
            em.close();                     // garante fechamento do EntityManager
        }
    }

    /**
     * Busca uma entidade pelo seu ID.
     *
     * @param id Identificador da entidade
     * @return Entidade encontrada ou null
     */
    public T buscarPorId(Integer id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(clazz, id);
        } finally {
            em.close();
        }
    }

    /**
     * Lista todas as entidades do tipo T no banco.
     *
     * Boas práticas:
     * - Usa reflection para detectar o nome da entidade (@Entity.name) se definido.
     * - Caso não tenha nome definido, usa o nome simples da classe.
     *
     * @return Lista de entidades
     */
    public List<T> listar() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            Entity entityAnnotation = clazz.getAnnotation(Entity.class);
            String entityName = (entityAnnotation != null && !entityAnnotation.name().isEmpty())
                    ? entityAnnotation.name()
                    : clazz.getSimpleName();

            return em.createQuery("FROM " + entityName, clazz)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    /**
     * Atualiza uma entidade existente no banco.
     *
     * Boas práticas:
     * - Sempre usar merge para atualizar entidades.
     * - Tratar rollback em caso de exceção.
     *
     * @param entidade Entidade a ser atualizada
     */
    public void atualizar(T entidade) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction et = null;

        try {
            et = em.getTransaction();
            et.begin();
            em.merge(entidade);  // atualiza entidade existente
            et.commit();
        } catch (Exception e) {
            if (et != null && et.isActive()) {
                et.rollback();  // desfaz transação em caso de erro
            }
            e.printStackTrace();
            throw new RuntimeException("Erro ao atualizar entidade", e);
        } finally {
            em.close();
        }
    }

    /**
     * Remove uma entidade pelo seu ID.
     *
     * Boas práticas:
     * - Sempre buscar a entidade antes de remover.
     * - Tratar rollback em caso de exceção.
     *
     * @param id Identificador da entidade a ser removida
     */
    public void excluir(Integer id) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction et = null;

        try {
            et = em.getTransaction();
            et.begin();

            T entidade = em.find(clazz, id); // busca entidade
            if (entidade != null) {
                em.remove(entidade);          // remove do banco
                System.out.println("Entidade removida com sucesso: " + entidade);
            } else {
                System.out.println("Entidade com id " + id + " não encontrada.");
            }

            et.commit();
        } catch (Exception e) {
            if (et != null && et.isActive()) {
                et.rollback(); // desfaz transação em caso de erro
            }
            e.printStackTrace();
            throw new RuntimeException("Erro ao excluir entidade", e);
        } finally {
            em.close();
        }
    }
}
