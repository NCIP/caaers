/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.listener;

import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.caaers.event.ResearchStaffModificationEvent;
import gov.nih.nci.cabig.caaers.security.CaaersSecurityFacade;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.ApplicationEvent;

/**
 * @author: Biju Joseph
 */
public class ResearchStaffModificationEventListener extends AbstractEventListener {
    
	private CaaersSecurityFacade caaersSecurityFacade;

	@Override
    public boolean isSupported(ApplicationEvent event) {
        return event instanceof ResearchStaffModificationEvent;
    }
    
    @Override
    public void preProcess(ApplicationEvent event){
        super.preProcess(event);
    	ResearchStaff rs = (ResearchStaff)event.getSource();
    	String userName = rs.getLoginId();
        if(StringUtils.isNotBlank(userName)){
           caaersSecurityFacade.clearUserCache(userName);
        }

    }
    
	
	public void setCaaersSecurityFacade(CaaersSecurityFacade caaersSecurityFacade) {
		this.caaersSecurityFacade = caaersSecurityFacade;
	}
}
