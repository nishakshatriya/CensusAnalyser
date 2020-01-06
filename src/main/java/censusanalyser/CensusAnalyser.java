package censusanalyser;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.w3c.dom.css.CSSValueList;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.StreamSupport;

public class CensusAnalyser {
    public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            Iterator<IndiaCensusCSV> indiaCensusCSVIterator = getCSVIterator(reader, IndiaCensusCSV.class);
            return this.getCount(indiaCensusCSVIterator);
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (RuntimeException e) {
            throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.Incorrect_CSV);
        }
    }

    private <E> int getCount(Iterator<E> indiaCensusCSVIterator) {
        Iterable<E> csvIterable = () -> indiaCensusCSVIterator;
        int namOfEateries = (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
        return namOfEateries;
    }

    public int loadStateCensusData(String csvFilePath) throws CensusAnalyserException
    {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath)))
        {
            Iterator<CSVStates> csvStatesIterator = getCSVIterator(reader, CSVStates.class);
            return this.getCount(csvStatesIterator);
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (RuntimeException e) {
            throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.Incorrect_CSV);
        }
    }



    private <E> Iterator getCSVIterator(Reader reader, Class csvClass) throws CensusAnalyserException {
        try {

            CsvToBeanBuilder<E> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
            csvToBeanBuilder.withType(csvClass);
            csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
            CsvToBean<E> csvToBean = csvToBeanBuilder.build();
            return csvToBean.iterator();
        } catch (IllegalArgumentException e) {
            throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.UNABLE_TO_PARSE);
        }
    }
}


