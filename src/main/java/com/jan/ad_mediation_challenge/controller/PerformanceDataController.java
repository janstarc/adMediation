package com.jan.ad_mediation_challenge.controller;

import com.jan.ad_mediation_challenge.domain.PerformanceData;
import com.jan.ad_mediation_challenge.repository.PerformanceDataSubset;
import com.jan.ad_mediation_challenge.service.PerformanceDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/performanceData")
public class PerformanceDataController {

    private PerformanceDataService performanceDataService;

    @Autowired
    public PerformanceDataController(PerformanceDataService performanceDataService){
        this.performanceDataService = performanceDataService;
    }

    // TODO To handlamo!

    @GetMapping("")
    public List<PerformanceData> getPerformanceData(@RequestParam(value = "platform") String platform,
                                                    @RequestParam(value = "osVersion") String osVersion,
                                                    @RequestParam(value = "appName") String appName,
                                                    @RequestParam(value = "appVersion") String appVersion,
                                                    @RequestParam(value = "countryCode") String countryCode){

        return performanceDataService.getPerformanceData(platform, osVersion, appName, appVersion, countryCode);
        //return "Data sent = " + platform + osVersion + appName + appVersion + countryCode;
    }

    @GetMapping("/subset")
    public List<PerformanceDataSubset> getPerformanceDataSubset(@RequestParam(value = "platform") String platform,
                                                                     @RequestParam(value = "osVersion") String osVersion,
                                                                     @RequestParam(value = "appName") String appName,
                                                                     @RequestParam(value = "appVersion") String appVersion,
                                                                     @RequestParam(value = "countryCode") String countryCode){

        return performanceDataService.getPerformanceDataSubset(platform, osVersion, appName, appVersion, countryCode);
        //return "Data sent = " + platform + osVersion + appName + appVersion + countryCode;
    }






}
