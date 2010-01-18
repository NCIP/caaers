package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.domain.report.Mandatory;
import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "CAAERS_FIELD_DEFS")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = { @Parameter(name = "sequence", value = "seq_caaers_field_defs_id") })
// TODO: why store field definitions for mandatory=false?
public class CaaersFieldDefinition extends AbstractMutableDomainObject implements Serializable {


	private static final long serialVersionUID = -3133021814678700402L;
	private String fieldPath;
    private Mandatory mandatory;
    private String tabName;

    public CaaersFieldDefinition() {
    }

    public CaaersFieldDefinition(String tabName, String fieldPath) {
        this(tabName, fieldPath, Mandatory.OPTIONAL);
    }

    public CaaersFieldDefinition(String tabName, String fieldPath, Mandatory mandatory) {
        this.mandatory = mandatory;
        this.fieldPath = fieldPath;
        this.tabName = tabName;
    }

    // @Transient
    // public boolean isMandatory() {
    // return getMandatory() != null && getMandatory();
    // }

    public String getFieldPath() {
        return fieldPath;
    }

    public void setFieldPath(String fieldPath) {
        this.fieldPath = fieldPath;
    }
    
    public String getTabName(){
    	return tabName;
    }
    
    public void setTabName(String tabName){
    	this.tabName = tabName;
    }
    
    @Type(type="mandatoryType")
    @Column(name="mandatory")
    public Mandatory getMandatory() {
        return mandatory;
    }

    public void setMandatory(Mandatory mandatory) {
        this.mandatory = mandatory;
    }

    @Override
    public String toString() {
        return "ReportMandatoryFieldDefinition[" + fieldPath + ", " + mandatory.toString() + "]";
    }
}
