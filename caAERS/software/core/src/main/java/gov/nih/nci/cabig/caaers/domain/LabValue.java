/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
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

    private int zone = 0;    //used to coverup oracle bug
    
    /** The value. */
    private String value;

    /** The date. */
    private Date date;

    /**
     * Gets the value.
     *
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the value.
     *
     * @param value the new value
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Gets the date.
     *
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * Sets the date.
     *
     * @param date the new date
     */
    public void setDate(Date date) {
        this.date = date;
    }

    public int getZone() {
        return zone;
    }

    public void setZone(int zone) {
        this.zone = zone;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
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
