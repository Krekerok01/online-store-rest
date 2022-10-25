package com.krekerok.onlinestore.services.interfaces;

import com.krekerok.onlinestore.pojo.AddProductRequest;
import org.springframework.http.ResponseEntity;

public interface ProductService {

    ResponseEntity<?> addProduct(AddProductRequest addProductRequest);

//    ResponseEntity<?> deleteProductById(int id);
//
//    ResponseEntity<?> deleteProductByName(String productName);
//
//    ResponseEntity<?> getProductById(int id);
}
