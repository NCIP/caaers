/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.accesscontrol.query.impl;

import gov.nih.nci.cabig.caaers.dao.query.HQLQuery;
import gov.nih.nci.cabig.caaers.dao.query.NativeSQLQuery;
import gov.nih.nci.cabig.caaers.domain.UserGroupType;
import gov.nih.nci.cabig.caaers.domain.index.IndexEntry;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.semanticbits.security.contentfilter.IdFetcher;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.type.StandardBasicTypes;


/**
 * Will return the ID of the studies that a particular user has access to.
 * @author Srini Akkala
 * @author Biju Joseph
 *
 */
public class CaaersStudyIdFetcherImpl extends AbstractIdFetcher implements IdFetcher {
    protected final Log log = LogFactory.getLog(CaaersStudyIdFetcherImpl.class);
    
	@Override
	public List fetch(String loginId) {

		List<IndexEntry> resultList = getCaaersSecurityFacade().getAccessibleStudyIds(loginId);
		if(CollectionUtils.isEmpty(resultList)) return resultList;
		
        if(log.isInfoEnabled()){
            log.info("Study Fetcher fetched : " + String.valueOf(resultList) );
        }

        List<IndexEntry> allStudyEntries = new ArrayList<IndexEntry>();
        for(IndexEntry entry : resultList){
            if(entry.isAllSiteOrAllStudy()) allStudyEntries.add(entry);
        }

        if(allStudyEntries.isEmpty()) return resultList;
        resultList.removeAll(allStudyEntries);

        Map<Integer, IndexEntry> studiesMap = new LinkedHashMap<Integer, IndexEntry>();
        for(IndexEntry entry : resultList) {
            studiesMap.put(entry.getEntityId(), entry);
        }

        List<IndexEntry> orgIndexEntries = getCaaersSecurityFacade().getAccessibleOrganizationIds(loginId);
        if(CollectionUtils.isNotEmpty(orgIndexEntries)){
            for(IndexEntry allStudyEntry : allStudyEntries){
                List<Integer> orgIdList = new ArrayList<Integer>();
                for(IndexEntry orgIndexEntry : orgIndexEntries){

                    if(orgIndexEntry.hasAnyOfTheRoles(allStudyEntry.getRoles().toArray(new UserGroupType[]{}))){
                         if(orgIndexEntry.isAllSiteOrAllStudy()){
                             List<Integer> allStudyId = (List<Integer>) search(new HQLQuery("select s.id from Study s"));
                             if(CollectionUtils.isNotEmpty(allStudyId)){
                                 for(Integer studyId : allStudyId){
                                     updateIndexEntry(studiesMap, studyId, allStudyEntry.getRoles() );
                                 }
                             }
                             break;
                         } else {
                             orgIdList.add(orgIndexEntry.getEntityId());
                         }
                    }


                }

                if(!orgIdList.isEmpty()) {
                    NativeSQLQuery studyQuery = new NativeSQLQuery("select distinct so.study_id as study_id from study_organizations so where so.site_id in (:orgIds) and so.retired_indicator = :ri");
                    studyQuery.setParameter("ri", false);
                    studyQuery.setParameterList("orgIds", orgIdList);
                    studyQuery.setScalar("study_id", StandardBasicTypes.INTEGER);
                    List<Object[]> studyIdList = (List<Object[]>) search(studyQuery);
                    if(CollectionUtils.isNotEmpty(studyIdList)){
                        for(Object[] id : studyIdList)  {
                            updateIndexEntry(studiesMap, (Integer)id[0], allStudyEntry.getRoles());
                        }
                    }
                }
            }
        }

        
		return  new ArrayList<IndexEntry>(studiesMap.values());
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
