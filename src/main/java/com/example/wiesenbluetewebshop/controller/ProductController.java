package com.example.wiesenbluetewebshop.controller;

import com.example.wiesenbluetewebshop.exception.ProductException;
import com.example.wiesenbluetewebshop.model.Product;
import com.example.wiesenbluetewebshop.service.CategoryService;
import com.example.wiesenbluetewebshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
//@CrossOrigin(origins = "*")
//@CrossOrigin(origins = "http://localhost:63343")
@CrossOrigin(origins = "http://localhost:63343", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE}, allowedHeaders = "*")
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/view")
    //@PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<Product>> viewAllProduct() throws ProductException {
        return new ResponseEntity<List<Product>>(productService.viewAllProduct(), HttpStatus.OK);
    }

    @GetMapping("view/category/{categoryId}")
    public ResponseEntity<List<Product>> viewProductByCategoryId(@PathVariable("categoryId") Long categoryId)
            throws ProductException {
        System.out.println("gedruckt");
        return new ResponseEntity<List<Product>>(categoryService.viewProductByCategory(categoryId), HttpStatus.OK);
    }
}
