package com.ecommerce.ecommercemvcapplication.repository;

import com.ecommerce.ecommercemvcapplication.model.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductModel, Long> {
    Optional<ProductModel> findByTitle(String url);
    @Query("select p from ProductModel p where p.title like concat('%', :query, '%')")
    List<ProductModel> searchProducts(String query);
}
