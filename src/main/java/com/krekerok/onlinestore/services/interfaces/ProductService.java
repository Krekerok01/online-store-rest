package com.krekerok.onlinestore.services.interfaces;

import com.krekerok.onlinestore.dto.requests.ProductRequest;
import com.krekerok.onlinestore.dto.responses.ProductResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {

    ResponseEntity<?> addProduct(ProductRequest productRequest);

    ResponseEntity<?> updateProduct(int id, ProductRequest productRequest);

    ResponseEntity<?> deleteProductById(int id);

    ResponseEntity<?> getProductById(int id);

    ResponseEntity<List<ProductResponse>> getAllProducts();


}
