package com.jan.ad_mediation_challenge.controller;

import com.jan.ad_mediation_challenge.domain.PerformanceData;
import com.jan.ad_mediation_challenge.service.PerformanceDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import view.PerformanceDTO;

import java.util.List;

@RestController
@RequestMapping("/performanceData")
public class PerformanceDataController {

    private PerformanceDataService performanceDataService;

    @Autowired
    public PerformanceDataController(PerformanceDataService performanceDataService){
        this.performanceDataService = performanceDataService;
    }

    @GetMapping("")
    public PerformanceDTO getPerformanceData(@RequestParam(value = "platform") String platform,
                                             @RequestParam(value = "osVersion") String osVersion,
                                             @RequestParam(value = "appName") String appName,
                                             @RequestParam(value = "appVersion") String appVersion,
                                             @RequestParam(value = "countryCode") String countryCode){

        return performanceDataService.getPerformanceData(platform, osVersion, appName, appVersion, countryCode);
        //return "Data sent = " + platform + osVersion + appName + appVersion + countryCode;
    }



}
