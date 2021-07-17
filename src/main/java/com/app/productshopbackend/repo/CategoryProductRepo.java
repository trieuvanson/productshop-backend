package com.app.productshopbackend.repo;


import com.app.productshopbackend.model.CategoryProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface CategoryProductRepo extends JpaRepository<CategoryProduct, Long> {
    @Query("select cp.id, cp.cate_name, p.name from Products p inner join CategoryProduct cp on p.cate_id = cp.id order by cp.id asc")

        List<String[]> getCateProduct();
}
