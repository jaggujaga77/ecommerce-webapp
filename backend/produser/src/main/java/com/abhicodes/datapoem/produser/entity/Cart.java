package com.abhicodes.datapoem.produser.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="cart")
public class Cart {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column
    private long cost;

    @Column
    private long wallet_balance;

    @Column
    private long productId;

    @Column
    private String productName;

    @Column
    private int prodQty;

    public Cart(long cost, long productId, String productName, int prodQty, long wallet_balance){
        this.cost = cost;
        this.wallet_balance = wallet_balance;
        this.productId = productId;
        this.productName = productName;
        this.prodQty = prodQty;
    }
}
