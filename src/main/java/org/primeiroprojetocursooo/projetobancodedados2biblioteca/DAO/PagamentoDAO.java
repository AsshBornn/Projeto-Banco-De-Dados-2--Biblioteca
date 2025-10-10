package org.primeiroprojetocursooo.projetobancodedados2biblioteca.DAO;

import jakarta.persistence.EntityManager;
import org.primeiroprojetocursooo.projetobancodedados2biblioteca.entity.Locacao;
import org.primeiroprojetocursooo.projetobancodedados2biblioteca.entity.Pagamento;
import org.primeiroprojetocursooo.projetobancodedados2biblioteca.entity.Usuario;
import org.primeiroprojetocursooo.projetobancodedados2biblioteca.util.JPAUtil;

import java.time.LocalDate;
import java.util.List;

public class PagamentoDAO extends GenericDAO<Pagamento> {

    public PagamentoDAO(Class<Pagamento> clazz) {
        super(clazz);
    }
    //CREATE
    @Override
    public void salvar(Pagamento entidade) {
        super.salvar(entidade);
    }
    //READ
    @Override
    public List<Pagamento> listar() {
        return super.listar();
    }
    //READ
    @Override
    public Pagamento buscarPorId(Integer id) {
        return super.buscarPorId(id);
    }
    //UPDATE
    @Override
    public void atualizar(Pagamento entidade) {
        super.atualizar(entidade);
    }
    //DELETE
    @Override
    public void excluir(Integer id) {
        super.excluir(id);
    }
    // METODO ESPECIFICO PARA BUSCAR UM PAGAMENTO POR LOCACAO
    public Pagamento buscarPorLocacao(Locacao locacao) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            String jpql = "SELECT p FROM Pagamento p WHERE p.locacao = :locacao";
            return em.createQuery(jpql, Pagamento.class)
                    .setParameter("locacao", locacao)
                    .getResultStream()
                    .findFirst()
                    .orElse(null);
        } finally {
            em.close();
        }
    }
    //BUSCAR PAGAMENTO POR DADA
    public List<Pagamento> buscarPorData(LocalDate data) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            String jpql = "SELECT p FROM Pagamento p WHERE p.dataPagamento = :data";
            return em.createQuery(jpql, Pagamento.class)
                    .setParameter("data", data)
                    .getResultList();
        } finally {
            em.close();
        }
    }
    //BUSCAR PAGAMENTO POR PERIORO
    public List<Pagamento> buscarPorPeriodo(LocalDate inicio, LocalDate fim) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            String jpql = "SELECT p FROM Pagamento p WHERE p.dataPagamento BETWEEN :inicio AND :fim";
            return em.createQuery(jpql, Pagamento.class)
                    .setParameter("inicio", inicio)
                    .setParameter("fim", fim)
                    .getResultList();
        } finally {
            em.close();
        }
    }
    //BUSCAR PAGAMENTOS POR USUARIOS
    public List<Pagamento> buscarPorUsuario(Usuario usuario) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            String jpql = "SELECT p FROM Pagamento p WHERE p.locacao.usuario = :usuario";
            return em.createQuery(jpql, Pagamento.class)
                    .setParameter("usuario", usuario)
                    .getResultList();
        } finally {
            em.close();
        }
    }





}
