package com.sda.eshop.controller;

import com.sda.eshop.model.Product;
import com.sda.eshop.service.ProductService;

import java.util.List;

public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    public List<Product> findAll() {
        return productService.findAll();
    }

    public Product findById(Long id) {
        return productService.findById(id);
    }
}
