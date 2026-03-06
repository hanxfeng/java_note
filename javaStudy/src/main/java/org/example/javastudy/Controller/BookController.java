package org.example.javastudy.Controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
public class BookController {

    @Value("${name}")
    private String name;


    @GetMapping
    public String selectById () {
        System.out.println(name);
        return "success";
    }
}
