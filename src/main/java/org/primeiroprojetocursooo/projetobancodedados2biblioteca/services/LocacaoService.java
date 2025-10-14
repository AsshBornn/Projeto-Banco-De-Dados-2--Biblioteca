package org.primeiroprojetocursooo.projetobancodedados2biblioteca.services;
import org.primeiroprojetocursooo.projetobancodedados2biblioteca.DAO.LocacaoDAO;
import org.primeiroprojetocursooo.projetobancodedados2biblioteca.entity.Locacao;


import java.util.List;

public class LocacaoService extends GenericService<Locacao,LocacaoDAO> {

    public LocacaoService() {
        super(new LocacaoDAO(Locacao.class));//Passa para o GenericService
    }

    @Override
    public void salvar(Locacao entidade) {//VERIFICA A ENTIDADE SE É NULA,TEM USUARIO E LIVROS
        super.salvar(entidade);
    }

    @Override
    public void atualizar(Locacao entidade) {
        super.atualizar(entidade);
    }

    @Override
    public void excluir(Integer id) {
        super.excluir(id);
    }

    @Override
    public Locacao buscarPorId(Integer id) {
        return getDao().buscarPorId(id);
    }
    @Override
    public List<Locacao> listar() {

        return super.listar();
    }
}
