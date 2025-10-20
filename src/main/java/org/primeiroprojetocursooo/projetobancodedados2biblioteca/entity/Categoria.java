package org.primeiroprojetocursooo.projetobancodedados2biblioteca.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * Classe representando a entidade Categoria no sistema de biblioteca.
 * Cada categoria pode conter múltiplos livros (relação 1:N).
 * Implementa Serializable para permitir persistência e transmissão de objetos.
 */
@Entity
@Table(name = "tb_categoria")
public class Categoria implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Identificador único da categoria.
     * Gerado automaticamente pelo banco de dados (IDENTITY).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Descrição da categoria (ex.: "Ficção", "Ciência").
     * Campo único para evitar duplicidade de categorias no banco.
     */
    @Column(unique = true)
    private String descricao;

    /**
     * Conjunto de livros associados a esta categoria.
     * Mapeamento One-to-Many (uma categoria possui vários livros).
     * FetchType.EAGER para carregar os livros junto com a categoria.
     * mappedBy indica que o relacionamento é mantido pela propriedade "categoria" na entidade Livro.
     */
    @OneToMany(mappedBy = "categoria", fetch = FetchType.EAGER)
    private Set<Livro> livros;

    /**
     * Construtor padrão sem argumentos.
     * Necessário para a criação de instâncias pelo JPA.
     */
    public Categoria() {}

    /**
     * Construtor para facilitar a criação de categorias manualmente.
     * @param id Identificador da categoria.
     * @param descricao Nome ou descrição da categoria.
     */
    public Categoria(Integer id, String descricao) {
        this.id = id;
        this.descricao = descricao;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Set<Livro> getLivros() {
        return livros;
    }

    // ===========================
    // equals e hashCode
    // ===========================

    /**
     * Compara categorias pelo seu identificador único.
     * Essencial para garantir consistência em coleções (Set, Map).
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Categoria categoria = (Categoria) o;
        return Objects.equals(id, categoria.id);
    }

    /**
     * Retorna hashCode baseado no id da categoria.
     * Necessário para uso correto em coleções baseadas em hash.
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
