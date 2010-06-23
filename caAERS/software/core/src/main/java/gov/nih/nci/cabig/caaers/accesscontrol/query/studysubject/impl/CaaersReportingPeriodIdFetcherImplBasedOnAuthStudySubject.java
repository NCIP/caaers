package gov.nih.nci.cabig.caaers.accesscontrol.query.studysubject.impl;

import gov.nih.nci.cabig.caaers.accesscontrol.query.impl.AbstractIdFetcher;
import gov.nih.nci.cabig.caaers.dao.query.HQLQuery;
import gov.nih.nci.cabig.caaers.domain.Investigator;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;

import java.util.List;

import com.semanticbits.security.contentfilter.IdFetcher;

/**
 * Will return the ID of Reporting period which the logged-in user have access.
 * This implementation doesnt check for user or access permissions , This implementation relies on authorized 
 * studies and subjects which are loaded prior to this index . 
 * @author Biju Joseph
 * @author Srini Akkala
 */
public class CaaersReportingPeriodIdFetcherImplBasedOnAuthStudySubject extends AbstractIdFetcher implements IdFetcher {

    public List<Integer> fetch(ResearchStaff rs){
         return fetchIds(rs.getLoginId());
    }

    public List<Integer> fetch(Investigator inv){
         return fetchIds(inv.getLoginId());
    }
 
    /**
     * The RP that are belonging to subjects the user can access, can be accessed by the user.
     * studies and subjects cab be accessed by this user are already loaded into StudyIndex and ParticipantIndex
     * So , This query joins with those indexes . 
     * 
     * @return
     */
    public List<Integer> fetchIds(String loginId){
    	StringBuilder hql = new StringBuilder("select distinct rp.id from  ParticipantIndex pi " )
    	.append(" join pi.participant p ")
    	.append(" join p.assignments spa ")
    	.append(" join spa.reportingPeriods rp ")
        .append(" where pi.loginId = :loginId ");
		
		HQLQuery query = new HQLQuery(hql.toString());
		query.getParameterMap().put("loginId", loginId);

    	List<Integer> resultList = (List<Integer>) search(query);
        return resultList;
    }

    
}

