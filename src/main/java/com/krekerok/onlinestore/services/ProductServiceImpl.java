package com.krekerok.onlinestore.services;

import com.krekerok.onlinestore.entities.Product;
import com.krekerok.onlinestore.entities.ProductsStatus;
import com.krekerok.onlinestore.entities.User;
import com.krekerok.onlinestore.pojo.AddProductRequest;
import com.krekerok.onlinestore.pojo.MessageResponse;
import com.krekerok.onlinestore.pojo.ProductResponse;
import com.krekerok.onlinestore.repositories.ProductRepository;
import com.krekerok.onlinestore.services.interfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ResponseEntity<?> getProductById(int id) {
        Optional<Product> optionalProduct = productRepository.findById(id);

        if(!optionalProduct.isPresent()){
            return ResponseEntity.status(401).body(new MessageResponse("Product NOT FOUND"));
        }

        Product product = optionalProduct.get();
        return  ResponseEntity.ok(new ProductResponse(id, product.getName(), product.getPrice(), product.getStatus(), product.getCreatedAt()));
    }

    @Override
    public ResponseEntity<?> addProduct(AddProductRequest addProductRequest) {

        if (productRepository.existsByName(addProductRequest.getName()))
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Product is exist"));

        int a = (int) (Math.random() * (ProductsStatus.values().length));
        String status = String.valueOf(ProductsStatus.values()[a]);


        Product product = new Product(addProductRequest.getName(), addProductRequest.getPrice(), status, new Date());
        productRepository.save(product);

        return ResponseEntity.ok(new MessageResponse("Product ADDED"));
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


}
