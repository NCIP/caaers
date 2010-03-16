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

@Entity
@Table(name = "MANDATORY_FIELD_DEFS")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = { @Parameter(name = "sequence", value = "seq_mandatory_field_defs_id") })
// TODO: why store field definitions for mandatory=false?
public class ReportMandatoryFieldDefinition extends AbstractMutableDomainObject implements Serializable {


	private static final long serialVersionUID = -3133021814678700402L;
	private String fieldPath;
    private RequirednessIndicator mandatory;

    private String ruleBindURL;
    private String ruleName;


    public ReportMandatoryFieldDefinition() {
    }

    public ReportMandatoryFieldDefinition(String fieldPath) {
        this(fieldPath, RequirednessIndicator.OPTIONAL);
    }

    public ReportMandatoryFieldDefinition(String fieldPath, RequirednessIndicator mandatory) {
        this.mandatory = mandatory;
        this.fieldPath = fieldPath;
    }


    public String getFieldPath() {
        return fieldPath;
    }

    public void setFieldPath(String fieldPath) {
        this.fieldPath = fieldPath;
    }
    @Type(type="requirednessType")
    @Column(name="mandatory")
    public RequirednessIndicator getMandatory() {
        return mandatory;
    }

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

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }
    
    @Transient
    public List<String> getRuleNames(){
       List<String> ruleList = new ArrayList<String>();
       String[] names = StringUtils.split(ruleName, '#');
       if(names != null){
         for(String name:names) ruleList.add(name);
       }
       return ruleList;
    }

    public void setRuleNames(List<String> rulenames){
        ruleName = StringUtils.join(rulenames, '#');
    }

    @Override
    public String toString() {
        return "ReportMandatoryFieldDefinition[" + fieldPath + ", " + mandatory.toString() + "]";
    }
}
