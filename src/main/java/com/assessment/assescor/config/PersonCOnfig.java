package com.assessment.assescor.config;

import com.assessment.assescor.entity.Person;
import com.assessment.assescor.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;

@Configuration
@EntityScan(basePackages = "com.assessment.assescor")
public class PersonCOnfig {


    @Bean
    CommandLineRunner commandLineRunner(PersonRepository personRepository){
        return args -> {
            new Person(1, "Raghu", "Palakodety", "45127", "Essen", "grün");
        };
    }


}
