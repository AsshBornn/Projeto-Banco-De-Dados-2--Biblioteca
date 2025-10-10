package org.primeiroprojetocursooo.projetobancodedados2biblioteca.services;

import org.primeiroprojetocursooo.projetobancodedados2biblioteca.DAO.GenericDAO;
import org.primeiroprojetocursooo.projetobancodedados2biblioteca.DAO.LocacaoDAO;
import org.primeiroprojetocursooo.projetobancodedados2biblioteca.entity.Locacao;

import java.util.List;

public class LocacaoService extends GenericService<Locacao> {
    public LocacaoService() {
        super(new LocacaoDAO(Locacao.class));
    }
    @Override
    public void salvar(Locacao entidade) {
        super.salvar(entidade);
    }
    @Override
    public void atualizar(Locacao entidade) {
        super.atualizar(entidade);
    }
    @Override
    public void excluir(Long id) {
        super.excluir(id);
    }
    @Override
    public Locacao buscarPorId(Long id) {
        return super.buscarPorId(id);
    }
    @Override
    public List<Locacao> listar() {
        return super.listar();
    }
}
