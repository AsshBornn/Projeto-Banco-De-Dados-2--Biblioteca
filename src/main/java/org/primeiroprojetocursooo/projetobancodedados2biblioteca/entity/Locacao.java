package org.primeiroprojetocursooo.projetobancodedados2biblioteca.entity;

import jakarta.persistence.*;
import org.primeiroprojetocursooo.projetobancodedados2biblioteca.entity.enums.LocacaoStatus;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
@Entity
public class Locacao implements Serializable {
    private static final long serialVersionUID = 1L;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;
    private LocalDate dataLocacao;
    private LocalTime dataDevolucao;
    private LocacaoStatus status;

    @ManyToOne
    @JoinColumn(name = "livro_id")
    private Livro livro;

    public Locacao() {}

    public Locacao(Integer id, LocalDate dataLocacao, LocalTime dataDevolucao) {
        this.id = id;
        this.dataLocacao = dataLocacao;
        this.dataDevolucao = dataDevolucao;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDataLocacao() {
        return dataLocacao;
    }

    public void setDataLocacao(LocalDate dataLocacao) {
        this.dataLocacao = dataLocacao;
    }

    public LocalTime getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalTime dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public LocacaoStatus getStatus() {
        return status;
    }

    public void setStatus(LocacaoStatus status) {
        this.status = status;
    }


}
