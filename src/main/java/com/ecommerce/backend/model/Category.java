package com.ecommerce.backend.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Category {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    private String categoryName;
    private String categoryDescription;

    @OneToMany(mappedBy = "category")
    private List<Product> products;

    public Category() {

    }

    public Category(String categoryName, String categoryDescription) {
        this.categoryName = categoryName;
        this.categoryDescription = categoryDescription;
    }


}
