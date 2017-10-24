package com.paunoski.eantoasinserverjava;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EanToAsinServerJavaApplication implements CommandLineRunner {
    @Autowired
    JavaCodeSnippet javaCodeSnippet;

    public static void main(String[] args) {
        SpringApplication.run(EanToAsinServerJavaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(javaCodeSnippet.getUrl("9780465095001"));
    }
}
