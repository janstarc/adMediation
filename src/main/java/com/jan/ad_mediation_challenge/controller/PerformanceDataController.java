package com.jan.ad_mediation_challenge.controller;

import com.jan.ad_mediation_challenge.repository.ResultList;
import com.jan.ad_mediation_challenge.service.PerformanceDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/performanceData")
public class PerformanceDataController {

    private PerformanceDataService performanceDataService;

    @Autowired
    public PerformanceDataController(PerformanceDataService performanceDataService){
        this.performanceDataService = performanceDataService;
    }

    // Request from smartphone app...
    @GetMapping("")
    public ResultList getPerformanceDataSubsetRows(@RequestParam(value = "platform") String platform,
                                                   @RequestParam(value = "osVersion") String osVersion,
                                                   @RequestParam(value = "appName") String appName,
                                                   @RequestParam(value = "appVersion") String appVersion,
                                                   @RequestParam(value = "countryCode") String countryCode){

        return performanceDataService.getTestSubsetRows(platform, osVersion, appName, appVersion, countryCode);
    }






}
