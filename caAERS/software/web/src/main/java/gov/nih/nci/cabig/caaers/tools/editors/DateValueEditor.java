package gov.nih.nci.cabig.caaers.tools.editors;

import gov.nih.nci.cabig.caaers.domain.DateValue;

import java.beans.PropertyEditorSupport;

public class DateValueEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (text == null || text.equals("")) setValue(null);
        String[] dateParts = text.split("/");
        int size = dateParts.length;
        if (size != 3) throw new IllegalArgumentException(
                        "Unknown format, expected format is 'mm/dd/yyyy'");
        DateValue dateValue = new DateValue();
        try {
            dateValue.setMonth(Integer.parseInt(dateParts[0]));
            dateValue.setDay(Integer.parseInt(dateParts[1]));
            dateValue.setYear(Integer.parseInt(dateParts[2]));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(
                            "Unknown format, unable to parse the date values, expected format is 'mm/dd/yyyy'",
                            e);
        }
        setValue(dateValue);
    }

    @Override
    public String getAsText() {
        DateValue dateValue = (DateValue) getValue();
        return (dateValue == null) ? null : dateValue.toString();
    }

}
