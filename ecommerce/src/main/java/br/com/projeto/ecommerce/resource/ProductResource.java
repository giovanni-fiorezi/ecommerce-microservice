package br.com.projeto.ecommerce.resource;

import br.com.projeto.ecommerce.dto.ProductDto;
import br.com.projeto.ecommerce.enums.ProductCategory;
import br.com.projeto.ecommerce.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductResource {

    private final ProductService productService;

    public ProductResource(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> findAll() {
        List<ProductDto> products = productService.findAll();
        return ResponseEntity.ok().body(products);
    }

    @GetMapping("/find-by-category")
    public ResponseEntity<List<ProductDto>> findProductsByCategory(@RequestParam("category")ProductCategory productCategory) {
        List<ProductDto> products = productService.findProductsByCategory(productCategory);
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDto> findById(@PathVariable Long productId) {
        ProductDto dto = productService.findById(productId);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<String> saveProduct(@RequestBody ProductDto productDto) {
        productService.saveProduct(productDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Produto criado com sucesso!");
    }

    @DeleteMapping
    public ResponseEntity<String> saveProduct(@PathVariable Long id) {
        productService.removeProduct(id);
        return ResponseEntity.status(HttpStatus.CREATED).body("Produto removido com sucesso!");
    }

    @PostMapping
    public ResponseEntity<String> uploadFile(@RequestParam("file")MultipartFile file) throws IOException {
        productService.readAndSaveExcel(file);
        return ResponseEntity.status(HttpStatus.CREATED).body("Dados do arquivo salvo com sucesso!");
    }

}
