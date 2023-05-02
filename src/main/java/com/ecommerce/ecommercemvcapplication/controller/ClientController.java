package com.ecommerce.ecommercemvcapplication.controller;

import com.ecommerce.ecommercemvcapplication.model.ProductModel;
import com.ecommerce.ecommercemvcapplication.services.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ClientController {
    private ProductService productService;

    public ClientController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping("/customer")
    public String getCustomerPage(Model model, HttpServletRequest httpServletRequest){
        HttpSession session = httpServletRequest.getSession();
        if (session.getAttribute("email")==null){
            return "login_page";
        }
        List<ProductModel> products = productService.findAllProduct();
        model.addAttribute("products", products);
        return "client_home";
    }
    @GetMapping("customer/products/search")
    public String searchProduct(@RequestParam(value = "query") String query, Model model){
        List<ProductModel> products = productService.searchProducts(query);
        model.addAttribute("products", products);
        return "client_searchresult";
    }
    @GetMapping("customer/products/{productId}")
    public String productDetail(@PathVariable("productId") long productId, Model model){
        ProductModel product = productService.findProductById(productId);
        model.addAttribute("product", product);
        return "client_productdetail";
    }
}
