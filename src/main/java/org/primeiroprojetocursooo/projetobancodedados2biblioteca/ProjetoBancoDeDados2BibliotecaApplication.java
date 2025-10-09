package org.primeiroprojetocursooo.projetobancodedados2biblioteca;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.primeiroprojetocursooo.projetobancodedados2biblioteca.DAO.UsuarioDAO;
import org.primeiroprojetocursooo.projetobancodedados2biblioteca.entity.*;
import org.primeiroprojetocursooo.projetobancodedados2biblioteca.entity.enums.LocacaoStatus;
import org.primeiroprojetocursooo.projetobancodedados2biblioteca.util.JPAUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

import static org.primeiroprojetocursooo.projetobancodedados2biblioteca.entity.enums.LivroStatus.DISPONIVEL;

@SpringBootApplication
public class ProjetoBancoDeDados2BibliotecaApplication {

    public static void main(String[] args) {

        // 1️⃣ Criando locação e livros
        Usuario usuario = new Usuario(null,"Erick","erickgeovane2002@gmail.com","123456789","83999938317");
        Livro livro = new Livro(null, "Java Básico", 5.0);
        Categoria categoria = new Categoria(null,"javeiro");
        livro.setCategoria(categoria);
        livro.setStatus(DISPONIVEL);
        Locacao locacao = new Locacao(null, LocalDate.of(2025, 10, 1), LocalDate.of(2025, 10, 5), LocacaoStatus.LOCADA);
        locacao.getLivros().add(livro);
        Pagamento pagamento = new Pagamento(null, LocalDate.of(2025,10,5),locacao);

        System.out.println("Valor total da locação: R$" + locacao.getValorTotal());
        UsuarioDAO usuarioDAO = new UsuarioDAO(Usuario.class);
        usuarioDAO.listar();




    }
}
