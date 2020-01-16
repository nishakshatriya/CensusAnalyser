package censusanalyser;

import com.google.gson.Gson;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class CensusAnalyser {

    public enum Country{
        INDIA, US;
    }
    private Country country;

    Map<String,CensusDAO> censusList=new TreeMap<>();
    Map<String,CSVStateDAO> statesList = new TreeMap<>();

    public CensusAnalyser(Country country) {
        this.country=country;
    }

    public int loadCensusData(String...csvFilePath) throws CensusAnalyserException, IOException {
        censusList = AdapterFactory.loadCensusData(country, csvFilePath);
        return censusList.size();
    }

    public String SortingIndiaCSVFile() throws CensusAnalyserException {
        if (censusList == null || censusList.size() == 0) {
            throw new CensusAnalyserException("NO_CENSUS_DATA", CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }

        List<CensusDAO> sortedList = censusList.values().stream().collect(Collectors.toList());
        Comparator<CensusDAO> codeCsvComparator = (o1, o2) -> ((o1.state).compareTo(o2.state) < 0) ? -1 : 1;
        System.out.println(codeCsvComparator);
        Collections.sort(sortedList,codeCsvComparator);
        String sortedJson = new Gson().toJson(sortedList);
        System.out.println(sortedJson);
        return sortedJson;
    }

    public String SortingStateCSVFile() throws CensusAnalyserException {
        if (censusList == null || censusList.size() == 0) {
            throw new CensusAnalyserException("NO_CENSUS_DATA",
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }

        List sortedList = censusList.values().stream().sorted(Comparator.comparing(censusData -> censusData.stateCode))
                .map(censusDAO -> censusDAO.getCensusDTO(country)).collect(Collectors.toList());
        String sortedJson = new Gson().toJson(sortedList);
        System.out.println(sortedJson);
        return sortedJson;
    }

    public String SortingIndiaCSVFileArea() throws CensusAnalyserException {
        if (censusList == null || censusList.size() == 0) {
            throw new CensusAnalyserException("NO_CENSUS_DATA", CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }
        List<CensusDAO> sortedList = censusList.values().stream().collect(Collectors.toList());
        Comparator<CensusDAO> codeCsvComparator = (obj1, obj2) -> ((obj1.areaInSqKm) - (obj2.areaInSqKm) > 0) ? -1 : 1;
        System.out.println(codeCsvComparator);
        Collections.sort(sortedList, codeCsvComparator);
        String sortedJson = new Gson().toJson(sortedList);
        System.out.println(sortedJson);
        return sortedJson;
    }

    public String SortingIndiaCSVFileDensity() throws CensusAnalyserException {
            if (censusList == null || censusList.size() == 0) {
                throw new CensusAnalyserException("NO_CENSUS_DATA", CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
            }
            List<CensusDAO> sortedList = censusList.values().stream().collect(Collectors.toList());
            Comparator<CensusDAO> codeCsvComparator = (obj1, obj2) -> ((obj1.densityPerSqKm) - (obj2.densityPerSqKm) > 0) ? -1 : 1;
            System.out.println(codeCsvComparator);
            Collections.sort(sortedList, codeCsvComparator);
            String sortedJson = new Gson().toJson(sortedList);
            System.out.println(sortedJson);
            return sortedJson;
        }

        public String SortingIndiaCSVFilePopulation() throws CensusAnalyserException {
        if (censusList == null || censusList.size() == 0) {
            throw new CensusAnalyserException("NO_CENSUS_DATA", CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }
        List<CensusDAO> sortedList = censusList.values().stream().collect(Collectors.toList());
        Comparator<CensusDAO> codeCsvComparator = (obj1, obj2) -> ((obj1.population) - (obj2.population) > 0) ? -1 : 1;
        System.out.println(codeCsvComparator);
        Collections.sort(sortedList, codeCsvComparator);
        String sortedJson = new Gson().toJson(sortedList);
        System.out.println(sortedJson);
        return sortedJson;
    }
}


