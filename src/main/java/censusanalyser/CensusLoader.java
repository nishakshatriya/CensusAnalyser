package censusanalyser;

import csvFileBuilder.CSVBuilderException;
import csvFileBuilder.CSVBuilderFactory;
import csvFileBuilder.ICSVBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class CensusLoader {
    List<CensusDAO>censusLoader=new ArrayList<>();

    public<E> List<CensusDAO> loadCensusData(String csvFilePath, Class<E> CensusCSVClass) throws CensusAnalyserException {


        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
                ICSVBuilder icsvBuilder = CSVBuilderFactory.CreateCSVBuilder();
                List<E> CensusCSVList = icsvBuilder.getCSVInList(reader, CensusCSVClass);
                if(CensusCSVClass.getName().equals("censusanalyser.IndiaCensusCSV")) {
                    CensusCSVList.stream().filter(CensusData -> censusLoader.add(new CensusDAO((IndiaCensusCSV) CensusData))).collect(Collectors.toList());
                }
                else if(CensusCSVClass.getName().equals("censusanalyser.USCensusCSV")){
                    CensusCSVList.stream().filter(CensusData -> censusLoader.add(new CensusDAO((USCensusCSV) CensusData))).collect(Collectors.toList());

                }
            System.out.println(censusLoader);
            return censusLoader;
            } catch (IOException e) {
                throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
            } catch (RuntimeException e) {
                throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.Incorrect_CSV);
            } catch (CSVBuilderException e) {
                throw new CensusAnalyserException(e.getMessage(), e.type.name());
            }

        }

    }

