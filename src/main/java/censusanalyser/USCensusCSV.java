package censusanalyser;

import com.opencsv.bean.CsvBindByName;
//State Id,State,Population,Housing units,Total area,Water area,Land area,Population Density,Housing Density
public class USCensusCSV {

    public USCensusCSV() {
    }

    @CsvBindByName(column = "StateId", required = true)
    public String stateId;

    @CsvBindByName(column = "State", required = true)
    public String state;

    @CsvBindByName(column = "Population", required = true)
    public int population;

    @CsvBindByName(column = "HousingUnits", required = true)
    public int housingUnits;

    @CsvBindByName(column = "TotalArea", required = true)
    public float totalArea;

    @CsvBindByName(column = "WaterArea", required = true)
    public float waterArea;

    @CsvBindByName(column = "LandArea", required = true)
    public float landArea;

    @CsvBindByName(column = "PopulationDensity", required = true)
    public float populationDensity;

    @CsvBindByName(column = "HousingDensity", required = true)
    public float housingDensity;

    public USCensusCSV(String state, String stateCode, int population, float populationDensity, float totalArea) {

    }


    @Override
    public String toString() {
        return "USCensusCSV{" +
                "stateId=" + stateId +
                ", state='" + state + '\'' +
                ", population=" + population +
                ", housingUnits=" + housingUnits +
                ", totalArea='" + totalArea + '\'' +
                ", waterArea=" + waterArea +
                ", landArea=" + landArea +
                ", populationDensity=" + populationDensity +
                ", housingDensity='" + housingDensity + '\'' +
                '}';
    }
}
