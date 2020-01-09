package censusanalyser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import csvFileBuilder.CSVBuilderException;
import csvFileBuilder.CSVBuilderFactory;
import csvFileBuilder.ICSVBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.StreamSupport;

public class CensusAnalyser {
    public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
           ICSVBuilder icsvBuilder = CSVBuilderFactory.CreateCSVBuilder();
            List<IndiaCensusCSV> indiaCensusCSVList = icsvBuilder.getCSVInList(reader,IndiaCensusCSV.class);
            return indiaCensusCSVList.size();
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
            return csvStatesList.size();
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (RuntimeException e) {
            throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.Incorrect_CSV);
        } catch (CSVBuilderException e) {
            throw new CensusAnalyserException(e.getMessage(), e.type.name());
        }
    }

    public String SortingIndiaCSVFile(String csvFilePath) {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
            ICSVBuilder csvBuilder = CSVBuilderFactory.CreateCSVBuilder();
            List<IndiaCensusCSV> list = csvBuilder.getCSVInList(reader, IndiaCensusCSV.class);
            Comparator<IndiaCensusCSV> codeCsvComparator = (obj1, obj2) -> ((obj1.state).compareTo(obj2.state) < 0) ? -1 : 1;
            Collections.sort(list, codeCsvComparator);
            String sortedJson =new Gson().toJson(list);
            System.out.println(sortedJson);
            return sortedJson;
            } catch (CSVBuilderException e) {
            e.printStackTrace();
            } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String SortingStateCSVFile(String csvFilePath){

        try {
            Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
            ICSVBuilder csvBuilder = CSVBuilderFactory.CreateCSVBuilder();
            List<CSVStates> list = csvBuilder.getCSVInList(reader, CSVStates.class);
            Comparator<CSVStates> codeCsvComparator=(obj1,obj2) ->((obj1.StateCode).compareTo(obj2.StateCode)<0)?-1:1;
            Collections.sort(list,codeCsvComparator);
            String sortedJSON = new Gson().toJson(list);
            System.out.println(sortedJSON);
            return sortedJSON;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}


