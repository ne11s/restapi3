package org.example.restapi3.Controller;

import jakarta.servlet.http.HttpSession;
import org.example.restapi3.dto.ProductResponseDto;
import org.example.restapi3.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/cart")
public class CartSessionController {

    private ProductService service;

    private HttpSession session;

    public CartSessionController(ProductService service, HttpSession session) {
        this.service = service;
        this.session = session;
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> getCart (){
        List<Long> productId = (List<Long>) session.getAttribute("cart");
        List<ProductResponseDto> userResponseDtos = new ArrayList<>();

        if(productId != null){
            for (long id : productId){
                userResponseDtos.add(service.get(id));
            }
        }else{
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ArrayList<>());
        }
        return ResponseEntity.ok(userResponseDtos);
    }
    @GetMapping("/{id}")
    public ResponseEntity<String> addToCart (@PathVariable long id){
        List<Long> productId = (List<Long>) session.getAttribute("cart");

        if(productId == null){
            productId = new ArrayList<>();
        }

        productId.add(service.get(id).getId());

        session.setAttribute("cart",productId);
        return ResponseEntity.ok("Product add to this cart");
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeFromCart (@PathVariable long id){
        List<Long> productId = (List<Long>) session.getAttribute("cart");
        if(productId == null){
            productId = new ArrayList<>();
        }
        productId.remove(service.get(id).getId());
        session.setAttribute("cart",productId);
        return ResponseEntity.ok("Product remove from this cart");
    }
    @GetMapping("/total")
    public ResponseEntity<String> getTotal(){
        List<Long> productId = (List<Long>) session.getAttribute("cart");
        double total = 0;
        if(productId != null){
            for (long id : productId){
                total += service.get(id).getPrice();



            }
        }else{
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No products in this cart");
        }
        return ResponseEntity.ok("Total : " + total );
    }

}
