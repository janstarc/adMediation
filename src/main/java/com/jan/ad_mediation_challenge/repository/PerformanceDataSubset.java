package com.jan.ad_mediation_challenge.repository;

import com.jan.ad_mediation_challenge.domain.AdProvider;
import com.jan.ad_mediation_challenge.domain.AdType;
import com.jan.ad_mediation_challenge.domain.Country;
import com.jan.ad_mediation_challenge.domain.PerformanceData;
import org.springframework.data.repository.Repository;

public interface PerformanceDataSubset {


    // String
    int getPerformanceScore();
    AdProviderSubset getAdProvider();
    AdTypeSubset getAdType();
    CountrySubset getCountry();



    interface AdProviderSubset {
        int getIdAdProvider();
    }

    interface AdTypeSubset {
        int getIdAdType();
    }

    interface CountrySubset {
        String getCountryCode();
    }


}
