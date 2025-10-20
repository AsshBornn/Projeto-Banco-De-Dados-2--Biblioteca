package org.primeiroprojetocursooo.projetobancodedados2biblioteca.entity.enums;

/**
 * Enum LivroStatus representa o status de um livro no sistema.
 *
 * Cada valor indica a disponibilidade do livro para locação:
 * - DISPONIVEL: O livro está disponível para ser locado.
 * - LOCADO: O livro atualmente está locado por algum usuário.
 * - NAO_DISPONIVEL: O livro não pode ser locado (por exemplo, fora de circulação ou manutenção).
 *
 * Observações e boas práticas:
 * - Este enum é utilizado nas entidades JPA (ex.: Livro) com @Enumerated(EnumType.STRING)
 *   para que os valores sejam armazenados como strings no banco de dados,
 *   o que facilita leitura e manutenção.
 * - Sempre evite usar ordinals (EnumType.ORDINAL) em produção, pois alterações na ordem
 *   do enum podem corromper os dados persistidos.
 */
public enum LivroStatus {
    DISPONIVEL,
    LOCADO,
    NAO_DISPONIVEL
}
