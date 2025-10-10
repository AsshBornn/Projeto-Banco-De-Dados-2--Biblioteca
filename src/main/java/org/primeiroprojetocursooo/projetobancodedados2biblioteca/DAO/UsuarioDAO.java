package org.primeiroprojetocursooo.projetobancodedados2biblioteca.DAO;

import jakarta.persistence.EntityManager;
import org.primeiroprojetocursooo.projetobancodedados2biblioteca.entity.Categoria;
import org.primeiroprojetocursooo.projetobancodedados2biblioteca.entity.Locacao;
import org.primeiroprojetocursooo.projetobancodedados2biblioteca.entity.Usuario;
import org.primeiroprojetocursooo.projetobancodedados2biblioteca.entity.enums.LocacaoStatus;
import org.primeiroprojetocursooo.projetobancodedados2biblioteca.util.JPAUtil;

import java.util.List;

public class UsuarioDAO extends GenericDAO<Usuario> {

    public UsuarioDAO(Class<Usuario> clazz) {
        super(clazz);
    }
    //CREATE
    @Override
    public void salvar(Usuario entidade) {
        super.salvar(entidade);
    }
    //READ
    @Override
    public List<Usuario> listar() {
        return super.listar();
    }
    //READ
    @Override
    public Usuario buscarPorId(Integer id) {
        return super.buscarPorId(id);
    }
    //READ
    @Override
    public void atualizar(Usuario entidade) {
        super.atualizar(entidade);
    }
    //DELETE
    @Override
    public void excluir(Integer id) {
        super.excluir(id);
    }

    //METODO ESPECIFICO PARA VERIFICAÇÂO DE EMAIL SE JA EXISTE UMA USUARIO IGUAL
    public boolean existeEmail(String email) {
        EntityManager em = JPAUtil.getEntityManager();
        try {// SELECT PARA VERIFICAR SE EXISTE UM USUARIO COM EMAIL IGUAL e contar
            String jpql="SELECT COUNT(u) FROM Usuario u WHERE LOWER(u.email) = LOWER(:email)";
            // COUNT(u) para retorna quantos foram contados, LOWER nos dois para comparação não ser CaseSensitive
            Long count=em.createQuery(jpql,Long.class)// monta o TypedQuery
                    .setParameter("email", email)//Liga o Valor ao Parametro
                    .getSingleResult();//Retorna o Resultado
            return count>0;//Retorna caso for >1 sendo assim ja existindo usuario com este email
        }finally{
            em.close();
        }
    }
    //METODO ESPECIFICO BUSCAR EMAIL EXATO PARA VALIDAÇÂO
    public Usuario buscarUsuarioPorEmailExato(String email) {
        EntityManager em = JPAUtil.getEntityManager();
        try{
            String jpql= "SELECT u FROM Usuario u WHERE LOWER(u.email) = LOWER(:email)";
            return em.createQuery(jpql, Usuario.class)
                    .setParameter("email",email)
                    .getResultStream()
                    .findFirst()
                    .orElse(null);
        }finally {
            em.close();
        }
    }
    //METODO EXPECIFICO PARA BUSCAR POR NOME
    public List<Usuario> buscarUsuarioPorNome(String nome) {
        EntityManager em = JPAUtil.getEntityManager();// inicia o EntityManager
        try{
            //Monta o JPQL usando SELECT para o tipo categoria e utiliza WHERE para buscar pelo parametro no caso descrição
            String jpql= "SELECT u FROM Usuario u WHERE LOWER(u.nome) LIKE LOWER(:nome)";
            //IMPORTANTE: configura o parametro de espquisa utilizando o"%" utilizado abtes e depois do nome
            // para que permita uma busca parcial,
            // sendo qualquer parte da string salva, não so a exata
            return em.createQuery(jpql)
                    .setParameter("nome","%" + nome +"%")
                    .getResultList();// Retorna o resultado da Query.
        } catch (Exception e) {
            throw new RuntimeException("Erro ao Buscar Usuario por nome");
        }
    }
    //METODO EXPECIFICO PARA BUSCAR POR EMAIL
    public List<Usuario> buscarUsuarioPorEmail(String email) {
        EntityManager em = JPAUtil.getEntityManager();// inicia o EntityManager
        try{
            //Monta o JPQL usando SELECT para o tipo categoria e utiliza WHERE para buscar pelo parametro no caso descrição
            String jpql= "SELECT u FROM Usuario u WHERE LOWER(u.email) LIKE LOWER(:email)";
            //IMPORTANTE: configura o parametro de espquisa utilizando o"%" utilizado abtes e depois do nome
            // para que permita uma busca parcial,
            // sendo qualquer parte da string salva, não so a exata
            return em.createQuery(jpql)
                    .setParameter("email","%" + email +"%")
                    .getResultList();// Retorna o resultado da Query.
        } catch (Exception e) {
            throw new RuntimeException("Erro ao Buscar Usuario por Email");
        }
    }


}
