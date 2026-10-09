package com.abhicodes.datapoem.produser.repository;

import com.abhicodes.datapoem.produser.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Cart, Long> {

}
