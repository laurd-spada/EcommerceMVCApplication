package com.ecommerce.ecommercemvcapplication.controller;

import com.ecommerce.ecommercemvcapplication.model.CartItem;
import com.ecommerce.ecommercemvcapplication.services.CartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CartController {
    private CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }
    @GetMapping("/cartsection")
    public String viewCart(Model model){
        List<CartItem> items = cartService.findAllItem();
        model.addAttribute("items", items);
        return "client_cartsection";
    }
    @GetMapping("productId/{productId}/add")
    public String addToCart(@PathVariable("productId") Long productId) {
        cartService.addItemToCart(productId);
        return "redirect:/customer";
    }
    @GetMapping("/products/{productId}/remove")
    public String deleteCartItem(@PathVariable("productId") Long productId){
        cartService.delete(productId);
        return "redirect:/cartsection";
    }
//    @GetMapping("{productId}/viewcart")
//    public Cart viewCart() {
//        return cartService.getCart();
//    }
}
