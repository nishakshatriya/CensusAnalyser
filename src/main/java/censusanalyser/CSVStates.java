package censusanalyser;

import com.opencsv.bean.CsvBindByName;

public class CSVStates {
    //SrNo,StateName,TIN,StateCode


    @CsvBindByName(column = "StateName", required = true)
    public String StateName;


    @CsvBindByName(column = "StateCode", required = true)
    public String StateCode;

    @Override
    public String toString() {
        return "CSVStates{" +

                ", StateName=" + StateName +
                ", StateCode=" + StateCode+
                '}';
    }
}
