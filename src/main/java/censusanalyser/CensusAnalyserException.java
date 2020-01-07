package censusanalyser;

public class CensusAnalyserException extends Exception {

    enum ExceptionType {
        Incorrect_CSV, WRONG_FILE_TYPE, UNABLE_TO_PARSE, CENSUS_FILE_PROBLEM
    }

    ExceptionType type;

    public CensusAnalyserException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }

    public CensusAnalyserException(String message, String name) {
        super(message);
        this.type = ExceptionType.valueOf(name);
    }

}
