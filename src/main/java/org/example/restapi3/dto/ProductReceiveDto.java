package org.example.restapi3.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.restapi3.models.Product;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductReceiveDto {

    private long id;
    @Size(min = 3, max = 25)
    private String name;
    private Double price;

    public Product dtoToEntity (){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return Product.builder()
                .id(getId())
                .name(getName())
                .price(getPrice())
                .build();
    }
}
