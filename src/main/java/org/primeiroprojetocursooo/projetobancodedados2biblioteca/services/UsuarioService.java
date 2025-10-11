package org.primeiroprojetocursooo.projetobancodedados2biblioteca.services;
import org.primeiroprojetocursooo.projetobancodedados2biblioteca.DAO.UsuarioDAO;
import org.primeiroprojetocursooo.projetobancodedados2biblioteca.entity.Usuario;
import org.primeiroprojetocursooo.projetobancodedados2biblioteca.entity.enums.LocacaoStatus;

import java.util.List;

public class UsuarioService extends GenericService<Usuario> {
    private UsuarioDAO usuarioDAO;// DAO específico

    public UsuarioService() {
        super(new UsuarioDAO(Usuario.class)); // passa para GenericService
        this.usuarioDAO = (UsuarioDAO) super.getDao(); // pega a mesma instância para não criar duas instancias do DAO
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
}
