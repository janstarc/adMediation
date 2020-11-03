package com.jan.ad_mediation_challenge.controller;

import com.jan.ad_mediation_challenge.domain.PerformanceData;
import com.jan.ad_mediation_challenge.service.UpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/update")
public class UpdateDataController {

    private UpdateService updateService;

    @Autowired
    public UpdateDataController(UpdateService updateService){
        this.updateService = updateService;
    }

    @GetMapping("/list")
    public Iterable<PerformanceData> list(){
        return updateService.list();
    }
}
