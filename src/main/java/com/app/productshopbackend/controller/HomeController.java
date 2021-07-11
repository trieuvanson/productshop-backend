package com.app.productshopbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static jdk.nashorn.internal.objects.NativeArray.forEach;

@CrossOrigin
@RestController
@RequestMapping("/")
public class HomeController {
    @Autowired
    HttpServletRequest request;

    public static List<String> list = Arrays.asList("categories", "category-product", "coupon", "menus", "news", "note"
            , "options", "orderview", "product-images", "products", "slides", "submit-contact", "users");

    @GetMapping("/")
    public String Home() {
//        model.addAttribute("links", list);
//        model.addAttribute("url", request.getRequestURL().toString());
        String url = request.getRequestURL().toString();
        String body = "<tr>\n" +
                "            <td><a href=\""+url+"swagger-ui/index.html\">swagger-ui-api</a></td>\n" +
            "         </tr>";

        return body;
    }
}
