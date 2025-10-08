package org.primeiroprojetocursooo.projetobancodedados2biblioteca.DAO;

import org.primeiroprojetocursooo.projetobancodedados2biblioteca.entity.Locacao;

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
    public Locacao buscarPorId(Long id) {
        return super.buscarPorId(id);
    }
    //UPDATE
    @Override
    public void atualizar(Locacao entidade) {
        super.atualizar(entidade);
    }
    //DELETE
    @Override
    public void excluir(Long id) {
        super.excluir(id);
    }
}
