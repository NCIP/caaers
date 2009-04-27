package gov.nih.nci.cabig.caaers.utils;

import org.apache.commons.lang.time.DurationFormatUtils;
/**
 * This method will print the duration in a displayable fashion.
 * @author Biju Joseph
 *
 */
public class DurationUtils {
	
	private static final long SECOND = 1000L;
	private static final long MINUTE = SECOND * 60;
	private static final long HOUR = MINUTE * 60;
	private static final long DAY = HOUR * 24;
	private static final long MONTH = DAY * 30;
	private static final long YEAR = DAY * 365;
	
	public static String formatDuration(long actualDuration, String format){
		String msgPrefix ="Due in ";
		String msgSuffix = "overdue";
		if(actualDuration < 0){
			msgPrefix = "";
		}else {
			msgSuffix = "";
		}
		
		long duration = Math.abs(actualDuration);
		
		int years = (format.contains("y")) ? Integer.parseInt(DurationFormatUtils.formatDuration(duration, "y")) : 0;
		duration -= (years * YEAR);
		int months = (format.contains("M")) ?Integer.parseInt(DurationFormatUtils.formatDuration(duration, "M")):0;
		duration -= (months * MONTH);
		int days = (format.contains("d")) ?Integer.parseInt(DurationFormatUtils.formatDuration(duration, "d")):0;
		duration -= (days * DAY);
		int hours = (format.contains("H")) ?Integer.parseInt(DurationFormatUtils.formatDuration(duration, "H")):0;
		duration -= (hours * HOUR);
		int minutes = (format.contains("m")) ?Integer.parseInt(DurationFormatUtils.formatDuration(duration, "m")):0;
		duration -= (minutes * MINUTE);
		int seconds = (format.contains("s")) ?Integer.parseInt(DurationFormatUtils.formatDuration(duration, "s")):0;
		
		//apply rounding to days / Hours / minutes
		if(format.contains("d") && format.contains("H")){
			if(hours > 12){
				days +=1;
			}
			hours = 0;
		}
		
		//apply rounding of hours
		if(format.contains("m") && format.contains("H")){
			if(minutes > 30){
				hours += 1;
			}
			minutes = 0;
		}
		
		//apply rounding of minutes
		if(format.contains("m") && format.contains("s")){
			if(seconds > 30){
				minutes += 1;
			}
			seconds = 0;
		}
		
		StringBuffer sb = new StringBuffer(msgPrefix);
		if(years > 0 ){
			sb.append(years).append(years > 1 ? " years" : " year").append(" ");
		}
		if(months > 0){
			sb.append(months).append(months > 1 ? " months" : " month").append(" ");
		}
		if(days > 0){
			sb.append(days).append(days > 1 ? " days" : " day").append(" ");
		}
		if(hours > 0){
			sb.append(hours).append(hours > 1 ? " hours" : " hour").append(" ");
		}
		if(minutes > 0){
			sb.append(minutes).append(minutes > 1 ? " minutes" : " minute").append(" ");
		}
		if(seconds > 0){
			sb.append(seconds).append(seconds > 1 ? " seconds" : " second").append(" ");
		}
		sb.append(msgSuffix);
		return sb.toString();
		
	}
}
