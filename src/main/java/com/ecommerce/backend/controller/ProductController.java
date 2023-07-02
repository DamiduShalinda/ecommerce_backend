package com.ecommerce.backend.controller;


import com.ecommerce.backend.dto.ProductDTO;
import com.ecommerce.backend.model.Category;
import com.ecommerce.backend.model.Product;
import com.ecommerce.backend.services.CategoryService;
import com.ecommerce.backend.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;

    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @PostMapping("/product")
    public ResponseEntity<Product> addProduct(@RequestBody ProductDTO productDTO) {

        Category category = categoryService.getCategoryByID(productDTO.getCategoryId());

        if (category == null){
            return ResponseEntity.badRequest().build();
        }

        Product product = new Product();
        product.setProductName(productDTO.getProductName());
        product.setProductDescription(productDTO.getProductDescription());
        product.setPrice(productDTO.getPrice());
        product.setCategory(category);

        Product saveProduct = productService.addProduct(product);

        return ResponseEntity.ok(saveProduct);


    }
}
