package org.primeiroprojetocursooo.projetobancodedados2biblioteca.DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.primeiroprojetocursooo.projetobancodedados2biblioteca.entity.Locacao;
import org.primeiroprojetocursooo.projetobancodedados2biblioteca.entity.enums.LocacaoStatus;
import org.primeiroprojetocursooo.projetobancodedados2biblioteca.util.JPAUtil;

import java.time.LocalDate;
import java.util.List;

/**
 * DAO específico para a entidade Locacao.
 *
 * <p>
 * Extende GenericDAO para herdar operações CRUD básicas e adiciona métodos
 * específicos de Locacao, como buscas por status e período.
 * </p>
 *
 * <p>
 * Destaques:
 * - Relacionamentos importantes:
 *   - ManyToOne com Usuario
 *   - ManyToMany com Livro via JoinTable
 *   - OneToOne com Pagamento
 * - Uso de enum LocacaoStatus para controlar status da locação.
 * </p>
 */
public class LocacaoDAO extends GenericDAO<Locacao> {

    /**
     * Construtor que passa a classe Locacao para o GenericDAO.
     *
     * @param clazz Classe da entidade Locacao
     */
    public LocacaoDAO(Class<Locacao> clazz) {
        super(clazz);
    }

    // ===== CRUD Básico =====

    @Override
    public void salvar(Locacao entidade) {
        super.salvar(entidade);
    }

    @Override
    public List<Locacao> listar() {
        return super.listar();
    }

    @Override
    public Locacao buscarPorId(Integer id) {
        return super.buscarPorId(id);
    }

    @Override
    public void atualizar(Locacao entidade) {
        super.atualizar(entidade);
    }

    @Override
    public void excluir(Integer id) {
        super.excluir(id);
    }

    // ===== Consultas específicas =====

    /**
     * Busca todas as locações ativas (status LOCADA).
     *
     * @return Lista de locações ativas
     */
    public List<Locacao> buscarLocacoesAtivas() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            String jpql = "SELECT l FROM Locacao l WHERE l.status = :status";
            TypedQuery<Locacao> query = em.createQuery(jpql, Locacao.class);
            query.setParameter("status", LocacaoStatus.LOCADA);
            return query.getResultList();
        } finally {
            em.close(); // garante fechamento do EntityManager
        }
    }

    /**
     * Busca todas as locações finalizadas (status FINALIZADA).
     *
     * @return Lista de locações finalizadas
     */
    public List<Locacao> buscarLocacoesFinalizadas() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            String jpql = "SELECT l FROM Locacao l WHERE l.status = :status";
            TypedQuery<Locacao> query = em.createQuery(jpql, Locacao.class);
            query.setParameter("status", LocacaoStatus.FINALIZADA);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    /**
     * Busca todas as locações atrasadas (status ATRASADA).
     *
     * @return Lista de locações atrasadas
     */
    public List<Locacao> buscarLocacoesAtrasadas() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            String jpql = "SELECT l FROM Locacao l WHERE l.status = :status";
            TypedQuery<Locacao> query = em.createQuery(jpql, Locacao.class);
            query.setParameter("status", LocacaoStatus.ATRASADA);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    /**
     * Busca locações dentro de um período específico.
     *
     * <p>Usa BETWEEN para pegar locações com dataLocacao entre dataInicio e dataFim,
     * ordenando por dataLocacao ascendente.</p>
     *
     * @param dataInicio Data inicial do período
     * @param dataFim Data final do período
     * @return Lista de locações dentro do período
     */
    public List<Locacao> buscarPorPeriodo(LocalDate dataInicio, LocalDate dataFim) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            String jpql = "SELECT l FROM Locacao l " +
                    "WHERE l.dataLocacao BETWEEN :inicio AND :fim " +
                    "ORDER BY l.dataLocacao ASC";

            TypedQuery<Locacao> query = em.createQuery(jpql, Locacao.class);
            query.setParameter("inicio", dataInicio);
            query.setParameter("fim", dataFim);

            return query.getResultList();
        } finally {
            em.close(); // garante fechamento do EntityManager
        }
    }
}
