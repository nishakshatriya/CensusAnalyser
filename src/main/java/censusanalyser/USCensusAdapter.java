package censusanalyser;

import java.util.Map;

public class USCensusAdapter extends CensusAdapter {
    public Map<String, CensusDAO> loadStateCensusData(String... csvFilePath) throws CensusAnalyserException {
        Map<String, CensusDAO> censusDAOMap = super.loadCensusData(USCensusCSV.class, csvFilePath);
        this.loadStateCensusData(csvFilePath[1], censusDAOMap);
        return censusDAOMap;

    }
}
