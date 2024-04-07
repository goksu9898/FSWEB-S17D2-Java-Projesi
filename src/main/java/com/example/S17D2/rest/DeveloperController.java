package com.example.S17D2.rest;

import jakarta.annotation.PostConstruct;
import com.example.S17D2.model.Developer;
import com.example.S17D2.model.Experience;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.S17D2.tax.DeveloperTax;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class DeveloperController {
    Map<Integer, Developer> developers;
    DeveloperTax developerTax;

    @Autowired
    public DeveloperController(DeveloperTax developerTax) {
        this.developerTax = developerTax;
    }

    @PostConstruct
    public void postConstruct() {
        developers = new HashMap<>();
    }

    @GetMapping("/workintech/developers")
    public List<Developer> getAllDevelopers() {
        return developers.values().stream().toList();
    }

    @GetMapping("/workintech/developers/{id}")
    public Developer getDevelopersValues(@PathVariable int id) {
        return developers.get(id);
    }

    @PostMapping("/workintech/developers")
    public Developer saveDevelopers(@RequestBody Developer developer) {
        if (developer.getExperience() == Experience.JUNIOR) {
            Developer developerJr = new Developer(developer.getId(), developer.getName(), developer.getSalary(), developer.getExperience());
            developers.put(developer.getId(), developerJr);
            developerJr.setSalary(developerJr.getSalary() - developerTax.getSimpleTaxRate());
        } else if (developer.getExperience() == Experience.MID) {
            Developer developerMid = new Developer(developer.getId(), developer.getName(), developer.getSalary(), developer.getExperience());
            developers.put(developer.getId(), developerMid);
            developerMid.setSalary(developerMid.getSalary() - developerTax.getMiddleTaxRate());
        } else {
            Developer developerSr = new Developer(developer.getId(), developer.getName(), developer.getSalary(), developer.getExperience());
            developers.put(developer.getId(), developerSr);
            developerSr.setSalary(developerSr.getSalary() - developerTax.getUpperTaxRate());
        }
        return developer;
    }

    @PutMapping("/workintech/developers/{id}")
    public Developer updateDevelopers(@RequestBody Developer developer, @PathVariable int id) {
        developers.put(id, developer);
        return developers.get(id);
    }

    @DeleteMapping("/workintech/developers/{id}")
    public Developer deleteDevelopers(@PathVariable int id) {
        return developers.remove(id);
    }
}
