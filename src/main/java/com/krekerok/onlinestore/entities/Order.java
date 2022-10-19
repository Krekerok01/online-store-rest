package com.krekerok.onlinestore.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "order")
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "status")
    private String status;

    @Column(name = "created_at")
    private String createdAt;

    public Order(int userId, String status, String createdAt) {
        this.userId = userId;
        this.status = status;
        this.createdAt = createdAt;
    }
}
