package com.bme.lab.ptl.rest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author akberovr (Rovshan Akbarov)
 * created on 2020-10-21
 */

@RunWith(SpringRunner.class)
@WebMvcTest(RouteController.class)
public class RouteControllerTest {

    @Test
    public void getRoute_ShouldReturnRoute() throws Exception {

    }
}
