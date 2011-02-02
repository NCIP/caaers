package gov.nih.nci.cabig.caaers.domain.report;

import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

 
/**
 * This class wraps the {@link ReportMandatoryFieldDefinition}.
 * The mandatoryness will be evaluated. 
 * 
 * @author: Biju Joseph
 */
public class ReportMandatoryField extends AbstractMutableDomainObject implements Serializable{
   
   /** The field path. */
   private String fieldPath;
   
   /** The mandatory. */
   private Mandatory mandatory;
    
    /**
     * Instantiates a new report mandatory field.
     */
    public ReportMandatoryField(){
        this(null, null);
    }
    
    /**
     * Instantiates a new report mandatory field.
     *
     * @param fieldPath the field path
     * @param mandatory the mandatory
     */
    public ReportMandatoryField(String fieldPath, Mandatory mandatory) {
        this.fieldPath = fieldPath;
        this.mandatory = mandatory;
    }

    /**
     * Gets the field path.
     *
     * @return the field path
     */
    public String getFieldPath() {
        return fieldPath;
    }

    /**
     * Sets the field path.
     *
     * @param fieldPath the new field path
     */
    public void setFieldPath(String fieldPath) {
        this.fieldPath = fieldPath;
    }

    /**
     * Gets the mandatory.
     *
     * @return the mandatory
     */
    public Mandatory getMandatory() {
        return mandatory;
    }

    /**
     * Sets the mandatory.
     *
     * @param mandatory the new mandatory
     */
    public void setMandatory(Mandatory mandatory) {
        this.mandatory = mandatory;
    }

    /**
     * Checks if is self referenced.
     *
     * @return true, if is self referenced
     */
    public boolean isSelfReferenced(){
        return isSelfReferenced(fieldPath);
    }

    /**
     * Checks if is self referenced.
     *
     * @param fieldPath the field path
     * @return true, if is self referenced
     */
    public static boolean isSelfReferenced(String fieldPath){
        if(fieldPath == null) return false;
        return fieldPath.matches("(.*\\[\\d+\\].*)*");
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString(){
        StringBuilder sb = new StringBuilder("ReportMandatoryField [")
                .append(fieldPath)
                .append(",").append(mandatory.name())
                .append("]");
        return sb.toString();
   }
}
