package com.ecommerce.ecommercemvcapplication.services;


import com.ecommerce.ecommercemvcapplication.model.CartItem;

import java.util.List;

public interface CartService {
    void addItemToCart(Long productId);
    List<CartItem> findAllItem();

    void updateItemInCart(Long productId, Integer quantity);

    void removeItemFromCart(Long productId);
    void delete(Long productId);

//    Cart getCart();
}
