package org.primeiroprojetocursooo.projetobancodedados2biblioteca.services;
import org.primeiroprojetocursooo.projetobancodedados2biblioteca.DAO.CategoriaDAO;
import org.primeiroprojetocursooo.projetobancodedados2biblioteca.entity.Categoria;

import java.util.List;

public class CategoriaService extends GenericService<Categoria,CategoriaDAO> {

    public CategoriaService() {
        super(new CategoriaDAO(Categoria.class)); // passa para GenericService
    }
    @Override
    public void salvar(Categoria entidade) {
        if(isValid(entidade)){
        salvar(entidade);}
    }
    @Override
    public void atualizar(Categoria entidade) {
       if(isValid(entidade)){
           atualizar(entidade);
       }
    }
    //METODO BUSCAR POR DESCRIÇÂO
    public List<Categoria> buscarPorDescricao(String descricao) {
        return getDao().buscarPorDescricao(descricao);
    }


    private boolean isValid(Categoria entidade) {
        if(entidade.getDescricao().isBlank()){
            throw new IllegalArgumentException("Descrição Vazia");
        }
        if(getDao().existeDescricao(entidade.getDescricao())){
            throw new IllegalArgumentException("Descrição Já Cadastrada");
        }
        return true;
    }

}
