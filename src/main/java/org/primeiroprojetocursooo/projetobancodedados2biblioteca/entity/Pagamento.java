package org.primeiroprojetocursooo.projetobancodedados2biblioteca.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Entidade Pagamento representando o pagamento de uma locação de livros.
 * Cada pagamento está associado a uma única locação (One-to-One).
 *
 * Implementa Serializable para persistência e transmissão de objetos.
 */
@Entity
@Table(name = "tb_pagamento")
public class Pagamento implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Identificador único do pagamento.
     * Gerado automaticamente pelo banco de dados.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Data em que o pagamento foi realizado.
     */
    private LocalDate dataPagamento;

    /**
     * Locação associada a este pagamento.
     * Relação One-to-One:
     * - Cada pagamento pertence a uma única locação.
     * - O atributo "locacao_id" na tabela tb_pagamento referencia a locação correspondente.
     */
    @OneToOne
    @JoinColumn(name = "locacao_id")
    private Locacao locacao;

    // ===========================
    // Construtores
    // ===========================

    /**
     * Construtor padrão sem argumentos.
     * Necessário para o funcionamento do JPA.
     */
    public Pagamento() {}

    /**
     * Construtor para facilitar a criação manual de objetos Pagamento.
     *
     * @param id Identificador do pagamento
     * @param dataPagamento Data em que o pagamento foi efetuado
     * @param locacao Locação associada a este pagamento
     */
    public Pagamento(Integer id, LocalDate dataPagamento, Locacao locacao) {
        this.id = id;
        this.dataPagamento = dataPagamento;
        this.locacao = locacao;
    }

    // ===========================
    // Getters e Setters
    // ===========================

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDate dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public Locacao getLocacao() {
        return locacao;
    }

    public void setLocacao(Locacao locacao) {
        this.locacao = locacao;
    }

    // ===========================
    // Métodos auxiliares
    // ===========================

    /**
     * Retorna o valor total do pagamento, baseado no valor total da locação associada.
     * Se não houver locação vinculada, retorna 0.0.
     *
     * Boa prática:
     * - Evita NullPointerException verificando se locacao é null.
     * - Reutiliza lógica centralizada em Locacao.getValorTotal().
     *
     * @return Valor total a ser pago
     */
    public Double getValor() {
        if (locacao == null) return 0.0;
        return locacao.getValorTotal();
    }

    // ===========================
    // equals e hashCode
    // ===========================

    /**
     * Compara pagamentos pelo identificador único.
     * Essencial para consistência em coleções (Set, Map) e para o JPA.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pagamento pagamento = (Pagamento) o;
        return Objects.equals(id, pagamento.id);
    }

    /**
     * Retorna hashCode baseado no id do pagamento.
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
