package com.jan.ad_mediation_challenge.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jan.ad_mediation_challenge.domain.PerformanceData;
import com.jan.ad_mediation_challenge.repository.SqlDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class UpdateService  {

    private SqlDAO sqlDAO;
    Logger logger = LoggerFactory.getLogger(UpdateService.class);

    @Autowired
    public UpdateService(SqlDAO sqlDAO) {
        this.sqlDAO = sqlDAO;
    }

    public Iterable<PerformanceData> list(){
        return sqlDAO.findAll();
    }

    // Save methods - for one and for list of perfData

    public PerformanceData save(PerformanceData perfData){
        return sqlDAO.save(perfData);
    }



    public void save(List<PerformanceData> perfDataAll) {
        sqlDAO.saveAll(perfDataAll);
    }

    public int updatePerfData(String updatedData){

        logger.info("AAAA: " + updatedData);

        // read JSON and load json
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<PerformanceData>> typeReference = new TypeReference<List<PerformanceData>>(){};

        try {
            List<PerformanceData> updates = mapper.readValue(updatedData, typeReference);
            logger.info("Perf score: " + String.valueOf(updates.get(2).getPerformanceScore()));
            save(updates);
            System.out.println("Data updated!");
        } catch (IOException e){
            System.out.println("Unable to save users: " + e.getMessage());
        }

        return 1;
    }

    public int deleteAndInsertData(String newData){

        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<PerformanceData>> typeReference = new TypeReference<List<PerformanceData>>(){};

        try {
            List<PerformanceData> updates = mapper.readValue(newData, typeReference);
            logger.info("Perf score: " + String.valueOf(updates.get(2).getPerformanceScore()));
            sqlDAO.deleteAllInBatch();
            save(updates);
            System.out.println("Data Saved!");
        } catch (IOException e){
            System.out.println("Unable to save data: " + e.getMessage());
        }

        return 1;
    }

    public int deleteData(){
        sqlDAO.deleteAllInBatch();
        return 1;
    }


}
