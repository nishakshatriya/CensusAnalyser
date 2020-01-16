package censusanalyser;

import csvFileBuilder.CSVBuilderException;
import csvFileBuilder.CSVBuilderFactory;
import csvFileBuilder.ICSVBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.StreamSupport;

public abstract class CensusAdapter {



    public abstract  <E> Map<String, CensusDAO> loadCensusData(String...csvFilePath) throws CensusAnalyserException;

        public <E> Map<String , CensusDAO>loadCensusData(Class <E> censusCSV, String... csvFilePath)throws CensusAnalyserException{
            Map<String, CensusDAO> censusDAOMap = new TreeMap<>();
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath[0]))) {
            ICSVBuilder icsvBuilder = CSVBuilderFactory.CreateCSVBuilder();
            if (censusCSV.getName().equals("censusanalyser.IndiaCensusCSV")) {
                List<E> CensusCSVList = icsvBuilder.getCSVInList(reader, censusCSV);
                StreamSupport.stream(CensusCSVList.spliterator(), false)
                        .map(IndiaCensusCSV.class::cast)
                        .forEach(csv -> censusDAOMap.put(csv.state, new CensusDAO(csv)));
            }
            if (censusCSV.getName().equals("censusanalyser.USCensusCSV")) {
                List<E> CensusCSVList = icsvBuilder.getCSVInList(reader, censusCSV);
                StreamSupport.stream(CensusCSVList.spliterator(), false)
                        .map(USCensusCSV.class::cast)
                        .forEach(csv -> censusDAOMap.put(csv.state, new CensusDAO(csv)));
            }
//            if (csvFilePath.length == 1)return censusDAOMap;
//            this.loadStateCensusData(csvFilePath[1],censusDAOMap);
          return censusDAOMap;

        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (RuntimeException e) {
            throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.Incorrect_CSV);
        } catch (CSVBuilderException e) {
            throw new CensusAnalyserException(e.getMessage(), e.type.name());
        }
       // return censusDAOMap;
    }
}

//    public int loadStateCensusData(String csvFilePath, Map<String, CensusDAO> censusDAOMap) throws CensusAnalyserException {
//        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
//            ICSVBuilder icsvBuilder = CSVBuilderFactory.CreateCSVBuilder();
//            Iterator<CSVStates> statesIterator = icsvBuilder.getCSVIterator(reader, CSVStates.class);
//            Iterable<CSVStates> csvStatesIterable = () -> statesIterator;
//            StreamSupport.stream(csvStatesIterable.spliterator(),false)
//                    .filter(CSVStateList->censusDAOMap.get(CSVStateList.StateName)!=null)
//                    .forEach(CSVStateList ->censusDAOMap.get(CSVStateList.StateName).stateCode=CSVStateList.StateCode);
//            System.out.println(censusDAOMap);
//            return censusDAOMap.size();
//        } catch (IOException e) {
//            throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
//        } catch (RuntimeException e) {
//            throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.Incorrect_CSV);
//        } catch (CSVBuilderException e) {
//            throw new CensusAnalyserException(e.getMessage(), e.type.name());
//        }
//    }

//    }

