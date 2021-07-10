package com.app.productshopbackend.repo;


import com.app.productshopbackend.model.OrdersOverView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersOverViewRepo extends JpaRepository<OrdersOverView, Long> {
}
