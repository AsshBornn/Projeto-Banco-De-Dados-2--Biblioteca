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
        // Valida campos obrigatórios
        if(entidade == null || entidade.getNome() == null || entidade.getNome().isBlank()) {
            throw new IllegalArgumentException("Nome do usuário é obrigatório");
        }
        if(entidade.getEmail() == null || entidade.getEmail().isBlank()) {
            throw new IllegalArgumentException("E-mail é obrigatório");
        }
        // Valida e-mail
        if(!entidade.getEmail().matches("^[\\w.-]+@[\\w.-]+\\.\\w{2,}$")) {
            throw new IllegalArgumentException("E-mail inválido");
        }
        // Verifica se o e-mail já existe
        if(usuarioDAO.existeEmail(entidade.getEmail())) {
            throw new IllegalArgumentException("E-mail já cadastrado");
        }
        super.salvar(entidade);
    }
    @Override
    public void atualizar(Usuario entidade) {
        // Valida campos obrigatórios
        if(entidade == null || entidade.getNome() == null || entidade.getNome().isBlank()) {
            throw new IllegalArgumentException("Nome do usuário é obrigatório");
        }
        if(entidade.getEmail() == null || entidade.getEmail().isBlank()) {
            throw new IllegalArgumentException("E-mail é obrigatório");
        }
        // Valida formato do e-mail
        if(!entidade.getEmail().matches("^[\\w.-]+@[\\w.-]+\\.\\w{2,}$")) {
            throw new IllegalArgumentException("E-mail inválido");
        }
        // Verifica se o usuário existe
        Usuario usuarioExistente = usuarioDAO.buscarPorId(entidade.getId());
        if(usuarioExistente == null) {
            throw new RuntimeException("Usuário não encontrado");
        }
        // Verifica se o e-mail já existe em outro usuário
        Usuario usuarioComMesmoEmail = usuarioDAO.buscarUsuarioPorEmailExato(entidade.getEmail());
        if(usuarioComMesmoEmail != null && !usuarioComMesmoEmail.getId().equals(entidade.getId())) {
            throw new IllegalArgumentException("E-mail já cadastrado para outro usuário");
        }
        // Atualiza o usuário
        super.atualizar(entidade);
    }
    @Override
    public void excluir(Integer id) {
        // Busca o usuário pelo ID
        Usuario usuario = usuarioDAO.buscarPorId(id);
        // Verifica se o usuário existe
        if (usuario == null) {
            throw new RuntimeException("Usuário não encontrado");
        }
        // Verifica se o usuário possui locações ativas
        boolean possuiLocacoesAtivas = usuario.getLocacoes().stream()
                .anyMatch(l -> l.getStatus() == LocacaoStatus.LOCADA || l.getStatus() == LocacaoStatus.ATRASADA);
        if (possuiLocacoesAtivas) {
            throw new RuntimeException("Não é possível excluir usuário com locações ativas ou atrasadas");
        }
        // Caso passe em todas as validações, chama o método genérico para excluir
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
    //METODO ESPECIFICO BUSCAR USUARIOS POR NOME
    public List<Usuario> buscarUsuarioPorNome(String nome){
        return usuarioDAO.buscarUsuarioPorNome(nome);
    }
    public List<Usuario> buscarUsuarioPorEmail(String email){
        return usuarioDAO.buscarUsuarioPorEmail(email);
    }

}
