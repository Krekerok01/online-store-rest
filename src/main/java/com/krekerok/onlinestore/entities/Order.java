package com.krekerok.onlinestore.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "orders")
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
    private Date createdAt;

    public Order(int userId, String status, Date createdAt) {
        this.userId = userId;
        this.status = status;
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Order{" +
                "userId=" + userId +
                ", status='" + status + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
