package com.ecommerce.ecommercemvcapplication.repository;

import com.ecommerce.ecommercemvcapplication.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<CartItem, Long> {
}
