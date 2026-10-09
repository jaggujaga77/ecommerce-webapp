package com.abhicodes.datapoem.produser.repository;

import com.abhicodes.datapoem.produser.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CartRepository extends JpaRepository<Cart, Long> {

}
