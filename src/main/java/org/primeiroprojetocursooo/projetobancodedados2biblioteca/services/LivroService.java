package org.primeiroprojetocursooo.projetobancodedados2biblioteca.services;
import org.primeiroprojetocursooo.projetobancodedados2biblioteca.DAO.LivroDAO;
import org.primeiroprojetocursooo.projetobancodedados2biblioteca.entity.Livro;
import org.primeiroprojetocursooo.projetobancodedados2biblioteca.entity.enums.LivroStatus;

public class LivroService extends GenericService<Livro,LivroDAO> {

    public LivroService() {
        super(new LivroDAO(Livro.class));//Passa para o GenericService
    }
    @Override
    public void salvar(Livro entidade) {
        if(isValid(entidade)){
            entidade.setStatus(LivroStatus.DISPONIVEL);//MARCA O LIVRO COMO DISPONIVEL
            super.salvar(entidade);
        }
    }
    @Override
    public void atualizar(Livro entidade) {
        if(isValid(entidade)){
            super.atualizar(entidade);
        }
    }
    @Override
    public void excluir(Integer id) {
        super.excluir(id); // só exclui se passar nas regras acima
    }
    @Override
    public Livro buscarPorId(Integer id) {
        return super.buscarPorId(id);
    }
    private boolean isValid(Livro entidade){
        if(getDao().existeTITULO(entidade.getTitulo())){
            throw new IllegalArgumentException("Titulo ja existente");
        }
        return true;
    }
}

