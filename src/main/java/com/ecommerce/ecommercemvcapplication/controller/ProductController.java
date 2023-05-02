package com.ecommerce.ecommercemvcapplication.controller;

import com.ecommerce.ecommercemvcapplication.model.ProductModel;
import com.ecommerce.ecommercemvcapplication.services.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping("/admin")
    public String getAdminPage(Model model, HttpServletRequest httpServletRequest){
        HttpSession session = httpServletRequest.getSession();
        if (session.getAttribute("email")==null){
            return "login_page";
        }
        List<ProductModel> products = productService.findAllProduct();
        model.addAttribute("products", products);
        return "admin_home";
    }
    @GetMapping("/admin/viewandedit")
    public String adminViewAndEdit(Model model, HttpServletRequest httpServletRequest){
        HttpSession session = httpServletRequest.getSession();
        if (session.getAttribute("email")==null){
            return "login_page";
        }
        List<ProductModel> products = productService.findAllProduct();
        model.addAttribute("products", products);
        return "admin_editanddelete";
    }
    @GetMapping("/products/{productId}")
    public String productDetail(@PathVariable("productId") long productId, Model model){
        ProductModel product = productService.findProductById(productId);
        model.addAttribute("product", product);
        return "admin_productdetail";
    }
    @GetMapping("/admin/addproduct")
    public String adminAddProduct(HttpServletRequest httpServletRequest){
        HttpSession session = httpServletRequest.getSession();
        if (session.getAttribute("email")==null){
            return "login_page";
        }
        return "admin_addproduct";
    }
    @PostMapping("/admin/addproduct")
    public String saveProduct(@Valid
                              @ModelAttribute("/product") ProductModel productModel,
                              BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("product", productModel);
            return "admin_addproduct";
        }
        productService.saveProduct(productModel);
        return "redirect:/admin";
    }
    @GetMapping("/products/{productId}/edit")
    public String editProductForm(@PathVariable("productId") long productId, Model model){
        ProductModel product = productService.findProductById(productId);
        model.addAttribute("product", product);
        return "admin_viewandupdate";
    }
    @PostMapping("/products/{productId}/edit")
    public String updateProduct(@PathVariable("productId") Long productId,
                                @Valid @ModelAttribute("product") ProductModel product,
                                BindingResult result){
        if(result.hasErrors()){
            return "admin_viewandupdate";
        }
        product.setId(productId);
        productService.updateProduct(product);
        return "redirect:/admin/viewandedit";
    }
    @GetMapping("/products/{productId}/delete")
    public String deleteProduct(@PathVariable("productId") Long productId){
        productService.delete(productId);
        return "redirect:/admin/viewandedit";
    }
    @GetMapping("/products/search")
    public String searchProduct(@RequestParam(value = "query") String query, Model model){
        List<ProductModel> products = productService.searchProducts(query);
        model.addAttribute("products", products);
        return "admin_searchresult";
    }
}
