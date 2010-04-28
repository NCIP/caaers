package gov.nih.nci.cabig.caaers.accesscontrol.aspects;

import gov.nih.nci.cabig.caaers.accesscontrol.filter.QuerySecurityFiltererDispatcher;
import gov.nih.nci.cabig.caaers.dao.query.AbstractQuery;
import gov.nih.nci.cabig.caaers.domain.UserGroupType;
import gov.nih.nci.cabig.caaers.security.SecurityUtils;

import org.acegisecurity.Authentication;
import org.acegisecurity.context.SecurityContextHolder;

public class QuerySecurityFilterAspect  {

	private QuerySecurityFiltererDispatcher querySecurityFilterDispatcher;

	public Object applyFilter(AbstractQuery qry) throws Throwable {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if(authentication != null) {
			if (SecurityUtils.checkAuthorization(UserGroupType.caaers_super_user)) {
				return new Object[] {qry};	
			}
			String userName = SecurityUtils.getUserLoginName(authentication);
			querySecurityFilterDispatcher.filter(qry, userName);
		}
		return new Object[] {qry};			
	}
	public void setQuerySecurityFilterDispatcher(
			QuerySecurityFiltererDispatcher querySecurityFilterDispatcher) {
		this.querySecurityFilterDispatcher = querySecurityFilterDispatcher;
	}
}
