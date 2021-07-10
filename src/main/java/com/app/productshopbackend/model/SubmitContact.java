package com.app.productshopbackend.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "submit_contact")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubmitContact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "content")
    private String content;

    @Column(name = "status")
    private Boolean status;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name = "create_at")
    private Date create_at;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name = "update_at")
    private Date update_at;






}
