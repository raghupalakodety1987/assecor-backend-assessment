/*
package com.example.restservice;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.Request;
import org.junit.runner.Runner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CheckHTTPResponse {
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    public void shouldPassIfUploaded(){
        Assertions.assertTrue(this.restTemplate.postForObject("http://localhost:"+ port + "/api/csv/upload", ));
    }
}
*/
