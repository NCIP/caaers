package gov.nih.nci.cabig.caaers.accesscontrol.query.impl;

import gov.nih.nci.cabig.caaers.dao.query.HQLQuery;
import gov.nih.nci.cabig.caaers.domain.index.IndexEntry;

import java.util.*;

import com.semanticbits.security.contentfilter.IdFetcher;
import gov.nih.nci.cabig.caaers.security.SecurityUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Will find the organizations that can be accessed by the user.
 *
 * In addition to what CSM has provided, organization hierarchy for Coordinating center and Sponsor should be honoured. 
 * @author Biju Joseph
 *
 */
public class CaaersOrganizationIdFetcherImpl extends  AbstractIdFetcher implements IdFetcher{

    protected final Log log = LogFactory.getLog(CaaersOrganizationIdFetcherImpl.class);


    /**
     * Will figure out which organization the logged-in user can access.
     *
     * Note:- Study scoped roles of a ResearchStaff may have to honour the organization hierarchy rule.
     * i.e. A research staff belonging to Coordinating center can work under the same capacity, on the sites.
     *
     * @param loginId - username
     * @return List consisting of IndexEntry objects
     */
    @Override
    public List fetch(String loginId) {
        
       
        //find all the accessible organization ids
        List<IndexEntry> resultList = getCaaersSecurityFacade().getAccessibleOrganizationIds(loginId);
        if(resultList != null && !resultList.isEmpty()){
            List<Integer> allOrgIds = null;

            for(IndexEntry entry : resultList){
                List<Integer> orgIds = entry.getEntityIds();
                if(orgIds == null || orgIds.isEmpty()) continue; // no access
                if(orgIds.size() == 1 && orgIds.get(0).equals(Integer.MIN_VALUE)){ //all sites
                   if(allOrgIds == null){
                       HQLQuery query = new HQLQuery("select o.id from Organization o");
                       allOrgIds = (List<Integer>) search(query);
                   }
                   entry.setEntityIds(allOrgIds);
                   continue; //all site access
                }

                
               //for study scoped roles, need to honour organization hierarchy.
               if(SecurityUtils.isStudyScoped(entry.getRole().getCsmName())){
                   //can access all the study sites coordinated by his organizations, where he/she is active
                   StringBuilder hql = new StringBuilder("select distinct ss.organization.id from StudyOrganization so, StudySite ss ")
                      .append("join so.studyPersonnelsInternal sp " )
                      .append("join sp.siteResearchStaff srs ")
                      .append("join srs.researchStaff rs " )
                      .append("where ss.study = so.study ")
                      .append("and so.organization.id in (:orgIds) ")
                      .append("and rs.caaersUser.loginName = :loginId  ")
                      .append("and (sp.endDate is null or sp.endDate >= :endDate) ")
                      .append("and sp.startDate <= :startDate  ")
                      .append("and sp.retiredIndicator <> true ")
                      .append("and so.class in ('SFS', 'SCC') ");

                   Date today = new Date();
                   HQLQuery query = new HQLQuery(hql.toString());
                   query.setParameterList("orgIds", orgIds);
                   query.setParameter("startDate", today);
                   query.setParameter("endDate", today);
                   query.setParameter("loginId", loginId);

                   List siteOrgIds = search(query);
                   if(CollectionUtils.isNotEmpty(siteOrgIds)){
                       //merge both the organization ids.
                       HashSet<Integer> set = new HashSet<Integer>(siteOrgIds);
                       set.addAll(orgIds);
                       entry.setEntityIds(new ArrayList<Integer>(set));
                   }
               }

            }
        }


        if(log.isInfoEnabled()){
          log.info("Organization Fetcher fetched : " + String.valueOf(resultList) );  
        }

        return resultList;
    }

}
