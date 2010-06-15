package gov.nih.nci.cabig.caaers.accesscontrol.query.impl;

import gov.nih.nci.cabig.caaers.dao.query.HQLQuery;

import java.util.List;

import com.semanticbits.security.contentfilter.IdFetcher;

/**
 *
 * Will find all the subjects that can be accessed by the given user.
 *
 * Rules:
 *
 * Reaearch Staff
 *  Site Coordinator - Subject's belonging to organization
 *
 * For all other ResearchStaff and Investigator roles
 *  Study assignment + Subject's belonging to organization
 *
 * @author Srini Akkala
 * @author Biju Joseph
 *
 */
public class CaaersParticipantIdFetcherImpl extends AbstractIdFetcher implements IdFetcher {
	
	public List fetch(String loginId){
		
		StringBuilder hql = new StringBuilder("select distinct spa.participant.id from  StudyParticipantAssignment spa , StudyIndex si , OrganizationIndex oi " )
        .append(" where si.study = spa.studySite.study ")
        .append(" and oi.organization = spa.studySite.organization ")
        .append(" and si.loginId = :loginId ");
		
        HQLQuery query = new HQLQuery(hql.toString());
        query.setParameter("loginId", loginId);
        List<Integer> resultList = (List<Integer>) search(query);
       
		return resultList;
		
	}
 
}
