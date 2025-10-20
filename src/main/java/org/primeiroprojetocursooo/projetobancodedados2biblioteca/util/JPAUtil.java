package org.primeiroprojetocursooo.projetobancodedados2biblioteca.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 * Classe utilitária para gerenciar o EntityManager do JPA.
 *
 * Observações e boas práticas:
 * - A EntityManagerFactory deve ser criada apenas uma vez por aplicação, pois sua criação é cara.
 * - O EntityManager é leve e deve ser instanciado para cada operação/transação.
 * - Sempre feche o EntityManager após o uso para liberar recursos.
 * - Esta classe centraliza a criação e fechamento, facilitando manutenção e evitando múltiplas instâncias.
 */
public class JPAUtil {

    /**
     * EntityManagerFactory única para toda a aplicação.
     * Configuração de persistence-unit vem do persistence.xml (bibliotecaPU).
     */
    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("bibliotecaPU");

    /**
     * Retorna um novo EntityManager para operações com o banco de dados.
     * Cada chamada cria um EntityManager independente.
     *
     * Boa prática:
     * - Criar e fechar EntityManager dentro de um bloco try-finally ou usar try-with-resources quando possível.
     * - Não compartilhar EntityManager entre threads.
     */
    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    /**
     * Fecha a EntityManagerFactory ao final da aplicação.
     * Deve ser chamado uma única vez, geralmente em shutdown do sistema.
     */
    public static void close() {
        if (emf.isOpen()) {
            emf.close();
        }
    }
}
