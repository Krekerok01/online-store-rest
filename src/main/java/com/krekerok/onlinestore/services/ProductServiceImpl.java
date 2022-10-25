package com.krekerok.onlinestore.services;

import com.krekerok.onlinestore.entities.Product;
import com.krekerok.onlinestore.entities.ProductsStatus;
import com.krekerok.onlinestore.dto.requests.ProductRequest;
import com.krekerok.onlinestore.dto.responses.MessageResponse;
import com.krekerok.onlinestore.dto.responses.ProductResponse;
import com.krekerok.onlinestore.repositories.ProductRepository;
import com.krekerok.onlinestore.services.interfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

        if(!optionalProduct.isPresent())
            return ResponseEntity.status(401).body(new MessageResponse("Product NOT FOUND"));

        Product product = optionalProduct.get();
        return  ResponseEntity.ok(new ProductResponse(id, product.getName(), product.getPrice(), product.getStatus(), product.getCreatedAt()));
    }

    @Override
    public ResponseEntity<List<ProductResponse>> getAllProducts() {

        List<ProductResponse> products = productRepository.findAll()
                .stream()
                .map((product ->
                        new ProductResponse(product.getId(), product.getName(), product.getPrice(), product.getStatus(), product.getCreatedAt())))
                .collect(Collectors.toList());

        return ResponseEntity.ok(products);
    }


    @Override
    public ResponseEntity<?> addProduct(ProductRequest productRequest) {

        if (productRepository.existsByName(productRequest.getName()))
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Product is exist"));

        Product product = new Product(productRequest.getName(), productRequest.getPrice(), getRandomStatus(), new Date());
        productRepository.save(product);

        return ResponseEntity.ok(new MessageResponse("Product ADDED"));
    }



    private String getRandomStatus(){
        int a = (int) (Math.random() * (ProductsStatus.values().length));
        return String.valueOf(ProductsStatus.values()[a]);
    }


    @Override
    public ResponseEntity<?> updateProduct(int id, ProductRequest productRequest) {

        if (!productRepository.existsById(id))
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Product is not exist"));

        setNewDataToTheProductAndSaveIt(productRepository.findById(id).get(), productRequest.getName(), productRequest.getPrice());

        return ResponseEntity.ok(new MessageResponse("Product UPDATED"));
    }

    private void setNewDataToTheProductAndSaveIt(Product product, String name, int price) {
        product.setName(name);
        product.setPrice(price);
        productRepository.save(product);
    }


    @Override
    public ResponseEntity<?> deleteProductById(int id) {

        Optional<Product> optionalProduct = productRepository.findById(id);

        if(!optionalProduct.isPresent())
            return ResponseEntity.status(401).body(new MessageResponse("Product NOT FOUND"));

        if(!(optionalProduct.get().getStatus().equals(ProductsStatus.OUT_OF_STOCK.toString())))
            return ResponseEntity.status(401).body(new MessageResponse("You can't delete product with status IN_STOCK or RUNNING_LOW"));

        productRepository.deleteById(id);
        return ResponseEntity.ok(new MessageResponse("Product DELETED"));
    }

}
