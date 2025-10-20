package org.primeiroprojetocursooo.projetobancodedados2biblioteca.DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.primeiroprojetocursooo.projetobancodedados2biblioteca.entity.Usuario;
import org.primeiroprojetocursooo.projetobancodedados2biblioteca.util.JPAUtil;

import java.util.List;

/**
 * DAO específico para a entidade Usuario.
 *
 * <p>
 * Extende GenericDAO para herdar operações CRUD básicas e adiciona métodos
 * específicos de Usuario, como verificações de email ou busca por nome.
 * </p>
 *
 * <p>
 * Destaques:
 * - Relacionamento OneToMany com Locacao (cada usuário pode ter várias locações)
 * - Validação de existência de email antes de inserir ou atualizar
 * </p>
 */
public class UsuarioDAO extends GenericDAO<Usuario> {

    /**
     * Construtor que passa a classe Usuario para o GenericDAO
     *
     * @param clazz Classe da entidade Usuario
     */
    public UsuarioDAO(Class<Usuario> clazz) {
        super(clazz);
    }

    // ===== CRUD Básico =====

    @Override
    public void salvar(Usuario entidade) {
        super.salvar(entidade);
    }

    @Override
    public List<Usuario> listar() {
        return super.listar();
    }

    @Override
    public Usuario buscarPorId(Integer id) {
        return super.buscarPorId(id);
    }

    @Override
    public void atualizar(Usuario entidade) {
        super.atualizar(entidade);
    }

    @Override
    public void excluir(Integer id) {
        super.excluir(id);
    }

    // ===== Consultas específicas =====

    /**
     * Busca usuários pelo nome.
     *
     * <p>Atualmente retorna uma lista vazia (a ser implementado).</p>
     * <p>Recomendação: usar LIKE no JPQL para buscar nomes parcialmente correspondentes.</p>
     *
     * @param nome Nome do usuário a ser buscado
     * @return Lista de usuários que correspondem ao nome
     */
    public List<Usuario> buscarPorNome(String nome) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            // Exemplo de implementação futura:
            // String jpql = "SELECT u FROM Usuario u WHERE LOWER(u.nome) LIKE LOWER(:nome)";
            // TypedQuery<Usuario> query = em.createQuery(jpql, Usuario.class);
            // query.setParameter("nome", "%" + nome + "%");
            // return query.getResultList();

            return List.of(); // placeholder para estudo ou implementação futura
        } finally {
            em.close(); // garante fechamento do EntityManager
        }
    }

    /**
     * Verifica se já existe algum usuário com o mesmo email.
     *
     * <p>Utilizado para evitar duplicidade de emails, que devem ser únicos.</p>
     *
     * @param email Email a ser verificado
     * @return true se o email já existe, false caso contrário
     */
    public boolean existeEmail(String email) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            String jpql = "SELECT u FROM Usuario u WHERE LOWER(u.email)= LOWER(:email)";
            TypedQuery<Usuario> query = em.createQuery(jpql, Usuario.class);
            query.setParameter("email", email);
            return !query.getResultList().isEmpty(); // se a lista não estiver vazia, o email existe
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao verificar existência de email", e);
        } finally {
            em.close(); // garante fechamento do EntityManager
        }
    }
}
