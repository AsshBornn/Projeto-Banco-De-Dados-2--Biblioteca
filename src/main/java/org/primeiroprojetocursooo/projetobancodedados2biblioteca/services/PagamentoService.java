package org.primeiroprojetocursooo.projetobancodedados2biblioteca.services;

import org.primeiroprojetocursooo.projetobancodedados2biblioteca.DAO.PagamentoDAO;
import org.primeiroprojetocursooo.projetobancodedados2biblioteca.entity.Pagamento;
import org.primeiroprojetocursooo.projetobancodedados2biblioteca.entity.Usuario;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Serviço específico para a entidade Pagamento.
 *
 * <p>
 * Responsável por gerenciar regras de negócio relacionadas a pagamentos,
 * incluindo validação, buscas por período ou por usuário.
 * </p>
 *
 * <p>
 * Boas práticas aplicadas:
 * - Validação de entidade antes de salvar.
 * - Uso de GenericService para operações CRUD básicas.
 * - Delegação de consultas complexas ao DAO específico.
 * - Formatação de datas padronizada para uso interno ou exibição.
 * </p>
 */
public class PagamentoService extends GenericService<Pagamento, PagamentoDAO> {

    /**
     * Formato de data padrão (dd/MM/yyyy) para exibição ou conversão.
     */
    private static final DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    /**
     * Construtor que instancia o GenericService com o DAO específico.
     */
    public PagamentoService() {
        super(new PagamentoDAO(Pagamento.class));
    }

    /**
     * Salva um pagamento após validação.
     *
     * @param pagamento Pagamento a ser salvo
     * @throws IllegalArgumentException se o pagamento for inválido
     */
    @Override
    public void salvar(Pagamento pagamento) {
        if (isValid(pagamento)) {
            super.salvar(pagamento); // Chama método do GenericService para persistência
        }
    }

    /**
     * Valida a entidade Pagamento antes de persistir.
     *
     * <p>
     * Regras de validação:
     * - O pagamento não pode ser nulo.
     * - A locação associada ao pagamento não pode ser nula.
     * </p>
     *
     * @param pagamento Pagamento a ser validado
     * @return true se válido
     * @throws IllegalArgumentException se não atender as regras
     */
    public boolean isValid(Pagamento pagamento) {
        if (pagamento == null) {
            throw new IllegalArgumentException("Pagamento não pode ser nulo");
        }
        if (pagamento.getLocacao() == null) {
            throw new IllegalArgumentException("Locação não pode ser nula");
        }
        return true;
    }

    /**
     * Busca pagamentos realizados dentro de um período específico.
     *
     * @param dataInicio Data inicial do período
     * @param dataFim    Data final do período
     * @return Lista de pagamentos correspondentes ao período
     */
    public List<Pagamento> buscarPorPeriodo(LocalDate dataInicio, LocalDate dataFim) {
        return getDao().buscarPagamentosPorPeriodo(dataInicio, dataFim);
    }

    /**
     * Busca pagamentos realizados por um usuário específico.
     *
     * @param usuario Usuário que realizou os pagamentos
     * @return Lista de pagamentos do usuário
     * @throws IllegalArgumentException se o usuário for nulo ou inválido
     */
    public List<Pagamento> buscarPagamentosPorUsuario(Usuario usuario) {
        if (usuario == null || usuario.getId() == null) {
            throw new IllegalArgumentException("Usuário inválido para busca de pagamentos");
        }
        return getDao().buscarPagamentosPorUsuario(usuario);
    }
}
