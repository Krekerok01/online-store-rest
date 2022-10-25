package com.krekerok.onlinestore.repositories;

import com.krekerok.onlinestore.entities.Product;
import com.krekerok.onlinestore.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    Boolean existsByName(String name);
}
