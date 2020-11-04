package com.jan.ad_mediation_challenge;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jan.ad_mediation_challenge.domain.PerformanceData;
import com.jan.ad_mediation_challenge.service.UpdateService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@SpringBootApplication
public class AdMediationChallengeApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdMediationChallengeApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(UpdateService updateService){
        return args -> {
            // read JSON and load json
            ObjectMapper mapper = new ObjectMapper();
            TypeReference<List<PerformanceData>> typeReference = new TypeReference<List<PerformanceData>>(){};
            InputStream inputStream = TypeReference.class.getResourceAsStream("/json/batchResults2.json");
            try {
                List<PerformanceData> updates = mapper.readValue(inputStream, typeReference);
                updateService.save(updates);
                System.out.println("Data Saved!");
            } catch (IOException e){
                System.out.println("Unable to save users: " + e.getMessage());
            }
        };
    }

}
