package gov.nih.nci.cabig.caaers.accesscontrol.query.impl;

import gov.nih.nci.cabig.caaers.dao.query.HQLQuery;
import gov.nih.nci.cabig.caaers.security.CaaersSecurityFacadeImpl;

import java.util.ArrayList;
import java.util.List;

import com.semanticbits.security.contentfilter.IdFetcher;


public class CaaersInvestigatorIdFetcherImpl extends AbstractIdFetcher implements IdFetcher {
	
	public List fetch(String loginId){
		// get all organizations user has access to .. 
		List<Integer> organizationIds = getAccesibleOrganizationsIncludingStudySites(loginId);
		List resultList = new ArrayList();
		StringBuilder hql = new StringBuilder("select distinct sinv.investigator.id from  SiteInvestigator sinv" );
        hql.append(" where sinv.organization.id in (:organizationIds) ");
        if (organizationIds.size() > 0) {
        	if (organizationIds.size() == 1 && organizationIds.get(0) == CaaersSecurityFacadeImpl.ALL_IDS_FABRICATED_ID) {
	        	HQLQuery query = new HQLQuery("select distinct sinv.investigator.id from  SiteInvestigator sinv" );
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
