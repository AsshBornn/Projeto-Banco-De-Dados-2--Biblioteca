package org.primeiroprojetocursooo.projetobancodedados2biblioteca.services;

import org.primeiroprojetocursooo.projetobancodedados2biblioteca.DAO.LocacaoDAO;
import org.primeiroprojetocursooo.projetobancodedados2biblioteca.entity.Locacao;
import org.primeiroprojetocursooo.projetobancodedados2biblioteca.entity.enums.LocacaoStatus;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

/**
 * Serviço específico para a entidade Locacao.
 *
 * <p>
 * Responsável por gerenciar regras de negócio relacionadas a locações de livros,
 * incluindo validação, status e delegação de persistência ao DAO.
 * </p>
 *
 * <p>
 * Boas práticas aplicadas:
 * - Validação de entidade antes de salvar ou atualizar.
 * - Verificação se a locação contém livros.
 * - Delegação de buscas complexas para o DAO específico.
 * - Uso de enums (LocacaoStatus) para controle de status das locações.
 * </p>
 */
public class LocacaoService extends GenericService<Locacao, LocacaoDAO> {

    /**
     * Construtor que instancia o GenericService com o DAO específico.
     */
    public LocacaoService() {
        super(new LocacaoDAO(Locacao.class));
    }

    /**
     * Salva uma locação após validação.
     *
     * @param locacao Locação a ser salva
     * @throws IllegalArgumentException se a locação for inválida
     */
    @Override
    public void salvar(Locacao locacao) {
        if (isValid(locacao)) {
            super.salvar(locacao); // chama o método do GenericService
        }
    }

    /**
     * Atualiza uma locação existente após validação.
     *
     * @param locacao Locação a ser atualizada
     * @throws IllegalArgumentException se a locação for inválida
     */
    @Override
    public void atualizar(Locacao locacao) {
        if (isValid(locacao)) {
            super.atualizar(locacao);
        }
    }

    /**
     * Valida a entidade Locacao antes de persistir ou atualizar.
     *
     * <p>
     * Regras de validação:
     * - A locação não pode ser nula.
     * - Deve conter pelo menos um livro.
     * </p>
     *
     * @param locacao Locação a ser validada
     * @return true se válido
     * @throws IllegalArgumentException se não atender as regras
     */
    public boolean isValid(Locacao locacao) {
        if (locacao == null) {
            throw new IllegalArgumentException("Locação não pode ser nula");
        }
        if (locacao.getLivros() == null || locacao.getLivros().isEmpty()) {
            throw new IllegalArgumentException("A locação precisa ter pelo menos um livro");
        }
        return true;
    }

    /**
     * Busca locações filtradas pelo status.
     *
     * @param status Status da locação (LOCADA, FINALIZADA, ATRASADA)
     * @return Lista de locações correspondentes
     */
    public List<Locacao> buscarLocacoes(LocacaoStatus status) {
        if (status == LocacaoStatus.FINALIZADA) {
            return getDao().buscarLocacoesFinalizadas();
        }
        if (status == LocacaoStatus.LOCADA) {
            return getDao().buscarLocacoesAtivas();
        }
        if (status == LocacaoStatus.ATRASADA) {
            return getDao().buscarLocacoesAtrasadas();
        }
        System.out.println("⚠️ Status não identificado: " + status);
        return Collections.emptyList();
    }

    /**
     * Busca locações dentro de um período de datas.
     *
     * @param dataInicio Data inicial do período
     * @param dataFim    Data final do período
     * @return Lista de locações dentro do período
     */
    public List<Locacao> buscarLocacaoPorPeriodo(LocalDate dataInicio, LocalDate dataFim) {
        return getDao().buscarPorPeriodo(dataInicio, dataFim);
    }
}
