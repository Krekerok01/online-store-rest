package com.krekerok.onlinestore.contollers;

import com.krekerok.onlinestore.dto.requests.AddProductRequest;
import com.krekerok.onlinestore.dto.responses.ProductResponse;
import com.krekerok.onlinestore.services.interfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addProduct(@RequestBody AddProductRequest addProductRequest) {
        return productService.addProduct(addProductRequest);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<?> getProduct(@PathVariable int id) {
        return productService.getProductById(id);
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable int id) {
        return productService.deleteProductById(id);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProductResponse>> getAllProducts(){
        return productService.getAllProducts();
    }
}
