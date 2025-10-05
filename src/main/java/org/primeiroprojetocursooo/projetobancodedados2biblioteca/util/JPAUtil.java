import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 * Classe utilitária para fornecer EntityManager do JPA
 */
public class JPAUtil {

    // Cria a EntityManagerFactory uma única vez
    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("bibliotecaPU");

    /**
     * Retorna um novo EntityManager para operações com o banco
     */
    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    /**
     * Fecha a EntityManagerFactory quando a aplicação termina
     */
    public static void close() {
        if (emf.isOpen()) {
            emf.close();
        }
    }
}
