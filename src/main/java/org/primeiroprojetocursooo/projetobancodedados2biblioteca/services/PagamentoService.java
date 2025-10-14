package org.primeiroprojetocursooo.projetobancodedados2biblioteca.services;
import org.primeiroprojetocursooo.projetobancodedados2biblioteca.DAO.PagamentoDAO;
import org.primeiroprojetocursooo.projetobancodedados2biblioteca.entity.Pagamento;


import java.time.format.DateTimeFormatter;
import java.util.List;

public class PagamentoService extends GenericService<Pagamento,PagamentoDAO> {
    private static final DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public PagamentoService() {
        super(new PagamentoDAO(Pagamento.class));//Passa para o GenericService
    }
    @Override
    public void salvar(Pagamento entidade) {
        super.salvar(entidade);
    }
    public void excluir(Integer id) {
        super.excluir(id);
    }

    @Override
    public Pagamento buscarPorId(Integer id) {
        return super.buscarPorId(id);
    }
    @Override
    public List<Pagamento> listar() {
        return super.listar();
    }
}
