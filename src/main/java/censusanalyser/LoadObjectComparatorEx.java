package censusanalyser;

import java.util.Comparator;

public class LoadObjectComparatorEx implements Comparator {
    @Override
    public int compare(Object o, Object o1) {
        if (o.toString().compareTo(o1.toString()) < 0){
            return -1;
        }else if (o.toString().compareTo(o1.toString()) > 0){
            return 1;
        }else if (o.toString().compareTo(o1.toString()) == 0){
            return 0;
        }
        return 0;
    }
}
