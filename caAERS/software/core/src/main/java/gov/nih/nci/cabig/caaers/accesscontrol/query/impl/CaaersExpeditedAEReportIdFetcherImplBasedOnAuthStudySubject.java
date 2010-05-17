package gov.nih.nci.cabig.caaers.accesscontrol.query.impl;

import gov.nih.nci.cabig.caaers.dao.query.HQLQuery;
import gov.nih.nci.cabig.caaers.domain.Investigator;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;

import java.util.List;

import com.semanticbits.security.contentfilter.IdFetcher;

/**
 * Will return the ID of ExpeditedAdverseEventReport which the logged-in user have access.
 * This implementation doesnt check for user or access permissions , This implementation relies on authorized 
 * studies and subjects which are loaded prior to this index . 
 * @author Biju Joseph
 * @author Srini Akkala
 */
public class CaaersExpeditedAEReportIdFetcherImplBasedOnAuthStudySubject extends AbstractIdFetcher implements IdFetcher {

    public List<Integer> fetch(ResearchStaff rs){
         return fetchIds();
    }

    public List<Integer> fetch(Investigator inv){
         return fetchIds();
    }
 
    /**
     * The reports that are belonging to subjects the user can access, can be accessed by the user.
     * studies and subjects cab be accessed by this user are already loaded into StudyIndex and ParticipantIndex
     * So , This query joins with those indexes . 
     * 
     * @return
     */
    private List<Integer> fetchIds(){
    	StringBuilder hql = new StringBuilder("select distinct r.id from  StudyOrganization so ,StudyParticipantAssignment a " )
        .append(" ,StudyIndex si , ParticipantIndex pi ")
    	.append(" join a.reportingPeriods rp ")
        .append(" join rp.aeReports r ")
        .append(" join a.studySite ss  " )
        .append(" where ss.study = so.study ")
        .append(" and ss.study = si.study ")
        .append(" and pi.participant = a.participant");
		
		HQLQuery query = new HQLQuery(hql.toString());

    	List<Integer> resultList = (List<Integer>) search(query);
        return resultList;
    }

    
}
