package org.primeiroprojetocursooo.projetobancodedados2biblioteca.entity;

import jakarta.persistence.*;
import org.primeiroprojetocursooo.projetobancodedados2biblioteca.entity.enums.LocacaoStatus;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="tb_locacao")
public class Locacao implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDate dataLocacao;
    private LocalDate dataDevolucao;
    @Enumerated(EnumType.STRING)
    private LocacaoStatus status;


    @ManyToOne
    @JoinColumn(name="cliente_id")
    private Usuario usuario;

    @ManyToMany
    @JoinTable(
            name = "tb_locacao_livro",
            joinColumns = @JoinColumn(name = "locacao_id"),
            inverseJoinColumns = @JoinColumn(name = "livro_id")
    )
    private Set<Livro> livros = new HashSet<>();


    public Locacao() {}

    public Locacao(Integer id, LocalDate dataLocacao, LocalDate dataDevolucao) {
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

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public LocacaoStatus getStatus() {
        return status;
    }

    public void setStatus(LocacaoStatus status) {
        this.status = status;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Set<Livro> getLivros() {
        return livros;
    }

    public Double getValorTotal() {
        if (livros.isEmpty()) return 0.0;

        long dias = java.time.temporal.ChronoUnit.DAYS.between(dataLocacao, dataDevolucao);
        if (dias <= 0) dias = 1; // mínimo de 1 dia

        double valorTotal = 0.0;
        for (Livro livro : livros) {
            valorTotal += livro.getPreco() * dias;
        }
        return valorTotal;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Locacao locacao = (Locacao) o;
        return Objects.equals(id, locacao.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
