package com.example.demo.controller;

import com.example.demo.dao.Person;
import com.example.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonController {
    @Autowired
    private PersonService personService;

    @GetMapping("/person")
    public ResponseEntity<List<Person>> getPersonList () {
        return new ResponseEntity<>(personService.getPersons(), HttpStatus.OK);
    }
    @GetMapping("/person/{id}")
    public ResponseEntity<Person> getPerson (@PathVariable Long id) {
        return new ResponseEntity<>(personService.getPerson(id), HttpStatus.OK);
    }
    @PostMapping("/person/create")
    public ResponseEntity<String> createPerson (@RequestBody Person person) {
        personService.create(person);
        return new ResponseEntity<>("Person created successfully", HttpStatus.OK);
    }
}
