package org.primeiroprojetocursooo.projetobancodedados2biblioteca.DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.primeiroprojetocursooo.projetobancodedados2biblioteca.entity.Livro;
import org.primeiroprojetocursooo.projetobancodedados2biblioteca.util.JPAUtil;

import java.util.List;

/**
 * DAO específico para a entidade Livro.
 *
 * <p>
 * Extende GenericDAO para herdar operações CRUD básicas e adiciona métodos
 * específicos de Livro, como verificação de existência de título e busca por título.
 * </p>
 *
 * <p>
 * Destaques:
 * - O relacionamento ManyToMany com Locacao é tratado na entidade Livro.
 * - O enum LivroStatus deve ser utilizado corretamente ao criar ou atualizar livros.
 * </p>
 */
public class LivroDAO extends GenericDAO<Livro> {

    /**
     * Construtor que passa a classe Livro para o GenericDAO.
     *
     * @param clazz Classe da entidade Livro
     */
    public LivroDAO(Class<Livro> clazz) {
        super(clazz);
    }

    /**
     * Salva um livro no banco de dados.
     *
     * <p>Chama o método salvar do GenericDAO.</p>
     *
     * @param entidade Livro a ser persistido
     */
    @Override
    public void salvar(Livro entidade) {
        super.salvar(entidade);
    }

    /**
     * Lista todos os livros cadastrados.
     *
     * <p>Chama o método listar do GenericDAO.</p>
     *
     * @return Lista de livros
     */
    @Override
    public List<Livro> listar() {
        return super.listar();
    }

    /**
     * Busca um livro por seu ID.
     *
     * <p>Chama o método buscarPorId do GenericDAO.</p>
     *
     * @param id ID do livro
     * @return Livro correspondente ou null se não encontrado
     */
    @Override
    public Livro buscarPorId(Integer id) {
        return super.buscarPorId(id);
    }

    /**
     * Atualiza um livro existente.
     *
     * <p>Chama o método atualizar do GenericDAO.</p>
     *
     * @param entidade Livro a ser atualizado
     */
    @Override
    public void atualizar(Livro entidade) {
        super.atualizar(entidade);
    }

    /**
     * Exclui um livro pelo ID.
     *
     * <p>Chama o método excluir do GenericDAO.</p>
     *
     * @param id ID do livro a ser removido
     */
    @Override
    public void excluir(Integer id) {
        super.excluir(id);
    }

    /**
     * Verifica se já existe um livro com o título fornecido.
     *
     * <p>Boas práticas:
     * - Usa LOWER() para comparação case-insensitive.
     * - Fecha o EntityManager no bloco finally.
     * - Captura exceções e fornece feedback detalhado.
     * </p>
     *
     * @param titulo Título do livro a verificar
     * @return true se já existir, false caso contrário
     */
    public boolean existeTitulo(String titulo) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            String jpql = "SELECT l FROM Livro l WHERE LOWER(l.titulo) = LOWER(:titulo)";
            TypedQuery<Livro> query = em.createQuery(jpql, Livro.class);
            query.setParameter("titulo", titulo);
            return !query.getResultList().isEmpty();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao verificar a existência do título", e);
        } finally {
            em.close(); // garante fechamento do EntityManager
        }
    }

    /**
     * Busca livros cujo título contenha a string fornecida.
     *
     * <p>Boas práticas:
     * - Usa LIKE e LOWER() para busca case-insensitive e parcial.
     * - Fecha o EntityManager no bloco finally.
     * </p>
     *
     * @param titulo Parte do título a ser buscado
     * @return Lista de livros que correspondem ao critério
     */
    public List<Livro> buscarPorTitulo(String titulo) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            String jpql = "SELECT l FROM Livro l WHERE LOWER(l.titulo) LIKE LOWER(:titulo)";
            TypedQuery<Livro> query = em.createQuery(jpql, Livro.class);
            query.setParameter("titulo", "%" + titulo + "%"); // busca parcial
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao procurar livros por título", e);
        } finally {
            em.close(); // garante fechamento do EntityManager
        }
    }
}
