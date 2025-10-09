package org.primeiroprojetocursooo.projetobancodedados2biblioteca.DAO;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.primeiroprojetocursooo.projetobancodedados2biblioteca.util.JPAUtil;

import java.util.List;

//DAO generico para Criação de Um CRUD generico, fazendo herança para as classes DAO
public class GenericDAO <T>{
    //Atributo para guarda a entidade.
    private final Class<T> clazz;

    // Construtor para receber a entidade e usar os comandos.
    public GenericDAO(Class<T> clazz) {
        this.clazz = clazz;
    }
    //CREATE
    public void salvar(T entidade) {
        EntityManager em= JPAUtil.getEntityManager();//Inicia o EntityManager
        try{//Uso do EntityManager deve ser feito em tranzaçõpes
            em.getTransaction().begin();//inicia a tranzação
            em.persist(entidade);//manda persistir no banco de dados
            em.getTransaction().commit();//confirma se deu certo
        }catch(Exception e){//capturar exceção se der errado
            em.getTransaction().rollback();//desfazer a transação
             e.printStackTrace();/*mostrar erro */}
        finally {
            em.close();/* garantir que mesmo que ocorra erro feche a transação*/}
    }
    //READ por ID

    public T buscarPorId(Long id) {
        EntityManager em= JPAUtil.getEntityManager();// inicia o EM
        try{
            return em.find(clazz, id);
        }finally{
            em.close();
        }
    }
    //READ Todos
    public List<T> listar() {
        EntityManager em= JPAUtil.getEntityManager();//Inicia o EM
        try{
            Entity entityAnnotation=clazz.getAnnotation(Entity.class);// Dececta a anotação da classe @Entity
            // para não da erro na Hora de Procurar no bd

            // Pega o Nome Definido e poem em uma String se for diferente de null
            String entityName = entityAnnotation !=null? entityAnnotation.name():null;
            //Compara se é nullo ou vazio o entityName
            //caso seja atribui o getSimplename ou nome da classe
            if(entityName==null||entityName.isEmpty()){
                entityName = clazz.getSimpleName();
            }
            // Monta a consulta JPQL dinamicamente
            return em.createQuery("FROM " + entityName, clazz)
                    .getResultList();
        }finally{
            em.close();
        }
    }
    //UPDATE

    public void atualizar(T entidade) {
        EntityManager em= JPAUtil.getEntityManager();// inicia o EM
        EntityTransaction et = null;

        try{
            et=em.getTransaction();
            et.begin();
            em.merge(entidade);
            et.commit();
        }catch(Exception e){
            if(et!=null&& et.isActive()){
                et.rollback();
            }
            e.printStackTrace(); // ou lançar RuntimeException
            throw new RuntimeException("Erro ao atualizar/excluir", e);
        }finally {
            em.close();
        }
    }
    //DELETE
    public void excluir(Long id) {
        EntityManager em= JPAUtil.getEntityManager();
        EntityTransaction et = null;

        try {
            et=em.getTransaction();
            et.begin();

            T entidade = em.find(clazz, id);
            if (entidade != null) {
                em.remove(entidade);
                System.out.println("Entidade removida com sucesso: " + entidade);
            } else {
                System.out.println("Entidade com id " + id + " não encontrada.");
            }
            et.commit();
        }catch (Exception e){
            if(et !=null &&et.isActive()){
                et.rollback();
            }e.printStackTrace();
            throw new RuntimeException("Erro ao atualizar/excluir", e);
    }finally {
            em.close();
        }
    }
}
