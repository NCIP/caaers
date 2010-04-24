package gov.nih.nci.cabig.caaers.accesscontrol.query.impl;

import com.semanticbits.security.contentfilter.IdFetcher;
import gov.nih.nci.cabig.caaers.dao.query.HQLQuery;
import gov.nih.nci.cabig.caaers.domain.Investigator;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;

import java.util.Date;
import java.util.List;

/**
 * Will find the organizations that can be accessed by the user.
 *
 * Rules :
 *  Investigator         - Study Assignment + all study organizations belong to his organization for those studies
 *  AE Coordinator       - Study Assignment + all study organizations belong to his organization for those studies
 *  Subject Coordinator  - Study Assignment + all study organizations belong to his organization for those studies
 *  Data Coordinator     - Study Assignment + all study organizations belong to his organization for those studies
 *  Report Reviewer      - Study Assignment + all study organizations belong to his organization for those studies
 *
 *  Study Coordinator  - No filtering needed
 *  Site Coordinator   - No filtering needed
 *
 * @author Biju Joseph
 *
 */
public class CaaersOrganizationIdFetcherImpl extends  AbstractIdFetcher implements IdFetcher{


    /**
     * Based on the fact that for Study Coordinators and Site Coordinators, the fetchers will not be called, all users
     * at all roles can go with "Study Assignment + all study organizations belong to his organization for those studies"
     * filtering.
     * @param rs - research staff
     * @return
     */
    public List fetch(ResearchStaff rs) {

        StringBuilder hql = new StringBuilder("select distinct so.organization.id from  StudyOrganization so ,StudyPersonnel sp ")
                .append(" join sp.studyOrganization ss  " )
                .append(" join sp.siteResearchStaff srs " )
                .append(" join srs.researchStaff rs " )
                .append(" where ss.study = so.study ")
                .append(" and rs.loginId = :loginId ")
                .append(" and sp.startDate<= :stDate ")
                .append(" and (sp.endDate is null or sp.endDate >= :enDate ) " )
                .append(" and sp.retiredIndicator <> true");

        Date d = new Date();
        HQLQuery query = new HQLQuery(hql.toString());
        query.getParameterMap().put("loginId", rs.getLoginId());
        query.getParameterMap().put("stDate", d);
        query.getParameterMap().put("enDate", d);

        List<Integer> resultList = (List<Integer>) search(query);
        return resultList;
    }



    /**
     *For all investigators the below filtering rule is applied
     *  "Study Assignment + all study organizations belong to his organization for those studies"
     * @param inv - investigator
     * @return
     */
    public List fetch(Investigator inv) {

        //subjects of his site on studies he is also associated-to + subjects in sites of that are coordinated/sponsored by his site.
        StringBuilder hql = new StringBuilder("select distinct so.id from  StudyOrganization so ,StudyInvestigator sti " )
                .append(" join sti.studyOrganization ss  " )
                .append(" join sti.siteInvestigator si " )
                .append(" join si.investigator i ")
                .append(" where ss.study = so.study ")
                .append(" and i = :inv")
                .append(" and sti.startDate<= :stDate ")
                .append(" and (sti.endDate is null or sti.endDate >= :enDate ) " )
                .append(" and sti.retiredIndicator <> true");

        Date d = new Date();

        HQLQuery query = new HQLQuery(hql.toString());
        query.getParameterMap().put("inv",inv);
        query.getParameterMap().put("stDate", d);
        query.getParameterMap().put("enDate", d);


        List<Integer> resultList = (List<Integer>) search(query);
        return resultList;
    }
    
}
