package org.primeiroprojetocursooo.projetobancodedados2biblioteca.DAO;

import jakarta.persistence.EntityManager;
import org.primeiroprojetocursooo.projetobancodedados2biblioteca.entity.Categoria;
import org.primeiroprojetocursooo.projetobancodedados2biblioteca.util.JPAUtil;

public class CategoriaDAO {

    public void salvar(Categoria categoria) {
        EntityManager em = JPAUtil.getEntityManager(); //Inicia o EntityManager
        try {
            em.getTransaction().begin();// Para Fazer a persistencia o EM precisa fazer, como uma transação
            em.persist(categoria);//persistencia da categoria
            em.getTransaction().commit();//confirma a transação
        }catch(Exception e) {//catch para capturar excessão em caso de erro
            if(em.getTransaction().isActive()) {//verifica se deu erro na transação
                em.getTransaction().rollback();//desfaz a transação
            }
            e.printStackTrace();//print do erro
        }
        finally {//garante que o EM seja fechado mesmo se der erro
            em.close();
        }
    }



}
