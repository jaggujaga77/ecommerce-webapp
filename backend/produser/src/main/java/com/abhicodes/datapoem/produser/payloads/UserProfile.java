package com.abhicodes.datapoem.produser.payloads;

import com.abhicodes.datapoem.produser.entity.Cart;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserProfile {
    private Long id;
    private String username;
    private String name;
    private Long wallet_balance;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getWallet_balance() {
        return wallet_balance;
    }

    public void setWallet_balance(Long wallet_balance) {
        this.wallet_balance = wallet_balance;
    }
}
