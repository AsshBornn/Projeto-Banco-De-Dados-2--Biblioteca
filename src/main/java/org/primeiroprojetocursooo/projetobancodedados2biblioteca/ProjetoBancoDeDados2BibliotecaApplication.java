package org.primeiroprojetocursooo.projetobancodedados2biblioteca;

import org.primeiroprojetocursooo.projetobancodedados2biblioteca.entity.Livro;
import org.primeiroprojetocursooo.projetobancodedados2biblioteca.entity.Locacao;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class ProjetoBancoDeDados2BibliotecaApplication {

    public static void main(String[] args) {
        Locacao loc = new Locacao(1, LocalDate.of(2025,10,1), LocalDate.of(2025,10,5));
        Livro l1 = new Livro(1, "Java Básico", 5.0);
        Livro l2 = new Livro(2, "Spring Boot", 8.0);
        loc.getLivros().add(l1);
        loc.getLivros().add(l2);

        System.out.println("Valor total da locação: R$" + loc.getValorTotal());

        //SpringApplication.run(ProjetoBancoDeDados2BibliotecaApplication.class, args);
    }

}
