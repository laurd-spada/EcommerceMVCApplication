package com.ecommerce.ecommercemvcapplication.services.impl;

import com.ecommerce.ecommercemvcapplication.model.ProductModel;
import com.ecommerce.ecommercemvcapplication.repository.ProductRepository;
import com.ecommerce.ecommercemvcapplication.services.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductModel> findAllProduct() {
        List<ProductModel> products = productRepository.findAll();
        return products.stream().map((product) -> mapToProduct(product)).collect(Collectors.toList());
    }

    @Override
    public ProductModel saveProduct(ProductModel productModel) {
        ProductModel product = mapToProduct(productModel);
        return productRepository.save(product);
    }

    @Override
    public ProductModel findProductById(long productId) {
        ProductModel product = productRepository.findById(productId).get();
        return mapToProduct(product);
    }

    @Override
    public void updateProduct(ProductModel productModel) {
        ProductModel product = mapToProduct(productModel);
        productRepository.save(product);
    }

    @Override
    public void delete(Long productId) {
        productRepository.deleteById(productId);
    }

    @Override
    public List<ProductModel> searchProducts(String query) {
        List<ProductModel> products = productRepository.searchProducts(query);
        return products.stream().map(product -> mapToProduct(product)).collect(Collectors.toList());
    }
    private ProductModel mapToProduct(ProductModel productModel){
        ProductModel product = ProductModel.builder()
                .id(productModel.getId())
                .title(productModel.getTitle())
                .photoUrl(productModel.getPhotoUrl())
                .content(productModel.getContent())
                .price(productModel.getPrice())
                .category(productModel.getCategory())
                .createdOn(productModel.getCreatedOn())
                .updatedOn(productModel.getUpdatedOn())
                .build();
        return product;
    }
}
