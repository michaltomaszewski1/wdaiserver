package com.example.demo.service;

import com.example.demo.dao.Person;
import com.example.demo.repository.PersonsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PersonService {
    public List<Person> getPersons();
    public Person getPerson(String surname);
    public Person create(Person person);
    public Person getPerson(Long id);
}
