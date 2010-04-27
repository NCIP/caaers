package gov.nih.nci.cabig.caaers.accesscontrol.aspects;

import org.acegisecurity.Authentication;
import org.acegisecurity.context.SecurityContextHolder;

import gov.nih.nci.cabig.caaers.accesscontrol.BaseSecurityFilterer;
import gov.nih.nci.cabig.caaers.accesscontrol.filter.QuerySecurityFiltererDispatcher;
import gov.nih.nci.cabig.caaers.dao.query.AbstractQuery;
import gov.nih.nci.cabig.caaers.security.SecurityUtils;

public class QuerySecurityFilterAspect extends BaseSecurityFilterer {

	private QuerySecurityFiltererDispatcher querySecurityFilterDispatcher;

	public Object applyFilter(AbstractQuery qry) throws Throwable {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication != null) {
			
			if (filteringNotRequired(authentication)) {
				return new Object[] {qry};	
			}
			String userName = SecurityUtils.getUserLoginName(authentication);
			querySecurityFilterDispatcher.filter(qry, userName);
		}
		
		//
		
		return new Object[] {qry};	
		
	}
	public void setQuerySecurityFilterDispatcher(
			QuerySecurityFiltererDispatcher querySecurityFilterDispatcher) {
		this.querySecurityFilterDispatcher = querySecurityFilterDispatcher;
	}
}
