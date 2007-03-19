/**
 * 
 */
package gov.nih.nci.cabig.caaers.web.security;

import gov.nih.nci.cabig.caaers.web.chrome.Task;

/**
 * 
 * @author <a href="mailto:joshua.phillips@semanticbits.com">Joshua Phillips</a>
 * 
 */
public class TaskPrivilegeAndObjectIdGenerator extends AbstractPrivilegeAndObjectIdGenerator {
	
	protected String getKeyValue(Object object){
		return ((Task)object).getUrl();
	}
	
	protected boolean supports(Object object){
		return object instanceof Task;
	}

}
