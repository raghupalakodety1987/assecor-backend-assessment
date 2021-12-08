package com.assessment.assescor;

import com.assessment.assescor.config.PersonCOnfig;
import com.assessment.assescor.controller.PersonController;
import com.assessment.assescor.entity.Person;
import com.assessment.assescor.repository.PersonRepository;
import com.assessment.assescor.service.PersonService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


//@SpringBootTest(classes = PersonController.class)
//@ContextConfiguration(classes = {PersonCOnfig.class, CSVConfig.class},  loader = AnnotationConfigContextLoader.class)
@ActiveProfiles("test")

@RunWith(SpringRunner.class)
//@WebMvcTest(value = PersonController.class, secure = false)
@SpringBootTest(classes = PersonCOnfig.class)
@AutoConfigureMockMvc
public class PersonControllerTest {
    private static final ObjectMapper om = new ObjectMapper();

    private static Logger logger = LoggerFactory.getLogger(PersonControllerTest.class);

    private static final String LASTNAME = "Palakodety";
    private static final String NAME = "Matthias";


    @Autowired
    private MockMvc mockMvc;


    private ObjectMapper objectMapper;

    @MockBean
    private PersonService personService;

    @Autowired
    private PersonRepository personRepository;

    @MockBean
    private PersonController personController;


    private Person person1;

    private Person person2;

    private Person person3;

    private Person person4;

    private Person person5;

    private String personJson;

    @Autowired
    private WebApplicationContext webApplicationContext;
    private org.springframework.http.ResponseEntity ResponseEntity;

    @Before
    public void setUp() throws JsonProcessingException {
        person1 = new Person(1L, "Raghunandan", "Palakodety", "45127", "Essen", "blau");
        person2 = new Person(2L, "Matthias", "Müller", "45127", "Essen", "grün");
        person3 = new Person(3L, "Jürgen", "Gall", "45127", "Essen", "blau");
        person3 = new Person(4L, "Heinrich", "Alexander", "45147", "Essen", "grün");
        person4 = new Person(10L, "Monke", "Hans", "45147", "Bonn", "violett");
        person5 = new Person(11L, "Ethan", "Hawke", "45127", "Essen", "Lila");
        objectMapper = new ObjectMapper();
        mockMvc = MockMvcBuilders.standaloneSetup(personController).build();


    }


    @Test
    public void testGetPersonById() throws Exception {


        ResponseEntity<Person> responseEntity = new ResponseEntity<>(person2, HttpStatus.OK);

        when(personController.getPersonById(any(Long.class))).thenReturn(responseEntity);


        MvcResult result = mockMvc.perform(get("/api/v1/person/persons/{Id}", 1)).andExpect(status().isOk()).andReturn();
        Assert.assertEquals(NAME, person2.getName());
    }


    @Test
    public void testGetPersons() throws Exception {

       List<Person> personsList = Arrays.asList(person1, person2, person3, person4);

        ResponseEntity<List<Person>> responseEntity = new ResponseEntity<>(personsList, HttpStatus.OK);

        when(personController.getAllPersons()).thenReturn(responseEntity);


        MvcResult result = (MvcResult) this.mockMvc.perform(MockMvcRequestBuilders
                .get("/api/v1/person/persons")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
        .andReturn();

        List<Person> persons = objectMapper.readValue(result.getResponse().getContentAsString(StandardCharsets.UTF_8),
                objectMapper.getTypeFactory().constructCollectionType(List.class, Person.class));

        Person personResult = persons.stream().filter(p -> p.getLastName().equals(LASTNAME)).findFirst().orElse(null);
        Assert.assertEquals(persons.size(), 4);
        Assert.assertNotNull(personResult);
    }


    @Test
    public void testGetPersonsByColor() throws Exception {


        List<Person> personsList = Arrays.asList(person1, person3);

        ResponseEntity<List<Person>> responseEntity = new ResponseEntity<>(personsList, HttpStatus.OK);

        when(personController.getUsersByColor(any(String.class))).thenReturn(responseEntity);

        MvcResult result = (MvcResult) this.mockMvc.perform(get("/api/v1/person/persons/color/{Color}", "blau").characterEncoding(StandardCharsets.UTF_8.name())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print()).andReturn();

        List<Person> persons = objectMapper.readValue(result.getResponse().getContentAsString(StandardCharsets.UTF_8),
                objectMapper.getTypeFactory().constructCollectionType(List.class, Person.class));
        Assert.assertEquals(persons.size(), 2);
    }


    @Test
    public void whenSaved_thenShouldbeSuccessful() throws Exception {


        Person personToBeSaved = person4;

        ResponseEntity<Person> responseEntity = new ResponseEntity<>(personToBeSaved, HttpStatus.OK);

        when(personController.savePerson(any(Person.class))).thenReturn(responseEntity);


        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/person/persons/save").contentType(MediaType.APPLICATION_JSON)
                .content(String.valueOf(responseEntity)))
                .andExpect(status().isOk())
                .andDo(print());
    }
}
