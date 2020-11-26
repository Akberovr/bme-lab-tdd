package com.bme.lab.ptl.rest;

import static org.mockito.BDDMockito.given;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.bme.lab.ptl.domain.Route;
import com.bme.lab.ptl.service.RouteService;

/**
 * @author akberovr (Rovshan Akbarov)
 * created on 2020-10-21
 */

@ExtendWith(SpringExtension.class)
@WebMvcTest(RouteController.class)
public class RouteControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RouteService routeService;

    @Test
    public void getRoute_ShouldReturnRoute() throws Exception {

        given(routeService.getRoutes()).willReturn(new Route("Baku","Budapest"));

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/routes")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String expected = "{origin:Baku,destination:Budapest}";
        JSONAssert.assertEquals(expected, result.getResponse()
                .getContentAsString(), false);

    }
}
