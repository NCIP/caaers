package gov.nih.nci.cabig.caaers.tools.editors;


import gov.nih.nci.cabig.caaers.utils.DateUtils;
import org.apache.commons.lang.StringUtils;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.util.Date;

/**
 * There is a requirement that the Date fields should accept variour combination of dates
 * Eg:-
 *  m/d/yy
 *  m/dd/yyyy
 *  mm/dd/yy
 *  mm/dd/yyyy
 *  
 * @author: Biju Joseph
 */
public class DateEditor extends PropertyEditorSupport {

    /**
     * A date in proper string form. 
     * @return
     */
    @Override
    public String getAsText() {
        Date d = (Date)getValue();
        return (d == null) ? null : gov.nih.nci.cabig.caaers.utils.DateUtils.formatDate(d);
    }


    /**
     * Will convert various date strings to date.
     * Supported formats are:
     *
     *  MM/dd/yyyy
     *  MM/dd/yy
     *
     *  MM/d/yyyy
     *  MM/d/yy
     *
     *  M/dd/yyyy
     *  M/dd/yy
     *
     *  M/d/yyyy
     *  M/d/yy
     *  
     * @param text
     * @throws IllegalArgumentException
     */
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if(StringUtils.isBlank(text)){
            setValue(null);
        }else{
            try{
                Date d = DateUtils.parseDate(text);
                setValue(d);
            }catch(ParseException pe){
                throw new IllegalArgumentException( "Unknown format, unable to parse the date values, expected format is 'mm/dd/yyyy'",pe); 
            }
        }
    }
}
