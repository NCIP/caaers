package gov.nih.nci.cabig.caaers.accesscontrol.query.impl;

import com.semanticbits.security.contentfilter.IdFetcher;
import gov.nih.nci.cabig.caaers.dao.query.HQLQuery;
import gov.nih.nci.cabig.caaers.domain.Investigator;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.caaers.domain.SiteResearchStaff;
import gov.nih.nci.cabig.caaers.domain.UserGroupType;
import org.apache.commons.collections.CollectionUtils;

import java.util.*;

/**
 * Will return the ID of AdverseEventReportingPeriod which the logged-in user have access.
 * @author Biju Joseph
 * @author Srini Akkala
 */
public class CaaersReportingPeriodIdFetcherImpl extends AbstractIdFetcher implements IdFetcher {



    /**
     * The courses that are belonging to subjects the user can access, can be accessed by the user.
     *
     * Site Coordinator has access to subjects
     *  -  registered on study-sites which are associated to his organizations
     *  PLUS
     *  - registered on study-sites of those studies that are
     *    sponsored or coordinated by his organizations.
     *
     * All the others can access subjects
     *   - that are associated to the study-sites where he is associated
     *   PLUS
     *   - that are associated to study-sites of those studies where he is associated at coordinating-site or sponsor-site
     *
     * @param rs - A research staff.
     * @return
     */
    public List<Integer> fetch(ResearchStaff rs){


        //determine the Sites where the research staff is a  Site Coordinator.
        List<SiteResearchStaff> orgAccessFilterList = rs.findSiteResearchStaffByRoles(UserGroupType.caaers_site_cd.getCsmName());

         //The Organization ID for Organization filtering.
        Set<Integer> orgFilterIdSet = findOrganizationIdFromSiteResearchStaff(orgAccessFilterList);
        Set<Integer> participantIdSet = new HashSet<Integer>();

        if(CollectionUtils.isNotEmpty(orgFilterIdSet)){
            //subjects at his site + subjects at sites that are coordinated/sponsored.
            StringBuilder hql = new StringBuilder("select distinct rp.id from  StudyOrganization so ,StudyParticipantAssignment a ")
                    .append(" join a.reportingPeriods rp ")
                    .append(" join a.studySite ss where ss.study = so.study ")
                    .append(" and so.organization.id in (:orgIdSet) ");

            HQLQuery query = new HQLQuery(hql.toString());
            query.getParameterMap().put("orgIdSet", orgFilterIdSet);

            List<Integer> resultList = (List<Integer>) search(query);
            if(CollectionUtils.isNotEmpty(resultList)) participantIdSet.addAll(resultList);
        }

        //subjects of his site on studies he is also associated-to + subjects in sites of that are coordinated/sponsored by his site.
        StringBuilder hql = new StringBuilder("select distinct rp.id from  StudyOrganization so ,StudyParticipantAssignment a " )
                .append(" join a.reportingPeriods rp ")
                .append(" join a.studySite ss  " )
                .append(" join so.studyPersonnelsInternal sp " )
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

         if(participantIdSet.isEmpty()) return resultList;
         if(CollectionUtils.isNotEmpty(resultList)) participantIdSet.addAll(resultList);

         return new ArrayList<Integer>(participantIdSet);
    }



    /**
     * The courses that are belonging to subjects the user can access, can be accessed by the user.
     *
     * For investigators,
     *   - that are associated to the study-sites where he is associated
     *   PLUS
     *   - that are associated to study-sites of those studies where he is associated at coordinating-site or sponsor-site
     *
     * @param inv - Investigator.
     * @return
     */
    public List<Integer> fetch(Investigator inv){

        //subjects of his site on studies he is also associated-to + subjects in sites of that are coordinated/sponsored by his site.
        StringBuilder hql = new StringBuilder("select distinct rp.id from  StudyOrganization so ,StudyParticipantAssignment a " )
                .append(" join rp.aeReports r ")
                .append(" join a.studySite ss  " )
                .append(" join so.studyInvestigatorsInternal sti " )
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