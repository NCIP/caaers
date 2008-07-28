package gov.nih.nci.cabig.caaers.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
    /**
     * Compares two dates. The time fields are ignored.
     * 
     * @param d1 -
     *                Date 1
     * @param d2 -
     *                Date 2
     * @return 0 if same, -1 if d1 is less than d2.
     */
    public static int compareDate(Date d1, Date d2) {
        Calendar c1 = Calendar.getInstance();
        c1.setTime(d1);

        Calendar c2 = Calendar.getInstance();
        c2.setTime(d2);

        int x = c1.get(Calendar.YEAR);
        int y = c2.get(Calendar.YEAR);
        if (x != y) return x - y;

        x = c1.get(Calendar.MONTH);
        y = c2.get(Calendar.MONTH);
        if (x != y) return x - y;

        x = c1.get(Calendar.DATE);
        y = c2.get(Calendar.DATE);
        return x - y;
    }
    
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
    public static String formatDate(Date d){
    	return dateFormat.format(d);
    }
}
