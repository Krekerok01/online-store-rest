package com.krekerok.onlinestore.services.interfaces;

import com.krekerok.onlinestore.dto.requests.OrderRequest;
import org.springframework.http.ResponseEntity;

public interface OrderService {

    public ResponseEntity<?> createOrder(OrderRequest orderRequest, String jwt);
}
