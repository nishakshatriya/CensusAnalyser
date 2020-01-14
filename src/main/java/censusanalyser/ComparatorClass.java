package censusanalyser;

import java.util.Comparator;

public class ComparatorClass implements Comparator {
@Override
    public int compare(Object obj1, Object obj2){
    String str1=obj1.toString();
    String str2=obj2.toString();
    return str2.compareTo(str1);
}
}
