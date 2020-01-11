package censusanalyser;

//StateId,State,Population,HousingUnits,TotalArea,WaterArea,LandArea,PopulationDensity,HousingDensity
public class USCensusDAO{
    public String state;
    public String stateId;
    public int population;
    public int housingUnits;
    public float totalArea;
    public float waterArea;
    public float landArea;
    public float populationDensity;
    public float housingDensity;

    public USCensusDAO(USCensusCSV usCensusCSV) {
        stateId = usCensusCSV.stateId;
        state = usCensusCSV.state;
        population = usCensusCSV.population;
        housingUnits = usCensusCSV.housingUnits;
        totalArea=usCensusCSV.totalArea;
        waterArea=usCensusCSV.waterArea;
        landArea=usCensusCSV.landArea;
        populationDensity=usCensusCSV.populationDensity;
        housingDensity=usCensusCSV.housingDensity;

    }
}
