package gov.nih.nci.cabig.caaers.utils;

import gov.nih.nci.cabig.caaers.domain.DateValue;

import java.text.ParseException;
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
    
    public static DateValue parseDateString(String dateString){
    	if(dateString == null) return null;
    	DateValue dv = new DateValue();
    	
    	if(dateString.length() == 4){
    		dv.setYearString(dateString);
    		return dv;
    	}
    	
    	if(dateString.indexOf('/') < 0)  
    		throw new RuntimeException("Unknown format, expected format is 'mm/dd/yyyy' or 'mm/yyyy' or 'yyyy'");
    	 
    	String[] dateParts = dateString.split("/");
    	int size = dateParts.length;
    	//validate year
    	if(dateParts[size - 1].length() != 4) 
    		throw new RuntimeException("Unknown format, expected format is 'mm/dd/yyyy' or 'mm/yyyy' or 'yyyy'");
    	
    	if(size == 2){
    		 if(dateParts[0].length() != 2)
    			 throw new RuntimeException("Unknown format, expected format is 'mm/dd/yyyy' or 'mm/yyyy' or 'yyyy'");
    		
    		 dv.setMonthString(dateParts[0]);
    		 dv.setYearString(dateParts[1]);
    		 
    	}else if (size == 3){
    		if(dateParts[0].length() != 2)
   			 	throw new RuntimeException("Unknown format, expected format is 'mm/dd/yyyy' or 'mm/yyyy' or 'yyyy'");
    		if(dateParts[1].length() != 2)
   			 	throw new RuntimeException("Unknown format, expected format is 'mm/dd/yyyy' or 'mm/yyyy' or 'yyyy'");
    		
    		dv.setMonthString(dateParts[0]);
    		dv.setDayString(dateParts[1]);
   		 	dv.setYearString(dateParts[2]);
    	}else {
    		return null;
    	}
    	
    	return dv;
    }
}
