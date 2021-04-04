package com.bme.lab.ptl.rest;

import com.bme.lab.ptl.domain.Company;
import com.bme.lab.ptl.domain.Route;
import com.bme.lab.ptl.service.route.RouteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.CoreMatchers.is;

/**
 * @author akberovr (Rovshan Akbarov)
 * created on 2020-10-21
 */

@ExtendWith(SpringExtension.class)
@WebMvcTest(RouteController.class)
class RouteControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RouteService routeService;

    private List<Route> routeList;

    private ObjectMapper objectMapper;

    private Company company;

    private Route route;

    @BeforeEach
    void setUp() {
        company = new Company(1L, "KLM", "klm@gmail.com");

        routeList = new ArrayList<>();
        route = new Route(1L, "Baku", "Budapest", company);
        routeList.add(new Route(2L, "London", "Dublin", company));
        routeList.add(new Route(3L, "Warsaw", "Istanbul", company));
        routeList.add(route);

        objectMapper = new ObjectMapper();
    }

    @Test
    void findAll_ShouldReturnAllRoutes() throws Exception {
        given(routeService.findAll())
                .willReturn(routeList);
        mockMvc.perform(get("/company/routes")
                .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(routeList.size())));
    }

    @Test
    void getRouteByIdAndCompanyId_shouldReturnRoute() throws Exception {
        final Long companyId = company.getId();

        given(routeService.findByIdAndCompanyId(any(Long.class), any(Long.class)))
                .willReturn(Optional.of(route));

        mockMvc.perform(get("/company/{companyId}/routes/{id}", companyId, route.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.origin", is(route.getOrigin())))
                .andExpect(jsonPath("$.destination", is(route.getDestination())));
    }

    @Test
    void shouldReturn404_WhenFindByIdAndCompanyId() throws Exception {
        final Long companyId = company.getId();
        given(routeService.findByIdAndCompanyId(any(Long.class), any(Long.class)))
                .willReturn(Optional.empty());
        mockMvc.perform(get("/company/{companyId}/routes/{id}", companyId, route.getId()))
                .andExpect(status().isNotFound());
    }

    @Test
    void create_shouldCreateRoute() throws Exception {
        given(routeService.createRoute(any(Long.class), any(Route.class)))
                .willAnswer((invocation -> invocation.getArgument(1)));

        mockMvc.perform(post("/company/{companyId}/routes", company.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(route)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.origin", is(route.getOrigin())))
                .andExpect(jsonPath("$.destination", is(route.getDestination())));
    }

    @Test
    void deleteRoute_shouldDeleteRoute() throws Exception {
        given(routeService.findByIdAndCompanyId(any(Long.class), any(Long.class)))
                .willReturn(Optional.of(route));
        doNothing().when(routeService).delete(any(Long.class), any(Long.class));

        mockMvc.perform(delete("/company/{companyId}/routes/{routeId}", any(Long.class), any(Long.class)))
                .andExpect(status().isOk());
    }

}
