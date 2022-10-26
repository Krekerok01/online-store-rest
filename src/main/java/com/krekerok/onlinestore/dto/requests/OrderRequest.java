package com.krekerok.onlinestore.dto.requests;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderRequest {

    private List<ProductAndQuantity> order;
}
