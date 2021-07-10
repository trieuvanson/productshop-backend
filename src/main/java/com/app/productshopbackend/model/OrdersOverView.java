package com.app.productshopbackend.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "oders_overview")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrdersOverView {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cus_id")
    private int cus_id;

    @Column(name = "total")
    private Double total;

    @Column(name = "discount")
    private Double discount;

    @Column(name = "sub_total")
    private Double sub_total;

    @Column(name = "status")
    private byte status;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name = "create_at")
    private Date create_at;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name = "update_at")
    private Date update_at;






}
