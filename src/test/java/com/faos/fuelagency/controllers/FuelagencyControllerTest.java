package com.faos.fuelagency.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(FuelagencyController.class)
public class FuelagencyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetHomePage() throws Exception {
        // Test for home page "/" route
        mockMvc.perform(get("/"))
                .andExpect(status().isOk()) // Check HTTP status is 200 OK
                .andExpect(view().name("index")); // Verify the returned view name
    }

    @Test
    void testHandleError() throws Exception {
        // Test for "/error" route
        mockMvc.perform(get("/error"))
                .andExpect(status().isOk()) // Check HTTP status is 200 OK
                .andExpect(view().name("error-page")) // Verify the returned view name
                .andExpect(model().attributeExists("errorMessage")) // Check model attribute exists
                .andExpect(model().attribute("errorMessage", "We encountered an unexpected error. Please contact support if the issue persists."));
    }
}
