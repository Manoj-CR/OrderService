package org.example.orderservice.client;

import jakarta.websocket.ClientEndpoint;
import org.example.orderservice.model.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ProductClient {

    private RestTemplateBuilder restTemplateBuilder;
    public ProductClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    @GetMapping(value = "/products/{id}")
    public Product getProductById(@PathVariable(value = "id") Long productId){

        RestTemplate restTemplate=restTemplateBuilder.build();
        ResponseEntity<Product> productClientDto=restTemplate
                .getForEntity("http://localhost:8080/products/{id}", Product.class,productId);
        return productClientDto.getBody();
    }
}
