package com.paunoski.eantoasinserverjava.persistence;


import lombok.Data;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

@Entity
@Data
@Getter
public class Article {

    @Id
    String ean;
    String asin;
    String title;
    @Column
    @ElementCollection(targetClass=String.class)
    List<String> features;
    String productGroup;
    int quantity;

    public void addToQunatity(int i) {
        quantity += i;
    }
}