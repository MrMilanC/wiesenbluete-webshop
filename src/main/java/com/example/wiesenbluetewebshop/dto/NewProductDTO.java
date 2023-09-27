package com.example.wiesenbluetewebshop.dto;

import lombok.Data;

@Data
public class NewProductDTO {

    private Long productId;
    private String productName;
    private double price;
    private String description;
    private double quantity;
    private String imageName;
    private Long categoryId;

}
