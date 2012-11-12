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
        if(CollectionUtils.isEmpty(organizationIndexEntryList)) return organizationIndexEntryList;

        //if there is only all sites ?
        if(organizationIndexEntryList.size() == 1 && organizationIndexEntryList.get(0).isAllSiteOrAllStudy()) return organizationIndexEntryList;

        //TODO Do this only if we have study-scoped roles.
        //find the studies that this login can access
        List<IndexEntry> studyIndexEntryList = getCaaersSecurityFacade().getAccessibleStudyIds(loginId);
        if(CollectionUtils.isEmpty(studyIndexEntryList)) return studyIndexEntryList;

        //now pull out all study organization details from database.
        NativeSQLQuery studySiteQuery = new NativeSQLQuery("select study_identifier, site_id, type from study_organizations where retired_indicator <> 1 order by study_identifier");
        studySiteQuery.setScalar("study_identifier", StandardBasicTypes.INTEGER);
        studySiteQuery.setScalar("site_id", StandardBasicTypes.INTEGER);
        studySiteQuery.setScalar("type", StandardBasicTypes.STRING);

        List<Object[]> studyOrgDataList = (List<Object[]>) search(studySiteQuery);
        if(studyOrgDataList == null || studyOrgDataList.isEmpty()) return organizationIndexEntryList;

        //TODO - make sure that ALL Study is replaced.
        studyIndexEntryList = studyIndexEntryList;

        Map<Integer, IndexEntry> organizationIndexEntryMap = toEntityBasedIndex(organizationIndexEntryList);
        Map<Integer, IndexEntry> studyIdIndex = toEntityBasedIndex(studyIndexEntryList);
        
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

            //find studies this organization is managing.
            List<Integer> managedStudyIds = studySponsorStudyMap.get(sponsorOrgId);

             for(Integer studyId : managedStudyIds){

                 //is this study present in my accessible studies ?
                 IndexEntry studyIndexEntry =  studyIdIndex.get(studyId);
                 if(studyIndexEntry == null) continue;


                 //no - then either this login have no-access or this login has all site access
                 if(sponsorOrgIndexEntry == null) continue;

                 //find the sites this sponsor is managing
                 List<Integer> managedOrgIds = studyStudySiteMap.get(studyId);
                 if(managedOrgIds == null) continue;

                 //add the transitive role for the managed org. 
                 for(Integer managedOrgId : managedOrgIds){
                     IndexEntry managedOrgIndexEntry = organizationIndexEntryMap.get(managedOrgId);
                     if(managedOrgIndexEntry == null){
                         managedOrgIndexEntry  = new IndexEntry(managedOrgId);
                         organizationIndexEntryMap.put(managedOrgId, managedOrgIndexEntry);
                     }
                     managedOrgIndexEntry.addRole(sponsorOrgIndexEntry.getRoles());
                 }
                 
             }
            
        }

        //TODO : merge organizationIndexEntryList - to reduce size.
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


}
