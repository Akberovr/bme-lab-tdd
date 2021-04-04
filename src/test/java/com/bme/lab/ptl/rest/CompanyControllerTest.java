package com.bme.lab.ptl.rest;

import com.bme.lab.ptl.domain.Company;
import com.bme.lab.ptl.service.company.CompanyService;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.CoreMatchers.is;

/**
 * @author akberovr (Rovshan Akbarov)
 * created on 3/20/21
 */

@ExtendWith(SpringExtension.class)
@WebMvcTest(CompanyController.class)
class CompanyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CompanyService companyService;

    private ObjectMapper objectMapper;

    private List<Company> companyList;

    private Company company;

    @BeforeEach
    void setUp() {
        companyList = new ArrayList<>();
        companyList.add(new Company(1L, "XYZ", "xyz@gmail.org"));
        companyList.add(new Company(2L, "ABC", "abc@gmail.org"));
        companyList.add(new Company(3L, "KLM", "klm@gmail.org"));
        objectMapper = new ObjectMapper();
        company = new Company(1L, "KLM", "klm@gmail.com");
    }

    @Test
    void findAll_ShouldReturnAll() throws Exception {

        given(companyService.findAll())
                .willReturn(companyList);
        mockMvc.perform(get("/companies")
                .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(companyList.size())));
    }

    @Test
    void findById_ShouldFetchCompanyById() throws Exception {
        Long companyId = company.getId();
        given(companyService.findById(companyId))
                .willReturn(Optional.of(company));
        mockMvc.perform(get("/companies/{id}", companyId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(company.getName())))
                .andExpect(jsonPath("$.email", is(company.getEmail())));
    }

    @Test
    void shouldReturn404WhenCompanyById() throws Exception {
        final Long companyId = company.getId();
        given(companyService.findById(companyId))
                .willReturn(Optional.empty());
        mockMvc.perform(get("/companies/{id}", companyId))
                .andExpect(status().isNotFound());
    }

    @Test
    void create_ShouldCreateNewCompany() throws Exception {
        given(companyService.createCompany(any(Company.class)))
                .willAnswer((invocation -> invocation.getArgument(0)));

        mockMvc.perform(post("/companies")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(company)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.email", is(company.getEmail())))
                .andExpect(jsonPath("$.name", is(company.getName())));
    }

    @Test
    void deleteCompany_shouldDeleteCompany() throws Exception {
        Long companyId = company.getId();

        given(companyService.findById(companyId)).willReturn(Optional.of(company));
        doNothing().when(companyService).delete(companyId);

        mockMvc.perform(delete("/companies/{companyId}", companyId))
                .andExpect(status().isOk());
    }


}
