package censusanalyser;

import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;

public class CensusAnalyserTest {

    private static final String INDIA_CENSUS_CSV_FILE_PATH = "/home/admin1/CensusAnalyzerProblem/CensusAnalyser/src/test/resources/IndiaStateCensusData.csv";
    private static final String WRONG_CSV_FILE_PATH = "./src/main/resources/IndiaStateCensusData.csv";
    private static final String WRONG_CSV_FILE_TYPE = "/home/admin1/CensusAnalyzerProblem/CensusAnalyser/src/test/resources/IndiaStateCensusData.pdf";
    private static final String DELIMITER_PROBLEM = "/home/admin1/CensusAnalyzerProblem/CensusAnalyser/src/test/resources/IndiaStateCensusData.csv";
    private static final String HEADER_PROBLEM = "/home/admin1/CensusAnalyzerProblem/CensusAnalyser/src/test/resources/CensusHeader.csv";

    private static final String STATE_CENSUS_CSV_FILE_PATH = "/home/admin1/CensusAnalyzerProblem/CensusAnalyser/src/test/resources/IndiaStateCode.csv";
    private static final String STATE_WRONG_CSV_FILE_PATH = "/desktop/admin1/IdeaProjects/CensusAnalyser/CensusAnalyser/src/test/resources/IndiaStateCode.csv";
    private static final String STATE_WRONG_CSV_FILE_TYPE = "/home/admin1/CensusAnalyzerProblem/CensusAnalyser/src/test/resources/IndiaStateCode.pdf";
    private static final String STATE_DELIMITER_PROBLEM = "/home/admin1/CensusAnalyzerProblem/CensusAnalyser/src/test/resources/IndiaStateCode.csv";
    private static final String STATE_HEADER_PROBLEM = "/home/admin1/CensusAnalyzerProblem/CensusAnalyser/src/test/resources/StateHeaderFile.csv";

    @Test
    public void givenIndianCensusCSVFileReturnsCorrectRecords() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            int numOfRecords = censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            Assert.assertEquals(29, numOfRecords);
        } catch (CensusAnalyserException e) {
        }
    }

    @Test
    public void givenIndiaCensusData_WithWrongFile_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaCensusData(WRONG_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndiaCensusData_WithWrongType_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaCensusData(WRONG_CSV_FILE_TYPE);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndiaCensusData_WithWrongDelimiter_ShouldThrowException() {
        CensusAnalyser censusAnalyser = new CensusAnalyser();
        ExpectedException expectedException = ExpectedException.none();
        expectedException.expect(CensusAnalyserException.class);
        try {
            censusAnalyser.loadIndiaCensusData(DELIMITER_PROBLEM);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.Incorrect_CSV, e.type);
        }
    }

    @Test
    public void givenIndiaCensusData_WithWrongHeader_ShouldThrowException() {
        CensusAnalyser censusAnalyser = new CensusAnalyser();
        ExpectedException expectedException = ExpectedException.none();
        expectedException.expect(CensusAnalyserException.class);
        try {
            censusAnalyser.loadIndiaCensusData(HEADER_PROBLEM);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.Incorrect_CSV, e.type);
        }
    }

    @Test
    public void givenStateCensusCSVFileReturnsCorrectRecords() {
        int numOfRecords = 0;
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            numOfRecords = censusAnalyser.loadStateCensusData(STATE_CENSUS_CSV_FILE_PATH);
            System.out.println(numOfRecords);
            Assert.assertEquals(37, numOfRecords);
        } catch (CensusAnalyserException e) {
            System.out.println(numOfRecords);
        }
    }

    @Test
    public void givenStateCensusData_WithWrongFile_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadStateCensusData(STATE_WRONG_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenStateCensusData_WithWrongType_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadStateCensusData(STATE_WRONG_CSV_FILE_TYPE);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenStateCensusData_WithWrongDelimiter_ShouldThrowException() {
        CensusAnalyser censusAnalyser = new CensusAnalyser();
        ExpectedException expectedException = ExpectedException.none();
        expectedException.expect(CensusAnalyserException.class);
        try {
            censusAnalyser.loadStateCensusData(STATE_DELIMITER_PROBLEM);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.Incorrect_CSV, e.type);
        }
    }

    @Test
    public void givenStateCensusData_WithWrongHeader_ShouldThrowException() {
        CensusAnalyser censusAnalyser = new CensusAnalyser();
        ExpectedException expectedException = ExpectedException.none();
        expectedException.expect(CensusAnalyserException.class);
        try {
            censusAnalyser.loadStateCensusData(STATE_HEADER_PROBLEM);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.Incorrect_CSV, e.type);
        }
    }

    @Test
    public void givenStateCensusData_ShouldReturnFirstStateName() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ArrayList list = censusAnalyser.SortingIndiaCSVFile(INDIA_CENSUS_CSV_FILE_PATH);
            Assert.assertEquals(true, list.get(0).toString().contains("Andhra Pradesh"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenStateCensusData_ShouldReturnLastStateName() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ArrayList list = censusAnalyser.SortingIndiaCSVFile(INDIA_CENSUS_CSV_FILE_PATH);
            Assert.assertEquals(true, list.get(28).toString().contains("West Bengal"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndiaCensusData_IfDataIncorrectAtFirstIndex_ShouldReturnFalse() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ArrayList list = censusAnalyser.SortingIndiaCSVFile(INDIA_CENSUS_CSV_FILE_PATH);
            Assert.assertEquals(false, list.get(0).toString().contains("Maharashtra"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenStateCensusData_ShouldReturnFirstStateCode() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ArrayList list = censusAnalyser.SortingStateCSVFile(STATE_CENSUS_CSV_FILE_PATH);
            Assert.assertEquals(true, list.get(0).toString().contains("Andhra Pradesh"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenStateCensusData_ShouldReturnLastStateCode() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ArrayList list = censusAnalyser.SortingStateCSVFile(STATE_CENSUS_CSV_FILE_PATH);
            Assert.assertEquals(true, list.get(36).toString().contains("WB"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenStateCensusData_IfDataIncorrect_ShouldReturnFalse() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ArrayList list = censusAnalyser.SortingStateCSVFile(STATE_CENSUS_CSV_FILE_PATH);
            Assert.assertEquals(false, list.get(36).toString().contains("MH"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}