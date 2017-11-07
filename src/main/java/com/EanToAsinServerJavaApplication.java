package com;

import com.amazon.JavaCodeSnippet;
import com.paunoski.eantoasinserverjava.persistence.Article;
import com.paunoski.eantoasinserverjava.persistence.EanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class EanToAsinServerJavaApplication {
    @Autowired
    JavaCodeSnippet javaCodeSnippet;

    public static void main(String[] args) {
        SpringApplication.run(EanToAsinServerJavaApplication.class, args);
    }

    @Bean
    public CommandLineRunner initRepo(EanRepository repository) {
        return args -> {
            Article article = new Article();
            article.setEan("3010006699976");
            article.setAsin("B001CQV5R8");
            article.setTitle("Risiko - Ein Strategiespiel. Das Brettspiel (Erscheinungsjahr 1982)\n");
            article.setFeatures(Arrays.asList("Strategiespiel"));
            article.setProductGroup("Toy");

            repository.save(article);
        };
    }
}
