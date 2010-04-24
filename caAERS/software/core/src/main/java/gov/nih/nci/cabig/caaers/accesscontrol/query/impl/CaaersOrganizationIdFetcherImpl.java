package gov.nih.nci.cabig.caaers.accesscontrol.query.impl;

import gov.nih.nci.cabig.caaers.accesscontrol.BaseSecurityFilterer;
import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.dao.StudySiteDao;
import gov.nih.nci.cabig.caaers.dao.UserDao;
import gov.nih.nci.cabig.caaers.dao.query.HQLQuery;
import gov.nih.nci.cabig.caaers.domain.Organization;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.semanticbits.security.contentfilter.IdFetcher;
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
     * @param loginId - username
     * @return
     */
    public List fetch(String loginId) {

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
        query.getParameterMap().put("loginId", "sponsor");
        query.getParameterMap().put("stDate", d);
        query.getParameterMap().put("enDate", d);

        List<Integer> resultList = (List<Integer>) search(query);

       if(log.isDebugEnabled()){
         log.debug("Organization IDs accessible for [ " + loginId + " ] are : " + String.valueOf(resultList));
       }
        return resultList;
    }

    
}
