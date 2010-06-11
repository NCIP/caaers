package gov.nih.nci.cabig.caaers.accesscontrol.query.impl;

import gov.nih.nci.cabig.caaers.dao.query.HQLQuery;

import java.util.List;

import com.semanticbits.security.contentfilter.IdFetcher;


public class CaaersResearchStaffIdFetcherImpl extends AbstractIdFetcher implements IdFetcher {
	
	public List fetch(String loginId){
		
		StringBuilder hql = new StringBuilder("select distinct srs.researchStaff.id from  SiteResearchStaff srs , OrganizationIndex oi" )
        .append(" where oi.organization = srs.organization ")
        .append(" and oi.loginId = :loginId ");
		
        HQLQuery query = new HQLQuery(hql.toString());
        query.setParameter("loginId", loginId);
        List<Integer> resultList = (List<Integer>) search(query);
       
		return resultList;
		
	}
 
}
