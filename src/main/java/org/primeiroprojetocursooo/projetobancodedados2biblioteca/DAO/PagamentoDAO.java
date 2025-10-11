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
    public void salvar(Pagamento entidade) {super.salvar(entidade);}
    //READ
    @Override
    public List<Pagamento> listar() {return super.listar();}
    //READ
    @Override
    public Pagamento buscarPorId(Integer id) {return super.buscarPorId(id);}
    //UPDATE
    @Override
    public void atualizar(Pagamento entidade) {super.atualizar(entidade);}
    //DELETE
    @Override
    public void excluir(Integer id) {super.excluir(id);}

}
