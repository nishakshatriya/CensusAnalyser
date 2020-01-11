package censusanalyser;

import com.google.gson.Gson;
import csvFileBuilder.CSVBuilderException;
import csvFileBuilder.CSVBuilderFactory;
import csvFileBuilder.ICSVBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class CensusAnalyser {
    List<IndiaCensusDAO> censusList = null;
    List<CSVStateDAO> statesList = null;


    public CensusAnalyser() {
        this.censusList = new ArrayList<IndiaCensusDAO>();
        this.statesList = new ArrayList<CSVStateDAO>();
    }

    public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder icsvBuilder = CSVBuilderFactory.CreateCSVBuilder();
            List<IndiaCensusCSV> indiaCensusCSVList = icsvBuilder.getCSVInList(reader, IndiaCensusCSV.class);
            for (int i = 0; i < indiaCensusCSVList.size(); i++) {
                this.censusList.add(new IndiaCensusDAO(indiaCensusCSVList.get(i)));
            }
            return censusList.size();
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (RuntimeException e) {
            throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.Incorrect_CSV);
        } catch (CSVBuilderException e) {
            throw new CensusAnalyserException(e.getMessage(), e.type.name());
        }
    }


    public int loadStateCensusData(String csvFilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder icsvBuilder = CSVBuilderFactory.CreateCSVBuilder();
            List<CSVStates> csvStatesList = icsvBuilder.getCSVInList(reader, CSVStates.class);
            csvStatesList.stream().filter(stateCensusData -> statesList.add(new CSVStateDAO(stateCensusData))).collect(Collectors.toList());
//            for (int i = 0; i < csvStatesList.size(); i++) {
//                this.statesList.add(new CSVStateDAO(csvStatesList.get(i)));
//            }
            System.out.println(statesList);
            return statesList.size();
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (RuntimeException e) {
            throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.Incorrect_CSV);
        } catch (CSVBuilderException e) {
            throw new CensusAnalyserException(e.getMessage(), e.type.name());
        }
    }

    public String SortingIndiaCSVFile() throws CensusAnalyserException {
        if (censusList == null || censusList.size() == 0) {
            throw new CensusAnalyserException("NO_CENSUS_DATA", CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }
        Comparator<IndiaCensusDAO> codeCsvComparator = (o1, o2) -> ((o1.state).compareTo(o2.state) < 0) ? -1 : 1;
        System.out.println(codeCsvComparator);
        Collections.sort(censusList, codeCsvComparator);
        String sortedJson = new Gson().toJson(censusList);
        System.out.println(sortedJson);

        return sortedJson;
    }

    public String SortingStateCSVFile() throws CensusAnalyserException {
        if (statesList == null || statesList.size() == 0) {
            throw new CensusAnalyserException("NO_CENSUS_DATA", CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }
        Comparator<CSVStateDAO> codeCsvComparator = (obj1, obj2) -> ((obj1.StateCode).compareTo(obj2.StateCode) < 0) ? -1 : 1;
        Collections.sort(statesList, codeCsvComparator);
        String sortedJSON = new Gson().toJson(statesList);
        System.out.println(sortedJSON);
        return sortedJSON;
    }

    public String SortingIndiaCSVFilePopulation() throws CensusAnalyserException {
        if (censusList == null || censusList.size() == 0) {
            throw new CensusAnalyserException("NO_CENSUS_DATA", CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }
        Comparator<IndiaCensusDAO> codeCsvComparator = (obj1, obj2) -> ((obj1.population) - (obj2.population) > 0) ? -1 : 1;
        System.out.println(codeCsvComparator);
        Collections.sort(censusList, codeCsvComparator);
        String sortedJson = new Gson().toJson(censusList);
        System.out.println(sortedJson);
        return sortedJson;
    }

    public String SortingIndiaCSVFileDensity() throws CensusAnalyserException {
        if (censusList == null || censusList.size() == 0) {
            throw new CensusAnalyserException("NO_CENSUS_DATA", CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }
        Comparator<IndiaCensusDAO> codeCsvComparator = (obj1, obj2) -> ((obj1.densityPerSqKm) - (obj2.densityPerSqKm) > 0) ? -1 : 1;
        System.out.println(codeCsvComparator);
        Collections.sort(censusList, codeCsvComparator);
        String sortedJson = new Gson().toJson(censusList);
        System.out.println(sortedJson);
        return sortedJson;
    }

    public String SortingIndiaCSVFileArea() throws CensusAnalyserException {
        if (censusList == null || censusList.size() == 0) {
            throw new CensusAnalyserException("NO_CENSUS_DATA", CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }
        Comparator<IndiaCensusDAO> codeCsvComparator = (obj1, obj2) -> ((obj1.areaInSqKm) - (obj2.areaInSqKm) > 0) ? -1 : 1;
        System.out.println(codeCsvComparator);
        Collections.sort(censusList, codeCsvComparator);
        String sortedJson = new Gson().toJson(censusList);
        System.out.println(sortedJson);
        return sortedJson;

    }
}


