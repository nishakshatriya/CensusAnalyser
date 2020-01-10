package censusanalyser;

public class IndiaCensusDAO {
    public int densityPerSq;
    public String state;
    public int areaInSqKm;
    public int population;

    public IndiaCensusDAO(IndiaCensusCSV indiaCensusCSV){
        state = indiaCensusCSV.state;
        areaInSqKm = indiaCensusCSV.areaInSqKm;
        population = indiaCensusCSV.population;
        densityPerSq = indiaCensusCSV.densityPerSqKm;
    }
}
