package org.primeiroprojetocursooo.projetobancodedados2biblioteca.entity;

import jakarta.persistence.*;
import org.primeiroprojetocursooo.projetobancodedados2biblioteca.entity.enums.LivroStatus;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Livro implements Serializable {
    private static final long serialVersionUID = 1L;

    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    private Integer id;
    private String titulo;
    private Double preco;
    private LivroStatus status;

    @ManyToOne
    @JoinColumn(name="categoria_id")
    private Categoria categoria;

    @OneToMany(mappedBy = "locacao", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Locacao> locacoes = new HashSet<>();

    public Livro() {}

    public Livro(Integer id, String titulo, Double preco) {
        this.id = id;
        this.titulo = titulo;
        this.preco = preco;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Set<Locacao> getLocacoes() {
        return locacoes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Livro livro = (Livro) o;
        return Objects.equals(id, livro.id);
    }
    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
