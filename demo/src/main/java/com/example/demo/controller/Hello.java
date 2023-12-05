package com.example.demo.controller;

import com.example.demo.dao.Person;
import com.example.demo.model.Greeting;
import com.example.demo.repository.PersonsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Hello {
    @GetMapping("/hello")
    public ResponseEntity<Greeting> getName (
            @RequestParam(value = "name", defaultValue = "") String name) {
            return new ResponseEntity<Greeting>(new Greeting(name), HttpStatus.OK);
    }
}
