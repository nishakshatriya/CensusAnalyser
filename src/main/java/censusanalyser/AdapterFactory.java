package censusanalyser;

import java.io.IOException;
import java.util.Map;

public class AdapterFactory {
    public static <E> Map<String, CensusDAO> loadCensusData(CensusAnalyser.Country country, String... csvFilePath) throws CensusAnalyserException, IOException {
        if (country.equals(CensusAnalyser.Country.INDIA)) {
            return new CensusAdapter().loadCensusData(IndiaCensusCSV.class, csvFilePath);
        } else if (country.equals(CensusAnalyser.Country.US)) {
            return new CensusAdapter().loadCensusData(USCensusCSV.class, csvFilePath);
        } else {
            throw new CensusAnalyserException("INCORRECT_COUNTRY", CensusAnalyserException.ExceptionType.INCORRECT_COUNTRY);
        }
    }

}
