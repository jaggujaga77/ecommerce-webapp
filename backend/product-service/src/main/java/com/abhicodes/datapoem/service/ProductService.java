package com.abhicodes.datapoem.service;

import com.abhicodes.datapoem.entity.Product;
import com.abhicodes.datapoem.exception.ProductNotFoundException;
import com.abhicodes.datapoem.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository prodRepo;

    public List<Product> displayAllProducts() {
        return prodRepo.findAll();
    }

    public Product listProduct(long id) {
        return prodRepo.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }
}
