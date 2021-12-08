package com.assessment.assescor.config;

import com.assessment.assescor.entity.Person;
import com.assessment.assescor.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@TestConfiguration
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.assessment.assescor.repository", "com.assessment.assescor.controller"})
public class PersonCOnfig {


/*
    private PersonRepository personRepository;

    @Autowired
    PersonCOnfig(PersonRepository personRepository){
        this.personRepository = personRepository;
    }
*/


  /*  @Bean
    CommandLineRunner commandLineRunner(PersonRepository personRepository){
        return args -> {
            new Person(1, "Raghu", "Palakodety", "45127", "Essen", "grün");
        };
    }*/

  /*  @Bean
    public PersonRepository resourceStream() {
        return personRepository;
    }*/

}
