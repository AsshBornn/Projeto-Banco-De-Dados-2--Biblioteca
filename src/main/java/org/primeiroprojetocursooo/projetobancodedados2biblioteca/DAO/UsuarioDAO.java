package org.primeiroprojetocursooo.projetobancodedados2biblioteca.DAO;

import org.primeiroprojetocursooo.projetobancodedados2biblioteca.entity.Usuario;

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
    public Usuario buscarPorId(Long id) {
        return super.buscarPorId(id);
    }
    //READ
    @Override
    public void atualizar(Usuario entidade) {
        super.atualizar(entidade);
    }
    //DELETE
    @Override
    public void excluir(Long id) {
        super.excluir(id);
    }
}
