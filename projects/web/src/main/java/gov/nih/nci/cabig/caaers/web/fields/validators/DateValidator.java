package gov.nih.nci.cabig.caaers.web.fields.validators;

import java.util.Date;

public class DateValidator extends FieldValidator{
	
	@Override
	public boolean isValid(Object fieldValue) {
		if(fieldValue instanceof Date){
			Date date = (Date) fieldValue;
			Date now = new Date();
			if(date.compareTo(now) < 0) return false;
		}
		return true;
	}
	
	@Override
	public String getMessagePrefix() {
		return "Incorrect/future date value";
	}
	
}
