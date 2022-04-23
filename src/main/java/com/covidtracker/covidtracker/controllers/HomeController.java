package com.covidtracker.covidtracker.controllers;

import com.covidtracker.covidtracker.models.locationStats;
import com.covidtracker.covidtracker.services.CovidDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;

@Controller
public class HomeController {
    @Autowired
    CovidDataService covidDataService;
    @GetMapping("/")
    public String Home(Model model)
    {
        List<locationStats> allStats=covidDataService.getAllStats();
        int totalReportedCases=allStats.stream().mapToInt(stat-> stat.getLatestTotalCases()).sum();
        int totalNewCases=allStats.stream().mapToInt(stat-> stat.getDiffPrev()).sum();
        model.addAttribute("locationStats",covidDataService.getAllStats());
        model.addAttribute("totalReportedCases",totalReportedCases);
        model.addAttribute("totalNewCases",totalNewCases);

        return "home";
    }
}
