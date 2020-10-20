package com.bme.lab.ptl.integration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author akberovr (Rovshan Akbarov)
 * created on 2020-10-20
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class IntegrationTest {


    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getRoute_returnsAllRoutes()  throws Exception{
        //arrange

        //act
        ResponseEntity<Route> response;
        //assert

    }

}
