package org.primeiroprojetocursooo.projetobancodedados2biblioteca.services;

import org.primeiroprojetocursooo.projetobancodedados2biblioteca.DAO.GenericDAO;
import org.primeiroprojetocursooo.projetobancodedados2biblioteca.DAO.PagamentoDAO;
import org.primeiroprojetocursooo.projetobancodedados2biblioteca.entity.Pagamento;

import java.util.List;

public class PagamentoService extends GenericService<Pagamento> {
    public PagamentoService() {
        super(new PagamentoDAO(Pagamento.class));
    }
    @Override
    public void salvar(Pagamento entidade) {
        super.salvar(entidade);
    }
    @Override
    public void atualizar(Pagamento entidade) {
        super.atualizar(entidade);
    }
    @Override
    public void excluir(Long id) {
        super.excluir(id);
    }
    @Override
    public Pagamento buscarPorId(Long id) {
        return super.buscarPorId(id);
    }
    @Override
    public List<Pagamento> listar() {
        return super.listar();
    }
}
