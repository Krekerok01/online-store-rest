package com.krekerok.onlinestore.services.interfaces;

import com.krekerok.onlinestore.dto.requests.AddProductRequest;
import org.springframework.http.ResponseEntity;

public interface ProductService {

    ResponseEntity<?> addProduct(AddProductRequest addProductRequest);

    ResponseEntity<?> deleteProductById(int id);

    ResponseEntity<?> getProductById(int id);
}
