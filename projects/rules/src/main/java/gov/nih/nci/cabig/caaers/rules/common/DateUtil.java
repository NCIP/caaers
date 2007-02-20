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
}
