package censusanalyser;

public class CSVBuilderFactory {
    public static ICSVBuilder CreateCSVBuilder() {
        return new OpenCSVBuilder();
    }
}
