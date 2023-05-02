package com.ecommerce.ecommercemvcapplication.services;


import com.ecommerce.ecommercemvcapplication.model.ProductModel;

import java.util.List;

public interface ProductService {
    List<ProductModel> findAllProduct();
    ProductModel saveProduct(ProductModel productModel);
    ProductModel findProductById(long productId);
    void updateProduct(ProductModel productModel);
    void delete(Long productId);
    List<ProductModel> searchProducts(String query);

}
