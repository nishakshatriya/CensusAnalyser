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
            //Iterator<IndiaCensusCSV> indiaCensusCSVIterator = icsvBuilder.getCSVIterator(reader, IndiaCensusCSV.class);
            List<IndiaCensusCSV> indiaCensusCSVList = icsvBuilder.getCSVInList(reader,IndiaCensusCSV.class);
            //return this.getCount(indiaCensusCSVIterator);
            return indiaCensusCSVList.size();
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (RuntimeException e) {
            throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.Incorrect_CSV);
        } catch (CSVBuilderException e) {
            throw new CensusAnalyserException(e.getMessage(), e.type.name());
        }
    }

//    private <E> int getCount(Iterator<E> indiaCensusCSVIterator) {
//        Iterable<E> csvIterable = () -> indiaCensusCSVIterator;
//        int namOfEateries = (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
//        return namOfEateries;
//    }



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

    public ArrayList SortingIndiaCSVFile(String csvFilePath) throws CensusAnalyserException{
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath)))
        {ICSVBuilder icsvBuilder = CSVBuilderFactory.CreateCSVBuilder();

            Iterator<IndiaCensusCSV> indiaCensusCSVIterator = icsvBuilder.getCSVIterator(reader, IndiaCensusCSV.class);
            ArrayList list = new ArrayList<>();
            while (indiaCensusCSVIterator.hasNext()) {
                list.add(indiaCensusCSVIterator.next());
            }
            Comparator<IndiaCensusCSV> comparator = (obj1,obj2) ->((obj1.state).compareTo(obj2.state)<0)?-1:1;
            Collections.sort(list,comparator);
            Gson prettyGson=new GsonBuilder().setPrettyPrinting().create();
            String prettyJson=prettyGson.toJson(list);
            System.out.println(prettyJson);
            //System.out.println(list);
            return list;
        } catch (IOException e) {

        } catch (CSVBuilderException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.UNABLE_TO_PARSE);
        }catch (RuntimeException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.Incorrect_CSV);
        }
        return null;
    }
    public ArrayList SortingStateCSVFile(String csvFilePath) throws CensusAnalyserException{
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath)))
        {ICSVBuilder icsvBuilder = CSVBuilderFactory.CreateCSVBuilder();

            Iterator<CSVStates> csvStatesIterator = icsvBuilder.getCSVIterator(reader, CSVStates.class);
            ArrayList list = new ArrayList<>();
            while (csvStatesIterator.hasNext()) {
                list.add(csvStatesIterator.next());
            }
            Comparator<CSVStates> comparator = (obj1,obj2) ->((obj1.StateCode).compareTo(obj2.StateCode)<0)?-1:1;
            Collections.sort(list,comparator);
            Gson prettyGson=new GsonBuilder().setPrettyPrinting().create();
            String prettyJson=prettyGson.toJson(list);
            System.out.println(prettyJson);
            //System.out.println(list);
            return list;
        } catch (IOException e) {

        } catch (CSVBuilderException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.UNABLE_TO_PARSE);
        }catch (RuntimeException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.Incorrect_CSV);
        }
        return null;
    }

}


