package br.com.projeto.ecommerce.mapper;

import br.com.projeto.ecommerce.dto.ProductDto;
import br.com.projeto.ecommerce.models.Product;

public class ProductMapper {

    public static ProductDto entityToDto(Product product) {
        ProductDto dto = new ProductDto();
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setAmount(product.getAmount());
        dto.setProductCategory(product.getProductCategory());
        return dto;
    }

    public static Product dtoToEntity(ProductDto dto) {
        Product entity = new Product();
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());
        entity.setAmount(dto.getAmount());
        entity.setProductCategory(dto.getProductCategory());
        return entity;
    }
}
