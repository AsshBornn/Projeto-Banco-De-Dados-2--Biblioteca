package org.primeiroprojetocursooo.projetobancodedados2biblioteca.services;

import org.primeiroprojetocursooo.projetobancodedados2biblioteca.DAO.PagamentoDAO;
import org.primeiroprojetocursooo.projetobancodedados2biblioteca.entity.Locacao;
import org.primeiroprojetocursooo.projetobancodedados2biblioteca.entity.Pagamento;
import org.primeiroprojetocursooo.projetobancodedados2biblioteca.entity.Usuario;
import org.primeiroprojetocursooo.projetobancodedados2biblioteca.entity.enums.LocacaoStatus;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class PagamentoService extends GenericService<Pagamento> {
    private PagamentoDAO pagamentoDAO;
    private Pagamento pagamento;
    private static final DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public PagamentoService() {
        super(new PagamentoDAO(Pagamento.class));//Passa para o GenericService
        this.pagamentoDAO = (PagamentoDAO) super.getDao();//pega a mesma instancia para pode utilizar metodos especificos da classe LivroDAO sem criar outra instancia
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
