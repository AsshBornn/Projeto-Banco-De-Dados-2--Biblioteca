package org.primeiroprojetocursooo.projetobancodedados2biblioteca.services;
import org.primeiroprojetocursooo.projetobancodedados2biblioteca.DAO.UsuarioDAO;
import org.primeiroprojetocursooo.projetobancodedados2biblioteca.entity.Usuario;

import java.util.List;

public class UsuarioService extends GenericService<Usuario,UsuarioDAO> {
    public UsuarioService() {
        super(new UsuarioDAO(Usuario.class)); // passa para GenericService
    }
    public void salvar(Usuario entidade) {
        super.salvar(entidade);
    }
    @Override
    public void atualizar(Usuario entidade) {
        super.atualizar(entidade);
    }
    @Override
    public void excluir(Integer id) {
        super.excluir(id);
    }
    @Override
    public Usuario buscarPorId(Integer id) {
        return super.buscarPorId(id);
    }
    @Override
    public List<Usuario> listar() {
        return super.listar();
    }
    public List<Usuario> buscarPorNome(String nome) {
        return getDao().buscarPorNome(nome);
    }

}
