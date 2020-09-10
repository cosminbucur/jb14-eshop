package com.sda.eshop.service;

import com.sda.eshop.dao.ProductDao;
import com.sda.eshop.model.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collections;
import java.util.List;

import static com.sda.eshop.utils.Constants.*;

public class ProductService {

    private static final Logger log = LogManager.getLogger(ProductService.class);

    private ProductDao productDao;

    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
    }

    public List<Product> findAll() {
        log.info(PRODUCT_FIND_ALL);

        List<Product> products = productDao.findAll();

        if (products.isEmpty()) {
            log.info("Failed to find products.");
            return Collections.emptyList();
        }

        return products;
    }

    public Product findById(Long id) {
        log.info(PRODUCT_FIND_BY_ID, id);

        Product product = productDao.findById(id);

        if (product == null) {
            log.info(PRODUCT_FAIL_FIND_BY_ID, id);
            return null;
        }

        return product;
    }
}
