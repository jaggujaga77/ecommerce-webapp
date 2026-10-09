package com.abhicodes.datapoem.produser.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    //plain pojo for holding product object
    private long productId;
    private String productName;
    private int productPrice;
    private String productDesc;
    private String productMfgDate;
    private int productStock;
    private String productBrand;
}
