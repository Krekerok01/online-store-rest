package com.krekerok.onlinestore.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProductAndQuantity {

    private int productId;

    private int quantity;
}
