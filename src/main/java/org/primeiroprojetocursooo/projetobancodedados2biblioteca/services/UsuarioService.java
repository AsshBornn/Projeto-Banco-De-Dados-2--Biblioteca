package org.primeiroprojetocursooo.projetobancodedados2biblioteca.services;

import org.primeiroprojetocursooo.projetobancodedados2biblioteca.DAO.GenericDAO;
import org.primeiroprojetocursooo.projetobancodedados2biblioteca.DAO.UsuarioDAO;
import org.primeiroprojetocursooo.projetobancodedados2biblioteca.entity.Usuario;

import java.util.List;

public class UsuarioService extends GenericService<Usuario> {
    public UsuarioService() {
        super(new UsuarioDAO(Usuario.class));
    }
    @Override
    public void salvar(Usuario entidade) {
        super.salvar(entidade);
    }
    @Override
    public void atualizar(Usuario entidade) {
        super.atualizar(entidade);
    }
    @Override
    public void excluir(Long id) {
        super.excluir(id);
    }
    @Override
    public Usuario buscarPorId(Long id) {
        return super.buscarPorId(id);
    }
    @Override
    public List<Usuario> listar() {
        return super.listar();
    }
}
