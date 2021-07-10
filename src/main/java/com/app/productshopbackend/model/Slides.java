package com.app.productshopbackend.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "slides")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Slides {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "image")
    private String image;

    @Column(name = "link")
    private String link;

    @Column(name = "postion")
    private int postion;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name = "create_at")
    private Date create_at;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name = "update_at")
    private Date update_at;



}
