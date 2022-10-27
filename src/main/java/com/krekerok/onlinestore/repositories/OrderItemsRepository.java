package com.krekerok.onlinestore.repositories;

import com.krekerok.onlinestore.entities.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemsRepository extends JpaRepository<OrderItems, Integer> {

    List<OrderItems> findOrderItemsByOrderId(int id);
}
