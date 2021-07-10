package com.app.productshopbackend.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "product")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "slug")
    private String slug;

    @Column(name = "short_desc")
    private String short_desc;

    @Column(name = "regular_price")
    private Double regular_price;

    @Column(name = "sale_price")
    private Double sale_price;

    @Column(name = "description")
    private String description;

    @Column(name = "featured")
    private Boolean featured;

    @Column(name = "best_seller")
    private Boolean best_seller;

    @Column(name = "cate_id")
    private int cate_id;

    @Column(name = "thumbnail")
    private String thumbnail;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name = "create_at")
    private Date create_at;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name = "update_at")
    private Date update_at;






}
