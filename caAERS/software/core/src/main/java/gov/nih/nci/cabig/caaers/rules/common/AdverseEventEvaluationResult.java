package gov.nih.nci.cabig.caaers.rules.common;

import com.semanticbits.rules.impl.RuleEvaluationResult;
import gov.nih.nci.cabig.caaers.validation.ValidationErrors;

import java.util.List;

public class AdverseEventEvaluationResult {
    
    List<Object> outputObjects;
    RuleEvaluationResult ruleEvaluationResult;
    ValidationErrors validationErrors;



    public String getMessage() {
        if(ruleEvaluationResult != null) return ruleEvaluationResult.getMessage();
        return "no_rules_found";
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

    @Override
    public String toString() {
        return "Message :" + getMessage();
    }
}
