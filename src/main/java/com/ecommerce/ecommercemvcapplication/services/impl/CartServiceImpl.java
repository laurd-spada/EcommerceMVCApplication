package com.ecommerce.ecommercemvcapplication.services.impl;

import com.ecommerce.ecommercemvcapplication.model.CartItem;
import com.ecommerce.ecommercemvcapplication.model.ProductModel;
import com.ecommerce.ecommercemvcapplication.repository.CartRepository;
import com.ecommerce.ecommercemvcapplication.services.CartService;
import com.ecommerce.ecommercemvcapplication.services.ProductService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {
    private List<CartItem> cartItems = new ArrayList<>();
    private ProductService productService;
    private CartRepository cartRepository;

    public CartServiceImpl(ProductService productService, CartRepository cartRepository) {
        this.productService = productService;
        this.cartRepository = cartRepository;
    }

    @Override
    public void addItemToCart(Long productId) {
        System.out.println("start");
        Integer num =  1;
        ProductModel product = productService.findProductById(productId);
        if (product != null) {
            CartItem item = findCartItemByProductId(productId);
            if (item != null) {
                item.setQuantity(item.getQuantity() + 1);
                cartRepository.save(item);
            } else {
                item = new CartItem(product.getId(), product.getTitle(), product.getPrice(), num);
                cartItems.add(item);
                cartRepository.save(item);
            }
        }
        System.out.println("finish");
    }

    @Override
    public List<CartItem> findAllItem() {
        List<CartItem> items = cartRepository.findAll();
        return items.stream().map((item) -> mapToItems(item)).collect(Collectors.toList());
    }

    @Override
    public void updateItemInCart(Long productId, Integer quantity) {
        CartItem item = findCartItemByProductId(productId);
        if (item != null) {
            item.setQuantity(quantity);
        }
    }
    @Override
    public void removeItemFromCart(Long productId) {
        CartItem item = findCartItemByProductId(productId);
        if (item != null) {
            cartItems.remove(item);
        }
    }
    @Override
    public void delete(Long productId) {
        cartRepository.deleteById(productId);
    }
//    @Override
//    public Cart getCart() {
//        Cart cart = new Cart();
//        cart.setItems(cartItems);
//        return cart;
//    }

    private CartItem findCartItemByProductId(Long productId) {
        for (CartItem item : cartItems) {
            if (item.getProductId().equals(productId)) {
                return item;
            }
        }
        return null;
    }
    private CartItem mapToItems(CartItem cartItem){
        CartItem cartItem1 = CartItem.builder()
                .productId(cartItem.getProductId())
                .name(cartItem.getName())
                .price(cartItem.getPrice())
                .quantity(cartItem.getQuantity())
                .build();
        return cartItem1;
    }
}
