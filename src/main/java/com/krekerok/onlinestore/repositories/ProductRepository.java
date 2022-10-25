package com.krekerok.onlinestore.repositories;

import com.krekerok.onlinestore.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    Boolean existsByName(String name);

    Boolean existsById(int id);
}
