package censusanalyser;

import java.io.Reader;
import java.util.Iterator;

public interface ICSVBuilder {
    public  <E> Iterator <E>getCSVIterator(Reader reader, Class<E> indiaCensusCSVClass) throws CSVBuilderException;
}
