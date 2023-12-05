package com.example.demo.service;

import com.example.demo.dao.Person;
import com.example.demo.repository.PersonsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonsRepository personsRepository;
    @Override
    public List<Person> getPersons() {
        return (List<Person>) personsRepository.findAll();
    }

    @Override
    public Person getPerson(String surname) {
        for (Person person:
             personsRepository.findAll()) {
            if (Objects.equals(person.getSurname(), surname))
                return person;
        }
        return null;
    }

    @Override
    public Person create(Person person) {
        return personsRepository.save(person);
    }

    @Override
    public Person getPerson(Long id) {
        for (Person person:
             personsRepository.findAll()) {
            if (Objects.equals(person.getId(), id))
                return person;
        }
        return null;
    }
}
