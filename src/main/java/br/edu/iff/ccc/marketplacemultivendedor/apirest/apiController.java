package br.edu.iff.ccc.marketplacemultivendedor.apirest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class apiController {

    @GetMapping("/api/v1")
    public String api() {
        return "API Marketplace Multivendedor";
    }

}