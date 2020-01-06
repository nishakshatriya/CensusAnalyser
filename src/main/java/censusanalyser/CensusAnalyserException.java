package censusanalyser;

public class CensusAnalyserException extends Exception {

    enum ExceptionType {
        WRONG_DELIMTER_PROBLEM, WRONG_FILE_TYPE, WRONG_HEADER_PROBLEM, UNABLE_TO_PARSE, CENSUS_FILE_PROBLEM
    }

    ExceptionType type;

    public CensusAnalyserException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }

}
