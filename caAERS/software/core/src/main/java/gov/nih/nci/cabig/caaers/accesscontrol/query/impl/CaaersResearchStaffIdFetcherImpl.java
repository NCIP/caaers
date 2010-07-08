package gov.nih.nci.cabig.caaers.accesscontrol.query.impl;

import gov.nih.nci.cabig.caaers.dao.query.HQLQuery;
import gov.nih.nci.cabig.caaers.security.CaaersSecurityFacadeImpl;

import java.util.ArrayList;
import java.util.List;

import com.semanticbits.security.contentfilter.IdFetcher;


public class CaaersResearchStaffIdFetcherImpl extends AbstractIdFetcher implements IdFetcher {
	
	public List fetch(String loginId){
		// get all organizations user has access to .. 
		List<Integer> organizationIds = getAccesibleOrganizationsIncludingStudySites(loginId);
		List resultList = new ArrayList();
		StringBuilder hql = new StringBuilder("select distinct srs.researchStaff.id from  SiteResearchStaff srs" );
        hql.append(" where srs.organization.id in (:organizationIds) ");
        
        if (organizationIds.size() > 0) {
        	if (organizationIds.size() == 1 && organizationIds.get(0) == CaaersSecurityFacadeImpl.ALL_IDS_FABRICATED_ID) {
	        	HQLQuery query = new HQLQuery("select distinct srs.researchStaff.id from  SiteResearchStaff srs");
	        	resultList = (List<Integer>) search(query);
        	} else {
        		HQLQuery query = new HQLQuery(hql.toString());
	        	query.setParameterList("organizationIds", organizationIds);
	        	resultList = (List<Integer>) search(query);
        	}
        } 
       
		return resultList;
	}
 
}
