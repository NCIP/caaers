package gov.nih.nci.cabig.caaers.audit;

public class AuditStringUtils {

    public static boolean isEmpty(String str) {
        boolean test = true;
        if ((str == null) || (str.length() == 0)) {
            test = true;
        } else {
            test = false;
        }
        return test;
    }

}
