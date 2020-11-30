package com.bme.lab.ptl.integration;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.bme.lab.ptl.domain.Route;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author akberovr (Rovshan Akbarov)
 * created on 2020-10-20
 */


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class IntegrationTest {


    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getRoute_returnsAllRoutes()  throws Exception{
        //arrange

        //act
        ResponseEntity<Route> response =
                restTemplate.getForEntity("/routes",Route.class);
        //assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getOrigin()).isEqualTo("Baku");
        assertThat(response.getBody().getDestination()).isEqualTo("Budapest");

    }

}
