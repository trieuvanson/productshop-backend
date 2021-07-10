package com.app.productshopbackend.repo;


import com.app.productshopbackend.model.ProductImages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductImagesRepo extends JpaRepository<ProductImages, Long> {

    @Query("select pi.image_url from ProductImages pi inner join Products p on pi.product_id = p.id where p.id = :pId")
    List<String> getProductImagesName(Long pId);

}
