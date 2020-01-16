package censusanalyser;

import java.util.Map;

public class USCensusAdapter extends CensusAdapter {

    @Override
    public Map<String, CensusDAO> loadCensusData(String... csvFilePath) throws CensusAnalyserException {
        Map<String, CensusDAO> censusDAOMap = super.loadCensusData(USCensusCSV.class, csvFilePath);
        return censusDAOMap;

    }
}
