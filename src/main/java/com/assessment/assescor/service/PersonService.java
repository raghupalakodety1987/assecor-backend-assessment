package com.assessment.assescor.service;

import com.assessment.assescor.entity.Person;
import com.assessment.assescor.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    private final PersonRepository personRepository;


    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> getPersons() {
        return personRepository.findAll();
    }
    public Optional<Person> getPerson(Long id){ return personRepository.findById(id);}

    public Person savePerson(Person person){
        return personRepository.save(person);
    }

    public List<Person> getPersonsByColor(String color){
       return personRepository.findByColor(color);
    }

}
