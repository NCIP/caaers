/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain.report;

import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

 
/**
 * The Class ReportMandatoryFieldDefinition.
 */
@Entity
@Table(name = "MANDATORY_FIELD_DEFS")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = { @Parameter(name = "sequence", value = "seq_mandatory_field_defs_id") })
// TODO: why store field definitions for mandatory=false?
public class ReportMandatoryFieldDefinition extends AbstractMutableDomainObject implements Serializable {


	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -3133021814678700402L;
	
	/** The field path. */
	private String fieldPath;
    
    /** The mandatory. */
    private RequirednessIndicator mandatory;

    /** The rule bind url. */
    private String ruleBindURL;
    
    /** The rule name. */
    private String ruleName;

    /** The self referenced. */
    private boolean selfReferenced;


    /**
     * Instantiates a new report mandatory field definition.
     */
    public ReportMandatoryFieldDefinition() {
    }

    /**
     * Instantiates a new report mandatory field definition.
     *
     * @param fieldPath the field path
     */
    public ReportMandatoryFieldDefinition(String fieldPath) {
        this(fieldPath, RequirednessIndicator.OPTIONAL);
    }

    /**
     * Instantiates a new report mandatory field definition.
     *
     * @param fieldPath the field path
     * @param mandatory the mandatory
     */
    public ReportMandatoryFieldDefinition(String fieldPath, RequirednessIndicator mandatory) {
        this.mandatory = mandatory;
        this.fieldPath = fieldPath;
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
    @Type(type="requirednessType")
    @Column(name="mandatory")
    public RequirednessIndicator getMandatory() {
        return mandatory;
    }

    /**
     * Sets the mandatory.
     *
     * @param mandatory the new mandatory
     */
    public void setMandatory(RequirednessIndicator mandatory) {
        this.mandatory = mandatory;
    }

    /**
     * Represents the URL to which a rule based mandatory definition will be bound to.
     * @return   a bind URL
     */
    public String getRuleBindURL() {
        return ruleBindURL;
    }

    /**
     * Sets the rule bind url.
     *
     * @param ruleBindURL the new rule bind url
     */
    public void setRuleBindURL(String ruleBindURL) {
        this.ruleBindURL = ruleBindURL;
    }

    /**
     * Represents the name of the rule, that determine the mandatoryness of this field at runtime. 
     * @return  a rule name.
     */
    public String getRuleName() {
        return ruleName;
    }

    /**
     * Sets the rule name.
     *
     * @param ruleName the new rule name
     */
    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }
    
    /**
     * Gets the rule names.
     *
     * @return the rule names
     */
    @Transient
    public List<String> getRuleNames(){
       List<String> ruleList = new ArrayList<String>();
       String[] names = StringUtils.split(ruleName, '#');
       if(names != null){
         for(String name:names) ruleList.add(name);
       }
       return ruleList;
    }

    /**
     * Sets the rule names.
     *
     * @param rulenames the new rule names
     */
    public void setRuleNames(List<String> rulenames){
        ruleName = StringUtils.join(rulenames, '#');
    }

    /**
     * Checks if is self referenced.
     *
     * @return true, if is self referenced
     */
    public boolean isSelfReferenced() {
        return selfReferenced;
    }

    /**
     * Sets the self referenced.
     *
     * @param selfReferenced the new self referenced
     */
    public void setSelfReferenced(boolean selfReferenced) {
        this.selfReferenced = selfReferenced;
    }

    /**
     * Checks if is rule based.
     *
     * @return true, if is rule based
     */
    @Transient
    public boolean isRuleBased(){
        return getRuleName() != null;
    }


    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ReportMandatoryFieldDefinition[" + fieldPath + ", " + mandatory.toString() + "]";
    }
}
