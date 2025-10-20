package org.primeiroprojetocursooo.projetobancodedados2biblioteca.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Entidade Pagamento representando o pagamento de uma locação de livros.
 * Cada pagamento está associado a uma única locação (One-to-One).
 *
 * Novidade: agora o valor total da locação é persistido no banco.
 *
 * Implementa Serializable para persistência e transmissão de objetos.
 */
@Entity
@Table(name = "tb_pagamento")
public class Pagamento implements Serializable {

    private static final long serialVersionUID = 1L;

    // ===========================
    // Atributos
    // ===========================

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
     *
     * Ao definir a locação, o valor é calculado automaticamente.
     */
    @OneToOne
    @JoinColumn(name = "locacao_id", unique = true)
    private Locacao locacao;

    /**
     * Valor total do pagamento.
     * Persistido no banco para permitir consultas SQL diretas.
     */
    @Column(nullable = false)
    private Double valor = 0.0;

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
        this.setLocacao(locacao); // calcula valor automaticamente
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

    /**
     * Define a locação associada a este pagamento.
     * Ao associar, o valor total é calculado automaticamente.
     *
     * @param locacao Locação vinculada
     */
    public void setLocacao(Locacao locacao) {
        this.locacao = locacao;
        if (locacao != null) {
            this.valor = locacao.getValorTotal();
        } else {
            this.valor = 0.0;
        }
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    // ===========================
    // equals e hashCode
    // ===========================

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pagamento pagamento = (Pagamento) o;
        return Objects.equals(id, pagamento.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
