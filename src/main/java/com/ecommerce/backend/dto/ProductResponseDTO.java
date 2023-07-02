package com.ecommerce.backend.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponseDTO {

    private Long id;
    private String productName;
    private String productDescription;
    private double price;
    private String categoryName;
}
