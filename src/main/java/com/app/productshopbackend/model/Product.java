package com.app.productshopbackend.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
@Entity
@Table(name = "products")
@Data
public class Product implements Serializable  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String slug;
    private String short_desc;
    private Double regular_price;
    private Double sale_price;
    private String description;
    private Boolean featured;
    private Boolean best_seller;
    private String thumbnail;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name = "create_at")
    private Date create_at;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name = "update_at")
    private Date update_at;
    @ManyToOne
    @JoinColumn(name = "categoryid")
    Category category;






}
