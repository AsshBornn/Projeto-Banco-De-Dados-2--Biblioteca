package org.primeiroprojetocursooo.projetobancodedados2biblioteca.services;

import org.primeiroprojetocursooo.projetobancodedados2biblioteca.DAO.GenericDAO;
import java.util.List;

/**
 * Serviço genérico para manipulação de entidades.
 *
 * <p>
 * Encapsula operações básicas de CRUD utilizando um GenericDAO.
 * Permite reaproveitamento de código para qualquer entidade T com um DAO específico Y.
 * </p>
 *
 * <p>
 * <b>Parâmetros genéricos:</b>
 * <ul>
 *   <li>T: tipo da entidade (ex: Usuario, Livro, Locacao)</li>
 *   <li>Y: tipo do DAO correspondente, que deve estender GenericDAO<T></li>
 * </ul>
 * </p>
 *
 * <p>
 * Boas práticas aplicadas:
 * - Serviço delega operações para DAO, mantendo separação de responsabilidades.
 * - Permite acesso ao DAO específico através do método getDao() sem criar novas instâncias.
 * - Mantém consistência de transações e EntityManager gerenciados pelo DAO.
 * </p>
 *
 * @param <T> Entidade que será manipulada
 * @param <Y> DAO correspondente à entidade T
 */
public class GenericService<T, Y extends GenericDAO<T>> {

    /** DAO específico responsável pelas operações de persistência */
    private final Y dao;

    /**
     * Construtor que recebe o DAO específico.
     *
     * @param dao DAO correspondente à entidade T
     */
    public GenericService(Y dao) {
        this.dao = dao;
    }

    // ===== Operações CRUD básicas delegadas ao DAO =====

    /**
     * Salva a entidade no banco de dados.
     *
     * @param entidade Entidade a ser persistida
     */
    public void salvar(T entidade) {
        dao.salvar(entidade);
    }

    /**
     * Atualiza a entidade no banco de dados.
     *
     * @param entidade Entidade a ser atualizada
     */
    public void atualizar(T entidade) {
        dao.atualizar(entidade);
    }

    /**
     * Remove a entidade do banco pelo seu ID.
     *
     * @param id Identificador da entidade a ser removida
     */
    public void excluir(Integer id) {
        dao.excluir(id);
    }

    /**
     * Busca uma entidade pelo seu ID.
     *
     * @param id Identificador da entidade
     * @return Entidade correspondente ao ID ou null se não encontrada
     */
    public T buscarPorId(Integer id) {
        return dao.buscarPorId(id);
    }

    /**
     * Retorna todas as entidades do tipo T.
     *
     * @return Lista de entidades
     */
    public List<T> listar() {
        return dao.listar();
    }

    // ===== Acesso ao DAO específico =====

    /**
     * Retorna o DAO específico utilizado pelo serviço.
     *
     * <p>
     * Permite acessar métodos adicionais do DAO que não estão no GenericService
     * sem criar uma nova instância.
     * </p>
     *
     * @return DAO específico
     */
    public Y getDao() {
        return dao;
    }
}
