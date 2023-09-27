package com.example.wiesenbluetewebshop.repository;

import com.example.wiesenbluetewebshop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    //List<Product> findAllByCategory_Id(Long id);
    //List<Product> findAllByCategoryId(Long categoryId);

}
