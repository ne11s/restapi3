package org.example.restapi3.service;

import org.example.restapi3.dto.ProductReceiveDto;
import org.example.restapi3.dto.ProductResponseDto;
import org.example.restapi3.exeception.NotFoundException;
import org.example.restapi3.models.Product;
import org.example.restapi3.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductResponseDto create (ProductReceiveDto realisateurReceiveDto){
        return productRepository.save(realisateurReceiveDto.dtoToEntity()).entityToDto();
    }

    public ProductResponseDto get(long id){
        return productRepository.findById(id).orElseThrow(NotFoundException::new).entityToDto();
    }

    public List<ProductResponseDto> get(){
        return productRepository.findAll().stream().map(Product::entityToDto).toList();
    }
    public ProductResponseDto update(long id,ProductReceiveDto productReceiveDto){
        Product  productFound = productRepository.findById(id).orElseThrow(NotFoundException::new);
        Product productGet = productReceiveDto.dtoToEntity();
        productFound.setId(productGet.getId());
        productFound.setName(productGet.getName());
        productFound.setPrice(productGet.getPrice());
        return productRepository.save(productFound).entityToDto();
    }

    public void delete (long id){
        productRepository.deleteById(id);
    }

}
