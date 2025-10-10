package org.primeiroprojetocursooo.projetobancodedados2biblioteca.DAO;

import jakarta.persistence.EntityManager;
import org.primeiroprojetocursooo.projetobancodedados2biblioteca.entity.Locacao;
import org.primeiroprojetocursooo.projetobancodedados2biblioteca.entity.Usuario;

import org.primeiroprojetocursooo.projetobancodedados2biblioteca.util.JPAUtil;

import java.util.List;

public class LocacaoDAO extends GenericDAO<Locacao> {

    public LocacaoDAO(Class<Locacao> clazz) {
        super(clazz);
    }
    //CREATE
    @Override
    public void salvar(Locacao entidade) {
        super.salvar(entidade);
    }
    //READ
    @Override
    public List<Locacao> listar() {
        return super.listar();
    }
    //READ
    @Override
    public Locacao buscarPorId(Integer id) {
        return super.buscarPorId(id);
    }
    //UPDATE
    @Override
    public void atualizar(Locacao entidade) {
        super.atualizar(entidade);
    }
    //DELETE
    @Override
    public void excluir(Integer id) {
        super.excluir(id);
    }
    ////METODO EXPECIFICO BUSCAR LOCAÇÔES POR USUARIO
    public List<Locacao> buscarLocacoesPorUsuario(Usuario usuario) {
        // Cria o EntityManager para interagir com o banco
        EntityManager em = JPAUtil.getEntityManager();
        try {
            // Monta a query JPQL para buscar locações filtradas por usuário e status
            String jpql = "SELECT l FROM Locacao l WHERE l.usuario = :usuario";

            // Cria a query, seta os parâmetros e executa
            return em.createQuery(jpql, Locacao.class)
                    .setParameter("usuario", usuario) // seta o usuário
                    .getResultList();                 // retorna a lista de resultados
        } finally {
            // Fecha o EntityManager para liberar recursos
            em.close();
        }
    }
    public List<Locacao> listarLocacoesSemPagamento() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            String jpql = "SELECT l FROM Locacao l WHERE l.pagamento IS NULL";
            return em.createQuery(jpql, Locacao.class).getResultList();
        } finally {
            em.close();
        }
    }



}
