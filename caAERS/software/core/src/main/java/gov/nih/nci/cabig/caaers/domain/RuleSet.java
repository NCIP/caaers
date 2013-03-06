/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.rules.common.CaaersRuleUtil;
import gov.nih.nci.cabig.caaers.rules.common.RuleLevel;
import gov.nih.nci.cabig.caaers.rules.common.RuleType;
import gov.nih.nci.cabig.caaers.validation.ValidationErrors;
import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * A rule-set. In general rule scan be configured at 4 levels
 *   a. Sponsor
 *   b. Institution
 *   c. Sponsor &amps; Study
 *   d. Institution &amps; Study
 *   
 * Also the system supports 3 kinds of rules
 *   a. SAE reporting rules
 *   b. Mandatory section rules
 *   c. Field validation rules
 */
@Entity
@Table(name = "rule_sets")//rule_sets
@GenericGenerator(name = "id-generator", strategy = "native", parameters = { @Parameter(name = "sequence", value = "seq_rule_sets_id") })
public class RuleSet extends AbstractIdentifiableDomainObject implements Serializable  {

    public static final String STATUS_ENABLED= "Enabled";
    public static final String STATUS_DISABLED = "Not Enabled";

    private String ruleTypeName;
    private String ruleLevelName;
    
    private Organization organization;
    private Study study;
    
    private String status;

    private String ruleBindURI;
    
    private int deployedVersion = 0;
    private int stagedVersion = 0;


    public String getRuleTypeName() {
        return ruleTypeName;
    }

    public void setRuleTypeName(String ruleTypeName) {
        this.ruleTypeName = ruleTypeName;
    }

    public String getRuleLevelName() {
        return ruleLevelName;
    }

    public void setRuleLevelName(String ruleLevelName) {
        this.ruleLevelName = ruleLevelName;
    }

    @ManyToOne
    @JoinColumn(name = "organization_id")
    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    @ManyToOne
    @JoinColumn(name = "study_id")
    public Study getStudy() {
        return study;
    }

    public void setStudy(Study study) {
        this.study = study;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRuleBindURI() {
        return ruleBindURI;
    }

    public void setRuleBindURI(String ruleBindURI) {
        this.ruleBindURI = ruleBindURI;
    }

    public int getDeployedVersion() {
        return deployedVersion;
    }

    public void setDeployedVersion(int deployedVersion) {
        this.deployedVersion = deployedVersion;
    }

    public int getStagedVersion() {
        return stagedVersion;
    }

    public void setStagedVersion(int stagedVersion) {
        this.stagedVersion = stagedVersion;
    }

    @Transient
    public RuleType getRuleType(){
        if(StringUtils.isEmpty(getRuleTypeName())) return null;
        return RuleType.getByName(getRuleTypeName());
    }

    public void setRuleType(RuleType ruleType){
        setRuleTypeName(ruleType.getName());
    }
    
    @Transient
    public RuleLevel getRuleLevel(){
        if(StringUtils.isEmpty(getRuleLevelName())) return null;
        return RuleLevel.getByName(getRuleLevelName());
    }

    public void setRuleLevel(RuleLevel ruleLevel){
        if(ruleLevel != null)
            setRuleLevelName(ruleLevel.getName());
        else
            setRuleLevelName(null);
    }

    @Transient
    public Organization getSponsor(){
        return getOrganization();
    }

    public void setSponsor(Organization org){
        setOrganization(org);
    }


    @Transient
    public Organization getInstitution(){
        return getOrganization();
    }

    public void setInstitution(Organization org){
        setOrganization(org);
    }

    @Transient
    public boolean isEnabled(){
        return StringUtils.equals(STATUS_ENABLED, getStatus());
    }

    public ValidationErrors validate(){
        ValidationErrors validationErrors = new ValidationErrors();
        if(getRuleBindURI() == null) validationErrors.addValidationError("RUL_023", "Missing bind URI");
        if(getStatus() == null) validationErrors.addValidationError("RUL_024", "Missing rule status");
        if(getRuleTypeName() == null) validationErrors.addValidationError("RUL_025", "Missing rule type");
        if(( getRuleType() == RuleType.REPORT_SCHEDULING_RULES || getRuleType() == RuleType.MANDATORY_SECTIONS_RULES )&&  getRuleLevelName() == null) validationErrors.addValidationError("RUL_014" , "Missing rule level");
        if(getRuleLevelName() != null){
            RuleLevel level = getRuleLevel();
            if((level.isSponsorBased() || level.isInstitutionBased()) && getOrganization() == null ) validationErrors.addValidationError("RUL_011", "Missing sponsor or institution");
            if((level.isStudyBased()) && getStudy() == null ) validationErrors.addValidationError("RUL_012", "Missing study");
        }
        if(getRuleType() == RuleType.SAFETY_SIGNALLING_RULES && getStudy() == null) validationErrors.addValidationError("RUL_012", "Missing study");
        
        return validationErrors;
    }
    
    public String shortReadableName(){
        StringBuilder sbFileName = new StringBuilder();
        sbFileName.append(CaaersRuleUtil.getStringWithoutSpaces(getRuleType().getName()));
        if(getRuleLevel() != null){
            sbFileName.append("_")
                    .append(CaaersRuleUtil.getStringWithoutSpaces(getRuleLevel().getName()));
        }
        if(getOrganization() != null){
            sbFileName.append("_org_")
                    .append(CaaersRuleUtil.getStringWithoutSpaces(getOrganization().getNciInstituteCode()));
        }
        if(getStudy() != null){
            sbFileName.append("_study_")
                    .append(CaaersRuleUtil.getStringWithoutSpaces(getStudy().getPrimaryIdentifierValue()));
        }
        return sbFileName.toString();
    }

    @Override
    public String toString() {
        return "RuleSet{" +
                "ruleTypeName='" + ruleTypeName + '\'' +
                ", ruleLevelName='" + ruleLevelName + '\'' +
                ", organization=" + organization +
                ", study=" + study +
                ", status='" + status + '\'' +
                ", ruleBindURI='" + ruleBindURI + '\'' +
                '}';
    }
}
