package com.thecat.services;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import com.thecat.model.Product; 

@ApplicationScoped
public class ProductService {
    
    public List<Product> listProduct() {
        return Product.findAll().list();
    }

    public Product findProductById(Integer id) {
        return Product.findById(id);
    }

    public List<Product> findProductBySize(String size) {
        return Product.list("size", size.toUpperCase());
    }

    public long getProductCount() {
        return Product.count();
    }
}