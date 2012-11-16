package gov.nih.nci.cabig.caaers.accesscontrol.query.impl;

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
        
        String all_studies_results = "select distinct so.study_id as study_id from " +
                " study_organizations so " +
                " join organization_index oi on  oi.organization_id = so.site_id  " +
                " where retired_indicator <> :ri";
        
        Map<Integer, IndexEntry> studiesMap = new LinkedHashMap<Integer, IndexEntry>();
        
        for(IndexEntry entry : resultList){
            if(entry.isAllSiteOrAllStudy()) {
            	
            	// Prepare the condition
                StringBuffer roleCond = new StringBuffer("(");
                int size = entry.getRoles().size();
                for ( UserGroupType role: entry.getRoles()) {                	
                	roleCond.append( role.dbAlias() +  "=:" + role.dbAlias());
                	size--;
                	if ( size > 0 )
                		roleCond.append(" OR ");
                }
                roleCond.append(")");
                all_studies_results = all_studies_results + " and " + roleCond.toString();    
                
            	//now pull out all study organization details from database.
                NativeSQLQuery studySiteQuery = new NativeSQLQuery(all_studies_results);
                studySiteQuery.setScalar("study_id", StandardBasicTypes.INTEGER);
                studySiteQuery.setParameter("ri", true);
                
                for ( UserGroupType role: entry.getRoles()) {
                	studySiteQuery.setParameter(role.dbAlias(),true);
                }
                
                
                
                // Update the all site query with roles condition.
                
                List<Object[]> studyOrgDataList = (List<Object[]>) search(studySiteQuery);
                if(studyOrgDataList == null || studyOrgDataList.isEmpty()) break;
              
                constructIndexEntriesForExtrapolatedStudies(studyOrgDataList, entry.getRoles(), studiesMap);
                	
            } else {
            	updateStudiesMap(studiesMap, entry);
            }
        }
        
		return  new ArrayList<IndexEntry>(studiesMap.values());
	}
	
	private void constructIndexEntriesForExtrapolatedStudies( List<Object[]> studyOrgDataList,List<UserGroupType> roles, Map<Integer, IndexEntry> studiesMap) {
		
		for ( Object val : studyOrgDataList) {
			Integer entityId = (Integer)val;
			IndexEntry e = new IndexEntry(entityId);
			e.addRole(roles);
			updateStudiesMap(studiesMap, e);
		}	
	}
	
	private void updateStudiesMap( Map<Integer, IndexEntry> studiesMap, IndexEntry entry) {
		Integer entityId = entry.getEntityId();
		
		if ( studiesMap.containsKey(entityId)) {
			// update the roles.
				IndexEntry e = studiesMap.get(entityId);
				e.addRole(entry.getRoles());
				studiesMap.put(entityId, e);
				
		} else {
			studiesMap.put(entityId, entry);
		}
		
	}
	
}
