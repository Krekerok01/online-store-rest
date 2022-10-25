package com.krekerok.onlinestore.dto.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddProductRequest {

    private String name;
    private int price;
}
