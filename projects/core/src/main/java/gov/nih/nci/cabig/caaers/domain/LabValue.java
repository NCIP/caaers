package gov.nih.nci.cabig.caaers.domain;

import java.util.Date;

import javax.persistence.Embeddable;

import org.apache.commons.lang.StringUtils;

/**
 * This class represents the LabValue domain object associated with the Adverse event report.
 * 
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

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;

        if (obj == null) return false;
        if (!(obj instanceof LabValue)) return false;

        LabValue lv = (LabValue) obj;

        if (date == null && lv.date != null) return false;
        if (date != null && lv.date == null) return false;
        if (lv.date != null && !date.equals(lv.date)) return false;

        if (!StringUtils.equals(value, lv.value)) return false;

        return true;
    }
}
