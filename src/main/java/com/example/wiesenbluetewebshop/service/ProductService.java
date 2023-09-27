package com.example.wiesenbluetewebshop.service;

import com.example.wiesenbluetewebshop.exception.ProductException;
import com.example.wiesenbluetewebshop.model.Category;
import com.example.wiesenbluetewebshop.model.Product;
import com.example.wiesenbluetewebshop.repository.CategoryRepository;
import com.example.wiesenbluetewebshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Product> viewAllProduct() throws ProductException {
        List<Product> products = productRepository.findAll();
        if (products.size() > 0) {
            return products;
        } else {
            throw new ProductException("Products not found");
        }
    }

    public Product addProduct(Product product) throws ProductException, IOException {
        return productRepository.save(product);
    }

    public Product updateProduct(Product product) throws ProductException {
        Optional<Product> productUpdate = productRepository.findById(product.getProductId());
        if (productUpdate.isPresent()) {
            return productRepository.save(product);
        } else {
            throw new ProductException("Product not updated");
        }
    }

    public Product viewProduct(Long productId) throws ProductException {
        Optional<Product> opt = productRepository.findById(productId);
        if (opt.isPresent()) {
            return opt.get();
        } else {
            throw new ProductException("Product not found with product id - " + productId);
        }
    }

//    public List<Product> viewProductByCategory(Long categoryId) throws ProductException {
//        Optional<Category> category = categoryRepository.findById(categoryId);
//        if (category.isPresent()) {
//            return category.get().getProductList();
//        } else {
//            throw new ProductException("Product not found with category id - " + categoryId);
//        }
//    }



//    public List<Product> getAllProductsByCategoryId(Long categoryId) {
//        return productRepository.findAllByCategoryId(categoryId);
//    }

    public Product removeProduct(Long productId) throws ProductException {
        Product productRemoved = productRepository.findById(productId).orElseThrow(() -> new ProductException("Product not found"));
        productRepository.delete(productRemoved);
        return productRemoved;
    }


    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }
}
