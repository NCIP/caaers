/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.rules.common;

import com.semanticbits.rules.impl.RuleEvaluationResult;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.validation.ValidationErrors;
import org.apache.commons.lang.StringUtils;

import java.util.List;

public class AdverseEventEvaluationResult {
    
    List<Object> outputObjects;
    RuleEvaluationResult ruleEvaluationResult;
    ValidationErrors validationErrors;
    String ruleMetadata;

    public static AdverseEventEvaluationResult noRulesFound(String bindURI){
        RuleEvaluationResult ruleEvaluationResult = new RuleEvaluationResult(bindURI);
        ruleEvaluationResult.setMessage("no_rules_found");
        AdverseEventEvaluationResult result = new AdverseEventEvaluationResult();
        result.setRuleEvaluationResult(ruleEvaluationResult);
        return result;
    }

    public static AdverseEventEvaluationResult cannotDetermine(String bindURI){
        RuleEvaluationResult ruleEvaluationResult = new RuleEvaluationResult(bindURI);
        ruleEvaluationResult.setMessage(CaaersRuleUtil.CAN_NOT_DETERMINED);
        AdverseEventEvaluationResult result = new AdverseEventEvaluationResult();
        result.setRuleEvaluationResult(ruleEvaluationResult);
        return result;
    }

    public boolean isNoRulesFound(){
        return getMessage().equals("no_rules_found");
    }

    public boolean isCannotDetermine(){
        return getMessage().equals(CaaersRuleUtil.CAN_NOT_DETERMINED);
    }
    public String getMessage() {
        if(ruleEvaluationResult == null || StringUtils.isEmpty(ruleEvaluationResult.getMessage()))
        return "no_rules_found";
        return ruleEvaluationResult.getMessage();
    }

    public List<Object> getOutputObjects() {
        return outputObjects;
    }

    public void setOutputObjects(List<Object> outputObjects) {
        this.outputObjects = outputObjects;
        if(outputObjects != null) {
            for(Object o : outputObjects) {
                if(o instanceof RuleEvaluationResult) ruleEvaluationResult = (RuleEvaluationResult) o;
                if(o instanceof ValidationErrors) validationErrors = (ValidationErrors) o;
            }
        }
    }


    public ValidationErrors getValidationErrors() {
        return validationErrors;
    }

    public RuleEvaluationResult getRuleEvaluationResult() {
        return ruleEvaluationResult;
    }

    public void setRuleEvaluationResult(RuleEvaluationResult ruleEvaluationResult) {
        this.ruleEvaluationResult = ruleEvaluationResult;
    }

    public String getRuleMetadata() {
        return ruleMetadata;
    }

    public void setRuleMetadata(String ruleMetadata) {
        this.ruleMetadata = ruleMetadata;
    }

    public AdverseEventEvaluationResult populateRuleMetaData(RuleLevel l, Organization o, Study s) {
        if(l != null) ruleMetadata = "" + l.getDescription();
        if(o != null){
            String fullName = "";
            try{ 
                fullName = o.getFullName();
            }catch (Exception ignore){
            }
            ruleMetadata = ruleMetadata + ", organization : " + fullName;
        }
        if(s != null){
            String fullName = "";
            try{
                fullName = s.getPrimaryIdentifierValue();
            }catch (Exception ignore){
            }
            ruleMetadata = ruleMetadata + ", study : " + fullName;
        }
        return this;
    }

    @Override
    public String toString() {
        return "Message :" + getMessage();
    }
}
