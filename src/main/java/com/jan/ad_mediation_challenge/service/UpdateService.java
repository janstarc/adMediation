package com.jan.ad_mediation_challenge.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jan.ad_mediation_challenge.domain.PerformanceData;
import com.jan.ad_mediation_challenge.repository.SqlDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
        try{
            return sqlDAO.findAll();
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Save methods - for one and for list of perfData
    public PerformanceData save(PerformanceData perfData){
        return sqlDAO.save(perfData);
    }



    public void save(List<PerformanceData> perfDataAll) {
        try{
            sqlDAO.saveAll(perfDataAll);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public void deleteAndInsertData(String newData){

        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<PerformanceData>> typeReference = new TypeReference<List<PerformanceData>>(){};

        try {
            List<PerformanceData> updates = mapper.readValue(newData, typeReference);
            sqlDAO.deleteAllInBatch();
            save(updates);
            System.out.println("Data Saved!");
        } catch (IOException e){
            System.out.println("Unable to save data: " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    public void deleteData(){
        try{
            sqlDAO.deleteAllInBatch();
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
