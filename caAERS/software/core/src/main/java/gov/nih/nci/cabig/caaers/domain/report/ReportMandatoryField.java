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
   
   private String fieldPath;
   private Mandatory mandatory;
    public ReportMandatoryField(){
        this(null, null);
    }
    public ReportMandatoryField(String fieldPath, Mandatory mandatory) {
        this.fieldPath = fieldPath;
        this.mandatory = mandatory;
    }

    public String getFieldPath() {
        return fieldPath;
    }

    public void setFieldPath(String fieldPath) {
        this.fieldPath = fieldPath;
    }

    public Mandatory getMandatory() {
        return mandatory;
    }

    public void setMandatory(Mandatory mandatory) {
        this.mandatory = mandatory;
    }

    public boolean isSelfReferenced(){
        return isSelfReferenced(fieldPath);
    }

    public static boolean isSelfReferenced(String fieldPath){
        if(fieldPath == null) return false;
        return fieldPath.matches("(.*\\[\\d+\\].*)*");
    }

    public String toString(){
        StringBuilder sb = new StringBuilder("ReportMandatoryField [")
                .append(fieldPath)
                .append(",").append(mandatory.name())
                .append("]");
        return sb.toString();
   }
}
