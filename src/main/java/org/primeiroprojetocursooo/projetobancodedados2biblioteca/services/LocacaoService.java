package org.primeiroprojetocursooo.projetobancodedados2biblioteca.services;
import org.primeiroprojetocursooo.projetobancodedados2biblioteca.DAO.LivroDAO;
import org.primeiroprojetocursooo.projetobancodedados2biblioteca.DAO.LocacaoDAO;
import org.primeiroprojetocursooo.projetobancodedados2biblioteca.entity.Livro;
import org.primeiroprojetocursooo.projetobancodedados2biblioteca.entity.Locacao;
import org.primeiroprojetocursooo.projetobancodedados2biblioteca.entity.Usuario;
import org.primeiroprojetocursooo.projetobancodedados2biblioteca.entity.enums.LivroStatus;
import org.primeiroprojetocursooo.projetobancodedados2biblioteca.entity.enums.LocacaoStatus;

import java.util.List;

public class LocacaoService extends GenericService<Locacao> {
    private LivroDAO livroDAO;
    private LocacaoDAO locacaoDAO;

    public LocacaoService() {
        super(new LocacaoDAO(Locacao.class));//Passa para o GenericService
        this.locacaoDAO = (LocacaoDAO) super.getDao();//pega a mesma instancia para pode utilizar metodos especificos da classe LivroDAO sem criar outra instancia
        this.livroDAO = new LivroDAO(Livro.class); // inicializa DAO de livros
    }

    @Override
    public void salvar(Locacao entidade) {//VERIFICA A ENTIDADE SE É NULA,TEM USUARIO E LIVROS
        if(entidade==null || entidade.getUsuario()==null || entidade.getLivros() == null){
            throw new IllegalArgumentException("Locação inválida: usuário ou livros não informados");
        }
        if(entidade.getLivros().isEmpty()){//VERIFICA SE A LISTA DE LIVROS NÃO É VAZIA
            throw new IllegalArgumentException("Locação inválida: deve conter pelo menos um livro");
        }
        //Roda um for na lista de livros para verificar se todos os livros da locacao existe e estao disponiveis
        for(Livro livro : entidade.getLivros()){
            Livro livroExistente = livroDAO.buscarPorId(livro.getId());
            if(livroExistente == null){
                throw new IllegalArgumentException("Livro não encontrado: " + livro.getTitulo());
            }
            if(livroExistente.getStatus() != LivroStatus.DISPONIVEL){
                throw new IllegalArgumentException("Livro não disponível: " + livro.getTitulo());
            }
        }
        //Antes de Salvar Classifica a Locacao Como Locada
        entidade.setStatus(LocacaoStatus.LOCADA);
        //Antes de Salvar Classifica os Livros Como Locados
        for(Livro l : entidade.getLivros()) {
            l.setStatus(LivroStatus.LOCADO);
            livroDAO.atualizar(l); // garante que o status LOCADO seja salvo no banco
        }
        super.salvar(entidade);
    }

    @Override
    public void atualizar(Locacao entidade) {
        // Validações básicas
        if(entidade == null || entidade.getUsuario() == null || entidade.getLivros() == null || entidade.getLivros().isEmpty()) {
            throw new IllegalArgumentException("Locação inválida: usuário ou livros inexistentes");
        }
        // Verifica se a locação existe
        Locacao locacaoExistente = locacaoDAO.buscarPorId(entidade.getId());
        if(locacaoExistente == null){
            throw new RuntimeException("Locação não encontrada");
        }
        // Validação de datas
        if(entidade.getDataDevolucao() != null && entidade.getDataDevolucao().isBefore(entidade.getDataLocacao())){
            throw new IllegalArgumentException("Data de devolução não pode ser anterior à data da locação");
        }
        // Validação dos livros
        for(Livro livro : entidade.getLivros()){
            Livro livroExistente = livroDAO.buscarPorId(livro.getId());
            if(livroExistente == null){
                throw new IllegalArgumentException("Livro não encontrado: " + livro.getTitulo());
            }
            // Se a locação está ativa, todos os livros devem estar LOCADOS
            if(locacaoExistente.getStatus() == LocacaoStatus.LOCADA && livroExistente.getStatus() != LivroStatus.LOCADO){
                throw new IllegalArgumentException("Livro já foi devolvido: " + livro.getTitulo());
            }
        }
        // Atualiza o status da locação (se necessário)
        // Ex.: se todas as datas estiverem definidas, pode marcar como DEVOLVIDA
        if(entidade.getDataDevolucao() != null) {
            entidade.setStatus(LocacaoStatus.FINALIZADA);
            // Atualiza o status de todos os livros para DISPONÍVEL
            for(Livro l : entidade.getLivros()) {
                l.setStatus(LivroStatus.DISPONIVEL);
                livroDAO.atualizar(l); // garante que o status LOCADO seja salvo no banco
            }
        }
        super.atualizar(entidade);
    }

    @Override
    public void excluir(Integer id) {
        // Busca a locação pelo ID, já armazenando em uma variável para evitar múltiplas consultas ao banco
        Locacao locacao = buscarPorId(id);
        // Verifica se a locação existe
        if (locacao == null) {
            throw new RuntimeException("Locação não encontrada");
        }
        // Verifica se a locação já foi finalizada
        // Não é permitido excluir locações que já foram concluídas
        if (locacao.getStatus() == LocacaoStatus.FINALIZADA) {
            throw new RuntimeException("Não é possível excluir uma locação concluída");
        }
        // Para cada livro da locação, atualiza o status para DISPONÍVEL
        // Isso garante que os livros fiquem disponíveis para novas locações
        locacao.getLivros().forEach(l -> l.setStatus(LivroStatus.DISPONIVEL));
        // Chama o método excluir do GenericService para remover a locação do banco
        super.excluir(id);
    }

    @Override
    public Locacao buscarPorId(Integer id) {
        return locacaoDAO.buscarPorId(id);
    }
    @Override
    public List<Locacao> listar() {
        return super.listar();
    }
    // ======================== MÉTODOS ESPECÍFICOS ======================== //
    public List<Locacao> buscarPorUsuario(Usuario usuario){
        return locacaoDAO.buscarLocacoesPorUsuario(usuario);
    }
    public List<Locacao> listarLocacoesSemPagamento(){
        return locacaoDAO.listarLocacoesSemPagamento();
    }
}
