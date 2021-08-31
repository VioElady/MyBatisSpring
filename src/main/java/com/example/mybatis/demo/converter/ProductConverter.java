package com.example.mybatis.demo.converter;

import com.example.mybatis.demo.model.Product;
import com.example.mybatis.demo.dto.product.ProductDto;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductConverter {
    public ProductDto modelToDTO(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setTitle(product.getTitle());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());

        return productDto;
    }

    public List<ProductDto> modelToDTO(List<Product> products) {
        return products.stream().map(this::modelToDTO).collect(Collectors.toList());
    }

    public Product dtoToModel(ProductDto productDto) {
        Product product = new Product();
        product.setDescription(productDto.getDescription());
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        return product;
    }

    public List<Product> dtoToModel(List<ProductDto> dtoProducts) {
        return dtoProducts.stream().map(this::dtoToModel).collect(Collectors.toList());
    }

}
