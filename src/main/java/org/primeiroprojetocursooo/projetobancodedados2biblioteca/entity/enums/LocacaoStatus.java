package org.primeiroprojetocursooo.projetobancodedados2biblioteca.entity.enums;

/**
 * Enum LocacaoStatus representa o status de uma locação de livro no sistema.
 *
 * Cada valor indica a situação atual da locação:
 *
 * - LOCADA: A locação está ativa, o livro foi retirado pelo usuário e ainda não foi devolvido.
 * - FINALIZADA: O livro foi devolvido e a locação foi concluída.
 * - ATRASADA: A locação está vencida, ou seja, a data de devolução passou e o livro não foi devolvido.
 *
 * Observações e boas práticas:
 * - Este enum deve ser utilizado nas entidades JPA (ex.: Locacao) com a anotação
 *   @Enumerated(EnumType.STRING) para armazenar o valor como texto no banco de dados,
 *   garantindo legibilidade e evitando problemas caso a ordem do enum mude.
 * - Evite EnumType.ORDINAL em produção, pois mudanças na ordem podem corromper dados existentes.
 * - Enum ajuda a padronizar os estados possíveis, tornando o código mais seguro e legível.
 */
public enum LocacaoStatus {
    LOCADA,
    FINALIZADA,
    ATRASADA
}
