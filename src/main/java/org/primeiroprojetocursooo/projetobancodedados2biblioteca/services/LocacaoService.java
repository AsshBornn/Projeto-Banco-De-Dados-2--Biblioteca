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
        return locacaoDAO.buscarPorId(id);
    }
    @Override
    public List<Locacao> listar() {
        return super.listar();
    }
}
