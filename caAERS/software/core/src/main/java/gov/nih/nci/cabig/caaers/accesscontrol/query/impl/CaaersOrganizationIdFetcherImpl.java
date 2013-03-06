/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.accesscontrol.query.impl;

import gov.nih.nci.cabig.caaers.dao.query.HQLQuery;
import gov.nih.nci.cabig.caaers.domain.UserGroupType;
import gov.nih.nci.cabig.caaers.domain.index.IndexEntry;

import java.util.*;

import com.semanticbits.security.contentfilter.IdFetcher;
import gov.nih.nci.cabig.caaers.security.SecurityUtils;
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

    private String allStudySiteOrgIdHQL = new StringBuilder("select distinct ss.organization.id from StudyOrganization so, StudySite ss ")
                      .append("where ss.study = so.study ")
                      .append("and so.organization.id in (:orgIds) ")
                      .append("and ss.retiredIndicator <> true ")
                      .append("and so.retiredIndicator <> true ")
                      .append("and so.class in ('SFS', 'SCC') ").toString();

    private String studySiteOrgIdHQL = new StringBuilder("select distinct ss.organization.id from StudyOrganization so, StudySite ss ")
                      .append("where ss.study = so.study ")
                      .append("and so.organization.id in (:orgIds) ")
                      .append("and ss.retiredIndicator <> true ")
                      .append("and so.retiredIndicator <> true ")
                      .append("and so.study.id in (:studyIds) ")
                      .append("and so.class in ('SFS', 'SCC') ").toString();

    private String allOrgIdHQL = "select o.id from Organization o";

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
        List<IndexEntry> studyEntryList = null;
        List<Integer> allOrgIds = null;

        for (IndexEntry entry : resultList) {

            if (isEmpty(entry)) continue;
            List<Integer> orgIds = entry.getEntityIds();
            if (isAllSiteOrAllStudy(orgIds)) {
                //all site access
                if (allOrgIds == null) allOrgIds = (List<Integer>) search(new HQLQuery(allOrgIdHQL));
                entry.setEntityIds(allOrgIds);
                continue;
            }

            if (!SecurityUtils.isStudyScoped(entry.getRole().getCsmName())) continue;

            //honour the Oranization hierarchy.
            if (studyEntryList == null) studyEntryList = getCaaersSecurityFacade().getAccessibleStudyIds(loginId);
            IndexEntry studyEntry = findIndexEntry(studyEntryList, entry.getRole());  // find studies associated to this role.
            if (isEmpty(studyEntry)) continue;
            List<Integer> studyIds = studyEntry.getEntityIds();
            List<Integer> additionalOrgIds = null;
            if (isAllSiteOrAllStudy(studyIds)) {
                //all studies
                HQLQuery query = new HQLQuery(allStudySiteOrgIdHQL);
                query.setParameterList("orgIds", orgIds);
                additionalOrgIds = (List<Integer>) search(query);
            } else {
                HQLQuery query = new HQLQuery(studySiteOrgIdHQL);
                query.setParameterList("orgIds", orgIds);
                query.setParameterList("studyIds", studyIds);
                additionalOrgIds = (List<Integer>) search(query);
            }

            //merge the result.
            if (additionalOrgIds != null && !additionalOrgIds.isEmpty()) {
                HashSet<Integer> set = new HashSet<Integer>(additionalOrgIds);
                set.addAll(orgIds);
                entry.setEntityIds(new ArrayList<Integer>(set));
            }

        }


        if (log.isInfoEnabled()) {
            log.info("Organization Fetcher fetched : " + String.valueOf(resultList));
        }

        return resultList;
    }

    private IndexEntry findIndexEntry(List<IndexEntry> entryList, UserGroupType role){
        for(IndexEntry e : entryList) if(e.getRole().equals(role)) return e;
        return null;
    }

}
