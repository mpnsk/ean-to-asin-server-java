package com.paunoski.eantoasinserverjava;

import com.google.gson.Gson;
import com.paunoski.eantoasinserverjava.xml.ItemLookupResponse;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.SchemaOutputResolver;
import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.io.PrintWriter;

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


        RestTemplate restTemplate = new RestTemplate();
        String object = restTemplate.getForObject(url, String.class);
        JSONObject jsonObject = XML.toJSONObject(object);
        String string = jsonObject.toString(4);
        System.out.println(string);

        Gson gson = new Gson();
        ItemLookupResponse itemLookupResponse = gson.fromJson(string, ItemLookupResponse.class);
        System.out.println(itemLookupResponse);


        /*
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
        */
    }
}
