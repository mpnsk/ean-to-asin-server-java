package com.paunoski.eantoasinserverjava;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExposedController {

    @RequestMapping("/query")
    public String string(@RequestParam(name = "ean") String ean) {
        return ean;
    }
}
