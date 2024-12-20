package com.faos.fuelagency.controllers;

import com.faos.fuelagency.entities.Cylinder;
import com.faos.fuelagency.services.CylinderService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CylinderController.class)
class CylinderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CylinderService cylinderService;

    @Test
    void testGetAddCylinderPage() throws Exception {
        mockMvc.perform(get("/add-cylinder"))
                .andExpect(status().isOk())
                .andExpect(view().name("add-cylinder"))
                .andExpect(model().attributeExists("title", "desc", "cylinder"))
                .andExpect(model().attribute("title", "Add Cylinder"));
    }

    @Test
    void testGetUpdateCylinderPage_ValidId() throws Exception {
        Cylinder testCylinder = new Cylinder();
        testCylinder.setId(1);
        testCylinder.setStatus("available");
        testCylinder.setType("full");
        testCylinder.setWeight(15.5f);
        testCylinder.setLastRefillDate(LocalDate.of(2024, 12, 1));

        Mockito.when(cylinderService.getCylinderById(eq(1))).thenReturn(Optional.of(testCylinder));

        mockMvc.perform(get("/update-cylinder").param("cylinderId", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("add-cylinder"))
                .andExpect(model().attributeExists("title", "desc", "cylinder"))
                .andExpect(model().attribute("title", "Update Cylinder"))
                .andExpect(model().attribute("cylinder", testCylinder));
    }

    @Test
    void testGetUpdateCylinderPage_InvalidId() throws Exception {
        Mockito.when(cylinderService.getCylinderById(eq(999))).thenReturn(Optional.empty());

        mockMvc.perform(get("/update-cylinder").param("cylinderId", "999"))
                .andExpect(status().isOk())
                .andExpect(view().name("error-page"))
                .andExpect(model().attributeExists("errorMessage"))
                .andExpect(model().attribute("errorMessage", "Invalid Cylinder Id : 999"));
    }

    @Test
    void testSaveCylinder_Success() throws Exception {
        Cylinder testCylinder = new Cylinder();
        testCylinder.setId(1);
        testCylinder.setStatus("available");
        testCylinder.setType("full");
        testCylinder.setWeight(15.5f);
        testCylinder.setLastRefillDate(LocalDate.of(2024, 12, 1));

        Mockito.when(cylinderService.saveCylinder(any(Cylinder.class))).thenReturn(testCylinder);

        mockMvc.perform(post("/save-cylinder")
                        .flashAttr("cylinder", testCylinder))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/add-cylinder"))
                .andExpect(flash().attribute("successMessage", "Cylinder added successfully!"));
    }

    @Test
    void testSaveCylinder_Failure() throws Exception {
        Cylinder testCylinder = new Cylinder();
        testCylinder.setStatus("available");
        testCylinder.setType("full");
        testCylinder.setWeight(15.5f);
        testCylinder.setLastRefillDate(LocalDate.of(2024, 12, 1));

        Mockito.when(cylinderService.saveCylinder(any(Cylinder.class))).thenReturn(new Cylinder()); // No ID, simulating failure

        mockMvc.perform(post("/save-cylinder")
                        .flashAttr("cylinder", testCylinder))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/add-cylinder"))
                .andExpect(flash().attribute("errorMessage", "Failed to add the cylinder. Please try again."));
    }

    @Test
    void testGetViewCylinders() throws Exception {
        Cylinder cylinder1 = new Cylinder();
        cylinder1.setId(1);
        cylinder1.setStatus("available");
        cylinder1.setType("full");
        cylinder1.setWeight(15.5f);
        cylinder1.setLastRefillDate(LocalDate.of(2024, 12, 1));

        Cylinder cylinder2 = new Cylinder();
        cylinder2.setId(2);
        cylinder2.setStatus("delivered");
        cylinder2.setType("empty");
        cylinder2.setWeight(12.0f);
        cylinder2.setLastRefillDate(LocalDate.of(2024, 11, 30));

        Mockito.when(cylinderService.getAllCylinders()).thenReturn(Arrays.asList(cylinder1, cylinder2));

        mockMvc.perform(get("/view-cylinders"))
                .andExpect(status().isOk())
                .andExpect(view().name("view-cylinders"))
                .andExpect(model().attributeExists("cylinders"))
                .andExpect(model().attribute("cylinders", Arrays.asList(cylinder1, cylinder2)));
    }

    @Test
    void testDeleteCylinder() throws Exception {
        Mockito.doNothing().when(cylinderService).deleteCylinderById(eq(1));

        mockMvc.perform(get("/delete-cylinder").param("cylinderId", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("view-cylinders"));
    }
}

