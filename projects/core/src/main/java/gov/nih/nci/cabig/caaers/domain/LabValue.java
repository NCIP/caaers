package gov.nih.nci.cabig.caaers.domain;

import javax.persistence.Embeddable;
import java.util.Date;

/**
 * @author Rhett Sutphin
 */
@Embeddable
public class LabValue {
    private String value;
    private Date date;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
