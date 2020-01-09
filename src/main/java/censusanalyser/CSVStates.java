package censusanalyser;

import com.opencsv.bean.CsvBindByName;

public class CSVStates {
    //SrNo,StateName,TIN,StateCode

    @CsvBindByName(column = "SrNo", required = true)
    public String SrNo;

    @CsvBindByName(column = "StateName", required = true)
    public String StateName;

    @CsvBindByName(column ="TIN", required = true)
    public String TIN;


    @CsvBindByName(column = "StateCode", required = true)
    public String StateCode;

    @Override
    public String toString() {
        return "CSVStates{" +
                ", SrNo=" + SrNo +
                ", StateName=" + StateName +
                ", TIN=" + TIN +
                ", StateCode=" + StateCode+
                '}';
    }
}
