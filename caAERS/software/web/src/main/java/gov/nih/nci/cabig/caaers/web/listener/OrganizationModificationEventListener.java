package gov.nih.nci.cabig.caaers.web.listener;

import gov.nih.nci.cabig.caaers.event.OrganizationModificationEvent;
import gov.nih.nci.cabig.caaers.security.CaaersSecurityFacade;
import gov.nih.nci.cabig.caaers.security.SecurityUtils;

import org.springframework.context.ApplicationEvent;

public class OrganizationModificationEventListener extends AbstractEventListener {
	
	private CaaersSecurityFacade caaersSecurityFacade;
	
	@Override
	public boolean isSupported(ApplicationEvent event) {
		return event instanceof OrganizationModificationEvent;
	}
	
    @Override
    public void preProcess(ApplicationEvent event) {
    	String userName = SecurityUtils.getUserLoginName(getAuthentication(event));
        caaersSecurityFacade.clearUserCache(userName);
    }

	public void setCaaersSecurityFacade(CaaersSecurityFacade caaersSecurityFacade) {
		this.caaersSecurityFacade = caaersSecurityFacade;
	}

}
