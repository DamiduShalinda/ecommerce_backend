package com.ecommerce.backend.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Table
@Entity
public class Category {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @Column
    private String categoryName;
    @Column
    private String categoryDescription;

    public Category() {

    }

    public Category(String categoryName, String categoryDescription) {
        this.categoryName = categoryName;
        this.categoryDescription = categoryDescription;
    }


}
