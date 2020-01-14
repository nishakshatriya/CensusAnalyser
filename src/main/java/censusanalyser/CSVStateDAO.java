package censusanalyser;
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

   public String getStateCode(){
        return StateCode;
    }
}
