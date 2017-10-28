package com.paunoski.eantoasinserverjava.persistence;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EanRepository extends CrudRepository<Article, String> {
    List<Article> findByEan(String ean);

}
