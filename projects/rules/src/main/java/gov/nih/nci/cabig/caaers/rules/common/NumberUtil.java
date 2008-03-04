package gov.nih.nci.cabig.caaers.rules.common;

public class NumberUtil {
    public static int compare(Integer i1, Integer i2) {
        if (i1 == null) return 1;
        if (i2 == null) return -1;
        return i2.compareTo(i1);
    }
}
