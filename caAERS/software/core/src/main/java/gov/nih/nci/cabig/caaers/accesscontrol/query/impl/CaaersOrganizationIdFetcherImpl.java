package gov.nih.nci.cabig.caaers.accesscontrol.query.impl;

import gov.nih.nci.cabig.caaers.dao.query.HQLQuery;
import gov.nih.nci.cabig.caaers.dao.query.NativeSQLQuery;
import gov.nih.nci.cabig.caaers.domain.UserGroupType;
import gov.nih.nci.cabig.caaers.domain.index.IndexEntry;

import java.util.*;

import com.semanticbits.security.contentfilter.IdFetcher;
import gov.nih.nci.cabig.caaers.security.SecurityUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.type.StandardBasicTypes;

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
        List<IndexEntry> organizationIndexEntryList = getCaaersSecurityFacade().getAccessibleOrganizationIds(loginId);
        if(log.isInfoEnabled()) log.info(" Organization Index entries obtained from CSM {" + String.valueOf(organizationIndexEntryList) + "}");
        if(CollectionUtils.isEmpty(organizationIndexEntryList)) return organizationIndexEntryList;

        Map<Integer, IndexEntry> organizationIndexEntryMap = toEntityBasedIndex(organizationIndexEntryList);
        IndexEntry allSiteEntry = organizationIndexEntryMap.get(Integer.MIN_VALUE);
        if(allSiteEntry != null){
            //extrapolate  - all site
            List<Integer> allOrgIds = (List<Integer>) search(new HQLQuery("select o.id from Organization o"));
            if(CollectionUtils.isNotEmpty(allOrgIds)){
                for(Integer orgId : allOrgIds){
                    updateIndexEntry(organizationIndexEntryMap, orgId, allSiteEntry.getRoles());
                }
            }
            organizationIndexEntryMap.remove(allSiteEntry.getEntityId());
        }


        //now pull out all accessible study organization details from database.
        NativeSQLQuery studySiteQuery = new NativeSQLQuery("select so.study_id as study_id , so.site_id as site_id, so.type as type " +
                "from study_organizations so " +
                "join studies s on s.id = so.study_id " +
                "join study_index si on s.id = si.study_id where si.login_id = :loginId and so.retired_indicator = :ri");
        studySiteQuery.setScalar("study_id", StandardBasicTypes.INTEGER);
        studySiteQuery.setScalar("site_id", StandardBasicTypes.INTEGER);
        studySiteQuery.setScalar("type", StandardBasicTypes.STRING);
        studySiteQuery.setParameter("ri", false);
        studySiteQuery.setParameter("loginId", loginId);

        List<Object[]> studyOrgDataList = (List<Object[]>) search(studySiteQuery);

        if(CollectionUtils.isEmpty(studyOrgDataList)) return new ArrayList(organizationIndexEntryMap.values());


        //create and map with the study organization data - for easy retrieval
        Map<Integer, List<Integer>> studyStudySiteMap = new HashMap<Integer, List<Integer>>();    //stores study - sites
        Map<Integer,  List<Integer>> studySponsorStudyMap = new HashMap<Integer, List<Integer>>();   //stores sponsors-studyid
        for(Object[] row : studyOrgDataList){
            List<Integer> sitesList = studyStudySiteMap.get(row[0]);
            if(sitesList == null) {
                sitesList = new ArrayList<Integer>();
                studyStudySiteMap.put((Integer)row[0], sitesList);
            }
            if(row[1] != null && StringUtils.equals(String.valueOf(row[2]), "SST") ){
                sitesList.add((Integer) row[1]);
            }


            if(row[1] != null && ( StringUtils.equals(String.valueOf(row[2]), "SFS") || StringUtils.equals(String.valueOf(row[2]), "SCC"))){
                List<Integer> studyList = studySponsorStudyMap.get((Integer)row[1]);
                if(studyList == null){
                    studyList = new ArrayList<Integer>();
                    studySponsorStudyMap.put((Integer) row[1], studyList);
                }
                studyList.add((Integer) row[0]);
            }
        }
        
        //loop through all the sponsors in the DB
        for(Integer sponsorOrgId : studySponsorStudyMap.keySet()){
            //is my login associated to sponsor directly ?
            IndexEntry sponsorOrgIndexEntry =  organizationIndexEntryMap.get(sponsorOrgId);
            if(sponsorOrgIndexEntry == null) continue;

            //find studies this organization is managing.
            List<Integer> managedStudyIds = studySponsorStudyMap.get(sponsorOrgId);
            if(CollectionUtils.isEmpty(managedStudyIds)) continue;

             for(Integer studyId : managedStudyIds){

                 //find the sites this sponsor is managing
                 List<Integer> managedOrgIds = studyStudySiteMap.get(studyId);
                 if(CollectionUtils.isEmpty(managedOrgIds)) continue;

                 //add the transitive role for the managed org. 
                 for(Integer managedOrgId : managedOrgIds){
                     updateIndexEntry(organizationIndexEntryMap, managedOrgId, sponsorOrgIndexEntry.getRoles());
                     if(log.isInfoEnabled()) log.info("Transitive Organization Index Entry " +  managedOrgId + " via Study " + studyId + " , sponsors [" + sponsorOrgId + "]");
                 }
             }
        }

        List<IndexEntry> updatedOrganizationIndexEntryList =  new ArrayList<IndexEntry>(organizationIndexEntryMap.values());


        if (log.isInfoEnabled()) {
            log.info("Organization Fetcher fetched : " + String.valueOf(updatedOrganizationIndexEntryList));
        }

        return updatedOrganizationIndexEntryList;
    }

    /**
     * Will convert an IndexEntry list to a map, with "entity-id" as the key
     * @param entries
     * @return
     */
    private Map<Integer, IndexEntry> toEntityBasedIndex(List<IndexEntry> entries){
        Map<Integer, IndexEntry> map = new LinkedHashMap<Integer, IndexEntry>(entries.size());
        for(IndexEntry entry : entries) map.put(entry.getEntityId(), entry);
        return map;
    }

    private void updateIndexEntry(Map<Integer, IndexEntry> entryMap, Integer entityId, List<UserGroupType> roles){
        IndexEntry entry = entryMap.get(entityId);
        if(entry == null){
            entry = new IndexEntry(entityId);
            entryMap.put(entityId, entry);
        }
        entry.addRoles(roles);
    }


}
