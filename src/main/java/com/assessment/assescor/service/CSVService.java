package com.assessment.assescor.service;

import com.assessment.assescor.entity.Person;
import com.assessment.assescor.repository.PersonRepository;
import com.assessment.assescor.helper.CSVHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Component
public class CSVService {

    @Autowired
    private PersonRepository repository;

    public void save(MultipartFile file){
        try{
            List<Person> users = CSVHelper.csvToPersons(file.getInputStream());
            repository.saveAll(users);
        } catch (IOException e) {
            throw new RuntimeException("Fail to store the CSV data " + e.getMessage());
        }
    }

    public ByteArrayInputStream load(){
        List<Person> users = repository.findAll();
        ByteArrayInputStream in = (ByteArrayInputStream) CSVHelper.csvToPersons((InputStream) users);
        return in;
    }

    public List<Person> getAllPersons() {
        return (List<Person>) repository.findAll();
    }

  /*  public Person getPersonById(Long Id){
        return repository.findById(Id);
    }*/

    public List<Person> getPersonsByColor(String Color){
        return repository.findByColor(Color);
    }

    public Person getById(Long Id){return repository.getById(Id);}
}
