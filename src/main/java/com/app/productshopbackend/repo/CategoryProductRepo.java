package com.app.productshopbackend.repo;


import com.app.productshopbackend.model.Category;
import com.app.productshopbackend.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryProductRepo extends JpaRepository<Category, Integer> {
    @Query("select cp.id, cp.cate_name, p.name from Product p inner join Category cp on p.category.id = cp.id order by cp.id asc")

    List<String[]> getCateProduct();

    @Query("select p from Product p inner join Category cp on p.category.id = cp.id where cp.id = :id")

    List<Product> getCateProduct(@Param("id") Integer id);
}
