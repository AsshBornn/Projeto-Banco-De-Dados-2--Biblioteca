package org.primeiroprojetocursooo.projetobancodedados2biblioteca.services;

import org.primeiroprojetocursooo.projetobancodedados2biblioteca.DAO.LivroDAO;
import org.primeiroprojetocursooo.projetobancodedados2biblioteca.entity.Livro;
import org.primeiroprojetocursooo.projetobancodedados2biblioteca.entity.enums.LivroStatus;

import java.util.List;

/**
 * Serviço específico para a entidade Livro.
 *
 * <p>
 * Responsável por gerenciar regras de negócio relacionadas a Livro,
 * incluindo validação, status e delegação de persistência ao DAO.
 * </p>
 *
 * <p>
 * Boas práticas aplicadas:
 * - Validação antes de salvar ou atualizar a entidade.
 * - Define o status do livro como DISPONIVEL ao criar.
 * - Reuso do GenericService para operações CRUD básicas.
 * - Uso do DAO específico via getDao() para métodos customizados.
 * </p>
 */
public class LivroService extends GenericService<Livro, LivroDAO> {

    /**
     * Construtor que instancia o GenericService com o DAO específico.
     */
    public LivroService() {
        super(new LivroDAO(Livro.class)); // passa o DAO específico para o GenericService
    }

    /**
     * Salva um livro após validação e define seu status como DISPONÍVEL.
     *
     * @param livro Livro a ser salvo
     * @throws IllegalArgumentException se o livro for inválido
     */
    @Override
    public void salvar(Livro livro) {
        if (isValid(livro)) {
            livro.setStatus(LivroStatus.DISPONIVEL); // Marca o livro como disponível ao criar
            super.salvar(livro); // chama o método do GenericService
        }
    }

    /**
     * Atualiza um livro existente após validação.
     *
     * @param livro Livro a ser atualizado
     * @throws IllegalArgumentException se o livro for inválido
     */
    @Override
    public void atualizar(Livro livro) {
        if (isValid(livro)) {
            super.atualizar(livro); // chama o método do GenericService
        }
    }

    /**
     * Busca livros cujo título contém a string informada.
     *
     * @param titulo Parte ou total do título
     * @return Lista de livros correspondentes
     */
    public List<Livro> buscarPorTitulo(String titulo) {
        return getDao().buscarPorTitulo(titulo);
    }

    /**
     * Valida a entidade Livro antes de persistir ou atualizar.
     *
     * <p>
     * Regras de validação:
     * - Título não pode estar em branco
     * - Título não pode estar duplicado no banco
     * </p>
     *
     * @param livro Livro a ser validado
     * @return true se válido
     * @throws IllegalArgumentException se o livro não atender as regras
     */
    private boolean isValid(Livro livro) {
        // Valida se o título não está vazio
        if (livro.getTitulo() == null || livro.getTitulo().isBlank()) {
            throw new IllegalArgumentException("Título não pode estar vazio");
        }
        // Valida se o título já existe no banco
        if (getDao().existeTitulo(livro.getTitulo())) {
            throw new IllegalArgumentException("Título já existente");
        }
        return true;
    }
}
