package org.primeiroprojetocursooo.projetobancodedados2biblioteca.entity;

import jakarta.persistence.*;
import org.primeiroprojetocursooo.projetobancodedados2biblioteca.entity.enums.LivroStatus;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * Entidade Livro representando os livros disponíveis na biblioteca.
 * Cada livro possui uma categoria (Many-to-One) e pode participar de múltiplas locações (Many-to-Many).
 * Implementa Serializable para permitir persistência e transmissão de objetos.
 */
@Entity
@Table(name = "tb_livro")
public class Livro implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Identificador único do livro.
     * Gerado automaticamente pelo banco de dados (IDENTITY).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Título do livro.
     * Campo único para evitar duplicidade de registros.
     */
    @Column(unique = true)
    private String titulo;

    /**
     * Preço do livro.
     */
    private Double preco;

    /**
     * Status do livro (DISPONIVEL, LOCADO, NAO_DISPONIVEL).
     * Mapear enums como String no banco facilita leitura e manutenção.
     */
    @Enumerated(EnumType.STRING)
    private LivroStatus status;

    /**
     * Categoria à qual o livro pertence.
     * Relação Many-to-One: muitos livros podem pertencer a uma categoria.
     * A coluna "categoria_id" referencia a chave primária da tabela Categoria.
     */
    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    /**
     * Conjunto de locações que incluem este livro.
     * Relação Many-to-Many com a entidade Locacao, mapeada pelo atributo "livros" em Locacao.
     * FetchType.EAGER para carregar as locações junto com o livro.
     */
    @ManyToMany(mappedBy = "livros", fetch = FetchType.EAGER)
    private Set<Locacao> locacoes;

    // ===========================
    // Construtores
    // ===========================

    /**
     * Construtor padrão sem argumentos.
     * Necessário para JPA.
     */
    public Livro() {}

    /**
     * Construtor para facilitar a criação de objetos manualmente.
     *
     * @param id Identificador do livro
     * @param titulo Título do livro
     * @param preco Preço do livro
     */
    public Livro(Integer id, String titulo, Double preco) {
        this.id = id;
        this.titulo = titulo;
        this.preco = preco;
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

    public LivroStatus getStatus() {
        return status;
    }

    public void setStatus(LivroStatus status) {
        this.status = status;
    }

    public Set<Locacao> getLocacoes() {
        return locacoes;
    }

    // ===========================
    // equals, hashCode e toString
    // ===========================

    /**
     * Compara livros pelo identificador único.
     * Essencial para consistência em coleções (Set, Map).
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Livro livro = (Livro) o;
        return Objects.equals(id, livro.id);
    }

    /**
     * Retorna hashCode baseado no id do livro.
     * Necessário para uso correto em coleções baseadas em hash.
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    /**
     * Representação em String do livro.
     * Útil para exibição em logs, menus e relatórios.
     */
    @Override
    public String toString() {
        return titulo + " (ID: " + id + ")";
    }
}
