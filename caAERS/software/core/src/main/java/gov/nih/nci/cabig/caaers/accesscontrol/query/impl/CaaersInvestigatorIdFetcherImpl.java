package gov.nih.nci.cabig.caaers.accesscontrol.query.impl;

import gov.nih.nci.cabig.caaers.dao.query.HQLQuery;

import java.util.List;

import com.semanticbits.security.contentfilter.IdFetcher;


public class CaaersInvestigatorIdFetcherImpl extends AbstractIdFetcher implements IdFetcher {
	
	public List fetch(String loginId){
		
		StringBuilder hql = new StringBuilder("select distinct sinv.investigator.id from  SiteInvestigator sinv , OrganizationIndex oi" )
        .append(" where oi.organization = sinv.organization ")
        .append(" and oi.loginId = :loginId ");
		
        HQLQuery query = new HQLQuery(hql.toString());
        query.setParameter("loginId", loginId);
        List<Integer> resultList = (List<Integer>) search(query);
       
		return resultList;
		
	}
 
}
