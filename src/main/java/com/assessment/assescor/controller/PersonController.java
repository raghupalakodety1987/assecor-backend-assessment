package com.assessment.assescor.controller;

import com.assessment.assescor.entity.Person;
import com.assessment.assescor.service.CSVService;
import com.assessment.assescor.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/person")
public class PersonController {

    private final CSVService fileService;


    private final PersonService personService;

    @Autowired
    public PersonController(CSVService fileService, PersonService personService) {
        this.fileService = fileService;
        this.personService = personService;
    }

    @GetMapping
    public List<Person> getPersons(){
        return personService.getPersons();
    }

    @GetMapping("/persons")
    public ResponseEntity<Object> getAllUsers() {
        try {
            List<Person> persons = fileService.getAllPersons();

            if (persons.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<Object>(persons, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/persons/{Id}")
    public ResponseEntity<Object> getUserById(@PathVariable("Id") Long Id) {
        try {
            Optional<Person> person = fileService.getPersonById(Id);

            if (person == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<Object>(person, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/persons/color/{Color}")
    public ResponseEntity<Object> getUsersByColor(@PathVariable("Color") String Color) {
        try {
            List<Person> persons = fileService.getPersonsByColor(Color);

            if (persons == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<Object>(persons, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
