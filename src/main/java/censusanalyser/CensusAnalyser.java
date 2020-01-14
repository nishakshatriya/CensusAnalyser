package censusanalyser;

import com.google.gson.Gson;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class CensusAnalyser {
    Map<String,CensusDAO> censusList=null;
    Map<String,CSVStateDAO> statesList = null;

    public CensusAnalyser() {
        this.censusList=new TreeMap<>();
        this.statesList= new TreeMap<>();
    }

    public int loadIndiaCensusData(String... csvFilePath) throws CensusAnalyserException, IOException {
        censusList=new CensusLoader().loadCensusData(IndiaCensusCSV.class,csvFilePath);
        return  censusList.size();
    }

    public int loadUSCensusData(String... csvFilePath) throws CensusAnalyserException, IOException {
        censusList=new CensusLoader().loadCensusData(USCensusCSV.class,csvFilePath);
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
        List<CensusDAO> sortedList = censusList.values().stream().collect(Collectors.toList());

        List<CensusDAO> censusList = sortedList.stream().sorted(Comparator.comparing(CensusDAO::getStateCode)).collect(Collectors.toList());
        String jsonString = new Gson().toJson(censusList);
        System.out.println(jsonString);
        return jsonString;

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


