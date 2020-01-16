package censusanalyser;

import java.io.IOException;
import java.util.Map;

public class AdapterFactory {
    public static <E> Map<String, CensusDAO> loadCensusData(CensusAnalyser.Country country, String... csvFilePath) throws CensusAnalyserException, IOException {
        if (country.equals(CensusAnalyser.Country.INDIA)) {
            return new CensusLoader().loadCensusData(csvFilePath);
        } else if (country.equals(CensusAnalyser.Country.US)) {
            return new USCensusAdapter().loadCensusData(csvFilePath);
        } else {
            throw new CensusAnalyserException("INCORRECT_COUNTRY", CensusAnalyserException.ExceptionType.INCORRECT_COUNTRY);
        }
    }

}
