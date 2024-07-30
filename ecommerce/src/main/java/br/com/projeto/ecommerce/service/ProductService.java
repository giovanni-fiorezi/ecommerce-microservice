package br.com.projeto.ecommerce.service;

import br.com.projeto.ecommerce.dto.ProductDto;
import br.com.projeto.ecommerce.enums.ProductCategory;
import br.com.projeto.ecommerce.models.Product;
import br.com.projeto.ecommerce.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductDto> findAll() {
        try {
            List<Product> products = productRepository.findAll();
            return products.stream().map(
                    p -> modelMapper.map(p, ProductDto.class)).collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao buscar todos os produtos!");
        }
    }

    public List<ProductDto> findProductsByCategory(ProductCategory productCategory) {
        try {
            List<Product> products = productRepository.findAll();
            return products.stream()
                    .filter(product -> product.getProductCategory().equals(productCategory))
                    .map(p -> modelMapper.map(p, ProductDto.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao buscar os produtos!");
        }
    }

    public ProductDto findById(Long id) {
        if(id != null) {
            Optional<Product> product = productRepository.findById(id);
            return modelMapper.map(product, ProductDto.class);
        } else {
            throw new RuntimeException("Insira um id para fazer a busca!");
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void saveProduct(ProductDto dto) {
        try {
            if(dto == null) {
                throw new RuntimeException("Insira os dados do produto!");
            }
            Product product = modelMapper.map(dto, Product.class);
            productRepository.save(product);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Transactional(rollbackFor=Exception.class)
    public void removeProduct(Long id) {
        if(id == null) {
            throw new RuntimeException("Insira um id válido!");
        }
        Optional<Product> product = productRepository.findById(id);
        product.ifPresentOrElse(
                p -> productRepository.deleteById(id),
                () -> {
                    throw new RuntimeException("Produto não encontrado com ID: " + id);
                }
        );
    }



}
