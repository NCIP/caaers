/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.listener;

import gov.nih.nci.cabig.caaers.domain.Investigator;
import gov.nih.nci.cabig.caaers.event.InvestigatorModificationEvent;
import gov.nih.nci.cabig.caaers.security.CaaersSecurityFacade;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.ApplicationEvent;

/**
 * @author: Biju Joseph
 */
public class InvestigatorModificationEventListener extends AbstractEventListener {
   
	private CaaersSecurityFacade caaersSecurityFacade;
	
	@Override
    public boolean isSupported(ApplicationEvent event) {
        return event instanceof InvestigatorModificationEvent;
    }
    
    @Override
    public void preProcess(ApplicationEvent event){
    	Investigator inv = (Investigator)event.getSource();
    	String userName = inv.getLoginId();
        if(StringUtils.isNotBlank(userName)){
            caaersSecurityFacade.clearUserCache(userName);
        }

    }
    
	
	public void setCaaersSecurityFacade(CaaersSecurityFacade caaersSecurityFacade) {
		this.caaersSecurityFacade = caaersSecurityFacade;
	}
	
}
