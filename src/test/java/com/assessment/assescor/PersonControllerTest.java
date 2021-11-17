package com.assessment.assescor;

import com.assessment.assescor.config.CSVConfig;
import com.assessment.assescor.config.PersonCOnfig;
import com.assessment.assescor.controller.PersonController;
import com.assessment.assescor.entity.Person;
import com.assessment.assescor.repository.PersonRepository;
import com.assessment.assescor.service.CSVService;
import com.assessment.assescor.service.PersonService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(classes = PersonController.class)
@AutoConfigureMockMvc
@ContextConfiguration(classes = {PersonCOnfig.class, CSVConfig.class},  loader = AnnotationConfigContextLoader.class)
@ActiveProfiles("test")
@RunWith(MockitoJUnitRunner.class)
public class PersonControllerTest {
    private static final ObjectMapper om = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private PersonRepository personRepository;

    @Autowired
    @Qualifier("resourceStream")
    private CSVService csvService;

    @Mock
    private PersonController personController;

    @Mock
    private PersonService personService;

    public PersonControllerTest(){
        csvService = new CSVService();
        personService = new PersonService(personRepository);
        personController = new PersonController(csvService, personService);
    }

    @Before
    public void init(){
        //ong id, String name, String lastName, String zipCode, String city, String color
        mockMvc = MockMvcBuilders.standaloneSetup(personController).build();
        Person person1 = new Person(1, "Raghu", "Palakodety", "45127", "Essen", "grün");
        when(personRepository.findById(1L)).thenReturn(Optional.of((person1)));
        //when(personController.getPersons()).thenReturn((List<Person>) Stream.of(person1, person2));
    }

    @Test
    public void findPersonById() throws Exception {
/*        mockMvc.perform(get("/persons/1")).
                andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON)).
                andExpect(status().isOk()).
                andExpect(MockMvcResultMatchers.jsonPath("$id", is(1))).
                andExpect((ResultMatcher) jsonPath("$name", is("Müller"))).
                andExpect((ResultMatcher) jsonPath("$lastName", is("Hans"))).
                andExpect((ResultMatcher) jsonPath("$zipCode", is("67742"))).
                andExpect((ResultMatcher) jsonPath("$city", is("Lauterecken"))).
                andExpect((ResultMatcher) jsonPath("$color", is("blau")));*/
        Person person2 = new Person(1L, "Raghu", "Palakodety", "45127", "Essen", "grün");

        when(personService.getPerson(any(Long.class))).thenReturn(Optional.of(person2));
        //doReturn(person2).when(personService).getPerson(any(Long.class));
        mockMvc.perform(MockMvcRequestBuilders.
                get("/api/v1/person/persons/1").
                accept(MediaType.APPLICATION_JSON)).
                andDo(print()).
                andExpect(status().isOk()).
                andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L)).
                andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Müller"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("Hans")).
                andExpect(MockMvcResultMatchers.jsonPath("$.zipCode").value("67742"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.city").value("Lauterecken"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.color").value("blau"));

        verify(personRepository, times(1)).findById(1L);
    }
}
