package org.primeiroprojetocursooo.projetobancodedados2biblioteca.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * Entidade Usuario representando os usuários do sistema de locação de livros.
 * Cada usuário pode ter várias locações associadas (One-to-Many).
 *
 * Implementa Serializable para persistência e transmissão de objetos.
 */
@Entity
@Table(name = "tb_usuario")
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Identificador único do usuário.
     * Gerado automaticamente pelo banco de dados.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Nome do usuário.
     */
    private String nome;

    /**
     * Email do usuário. Deve ser único no banco de dados.
     */
    @Column(unique = true)
    private String email;

    /**
     * Telefone do usuário. Deve ser único no banco de dados.
     */
    @Column(unique = true)
    private String telefone;

    /**
     * Conjunto de locações associadas ao usuário.
     * Relação One-to-Many:
     * - Um usuário pode ter várias locações.
     * - O atributo "usuario" na entidade Locacao referencia esta relação.
     * - FetchType.EAGER: Carrega as locações automaticamente junto com o usuário.
     */
    @OneToMany(mappedBy = "usuario", fetch = FetchType.EAGER)
    private Set<Locacao> locacoes;

    // ===========================
    // Construtores
    // ===========================

    /**
     * Construtor padrão sem argumentos.
     * Necessário para o funcionamento do JPA.
     */
    public Usuario() {}

    /**
     * Construtor para facilitar a criação manual de objetos Usuario.
     *
     * @param id Identificador do usuário
     * @param nome Nome do usuário
     * @param email Email do usuário
     * @param telefone Telefone do usuário
     */
    public Usuario(Integer id, String nome, String email, String telefone) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Set<Locacao> getLocacoes() {
        return locacoes;
    }

    // ===========================
    // equals e hashCode
    // ===========================

    /**
     * Compara usuários pelo identificador único.
     * Essencial para consistência em coleções (Set, Map) e para o JPA.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(id, usuario.id);
    }

    /**
     * Retorna hashCode baseado no id do usuário.
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
