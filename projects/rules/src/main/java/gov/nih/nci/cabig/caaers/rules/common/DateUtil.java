package gov.nih.nci.cabig.caaers.rules.common;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    public static Calendar dateToCalendar(Date date) {
        if (date == null) return null;
        Calendar cal = Calendar.getInstance();
        cal.setTime( date );
        return cal;
    } 
    
    /** 
	   Compares two dates
     * @param o1
     * @param o2
     * @return o1 == o2 , returns 0
     *         o1 < o2, returns 1
     *         o1 > 02  return -1
     */
    public static int compare(Object o1, Object o2) {
    	if(o1 == null || !(o1 instanceof Date)) return 1;
    	if(o2 == null || !(o2 instanceof Date)) return -1;

    	Date d1 = (Date)o1;
    	Date d2 = (Date)o2;
    	
    	return d2.compareTo(d1);
    }
}
