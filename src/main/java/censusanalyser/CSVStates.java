package censusanalyser;

import com.opencsv.bean.CsvBindByName;

public class CSVStates {



    @CsvBindByName(column = "State Name", required = true)
    public int state;


    @CsvBindByName(column = "StateCode", required = true)
    public int stateCode;

    @Override
    public String toString() {
        return "CSVStates{" +

                ", state=" + state +
                ", stateCode=" + stateCode +
                '}';
    }
}
