package com.example.wiesenbluetewebshop.service;

import com.example.wiesenbluetewebshop.exception.ProductException;
import com.example.wiesenbluetewebshop.model.Category;
import com.example.wiesenbluetewebshop.model.Product;
import com.example.wiesenbluetewebshop.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;
    public List<Category> getAllCategory(){return categoryRepository.findAll();}
    public void addCategory(Category category){categoryRepository.save(category);}
    public void removeCategoryById(Long id){categoryRepository.deleteById(id);}
    public Optional<Category> getCategoryById(Long id){return categoryRepository.findById(id);}

    public List<Product> viewProductByCategory(Long categoryId) throws ProductException {
        Optional<Category> category = categoryRepository.findById(categoryId);
        if (category.isPresent()) {
            return category.get().getProductList();
        } else {
            throw new ProductException("Product not found with category id - " + categoryId);
        }
    }
}