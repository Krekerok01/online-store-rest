package com.krekerok.onlinestore.services;

import com.krekerok.onlinestore.entities.Product;
import com.krekerok.onlinestore.entities.ProductsStatus;
import com.krekerok.onlinestore.entities.User;
import com.krekerok.onlinestore.pojo.AddProductRequest;
import com.krekerok.onlinestore.pojo.MessageResponse;
import com.krekerok.onlinestore.repositories.ProductRepository;
import com.krekerok.onlinestore.services.interfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ResponseEntity<?> addProduct(AddProductRequest addProductRequest) {

        if (productRepository.existsByName(addProductRequest.getName()))
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Product is exist"));

        int a = (int) (Math.random() * (ProductsStatus.values().length - 1));
        String status = String.valueOf(ProductsStatus.values()[a]);

        System.out.println(status);
//        Product product = new Product(addProductRequest.getName(), addProductRequest.getPrice(), , new Date());
//        productRepository.save(product);
            return null;
    }

//    @Override
//    public ResponseEntity<?> deleteProductById(int id) {
//        return null;
//    }
//
//    @Override
//    public ResponseEntity<?> deleteProductByName(String productName) {
//        return null;
//    }
//
//    @Override
//    public ResponseEntity<?> getProductById(int id) {
//        return null;
//    }
}
