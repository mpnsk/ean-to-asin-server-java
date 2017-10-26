package com.paunoski.eantoasinserverjava;

import com.paunoski.eantoasinserverjava.xml.ItemLookupResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@RestController
public class ExposedController {
    private final JavaCodeSnippet javaCodeSnippet;
    private final RestTemplate restTemplate;

    @Autowired
    public ExposedController(JavaCodeSnippet javaCodeSnippet) {
        this.javaCodeSnippet = javaCodeSnippet;
        restTemplate = new RestTemplate();
    }

    @RequestMapping("/title")
    public String getTitle(String ean) {
        ItemLookupResponse response = getItemLookupResponse(ean);
        return response.items.item.itemAttributes.title;
    }

    @RequestMapping("/asin")
    public String getAsin(String ean) {
        ItemLookupResponse response = getItemLookupResponse(ean);
        return response.items.item.asin;
    }

    @RequestMapping("/features")
    public String getFeatures(String ean){
        ItemLookupResponse response = getItemLookupResponse(ean);
        StringBuilder stringBuilder = new StringBuilder();
        for (String feature : response.items.item.itemAttributes.featureList) {
            stringBuilder.append(feature);
            stringBuilder.append(';');
        }
        return String.valueOf(stringBuilder);
    }


    private ItemLookupResponse getItemLookupResponse(String ean) {
        URI url = URI.create(javaCodeSnippet.getUrl(ean));
        ItemLookupResponse itemLookupResponse = restTemplate.getForObject(url, ItemLookupResponse.class);
        return itemLookupResponse;
    }
//        String url = javaCodeSnippet.getUrl("3010006699976");
}
