package com.krekerok.onlinestore.pojo;

import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
public class ProductResponse {

    private int id;
    private String name;
    private int price;
    private String status;
    private Date createdAt;

    public ProductResponse(int id, String name, int price, String status, Date createdAt) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.status = status;
        this.createdAt = createdAt;
    }
}