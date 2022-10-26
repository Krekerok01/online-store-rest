package com.krekerok.onlinestore.services;

import com.krekerok.onlinestore.configs.jwt.JwtUtils;
import com.krekerok.onlinestore.dto.requests.OrderRequest;
import com.krekerok.onlinestore.dto.requests.ProductAndQuantity;
import com.krekerok.onlinestore.dto.responses.MessageResponse;
import com.krekerok.onlinestore.entities.*;
import com.krekerok.onlinestore.repositories.OrderItemsRepository;
import com.krekerok.onlinestore.repositories.OrderRepository;
import com.krekerok.onlinestore.services.interfaces.OrderService;
import com.krekerok.onlinestore.services.interfaces.ProductService;
import com.krekerok.onlinestore.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.Date;


@Service
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;
    private ProductService productService;
    private OrderItemsRepository orderItemsRepository;
    private UserService userService;
    private JwtUtils jwtUtils;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, ProductService productService, OrderItemsRepository orderItemsRepository, UserService userService, JwtUtils jwtUtils) {
        this.orderRepository = orderRepository;
        this.productService = productService;
        this.orderItemsRepository = orderItemsRepository;
        this.userService = userService;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public ResponseEntity<?> createOrder(OrderRequest orderRequest, String jwt) {

        User user = userService.getUserByUsername(jwtUtils.getUserNameFromJwtToken(jwt));
        Order order = new Order(user.getId(), getRandomOrderStatus(), new Date());
        orderRepository.save(order);

        for (int i = 0; i < 10; i++){
            System.out.println(getRandomOrderStatus());
        }

        fillOrderItemsTableWithDataFromRequest(orderRequest, order);

        return ResponseEntity.ok(new MessageResponse("Order CREATED"));
    }

    private String getRandomOrderStatus(){
        int a = (int) (Math.random() * (OrdersStatus.values().length - 1));
        return String.valueOf(OrdersStatus.values()[a]);
    }

    private void fillOrderItemsTableWithDataFromRequest(OrderRequest orderRequest, Order order) {
        for (ProductAndQuantity productAndQuantity: orderRequest.getOrder()){
            Product product = productService.getProductByProductId(productAndQuantity.getProductId());
            OrderItems orderItems = new OrderItems(order,product, productAndQuantity.getQuantity());
            orderItemsRepository.save(orderItems);
        }
    }
}
