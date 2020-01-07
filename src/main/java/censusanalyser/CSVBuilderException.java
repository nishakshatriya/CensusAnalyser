package censusanalyser;

public class CSVBuilderException extends Exception {

    enum ExceptionType {
        Incorrect_CSV, WRONG_FILE_TYPE, UNABLE_TO_PARSE, CENSUS_FILE_PROBLEM
    }

    CSVBuilderException.ExceptionType type;

    public CSVBuilderException(String message, ExceptionType unableToParse) {
        super(message);
        this.type = type;

    }
}
