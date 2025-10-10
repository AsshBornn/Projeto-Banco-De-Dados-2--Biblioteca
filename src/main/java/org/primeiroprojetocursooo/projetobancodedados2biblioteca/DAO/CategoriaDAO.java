package org.primeiroprojetocursooo.projetobancodedados2biblioteca.DAO;

import jakarta.persistence.EntityManager;
import org.primeiroprojetocursooo.projetobancodedados2biblioteca.entity.Categoria;
import org.primeiroprojetocursooo.projetobancodedados2biblioteca.util.JPAUtil;

import java.util.List;

public class CategoriaDAO extends GenericDAO<Categoria> {


    public CategoriaDAO(Class<Categoria> clazz) {
        super(clazz);
    }
    //CREATE
    @Override
    public void salvar(Categoria entidade) {
        super.salvar(entidade);
    }
    //READ
    @Override
    public List<Categoria> listar() {
        return super.listar();
    }
    //READ
    @Override
    public Categoria buscarPorId(Long id) {
        return super.buscarPorId(id);
    }
    //UPDATE
    @Override
    public void atualizar(Categoria entidade) {
        super.atualizar(entidade);
    }
    //DELETE

    @Override
    public void excluir(Long id) {
        super.excluir(id);
    }
    //METODO EXPECIFICO PARA BUSCAR POR DESCRIÇÃO
    public List<Categoria> buscarCategoriaPorDescricao(String descricao) {
        EntityManager em = JPAUtil.getEntityManager();// inicia o EntityManager
        try{
            //Monta o JPQL usando SELECT para o tipo categoria e utiliza WHERE para buscar pelo parametro no caso descrição
            String jpql= "SELECT c FROM Categoria c WHERE c.descricao LIKE :descricao";
            //IMPORTANTE: configura o parametro de espquisa utilizando o"%" utilizado abtes e depois da descrição
            // para que permita uma busca parcial,
            // sendo qualquer parte da string salva, não so a exata
            return em.createQuery(jpql)
                    .setParameter("descricao","%" + descricao+"%")
                    .getResultList();// Retorna o resultado da Query.
        } catch (Exception e) {
            throw new RuntimeException("Erro ao Buscar Categoria por Descrição");
        }
    }
    //METODO EXPECIFICO PARA VERIFICAR SE JA EXISTE UMA CATEGORIA IGUAL
    public boolean existeCategoriaPorDescricao(String descricao) {
        EntityManager em = JPAUtil.getEntityManager();
        try {// SELECT PARA VERIFICAR SE EXISTE UMA CATEGORIA COM DESCRIÇÃO IGUAL e contar
            String jpql="SELECT COUNT(c) FROM Categoria c WHERE LOWER(c.descricao) = LOWER(:descricao)";
            // COUNT(c) para retorna quantos foram contados, LOWER nos dois para comparação não ser CaseSensitive

            Long count=em.createQuery(jpql,Long.class)// monta o TypedQuery
                    .setParameter("descricao", descricao)//Liga o Valor ao Parametro
                    .getSingleResult();//Retorna o Resultado
            return count>0;//Retorna caso for >1 sendo assim ja existindo a Categoria
        }finally{
            em.close();
        }
    }


}
