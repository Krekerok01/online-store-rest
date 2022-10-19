package com.krekerok.onlinestore.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "product")
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private int price;

    @Column(name = "status")
    private String status;

    @Column(name = "created_at")
    private Date createdAt;


    public Product(String name, int price, String status, Date createdAt) {
        this.name = name;
        this.price = price;
        this.status = status;
        this.createdAt = createdAt;
    }
}
