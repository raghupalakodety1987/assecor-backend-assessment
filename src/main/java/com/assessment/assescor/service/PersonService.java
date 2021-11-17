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
    public void savePersons(List<Person> personList){
        personRepository.saveAll(personList);
    }

}
