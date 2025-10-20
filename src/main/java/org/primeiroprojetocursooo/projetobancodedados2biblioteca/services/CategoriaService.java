package org.primeiroprojetocursooo.projetobancodedados2biblioteca.services;

import org.primeiroprojetocursooo.projetobancodedados2biblioteca.DAO.CategoriaDAO;
import org.primeiroprojetocursooo.projetobancodedados2biblioteca.entity.Categoria;

import java.util.List;

/**
 * Serviço específico para a entidade Categoria.
 *
 * <p>
 * Responsável por gerenciar regras de negócio relacionadas a Categoria,
 * delegando operações de persistência ao DAO correspondente.
 * </p>
 *
 * <p>
 * Boas práticas aplicadas:
 * - Validação antes de salvar ou atualizar a entidade.
 * - Reuso do GenericService para operações CRUD básicas.
 * - Uso do DAO específico via getDao() para métodos customizados.
 * </p>
 */
public class CategoriaService extends GenericService<Categoria, CategoriaDAO> {

    /**
     * Construtor que instancia o GenericService com o DAO específico.
     */
    public CategoriaService() {
        super(new CategoriaDAO(Categoria.class)); // passa o DAO específico para o GenericService
    }

    /**
     * Salva uma nova categoria após validação.
     *
     * @param categoria Categoria a ser salva
     * @throws IllegalArgumentException se a categoria for inválida
     */
    @Override
    public void salvar(Categoria categoria) {
        if (isValid(categoria)) {
            super.salvar(categoria); // chama o método do GenericService
        }
    }

    /**
     * Atualiza uma categoria existente após validação.
     *
     * @param categoria Categoria a ser atualizada
     * @throws IllegalArgumentException se a categoria for inválida
     */
    @Override
    public void atualizar(Categoria categoria) {
        if (isValid(categoria)) {
            super.atualizar(categoria); // chama o método do GenericService
        }
    }

    /**
     * Busca categorias cujo nome contém a descrição informada.
     *
     * @param descricao Parte ou total da descrição
     * @return Lista de categorias correspondentes
     */
    public List<Categoria> buscarPorDescricao(String descricao) {
        return getDao().buscarPorDescricao(descricao);
    }

    /**
     * Valida a entidade Categoria antes de persistir ou atualizar.
     *
     * <p>
     * Regras de validação:
     * - Descrição não pode estar em branco
     * - Descrição não pode estar duplicada no banco
     * </p>
     *
     * @param categoria Categoria a ser validada
     * @return true se válida
     * @throws IllegalArgumentException se a categoria não atender as regras
     */
    private boolean isValid(Categoria categoria) {
        // Valida se a descrição não está vazia
        if (categoria.getDescricao() == null || categoria.getDescricao().isBlank()) {
            throw new IllegalArgumentException("Descrição não pode estar vazia");
        }
        // Valida se a descrição já existe no banco
        if (getDao().existeDescricao(categoria.getDescricao())) {
            throw new IllegalArgumentException("Descrição já cadastrada");
        }
        return true;
    }
}
