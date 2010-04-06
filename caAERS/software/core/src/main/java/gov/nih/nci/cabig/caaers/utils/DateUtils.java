package gov.nih.nci.cabig.caaers.utils;

import gov.nih.nci.cabig.caaers.domain.DateValue;
import org.apache.commons.lang.StringUtils;
import sun.util.calendar.Gregorian;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class DateUtils {

    public static final String DATE_PATTERN= "MM/dd/yyyy";

	/**
	 * Checks whether the given d, is greater than or equal to startDate and less than or equal to endDate.
	 * @param d, cannot be null
	 * @param startDate, cannot be null
	 * @param endDate, if null ignored
	 * @return
	 * {@link NullPointerException} if d or startDate is null
	 */
	public static boolean between(Date d, Date startDate, Date endDate){
		if(endDate == null){
			return compareDate(d, startDate) >=0;
		}else if (compareDate(endDate, d) ==  0){
			return false;
		}else{
			return compareDate(d, startDate) >= 0 && compareDate(d , endDate) <=0;
		}
	}
	
	/**
	 * This is a convenient method to get yesterday date
	 * @return
	 */
	public static Date yesterday(){
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, -1);
		return c.getTime();
	}
	
	/**
	 * This is a convenient method to get tomorrow
	 * @return
	 */
	public static Date tomorrow(){
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, 1);
		return c.getTime();
	}
	
	public static Date today(){
		Calendar c = Calendar.getInstance();
		return c.getTime();
	}

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

        if (d1 == null && d2 != null) return -1;
        if (d1 != null && d2 == null) return 1;

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
    /**
     *Compares two date objects along with time,
     * @param d1
     * @param d2
     * @return 0 if same, -1 if d1 is less and 1 if d1 is higher. 
     */
    public static int compateDateAndTime(Date d1, Date d2){
    	long t1 = d1.getTime();
    	long t2 = d2.getTime();
    	if(t1 > t2) return 1;
    	if(t1 < t2) return -1;
    	return 0;
    }
    
    public static String formatDate(Date d){
        //BJ: date formats are not thread safe. 
    	return new SimpleDateFormat(DATE_PATTERN).format(d);
    }

    public static Date parseDate(String strDate) throws ParseException{
        return parseDate(strDate, "MM/dd/yyyy","MM/dd/yy","M/dd/yyyy", "M/dd/yy","M/d/yyyy","M/d/yy","MM/d/yy","MM/d/yyyy");
    }

    public static Date parseDate(String dateStr, String... parsePatterns) throws ParseException{
        
        if (dateStr == null || parsePatterns == null) {
            throw new IllegalArgumentException("Date and Patterns must not be null");
        }

        String strDate = dateStr;
       //do year correction. (partial year >=50 will be 1999 and <50 will be 2000)
       String[] parts = StringUtils.split(dateStr, '/');
       int len = parts.length;

       if(len != 3 || parts[0].length() > 2 || parts[1].length() > 2) throw new ParseException("Unable to parse the date "+strDate, -1);

       String yStr = parts[2];

       if(!(yStr.length() == 4 || yStr.length() == 2)) throw new ParseException("Unable to parse the date "+ strDate , -1);
       if(yStr.length() == 2 && StringUtils.isNumeric(yStr)){

           if(Integer.parseInt(yStr) < 50)
               yStr = "20"+yStr;
           else
               yStr = "19" + yStr;

           parts[2] = yStr;
           strDate = StringUtils.join(parts,'/');
       }

        //BJ: date formats are not thread save, so we need to create one each time.
        SimpleDateFormat parser = null;
        ParsePosition pos = new ParsePosition(0);
        for (int i = 0; i < parsePatterns.length; i++) {
            if (i == 0) {
                parser = new SimpleDateFormat(parsePatterns[0]);
            } else {
                parser.applyPattern(parsePatterns[i]);
            }
            pos.setIndex(0);

            Date date = parser.parse(strDate, pos);
            if (date != null && pos.getIndex() == strDate.length()) {
                return date;
            }
        }
        throw new ParseException("Unable to parse the date: " + strDate, -1);
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
    	} else {
    		return null;
    	}
    	
    	return dv;
    }

    public static boolean isValidDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN);

        Date testDate;
        try {
          testDate = sdf.parse(date);
        }
        catch (ParseException e) {
          return false;
        }

        if (!sdf.format(testDate).equals(date)) {
          return false;
        }

        return true;
    }

    public static boolean isValidDate(DateValue d) {
        if (d.isNull() || d.isEmpty()) return true;
        return isValidDate(d.toString());
    }
}
