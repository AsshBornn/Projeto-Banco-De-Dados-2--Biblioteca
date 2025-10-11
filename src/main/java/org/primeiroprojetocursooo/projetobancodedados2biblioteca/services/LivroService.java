package org.primeiroprojetocursooo.projetobancodedados2biblioteca.services;
import org.primeiroprojetocursooo.projetobancodedados2biblioteca.DAO.LivroDAO;
import org.primeiroprojetocursooo.projetobancodedados2biblioteca.entity.Livro;
import org.primeiroprojetocursooo.projetobancodedados2biblioteca.entity.enums.LivroStatus;

import java.util.List;

public class LivroService extends GenericService<Livro> {
    private LivroDAO livroDAO;

    public LivroService() {
        super(new LivroDAO(Livro.class));//Passa para o GenericService
        this.livroDAO = (LivroDAO) super.getDao();//pega a mesma instancia para pode utilizar metodos especificos da classe LivroDAO sem criar outra instancia
    }
    @Override
    public void salvar(Livro entidade) {//verifica se a entidade e o titulo não é nulo ou é apenas espaços vazios

        entidade.setStatus(LivroStatus.DISPONIVEL);//MARCA O LIVRO COMO DISPONIVEL
        super.salvar(entidade);
    }
    @Override
    public void atualizar(Livro entidade) {
        super.atualizar(entidade);
    }
    @Override
    public void excluir(Integer id) {
        super.excluir(id); // só exclui se passar nas regras acima
    }
    @Override
    public Livro buscarPorId(Integer id) {
        return super.buscarPorId(id);
    }
    @Override
    public List<Livro> listar() {
        return super.listar();
    }
}
