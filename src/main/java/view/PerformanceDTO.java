package view;

import java.util.Objects;

public class PerformanceDTO {

    private final int performanceScore;
    private final int id_adProvider;
    private final int id_adType;
    private final String countryCode;

    public PerformanceDTO(int performanceScore, int id_adProvider, int id_adType, String countryCode) {
        this.performanceScore = performanceScore;
        this.id_adProvider = id_adProvider;
        this.id_adType = id_adType;
        this.countryCode = countryCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PerformanceDTO performanceDTO = (PerformanceDTO) o;

        return Objects.equals(countryCode, performanceDTO.countryCode)
                && performanceScore == performanceDTO.performanceScore
                && id_adProvider == performanceDTO.id_adProvider
                && id_adType == performanceDTO.id_adType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(performanceScore, id_adProvider, id_adType, countryCode);
    }

    public int getPerformanceScore() {
        return performanceScore;
    }

    public int getId_adProvider() {
        return id_adProvider;
    }

    public int getId_adType() {
        return id_adType;
    }

    public String getCountryCode() {
        return countryCode;
    }


     /*
    public PerformanceDTO(int performanceScore) {
        this.performanceScore = performanceScore;
    }
    */

    /*
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PerformanceDTO performanceDTO = (PerformanceDTO) o;

        return performanceScore == performanceDTO.performanceScore;
    }
    */
    /*
    @Override
    public int hashCode() {
        return Objects.hash(performanceScore);
    }

     */
}

