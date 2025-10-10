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
        if (entidade == null) {//VERIFICA SE A ENTIDADE NÃO É NULA
            throw new IllegalArgumentException("Pagamento inválido");
        }

        if (entidade.getLocacao() == null) {//VERIFICA SE EXISTE A LOCACAO
            throw new IllegalArgumentException("O pagamento deve estar vinculado a uma locação");
        }

        // Verifica se a locação já foi paga
        if (pagamentoDAO.buscarPorLocacao(entidade.getLocacao()) != null) {//VERIFICA SE JA FOI PAGA
            throw new IllegalArgumentException("Esta locação já possui um pagamento registrado");
        }

        // Verifica o status da locação
        if(entidade.getLocacao().getStatus() == LocacaoStatus.LOCADA
                || entidade.getLocacao().getStatus() == LocacaoStatus.ATRASADA) {
            // Marca a locação como finalizada ao registrar o pagamento
            entidade.getLocacao().setStatus(LocacaoStatus.FINALIZADA);
        }
        // Define data atual se não tiver
        if (entidade.getDataPagamento() == null) {
            entidade.setDataPagamento(LocalDate.now());
        }
        super.salvar(entidade);
    }

    public void atualizarDataPagamento(Pagamento pagamento, LocalDate novaData) {
        if (pagamento == null || pagamento.getId() == null) {
            throw new IllegalArgumentException("Pagamento inválido para atualização");
        }
        if (novaData == null) {
            throw new IllegalArgumentException("Nova data inválida");
        }
        pagamento.setDataPagamento(novaData);
        super.atualizar(pagamento); // só atualiza a data
    }
    /*
    @Override
    public void excluir(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("ID do pagamento não pode ser nulo");
        }

        Pagamento pagamento = pagamentoDAO.buscarPorId(id);
        if (pagamento == null) {
            throw new IllegalArgumentException("Pagamento não encontrado");
        }

        // Verifica se a locação ainda está associada
        if (pagamento.getLocacao() != null) {
            switch (pagamento.getLocacao().getStatus()) {
                case FINALIZADA, ATRASADA ->
                        throw new IllegalArgumentException("Não é possível excluir um pagamento vinculado a uma locação finalizada ou atrasada");
                case LOCADA -> {
                    // nesse caso, o pagamento pode até ser permitido remover
                    // se foi lançado por engano antes da finalização
                }
            }
        }
        super.excluir(id);
    }*/

    @Override
    public Pagamento buscarPorId(Integer id) {
        return super.buscarPorId(id);
    }
    @Override
    public List<Pagamento> listar() {
        return super.listar();
    }
    //METODO ESPECIFICO
    public Pagamento buscarPorLocacao(Locacao locacao) {
        if (locacao == null) {
            throw new IllegalArgumentException("Locação inválida");
        }
        return pagamentoDAO.buscarPorLocacao(locacao);
    }
    public List<Pagamento> buscarPorData(LocalDate data) {
        if (data == null) {
            throw new IllegalArgumentException("Data inválida");
        }
        return pagamentoDAO.buscarPorData(data);
    }
    public List<Pagamento> buscarPorPeriodo(LocalDate inicio, LocalDate fim) {
        if (inicio == null || fim == null) {
            throw new IllegalArgumentException("As datas de início e fim devem ser informadas");
        }
        if (fim.isBefore(inicio)) {
            throw new IllegalArgumentException("A data final não pode ser anterior à inicial");
        }
        return pagamentoDAO.buscarPorPeriodo(inicio, fim);
    }
    public List<Pagamento> buscarPorUsuario(Usuario usuario) {
        if (usuario == null) {
            throw new IllegalArgumentException("Usuário inválido");
        }
        return pagamentoDAO.buscarPorUsuario(usuario);
    }

    public double getPagamento() {
        return pagamento.getValor();
    }
}
