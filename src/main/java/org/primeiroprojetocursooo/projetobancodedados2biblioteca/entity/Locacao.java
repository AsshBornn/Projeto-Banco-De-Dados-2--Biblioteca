package org.primeiroprojetocursooo.projetobancodedados2biblioteca.entity;

import jakarta.persistence.*;
import org.primeiroprojetocursooo.projetobancodedados2biblioteca.entity.enums.LocacaoStatus;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Entidade Locacao representando a locação de livros por usuários.
 * Cada locação possui:
 * - Um usuário responsável (Many-to-One)
 * - Vários livros associados (Many-to-Many)
 * - Um pagamento vinculado (One-to-One)
 * - Datas de locação e devolução, e status da locação.
 *
 * Implementa Serializable para persistência e transmissão de objetos.
 */
@Entity
@Table(name = "tb_locacao")
public class Locacao implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Identificador único da locação.
     * Gerado automaticamente pelo banco de dados.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Data em que a locação foi realizada.
     */
    private LocalDate dataLocacao;

    /**
     * Data prevista ou real de devolução.
     */
    private LocalDate dataDevolucao;

    /**
     * Status da locação: LOCADA, FINALIZADA ou ATRASADA.
     * Mapear enums como STRING facilita leitura no banco.
     */
    @Enumerated(EnumType.STRING)
    private LocacaoStatus status;

    /**
     * Usuário que realizou a locação.
     * Relação Many-to-One: muitos empréstimos podem estar associados a um usuário.
     */
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    /**
     * Livros incluídos nesta locação.
     * Relação Many-to-Many com join table "tb_locacao_livro".
     * Cascade MERGE e PERSIST permitem salvar/atualizar livros automaticamente.
     * FetchType.EAGER garante que livros sejam carregados junto com a locação.
     */
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JoinTable(
            name = "tb_locacao_livro",
            joinColumns = @JoinColumn(name = "locacao_id"),
            inverseJoinColumns = @JoinColumn(name = "livro_id")
    )
    private Set<Livro> livros = new HashSet<>();//HashSet inicialização no construtor: evitar NullPoint quando chamar GetLivros

    /**
     * Pagamento associado a esta locação.
     * Relação One-to-One mapeada pelo atributo "locacao" na entidade Pagamento.
     * Cascade.ALL garante propagação de todas as operações (persist, merge, remove) para o pagamento.
     */
    @OneToOne(mappedBy = "locacao", cascade = CascadeType.ALL)
    private Pagamento pagamento;

    // ===========================
    // Construtores
    // ===========================

    /**
     * Construtor padrão sem argumentos.
     * Necessário para JPA.
     */
    public Locacao() {}

    /**
     * Construtor para facilitar criação manual de objetos.
     *
     * @param id Identificador da locação
     * @param dataLocacao Data de início da locação
     * @param dataDevolucao Data de devolução prevista
     * @param status Status da locação
     */
    public Locacao(Integer id, LocalDate dataLocacao, LocalDate dataDevolucao, LocacaoStatus status) {
        this.id = id;
        this.dataLocacao = dataLocacao;
        this.dataDevolucao = dataDevolucao;
        this.status = status;
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

    public Pagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }

    // ===========================
    // Métodos auxiliares
    // ===========================

    /**
     * Calcula o valor total da locação baseado no preço de cada livro
     * e na quantidade de dias entre a locação e a devolução (ou hoje, se não devolvido).
     *
     * @return Valor total da locação
     */
    public Double getValorTotal() {
        if (livros.isEmpty() || dataLocacao == null) return 0.0;

        // Usa data atual caso a devolução ainda não tenha ocorrido
        LocalDate dataFinal = (dataDevolucao != null) ? dataDevolucao : LocalDate.now();

        // Calcula quantidade de dias, garantindo pelo menos 1 dia
        double dias = Math.max(1, java.time.temporal.ChronoUnit.DAYS.between(dataLocacao, dataFinal));

        // Soma o preço de cada livro multiplicado pelo número de dias
        return livros.stream()
                .mapToDouble(l -> l.getPreco() * dias)
                .sum();
    }

    // ===========================
    // equals, hashCode
    // ===========================

    /**
     * Compara locações pelo identificador único.
     * Essencial para consistência em coleções (Set, Map).
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Locacao locacao = (Locacao) o;
        return Objects.equals(id, locacao.id);
    }

    /**
     * Retorna hashCode baseado no id da locação.
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
