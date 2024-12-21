package com.faos.fuelagency.controllers;

import com.faos.fuelagency.entities.Cylinder;
import com.faos.fuelagency.services.CylinderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class CylinderController {

    private final CylinderService cylinderService;

    public CylinderController(CylinderService cylinderService) {
        this.cylinderService = cylinderService;
    }

    @GetMapping("/add-cylinder")
    public String getAddCylinderPage(Model model){
        model.addAttribute("title", "Add Cylinder");
        model.addAttribute("desc", "Fill in the details below to add a new cylinder to the inventory.");
        model.addAttribute("cylinder", new Cylinder());
        return "add-cylinder";
    }

    @GetMapping("/update-cylinder")
    public String getUpdateCylinderPage(@RequestParam("cylinderId") int id, Model model){


        Optional<Cylinder> oldCylinder = cylinderService.getCylinderById(id);

        if (oldCylinder.isEmpty()){
            model.addAttribute("errorMessage", "Invalid Cylinder Id : " + id);
            return "error-page";
        }

        model.addAttribute("title", "Update Cylinder");
        model.addAttribute("desc", "Fill in the details below to update an existing cylinder in the inventory.");
        model.addAttribute("successMessage", null);
        model.addAttribute("errorMessage", null);
        model.addAttribute("cylinder", oldCylinder.get());
        return "add-cylinder";
    }

    @PostMapping("/save-cylinder")
    public String saveCylinder(@ModelAttribute("cylinder") Cylinder cylinder, RedirectAttributes redirectAttributes) {

        System.out.println("Cylinder: " + cylinder);

        Cylinder c = cylinderService.saveCylinder(cylinder);

        if (c.getId() != null) {
            redirectAttributes.addFlashAttribute("successMessage", "Cylinder added successfully!");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to add the cylinder. Please try again.");
        }

        return "redirect:/add-cylinder";
    }

    @GetMapping("/view-cylinders")
    public String getViewCylinder(Model model){
        List<Cylinder> cylinders = cylinderService.getAllCylinders();
        model.addAttribute("cylinders", cylinders);
        return "view-cylinders";
    }

    @GetMapping("/delete-cylinder")
    public String deleteCylinder(@RequestParam("cylinderId") int id){
        cylinderService.deleteCylinderById(id);
        return "redirect:view-cylinders";
    }

    @GetMapping("/filterCylinders")
    @ResponseBody
    public List<Cylinder> filterCylinders(
            @RequestParam(defaultValue = "all") String type,
            @RequestParam(defaultValue = "all") String status,
            @RequestParam(defaultValue = "") String query) {

        return cylinderService.filterCylinders(type, status, query);
    }
}
