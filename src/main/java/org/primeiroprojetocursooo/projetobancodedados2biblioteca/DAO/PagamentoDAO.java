package org.primeiroprojetocursooo.projetobancodedados2biblioteca.DAO;

import org.primeiroprojetocursooo.projetobancodedados2biblioteca.entity.Pagamento;

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
    public Pagamento buscarPorId(Long id) {
        return super.buscarPorId(id);
    }
    //UPDATE
    @Override
    public void atualizar(Pagamento entidade) {
        super.atualizar(entidade);
    }
    //DELETE
    @Override
    public void excluir(Long id) {
        super.excluir(id);
    }
}
