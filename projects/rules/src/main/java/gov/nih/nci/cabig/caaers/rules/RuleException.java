package gov.nih.nci.cabig.caaers.rules;

import gov.nih.nci.cabig.caaers.CaaersSystemException;

/**
 * @author un-ascribed
 */
public class RuleException extends CaaersSystemException {
	
	
    public RuleException(String message, Throwable cause) {
        this("RULE-001",message, cause);
    }

    public RuleException(Throwable cause) {
        this("RULE-001", "Rule Exception",cause);
    }
    
    public RuleException(String code, String message){
    	this(code, message, null);
    }
    
    public RuleException(String code, String message, Throwable cause){
    	super(code, message, cause);
    }
    
}