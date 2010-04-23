package gov.nih.nci.cabig.caaers.accesscontrol.query.impl;

import com.semanticbits.security.contentfilter.IdFetcher;
import gov.nih.nci.cabig.caaers.dao.query.HQLQuery;
import gov.nih.nci.cabig.caaers.domain.*;
import org.apache.commons.collections.CollectionUtils;

import java.util.*;


/**
 * Will return the ID of the studies that a particular user has access to.
 *
 * @author Srini Akkala
 * @author Biju Joseph
 *
 */
public class CaaersStudyIdFetcherImpl extends AbstractIdFetcher implements IdFetcher {


    /**
     * Will list the ID of all the studies the given login have access to.
     *
     * The rules are
     *
     *Research Staff:
     *  AE Coordinator	Study assignment
     *  Subject Coordinator	Study assignment
     *  Data Coordinator	Study assignment
     *  Central Office Report Reviewer	Study assignment
     *
     *  Study Coordinator	Organization association
     *  Site Coordinator	Organization association
     *
     * 
     *Investigator  Study assignment
     *
     * @param loginId - a user id. 
     * @return  A list consisting of Study Id. 
     */
    public List fetch(String loginId) {

       User user = findUser(loginId);
       List<Integer> studyIdList = null;
       if(user instanceof ResearchStaff){
           studyIdList =  findAccessibleStudyId((ResearchStaff)user);
       }else{
           studyIdList =  findAccessibleStudyId((Investigator) user);
       }
        
       if(log.isDebugEnabled()){
         log.debug("Study IDs accessible for [ " + loginId + " ] are : " + String.valueOf(studyIdList));
       }
        
       return studyIdList;

    }

    /**
     * Will give:
     * 
     * All studies where this ResearchStaff active as a StudyPersonnel
     *  PLUS
     * All studies associated to the Organizations where he is active as a Site/Study Coordinator. 
     *
     * All studies
      * @param rs - A ResearchStaff
     * @return    - A list consisting of Study Id. 
     */
    protected List<Integer> findAccessibleStudyId(ResearchStaff rs){
     /*
      *  Study Coordinator, Site Coordinator can access all the studies that are associated to his organization. 
      *  All other roles should be associated to the study to see it.
      *
      *  A user may be associated to multiple organizations on multiple roles.
      * So for those organizations user is assigned as Study/Site Coordinator, we will first find the studies.
      * Then will add to that all the studies the user is directly associated-to. 
      */

       //determine the Sites where the research staff is a StudyCoordinator Or Site Coordinator.
       List<SiteResearchStaff> orgAccessFilterList = rs.findSiteResearchStaffByRoles(UserGroupType.caaers_study_cd.getCsmName(),
               UserGroupType.caaers_site_cd.getCsmName());

        //The Organization ID for Organization filtering.
        Set<Integer> orgFilterIdSet = findOrganizationIdFromSiteResearchStaff(orgAccessFilterList);

        Set<Integer> studyIdSet = new HashSet<Integer>();

        if(CollectionUtils.isNotEmpty(orgFilterIdSet)){
            //find all the studies associated to users organization.
            StringBuilder hql = new StringBuilder("select distinct so.study.id from StudyOrganization so where so.organization.id in (:orgIdSet)");
            HQLQuery query = new HQLQuery(hql.toString());
            query.getParameterMap().put("orgIdSet", orgFilterIdSet);

            List<Integer> resultList = (List<Integer>) search(query);
            if(CollectionUtils.isNotEmpty(resultList)) studyIdSet.addAll(resultList);
        }

        //find all the studies where he is active
        StringBuilder hql = new StringBuilder("select distinct so.study.id from StudyPersonnel sp ")
                .append("join sp.studyOrganization so ")
                .append("join sp.siteResearchStaff srs ")
                .append("join srs.researchStaff rs ")
                .append("where rs.loginId = :loginId ")
                .append("and sp.startDate<= :stDate ")
                .append("and (sp.endDate is null or sp.endDate >= :enDate ) " )
                .append("and sp.retiredIndicator <> true");

        Date d = new Date();
        HQLQuery query = new HQLQuery(hql.toString());
        query.getParameterMap().put("loginId", rs.getLoginId());
        query.getParameterMap().put("stDate", d);
        query.getParameterMap().put("enDate", d);

        List<Integer> resultList = (List<Integer>) search(query);
        
        if(studyIdSet.isEmpty()) return resultList;
        
        if(CollectionUtils.isNotEmpty(resultList)) studyIdSet.addAll(resultList);
        
        return new ArrayList<Integer>(studyIdSet);
    }

    /**
     * All studies accessible to the investigator.
     * Rule :- Should return all Studies(id), that this investigator is active.
     *  
     * @param inv
     * @return
     */
    protected List<Integer> findAccessibleStudyId(Investigator inv){
        /*
         *Investigator Study assignment filtering, i.e. can access all studies assigned to him. 
         */

        StringBuilder hql = new StringBuilder("select distinct so.study.id from StudyInvestigator sti ")
               .append("join sti.studyOrganization so ")
               .append("join sti.siteInvestigator si ")
               .append("join si.investigator i ")
               .append("where i = :inv ")
               .append("and sti.startDate<=:stDate ")
               .append("and ( sti.endDate is null or sti.endDate >= :enDate ) ")
               .append("and sti.retiredIndicator <> true");
        Date d = new Date();
        HQLQuery query = new HQLQuery(hql.toString());
        query.getParameterMap().put("inv", inv);
        query.getParameterMap().put("stDate", d);
        query.getParameterMap().put("enDate", d);

        List<Integer> resultList = (List<Integer>) search(query);
        return resultList;

    }


}
