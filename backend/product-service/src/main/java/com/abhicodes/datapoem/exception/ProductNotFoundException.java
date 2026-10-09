package com.abhicodes.datapoem.exception;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(long id){
        super("Product doesn't exist " + id);
    }
}
