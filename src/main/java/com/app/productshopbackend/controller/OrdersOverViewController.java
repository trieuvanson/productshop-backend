package com.app.productshopbackend.controller;



import com.app.productshopbackend.exception.ResourceNotFoundException;
import com.app.productshopbackend.model.OrdersOverView;
import com.app.productshopbackend.repo.OrdersOverViewRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/orderview")
public class OrdersOverViewController {
    @Autowired
    OrdersOverViewRepo ordersOverViewRepo;

    @GetMapping("/")
    private List<OrdersOverView> getOrdersOverViews()  {
        return ordersOverViewRepo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdersOverView> getOrdersOverView(@PathVariable Long id)    {
        OrdersOverView OrdersOverView = ordersOverViewRepo.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("OrdersOverView not exist " + id));
        return ResponseEntity.ok(OrdersOverView);
    }

    @PostMapping("/")
    public OrdersOverView PostOrdersOverView(@RequestBody OrdersOverView OrdersOverView) {
        OrdersOverView.setCreate_at(new Date());
        OrdersOverView.setUpdate_at(new Date());
        return ordersOverViewRepo.save(OrdersOverView);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrdersOverView> PutOrdersOverView(@RequestBody OrdersOverView OrdersOverView, @PathVariable Long id)  {
        OrdersOverView newOrdersOverView = ordersOverViewRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("OrdersOverView not exist " + id));
        newOrdersOverView.setCus_id(OrdersOverView.getCus_id());
        newOrdersOverView.setTotal(OrdersOverView.getTotal());
        newOrdersOverView.setDiscount(OrdersOverView.getDiscount());
        newOrdersOverView.setStatus(OrdersOverView.getStatus());
        newOrdersOverView.setSub_total(OrdersOverView.getSub_total());
        newOrdersOverView.setUpdate_at(new Date());
        OrdersOverView updateOrdersOverView = ordersOverViewRepo.save(newOrdersOverView);
        return ResponseEntity.ok(updateOrdersOverView);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteOrdersOverView(@PathVariable Long id)    {
        OrdersOverView OrdersOverView = ordersOverViewRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("OrdersOverView not exist " + id));
        ordersOverViewRepo.delete(OrdersOverView);
        Map<String, Boolean> reponse = new HashMap<>();
        reponse.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(reponse);
    }
}
