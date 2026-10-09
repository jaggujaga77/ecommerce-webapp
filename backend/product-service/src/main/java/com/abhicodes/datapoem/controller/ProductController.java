package com.abhicodes.datapoem.controller;

import com.abhicodes.datapoem.entity.Product;
import com.abhicodes.datapoem.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class ProductController {
    @Autowired
    private ProductService prodService;

    List<Product> temp;
    @GetMapping("/products")
    public List<Product> displayAll(){
        temp = prodService.displayAllProducts();
        System.out.println(temp.get(1).toString());
        return prodService.displayAllProducts();
    }

    @PostMapping("/{id}")
    public Product listProduct(@PathVariable long id){
        System.out.println("Id: "+id);
        return prodService.listProduct(id);
    }
}
