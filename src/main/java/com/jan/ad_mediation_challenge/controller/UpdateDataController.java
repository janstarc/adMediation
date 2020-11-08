package com.jan.ad_mediation_challenge.controller;

import com.jan.ad_mediation_challenge.domain.PerformanceData;
import com.jan.ad_mediation_challenge.service.UpdateService;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/data")
public class UpdateDataController {

    private UpdateService updateService;

    @Autowired
    public UpdateDataController(UpdateService updateService){
        this.updateService = updateService;
    }

    @GetMapping("")
    public Iterable<PerformanceData> list(){
        return updateService.list();
    }

    @PutMapping("")
    public void deleteAndInsertPerfData(@Valid @NotNull @RequestBody String newData){
        updateService.deleteAndInsertData(newData);
    }

    @DeleteMapping("")
    public void deleteData(){
        updateService.deleteData();
    }
}
