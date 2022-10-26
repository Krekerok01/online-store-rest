package com.krekerok.onlinestore.services;

import com.krekerok.onlinestore.configs.jwt.JwtUtils;
import com.krekerok.onlinestore.dto.requests.OrderRequest;
import com.krekerok.onlinestore.dto.requests.ProductAndQuantity;
import com.krekerok.onlinestore.dto.responses.MessageResponse;
import com.krekerok.onlinestore.entities.*;
import com.krekerok.onlinestore.repositories.OrderItemsRepository;
import com.krekerok.onlinestore.repositories.OrderRepository;
import com.krekerok.onlinestore.repositories.ProductRepository;
import com.krekerok.onlinestore.repositories.UserRepository;
import com.krekerok.onlinestore.services.interfaces.OrderService;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.util.Date;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;
    private ProductRepository productRepository;
    private OrderItemsRepository orderItemsRepository;
    private UserRepository userRepository;
    private JwtUtils jwtUtils;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, ProductRepository productRepository, OrderItemsRepository orderItemsRepository, UserRepository userRepository, JwtUtils jwtUtils) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.orderItemsRepository = orderItemsRepository;
        this.userRepository = userRepository;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public ResponseEntity<?> createOrder(OrderRequest orderRequest, String jwt) {

        Optional<User> user = userRepository.findByUsername(jwtUtils.getUserNameFromJwtToken(jwt));

        if (!user.isPresent())
            return ResponseEntity.status(401).body(new MessageResponse("Orders can be created only by authorized users"));

        Order order = new Order(user.get().getId(), String.valueOf(ProductsStatus.values()[1]), new Date());
        orderRepository.save(order);


        for (ProductAndQuantity pr: orderRequest.getOrder()){
            int productId = pr.getProductId();
            int quantity = pr.getQuantity();

            //PRODUCT
            OrderItems orderItems = new OrderItems(order, productRepository.findById(productId).get(), quantity);
            orderItemsRepository.save(orderItems);

        }


        return ResponseEntity.ok(new MessageResponse("Order CREATED"));
    }
}
