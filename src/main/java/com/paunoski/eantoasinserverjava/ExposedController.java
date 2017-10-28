package com.paunoski.eantoasinserverjava;

import com.paunoski.eantoasinserverjava.persistence.Article;
import com.paunoski.eantoasinserverjava.persistence.EanRepository;
import com.paunoski.eantoasinserverjava.xml.ItemLookupResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@RestController
public class ExposedController {
    private final JavaCodeSnippet javaCodeSnippet;
    private final RestTemplate restTemplate;
    private final EanRepository repository;

    @Autowired
    public ExposedController(JavaCodeSnippet javaCodeSnippet, EanRepository repository) {
        this.javaCodeSnippet = javaCodeSnippet;
        restTemplate = new RestTemplate();
        this.repository = repository;
    }

    @GetMapping("/title")
    public String getTitle(String ean) {
        Article response = getItemLookupResponse(ean);
        return response.getTitle();
    }

    @GetMapping("/asin")
    public String getAsin(String ean) {
        Article response = getItemLookupResponse(ean);
        return response.getAsin();
    }

    @GetMapping("/features")
    public String getFeatures(String ean) {
        Article response = getItemLookupResponse(ean);
        StringBuilder stringBuilder = new StringBuilder();
        for (String feature : response.getFeatures()) {
            stringBuilder.append(feature);
            stringBuilder.append(';');
        }
        return String.valueOf(stringBuilder);
    }

    @GetMapping("/article")
    public Article getArticle(String ean){
        return getItemLookupResponse(ean);
    }

    @GetMapping("/")
    public Article getRoot(String ean) {
        return getArticle(ean);
    }

    @GetMapping("/add")
    public Article addArticle(String ean) {
        Article article = getArticle(ean);
        article.addToQunatity(1);
        System.out.println(article);
        repository.save(article);
        return article;
    }


    private Article getItemLookupResponse(String ean) {
        List<Article> byEan = repository.findByEan(ean);
        if (!byEan.isEmpty()) {
            Article article = byEan.get(0);
            return article;
        } else {
            URI url = URI.create(javaCodeSnippet.getUrl(ean));
            ItemLookupResponse response = restTemplate.getForObject(url, ItemLookupResponse.class);
            Article article = new Article();
            article.setEan(response.items.item.itemAttributes.ean);
            article.setAsin(response.items.item.asin);
            article.setTitle(response.items.item.itemAttributes.title);
            article.setFeatures(response.items.item.itemAttributes.featureList);
            article.setProductGroup(response.items.item.itemAttributes.productGroup);
            repository.save(article);
            return article;
        }

    }
//        String url = javaCodeSnippet.getUrl("3010006699976");
}
