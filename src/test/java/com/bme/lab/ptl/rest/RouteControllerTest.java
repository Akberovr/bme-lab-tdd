package com.bme.lab.ptl.rest;

import com.bme.lab.ptl.domain.Route;
import com.bme.lab.ptl.service.RouteService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author akberovr (Rovshan Akbarov)
 * created on 2020-10-21
 */

@RunWith(SpringRunner.class)
@WebMvcTest(RouteController.class)
public class RouteControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RouteService routeService;

    @Test
    public void getRoute_ShouldReturnRoute() throws Exception {

        given(routeService.getRoutes()).willReturn(new Route("Baku","Budapest"));

        mockMvc.perform(MockMvcRequestBuilders.get("/routes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("origin").value("Baku"))
                .andExpect(jsonPath("destination").value("Budapest"));
    }
}
