package org.example.restapi3.Controller;

import org.example.restapi3.dto.ProductReceiveDto;
import org.example.restapi3.dto.ProductResponseDto;
import org.example.restapi3.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/Product")
public class ProductController {

    private ProductService service;
    ProductController(ProductService productService) {
        this.service = productService;
    }
    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> getAll (){
        return ResponseEntity.ok(service.get());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> get (@PathVariable long id){
        return ResponseEntity.ok(service.get(id));
    }

    @PostMapping
    public ResponseEntity<ProductResponseDto> create (@RequestBody ProductReceiveDto productReceiveDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(productReceiveDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDto> update (@PathVariable long id , @RequestBody ProductReceiveDto productReceiveDto){
        return ResponseEntity.ok(service.update(id,productReceiveDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delte(@PathVariable long id){
        service.delete(id);
        return ResponseEntity.ok(String.format("product at id : %s is deleted",id));
    }
}
