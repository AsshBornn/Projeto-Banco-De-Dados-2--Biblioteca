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
}
