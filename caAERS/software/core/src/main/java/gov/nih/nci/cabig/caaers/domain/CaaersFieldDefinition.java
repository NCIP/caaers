package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.domain.report.Mandatory;
import gov.nih.nci.cabig.caaers.domain.report.RequirednessIndicator;
import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

 
/**
 * The Class CaaersFieldDefinition.
 */
@Entity
@Table(name = "CAAERS_FIELD_DEFS")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = { @Parameter(name = "sequence", value = "seq_caaers_field_defs_id") })
// TODO: why store field definitions for mandatory=false?
public class CaaersFieldDefinition extends AbstractMutableDomainObject implements Serializable {


	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -3133021814678700402L;
	
	/** The field path. */
	private String fieldPath;
    
    /** The mandatory. */
    private Mandatory mandatory;
    
    /** The tab name. */
    private String tabName;

    /**
     * Instantiates a new caaers field definition.
     */
    public CaaersFieldDefinition() {
    }

    /**
     * Instantiates a new caaers field definition.
     *
     * @param tabName the tab name
     * @param fieldPath the field path
     */
    public CaaersFieldDefinition(String tabName, String fieldPath) {
        this(tabName, fieldPath, Mandatory.OPTIONAL);
    }

    /**
     * Instantiates a new caaers field definition.
     *
     * @param tabName the tab name
     * @param fieldPath the field path
     * @param mandatory the mandatory
     */
    public CaaersFieldDefinition(String tabName, String fieldPath, Mandatory mandatory) {
        this.mandatory = mandatory;
        this.fieldPath = fieldPath;
        this.tabName = tabName;
    }

    // @Transient
    // public boolean isMandatory() {
    // return getMandatory() != null && getMandatory();
    // }

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
     * Gets the tab name.
     *
     * @return the tab name
     */
    public String getTabName(){
    	return tabName;
    }
    
    /**
     * Sets the tab name.
     *
     * @param tabName the new tab name
     */
    public void setTabName(String tabName){
    	this.tabName = tabName;
    }
    
    /**
     * Gets the mandatory.
     *
     * @return the mandatory
     */
    @Type(type="mandatoryType")
    @Column(name="mandatory")
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

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ReportMandatoryFieldDefinition[" + fieldPath + ", " + mandatory.toString() + "]";
    }
}
