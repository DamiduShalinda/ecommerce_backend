package com.ecommerce.backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {

    private String productName;
    private String productDescription;
    private double price;
    private Long categoryId;
}
