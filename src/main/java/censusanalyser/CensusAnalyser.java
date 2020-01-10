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

public class CensusAnalyser {
    List<IndiaCensusDAO> censusList = null;


    public CensusAnalyser() {
        this.censusList = new ArrayList<IndiaCensusDAO>();
    }
    List<CSVStates>csvStatesList = null;

    public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
           ICSVBuilder icsvBuilder = CSVBuilderFactory.CreateCSVBuilder();
           List <IndiaCensusCSV> indiaCensusCSVList = icsvBuilder.getCSVInList(reader,IndiaCensusCSV.class);
           for(int i=0; i<indiaCensusCSVList.size(); i++){
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
            csvStatesList = icsvBuilder.getCSVInList(reader, CSVStates.class);
            return csvStatesList.size();
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (RuntimeException e) {
            throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.Incorrect_CSV);
        } catch (CSVBuilderException e) {
            throw new CensusAnalyserException(e.getMessage(), e.type.name());
        }
    }

    public String SortingIndiaCSVFile() throws CensusAnalyserException {
       if(censusList == null || censusList.size()==0){
           throw new CensusAnalyserException("NO_CENSUS_DATA", CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
       }
            Comparator<IndiaCensusDAO> codeCsvComparator = (o1, o2) -> ((o1.state).compareTo(o2.state) < 0) ? -1 : 1;
            System.out.println(codeCsvComparator);
            Collections.sort(censusList, codeCsvComparator);
            String sortedJson =new Gson().toJson(censusList);
            System.out.println(sortedJson);

        return sortedJson;
    }

    public String SortingStateCSVFile() throws CensusAnalyserException {
        if(csvStatesList == null || csvStatesList.size()==0) {
            throw new CensusAnalyserException("NO_CENSUS_DATA", CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }
            Comparator<CSVStates> codeCsvComparator=(obj1,obj2) ->((obj1.StateCode).compareTo(obj2.StateCode)<0)?-1:1;
            Collections.sort(csvStatesList,codeCsvComparator);
            String sortedJSON = new Gson().toJson(csvStatesList);
            System.out.println(sortedJSON);
            return sortedJSON;
    }

}


