package org.primeiroprojetocursooo.projetobancodedados2biblioteca.DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.primeiroprojetocursooo.projetobancodedados2biblioteca.entity.Pagamento;
import org.primeiroprojetocursooo.projetobancodedados2biblioteca.entity.Usuario;
import org.primeiroprojetocursooo.projetobancodedados2biblioteca.util.JPAUtil;

import java.time.LocalDate;
import java.util.List;

/**
 * DAO específico para a entidade Pagamento.
 *
 * <p>
 * Extende GenericDAO para herdar operações CRUD básicas e adiciona métodos
 * específicos de Pagamento, como buscas por período e por usuário.
 * </p>
 *
 * <p>
 * Destaques:
 * - Relacionamento OneToOne com Locacao
 * - Permite consultas filtradas por período e por usuário associado
 * </p>
 */
public class PagamentoDAO extends GenericDAO<Pagamento> {

    /**
     * Construtor que passa a classe Pagamento para o GenericDAO
     *
     * @param clazz Classe da entidade Pagamento
     */
    public PagamentoDAO(Class<Pagamento> clazz) {
        super(clazz);
    }

    // ===== CRUD Básico =====

    @Override
    public void salvar(Pagamento entidade) {
        super.salvar(entidade);
    }

    @Override
    public List<Pagamento> listar() {
        return super.listar();
    }

    @Override
    public Pagamento buscarPorId(Integer id) {
        return super.buscarPorId(id);
    }

    @Override
    public void atualizar(Pagamento entidade) {
        super.atualizar(entidade);
    }

    // ===== Consultas específicas =====

    /**
     * Busca pagamentos realizados em um período específico.
     *
     * <p>Usa BETWEEN para pegar pagamentos com dataPagamento entre inicio e fim.</p>
     *
     * @param inicio Data inicial do período
     * @param fim Data final do período
     * @return Lista de pagamentos dentro do período
     */
    public List<Pagamento> buscarPagamentosPorPeriodo(LocalDate inicio, LocalDate fim) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            String jpql = "SELECT p FROM Pagamento p WHERE p.dataPagamento BETWEEN :inicio AND :fim";
            TypedQuery<Pagamento> query = em.createQuery(jpql, Pagamento.class);
            query.setParameter("inicio", inicio);
            query.setParameter("fim", fim);
            return query.getResultList();
        } finally {
            em.close(); // garante fechamento do EntityManager
        }
    }

    /**
     * Busca todos os pagamentos associados a um usuário específico.
     *
     * <p>Verifica se o usuário e o id são válidos antes de executar a consulta.
     * A busca utiliza o relacionamento Locacao -> Usuario.</p>
     *
     * @param usuario Usuário associado aos pagamentos
     * @return Lista de pagamentos do usuário
     * @throws IllegalArgumentException se o usuário for nulo ou não tiver id
     */
    public List<Pagamento> buscarPagamentosPorUsuario(Usuario usuario) {
        if (usuario == null || usuario.getId() == null) {
            throw new IllegalArgumentException("Usuário inválido");
        }

        EntityManager em = JPAUtil.getEntityManager();
        try {
            String jpql = "SELECT p FROM Pagamento p WHERE p.locacao.usuario.id = :usuarioId";
            TypedQuery<Pagamento> query = em.createQuery(jpql, Pagamento.class);
            query.setParameter("usuarioId", usuario.getId());
            return query.getResultList();
        } finally {
            em.close(); // garante fechamento do EntityManager
        }
    }
}
