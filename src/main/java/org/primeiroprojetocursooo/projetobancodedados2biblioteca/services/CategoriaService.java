package org.primeiroprojetocursooo.projetobancodedados2biblioteca.services;
import org.primeiroprojetocursooo.projetobancodedados2biblioteca.DAO.CategoriaDAO;
import org.primeiroprojetocursooo.projetobancodedados2biblioteca.entity.Categoria;

import java.util.List;

public class CategoriaService extends GenericService<Categoria> {
    private CategoriaDAO categoriaDAO; // DAO específico
    public CategoriaService() {
        super(new CategoriaDAO(Categoria.class)); // passa para GenericService
        this.categoriaDAO = (CategoriaDAO) super.getDao(); // pega a mesma instância para não criar duas instancias do DAO
    }
    @Override
    public void salvar(Categoria entidade) {//Verifica se a entidade e a descrição não é nulo
        if(entidade == null || entidade.getDescricao() == null||entidade.getDescricao().isBlank()){//verifica se não é nulla ou tem apenas espaços vazios
            throw new IllegalArgumentException("Categoria Invalida");
        }// Usa o Metodo Criado na CategoriaDAO para verificar se ja não existe essa categoria
        if(categoriaDAO.existeCategoriaPorDescricao(entidade.getDescricao())){
            throw new IllegalArgumentException("Categoria Ja cadastrado");
        }
        super.salvar(entidade);
    }
    @Override
    public void atualizar(Categoria entidade) {
        super.atualizar(entidade);
    }
    @Override
    public void excluir(Long id) {//Acionando Regra De Negocio a função Excluir
        Categoria categoria = categoriaDAO.buscarPorId(id);//Busca por id a categoria para entrar no if
        if(categoria == null) {
            throw new RuntimeException("Categoria não encontrada");// evita erro caso não exista a categoria
        }
        if(!categoria.getLivros().isEmpty()) {// Verifica se a categoria tem Livros Associados a ela
            throw new RuntimeException("Não é Possivel Escluir Categoria que Possui Livros");
        }
        super.excluir(id);//Esclui Apenas se A Categoria Existir e Não Tiver Livros Associados a ela
    }
    @Override
    public Categoria buscarPorId(Long id) {
        return super.buscarPorId(id);
    }
    @Override
    public List<Categoria> listar() {
        return super.listar();
    }
    public List<Categoria> buscarCategoriaPorDescricao(String descricao) {
        if(descricao == null || descricao.isEmpty()){
            return List.of();
        }
        return categoriaDAO.buscarCategoriaPorDescricao(descricao);
    }



}
