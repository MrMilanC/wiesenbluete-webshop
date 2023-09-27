package com.example.wiesenbluetewebshop.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
//@Embeddable
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    private String productName;
    private double price;
    private String description;
    private double quantity;
    private String imageName;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

//    @ManyToOne //(cascade = CascadeType.ALL)
//    //@JoinColumn (name = "category_id", referencedColumnName = "category_id")
//    private Category category;


}
