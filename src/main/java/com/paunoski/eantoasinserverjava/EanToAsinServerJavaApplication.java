package com.paunoski.eantoasinserverjava;

import com.paunoski.eantoasinserverjava.persistence.Article;
import com.paunoski.eantoasinserverjava.persistence.EanRepository;
import com.paunoski.eantoasinserverjava.xml.ItemLookupResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.SchemaOutputResolver;
import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

@SpringBootApplication
public class EanToAsinServerJavaApplication implements CommandLineRunner {
    @Autowired
    JavaCodeSnippet javaCodeSnippet;

    public static void main(String[] args) {
        SpringApplication.run(EanToAsinServerJavaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        String url = javaCodeSnippet.getUrl("9780465095001");
//        String url = "http://localhost:8081/simple.xml";
        String url = "http://localhost:8081/xml.xml";
//        System.out.println(url);


//        RestTemplate restTemplate = new RestTemplate();
//        ItemLookupResponse object = restTemplate.getForObject(url, ItemLookupResponse.class);
//        System.out.println(object);

        JAXBContext jaxbContext = JAXBContext.newInstance(ItemLookupResponse.class);
        jaxbContext.generateSchema(new SchemaOutputResolver() {

            @Override
            public Result createOutput(String namespaceUri, String suggestedFileName) throws IOException {
                StreamResult streamResult = new StreamResult(new PrintWriter(System.err) {
                    @Override
                    public void close() {
                    }
                });
                streamResult.setSystemId(suggestedFileName);
                return streamResult;
            }
        });
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
