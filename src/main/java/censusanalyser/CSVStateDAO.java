package censusanalyser;
//SrNo,StateName,TIN,StateCode
public class CSVStateDAO {
    public int TIN;
    public String StateCode;
    public String StateName;
    public int SrNo;

    public CSVStateDAO(CSVStates csvStates) {
        SrNo=csvStates.SrNo;
        StateName=csvStates.StateName;
        TIN=csvStates.TIN;
        StateCode=csvStates.StateCode;
    }
}
