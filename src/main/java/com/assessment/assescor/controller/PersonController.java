package com.assessment.assescor.controller;

import com.assessment.assescor.ResponseMessage;
import com.assessment.assescor.entity.Person;
import com.assessment.assescor.service.CSVService;
import com.assessment.assescor.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/person")
public class PersonController {

    private static final String SAVE_SUCCESSFUL = "Successfully saved the person details" ;
    private static final String COULD_NOT_SAVE = "Could not upload person details";
    private final CSVService fileService;


    private final PersonService personService;

    @Autowired
    public PersonController(CSVService fileService, PersonService personService) {
        this.fileService = fileService;
        this.personService = personService;
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<Object>  getPersons(){
        return  new ResponseEntity<Object>(personService.getPersons(), HttpStatus.OK);
    }

    @GetMapping(value = "/persons", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Person>> getAllPersons() {
        try {
            List<Person> persons = fileService.getAllPersons();

            if (persons.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<List<Person>>(persons, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/persons/{Id}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<Person> getPersonById(@PathVariable("Id") Long Id) {
        try {
            Person person = fileService.getById(Id);

            if (person == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<Person>(person, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/persons/color/{color}")
    public ResponseEntity<List<Person>> getUsersByColor(@PathVariable("color") String color) {
        try {
            List<Person> persons = fileService.getPersonsByColor(color);

            if (persons == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<List<Person>>(persons, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/person/save")
    public ResponseEntity<Person> savePerson(@RequestBody Person person) {
        String message = "";
        try{
            Person savedPerson = personService.savePerson(person);
            message = SAVE_SUCCESSFUL;
            return new ResponseEntity<Person>(person, HttpStatus.OK);
        }
        catch (Exception e){
            message =  COULD_NOT_SAVE + "!" + e.getMessage();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
