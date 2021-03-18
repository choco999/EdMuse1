package utilities;

public class DBUtility {

    public static boolean validCRN(String crn){
        return crn.matches("21[0-9]{3}");
    }
}
