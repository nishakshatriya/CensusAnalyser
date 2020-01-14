package censusanalyser;

import com.google.gson.Gson;
import csvFileBuilder.CSVBuilderException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;

import static censusanalyser.CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM;

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
    private static final String STATE_NULL_POINTER = "/home/admin1/CensusAnalyzerProblem/CensusAnalyser/src/test/resources/IndiaStateNullFile.csv";

    @Test
    public void givenIndianCensusCSVFileReturnsCorrectRecords() throws IOException, CSVBuilderException {
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
            Assert.assertEquals(CENSUS_FILE_PROBLEM, e.type);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndiaCensusData_WithWrongType_ShouldThrowException() throws IOException {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaCensusData(WRONG_CSV_FILE_TYPE);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndiaCensusData_WithWrongDelimiter_ShouldThrowException() throws IOException, CSVBuilderException {
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndiaAndStateCSVFile_ShouldReturnCommonRecords() {
        int numOfRecords = 0;
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            numOfRecords = censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH,STATE_CENSUS_CSV_FILE_PATH);
            System.out.println(numOfRecords);
            Assert.assertEquals(29, numOfRecords);
        } catch (CensusAnalyserException e) {
            System.out.println(numOfRecords);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenStateCensusData_WithWrongFile_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaCensusData(WRONG_CSV_FILE_PATH,STATE_WRONG_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CENSUS_FILE_PROBLEM, e.type);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenStateCensusData_WithWrongType_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaCensusData(WRONG_CSV_FILE_TYPE,STATE_WRONG_CSV_FILE_TYPE);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CENSUS_FILE_PROBLEM, e.type);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenStateCensusData_WithWrongDelimiter_ShouldThrowException() {
        CensusAnalyser censusAnalyser = new CensusAnalyser();
        ExpectedException expectedException = ExpectedException.none();
        expectedException.expect(CensusAnalyserException.class);
        try {
            censusAnalyser.loadIndiaCensusData(DELIMITER_PROBLEM,STATE_DELIMITER_PROBLEM);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.Incorrect_CSV, e.type);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenStateCensusData_WithWrongHeader_ShouldThrowException() {
        CensusAnalyser censusAnalyser = new CensusAnalyser();
        ExpectedException expectedException = ExpectedException.none();
        expectedException.expect(CensusAnalyserException.class);
        try {
            censusAnalyser.loadIndiaCensusData( HEADER_PROBLEM ,STATE_HEADER_PROBLEM);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.Incorrect_CSV, e.type);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenStateCensusData_ShouldReturnFirstStateName() throws CensusAnalyserException, IOException {
        CensusAnalyser censusAnalyser = new CensusAnalyser();
        censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
        String sortedCensusData = censusAnalyser.SortingIndiaCSVFile();
        IndiaCensusCSV[] indiaCensusCSVS = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
        Assert.assertEquals("Andhra Pradesh", indiaCensusCSVS[0].state);
    }

    @Test
    public void givenStateCensusData_ShouldReturnLastStateName() throws CensusAnalyserException, IOException {
        CensusAnalyser censusAnalyser = new CensusAnalyser();
        censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
        String sortedCensusData = censusAnalyser.SortingIndiaCSVFile();
        IndiaCensusCSV[] indiaCensusCSVS = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
        Assert.assertEquals("West Bengal", indiaCensusCSVS[28].state);
    }

//
    @Test
    public void givenStateCensusData_ShouldReturnFirstStateCode() throws CensusAnalyserException, IOException {
        CensusAnalyser censusAnalyser = new CensusAnalyser();
        censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH,STATE_CENSUS_CSV_FILE_PATH);
        String sortedCensusData = censusAnalyser.SortingIndiaCSVFile();
        CensusDAO[] states = new Gson().fromJson(sortedCensusData, CensusDAO[].class);
        System.out.println(states[0]);
        Assert.assertEquals("AP", states[0].stateCode);
    }

    @Test
    public void givenStateCensusData_ShouldReturnLastStateCode() throws CensusAnalyserException, IOException {
        CensusAnalyser censusAnalyser = new CensusAnalyser();
        censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH,STATE_CENSUS_CSV_FILE_PATH);
        String sortedCensusData = censusAnalyser.SortingStateCSVFile();
        CensusDAO[] states = new Gson().fromJson(sortedCensusData, CensusDAO[].class);
        Assert.assertEquals("WB", states[28].stateCode);
        System.out.println(states);
    }

    @Test
    public void givenStateCensusData_IfDataIncorrect_ShouldReturnFalse() throws CensusAnalyserException, IOException {
        {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH,STATE_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.SortingStateCSVFile();
            CensusDAO[] states = new Gson().fromJson(sortedCensusData, CensusDAO[].class);
            Assert.assertEquals("WB", states[28].stateCode);

        }
    }

    @Test
    public void WhenNullDataRetrived_ShouldThrowException() throws CensusAnalyserException, IOException {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadIndiaCensusData(STATE_NULL_POINTER);
            String sortedCensusData = censusAnalyser.SortingStateCSVFile();
            CensusDAO[] states = new Gson().fromJson(sortedCensusData, CensusDAO[].class);
            Assert.assertEquals("WB", states[0].stateCode);

        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndiaCensusData_ShouldReturnHighestPopulation() throws CensusAnalyserException, IOException {
        CensusAnalyser censusAnalyser = new CensusAnalyser();
        censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
        String sortedCensusData = censusAnalyser.SortingIndiaCSVFilePopulation();
        IndiaCensusCSV[] indiaCensusCSVS = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
        Assert.assertEquals(199812341,indiaCensusCSVS[0].population);
    }
    @Test
    public void givenIndiaCensusData_ShouldReturnLowestPopulation() throws CensusAnalyserException, IOException {
        CensusAnalyser censusAnalyser = new CensusAnalyser();
        censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
        String sortedCensusData = censusAnalyser.SortingIndiaCSVFilePopulation();
        IndiaCensusCSV [] indiaCensusCSVS = new Gson().fromJson(sortedCensusData,IndiaCensusCSV[].class);
        Assert.assertEquals(607688, indiaCensusCSVS[28].population);

    }

    @Test
    public void givenIndiaCensuData_ShouldReturnMostDensity() throws CensusAnalyserException, IOException {
        CensusAnalyser censusAnalyser = new CensusAnalyser();
        censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
        String sortedCensusData = censusAnalyser.SortingIndiaCSVFileDensity();
        IndiaCensusCSV [] indiaCensusCSVS = new Gson().fromJson(sortedCensusData,IndiaCensusCSV[].class);
        Assert.assertEquals(1102, indiaCensusCSVS[0].densityPerSqKm);
    }

    @Test
    public void givenIndiaCensusData_ShouldReturnLeastDensity() throws CensusAnalyserException, IOException {
        CensusAnalyser censusAnalyser = new CensusAnalyser();
        censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
        String sortedCensusData = censusAnalyser.SortingIndiaCSVFileDensity();
        IndiaCensusCSV [] indiaCensusCSVS = new Gson().fromJson(sortedCensusData,IndiaCensusCSV[].class);
        System.out.println(indiaCensusCSVS[28]);
        Assert.assertEquals(50, indiaCensusCSVS[28].densityPerSqKm);

    }
    @Test
    public void givenIndiaCensusData_ShouldReturnLargestArea() throws CensusAnalyserException, IOException {
        CensusAnalyser censusAnalyser = new CensusAnalyser();
        censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
        String sortedCensusData = censusAnalyser.SortingIndiaCSVFileArea();
        IndiaCensusCSV [] indiaCensusCSVS = new Gson().fromJson(sortedCensusData,IndiaCensusCSV[].class);
        Assert.assertEquals(342239, indiaCensusCSVS[0].areaInSqKm);
    }
    @Test
    public void givenIndiaCensusData_ShouldReturnLeastArea() throws CensusAnalyserException, IOException {
        CensusAnalyser censusAnalyser = new CensusAnalyser();
        censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
        String sortedCensusData = censusAnalyser.SortingIndiaCSVFileArea();
        IndiaCensusCSV [] indiaCensusCSVS = new Gson().fromJson(sortedCensusData,IndiaCensusCSV[].class);
        Assert.assertEquals(3702, indiaCensusCSVS[28].areaInSqKm);
    }

    @Test
    public void givenUSCensusData_ShouldLoadCorrectData() throws CensusAnalyserException, IOException {
        CensusAnalyser censusAnalyser = new CensusAnalyser();
        int totalRecords=censusAnalyser.loadUSCensusData("/home/admin1/CensusAnalyzerProblem/CensusAnalyser/src/test/resources/USCensusData.csv");
        System.out.println(totalRecords);
        Assert.assertEquals(51,totalRecords);
    }
}
