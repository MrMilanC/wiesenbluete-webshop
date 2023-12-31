package com.example.wiesenbluetewebshop.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
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

    public Product () {}

    public Product(long l, String s, double v) {
    }

    public void setId(long l) {
    }

//    @OneToOne(mappedBy = "item")
//    CartItem cartItem;

//    @ManyToOne //(cascade = CascadeType.ALL)
//    //@JoinColumn (name = "category_id", referencedColumnName = "category_id")
//    private Category category;


}
