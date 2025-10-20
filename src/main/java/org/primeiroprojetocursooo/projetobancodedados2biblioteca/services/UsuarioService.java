package org.primeiroprojetocursooo.projetobancodedados2biblioteca.services;

import org.primeiroprojetocursooo.projetobancodedados2biblioteca.DAO.UsuarioDAO;
import org.primeiroprojetocursooo.projetobancodedados2biblioteca.entity.Usuario;

import java.util.List;

/**
 * Serviço específico para a entidade Usuario.
 *
 * <p>
 * Responsável por gerenciar regras de negócio relacionadas aos usuários,
 * incluindo validação, verificação de e-mail e buscas por nome.
 * </p>
 *
 * <p>
 * Boas práticas aplicadas:
 * - Validação de entidade antes de salvar ou atualizar.
 * - Delegação de operações CRUD ao GenericService e consultas específicas ao DAO.
 * - Mensagens de erro claras e consistentes.
 * </p>
 */
public class UsuarioService extends GenericService<Usuario, UsuarioDAO> {

    /**
     * Construtor que instancia o GenericService com o DAO específico.
     */
    public UsuarioService() {
        super(new UsuarioDAO(Usuario.class));
    }

    /**
     * Salva um usuário após validação.
     *
     * @param usuario Usuário a ser salvo
     * @throws IllegalArgumentException se o usuário for inválido ou o e-mail já estiver em uso
     */
    @Override
    public void salvar(Usuario usuario) {
        // Verifica se o e-mail já está cadastrado
        if (existeEmail(usuario.getEmail())) {
            throw new IllegalArgumentException("E-mail já utilizado");
        }
        // Valida o usuário
        if (isValid(usuario)) {
            super.salvar(usuario); // Chama o método do GenericService
        }
    }

    /**
     * Atualiza um usuário após validação.
     *
     * @param usuario Usuário a ser atualizado
     * @throws IllegalArgumentException se o usuário for inválido
     */
    @Override
    public void atualizar(Usuario usuario) {
        if (isValid(usuario)) {
            super.atualizar(usuario);
        }
    }

    /**
     * Valida a entidade Usuario antes de persistir ou atualizar.
     *
     * Regras de validação:
     * - O usuário não pode ser nulo.
     * - Nome e e-mail não podem estar vazios.
     *
     * @param usuario Usuário a ser validado
     * @return true se válido
     * @throws IllegalArgumentException se a validação falhar
     */
    public boolean isValid(Usuario usuario) {
        if (usuario == null) {
            throw new IllegalArgumentException("Usuário inválido");
        }
        if (usuario.getNome() == null || usuario.getNome().isBlank()
                || usuario.getEmail() == null || usuario.getEmail().isBlank()) {
            throw new IllegalArgumentException("Nome ou e-mail do usuário inválido");
        }
        return true;
    }

    /**
     * Busca usuários pelo nome (parcial ou completo).
     *
     * @param nome Nome ou parte do nome a ser pesquisado
     * @return Lista de usuários correspondentes
     */
    public List<Usuario> buscarPorNome(String nome) {
        return getDao().buscarPorNome(nome);
    }

    /**
     * Verifica se um e-mail já está cadastrado no sistema.
     *
     * @param email E-mail a ser verificado
     * @return true se já existir, false caso contrário
     */
    public boolean existeEmail(String email) {
        return getDao().existeEmail(email);
    }
}
