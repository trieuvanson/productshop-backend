package com.app.productshopbackend.controller;


import com.app.productshopbackend.exception.ResourceNotFoundException;
import com.app.productshopbackend.model.OrderDetail;
import com.app.productshopbackend.repo.OrderDetailRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/order-detail")
public class OrderDetailController {
    @Autowired
    OrderDetailRepo orderDetailRepo;

    @GetMapping("/")
    private List<OrderDetail> getOrderDetails()  {
        return orderDetailRepo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDetail> getOrderDetail(@PathVariable Long id)    {
        OrderDetail OrderDetail = orderDetailRepo.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("OrderDetail not exist " + id));
        return ResponseEntity.ok(OrderDetail);
    }

    @PostMapping("/")
    public OrderDetail PostOrderDetail(@RequestBody OrderDetail OrderDetail) {
        OrderDetail.setCreate_at(new Date());
        OrderDetail.setUpdate_at(new Date());
        return orderDetailRepo.save(OrderDetail);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDetail> PutOrderDetail(@RequestBody OrderDetail OrderDetail, @PathVariable Long id)  {
        OrderDetail newOrderDetail = orderDetailRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("OrderDetail not exist " + id));
        newOrderDetail.setOrder_id(OrderDetail.getOrder_id());
        newOrderDetail.setTotal(OrderDetail.getTotal());
        newOrderDetail.setProduct_id(OrderDetail.getProduct_id());
        newOrderDetail.setQuantity(OrderDetail.getQuantity());
        newOrderDetail.setUpdate_at(new Date());
        OrderDetail updateOrderDetail = orderDetailRepo.save(newOrderDetail);
        return ResponseEntity.ok(updateOrderDetail);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteOrderDetail(@PathVariable Long id)    {
        OrderDetail OrderDetail = orderDetailRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("OrderDetail not exist " + id));
        orderDetailRepo.delete(OrderDetail);
        Map<String, Boolean> reponse = new HashMap<>();
        reponse.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(reponse);
    }
}
