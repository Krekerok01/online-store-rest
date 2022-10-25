package com.krekerok.onlinestore.services.interfaces;

import com.krekerok.onlinestore.dto.requests.AddProductRequest;
import com.krekerok.onlinestore.dto.responses.ProductResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {

    ResponseEntity<?> addProduct(AddProductRequest addProductRequest);

    ResponseEntity<?> deleteProductById(int id);

    ResponseEntity<?> getProductById(int id);

    ResponseEntity<List<ProductResponse>> getAllProducts();
}
