package com.assessment.assescor;

import com.assessment.assescor.config.PersonCOnfig;
import com.assessment.assescor.controller.PersonController;
import com.assessment.assescor.entity.Person;
import com.assessment.assescor.service.CSVService;
import com.assessment.assescor.service.PersonService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(PersonController.class)
@ContextConfiguration(classes = {PersonCOnfig.class, DataSourceTransactionManagerAutoConfiguration.class})
public class ApiTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CSVService csvService;

    @MockBean
    private PersonService personService;

    /*@InjectMocks
    private PersonController personController;*/

    /*@Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(personController).build();
    }*/



    @Test
    public void testPersonController() throws Exception{

        //String jsonResult = "{\"name\": \"Müller\", \"lastName\": \"Hans\", \"zipCode\": \"67742\", \"city\": \"Lauterecken\", \"color\": \"blau\", \"id\": 1}";
        Person person2 = new Person(1L, "Raghu", "Palakodety", "45127", "Essen", "grün");

        when(personService.getPerson(any(Long.class))).thenReturn(Optional.of(person2));

        mockMvc.perform(MockMvcRequestBuilders.
                get("/api/v1/person/persons/1").
                accept(MediaType.APPLICATION_JSON)).
                andDo(print()).
                andExpect(status().isOk()).
                andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1")).
                andExpect(MockMvcResultMatchers.jsonPath("$name").value("Müller"))
                .andExpect(MockMvcResultMatchers.jsonPath("$lastName").value("Hans")).
                 andExpect(MockMvcResultMatchers.jsonPath("$zipCode").value("67742"))
                .andExpect(MockMvcResultMatchers.jsonPath("$city").value("Lauterecken"))
                .andExpect(MockMvcResultMatchers.jsonPath("$color").value("blau"));

    }

}
