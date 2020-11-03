package com.jan.ad_mediation_challenge.service;

import com.jan.ad_mediation_challenge.domain.PerformanceData;
import com.jan.ad_mediation_challenge.repository.SqlDAO;
import com.jan.ad_mediation_challenge.repository.UpdateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UpdateService  {

    private SqlDAO sqlDAO;

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


}
