package censusanalyser;

public class CensusDAO {
    public int densityPerSqKm;
    public String state;
    public int areaInSqKm;
    public int population;
    public int housingUnits;
    public float totalArea;
    public float waterArea;
    public float landArea;
    public float populationDensity;
    public float housingDensity;
    public String stateCode;


    public CensusDAO(IndiaCensusCSV indiaCensusCSV) {
        state = indiaCensusCSV.state;
        areaInSqKm = indiaCensusCSV.areaInSqKm;
        densityPerSqKm = indiaCensusCSV.densityPerSqKm;
        population = indiaCensusCSV.population;
    }


    public CensusDAO(USCensusCSV usCensusCSV) {
        stateCode = usCensusCSV.stateId;
        state = usCensusCSV.state;
        population = usCensusCSV.population;
        housingUnits = usCensusCSV.housingUnits;
        totalArea=usCensusCSV.totalArea;
        waterArea=usCensusCSV.waterArea;
        landArea=usCensusCSV.landArea;
        populationDensity=usCensusCSV.populationDensity;
        housingDensity=usCensusCSV.housingDensity;

    }

    public String getStateCode(){
        return stateCode;
    }


    public Object getCensusDTO(CensusAnalyser.Country country){
        if(country.equals(CensusAnalyser.Country.US))
            return new USCensusCSV(state,stateCode,population,populationDensity,totalArea);
        return new IndiaCensusCSV(state,population,areaInSqKm,densityPerSqKm);
    }
}
