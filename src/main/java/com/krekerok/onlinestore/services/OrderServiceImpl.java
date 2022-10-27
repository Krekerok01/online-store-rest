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
import java.util.List;
import java.util.Optional;


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

        for (ProductAndQuantity productAndQuantity: orderRequest.getOrder()){
            Product product = productService.getProductByProductId(productAndQuantity.getProductId());
            if(!(product.getStatus().equals(ProductsStatus.OUT_OF_STOCK.toString())))
                return ResponseEntity.status(401).body(new MessageResponse("You can't add a product to an order that isn't in stock"));

            OrderItems orderItems = new OrderItems(order,product, productAndQuantity.getQuantity());
            orderItemsRepository.save(orderItems);
        }

        return ResponseEntity.ok(new MessageResponse("Order CREATED"));
    }

    @Override
    public ResponseEntity<?> deleteOrderById(int id) {

        Optional<Order> optionalOrder= orderRepository.findById(id);

        if(!optionalOrder.isPresent())
            return ResponseEntity.status(401).body(new MessageResponse("Order with id - " + id + " -  NOT FOUND"));

        deleteDataFromOrderItemsTableByOrderId(id);
        orderRepository.deleteById(id);

        return ResponseEntity.ok(new MessageResponse("Order DELETED"));
    }

    private void deleteDataFromOrderItemsTableByOrderId(int id) {
        for (OrderItems orderItems : orderItemsRepository.findOrderItemsByOrderId(id)){
            orderItemsRepository.deleteById(orderItems.getId());
        }
    }

    private String getRandomOrderStatus(){
        int a = (int) (Math.random() * (OrdersStatus.values().length - 1));
        return String.valueOf(OrdersStatus.values()[a]);
    }

}
