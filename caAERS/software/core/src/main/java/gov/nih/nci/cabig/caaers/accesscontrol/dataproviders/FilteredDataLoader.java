package gov.nih.nci.cabig.caaers.accesscontrol.dataproviders;

import gov.nih.nci.cabig.caaers.dao.index.AbstractIndexDao;
import gov.nih.nci.cabig.caaers.domain.UserGroupType;
import gov.nih.nci.cabig.caaers.domain.index.IndexEntry;
import gov.nih.nci.cabig.caaers.reportdefinition.GroupType;
import gov.nih.nci.cabig.caaers.security.SecurityUtils;

import java.util.*;

import org.acegisecurity.Authentication;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Transactional;

import com.semanticbits.security.contentfilter.IdFetcher;

/**
 * Will take care of updating an index
 * @author Biju Joseph
 * @author Srini Akkala
 */
public class FilteredDataLoader {
	
	private List<IdFetcher> idFetchers = new ArrayList<IdFetcher>();
	private LinkedHashMap idFetcherIndexDaoMap;
	protected final Log log = LogFactory.getLog(getClass());

	public void updateIndexByUserName(Authentication authentication){
		String userName = SecurityUtils.getUserLoginName(authentication);
        long begin = System.currentTimeMillis();
        
		log.info("**BEGIN Updating Index for User " + userName);
		for (IdFetcher idFetcher : idFetchers) {
			long t1 = System.currentTimeMillis();
			List<IndexEntry> indexEntries = idFetcher.fetch(userName);
            long t2 = System.currentTimeMillis();
			log.info("TIME TOOK TO FETCH IDS " +idFetcher.getClass().getName() +" ..."+ (t2-t1)/1000 + " seconds");
			AbstractIndexDao indexDao = (AbstractIndexDao)idFetcherIndexDaoMap.get(idFetcher);

            List<IndexEntry> existingIndexEntries = indexDao.queryAllIndexEntries(userName);
            long t3 = System.currentTimeMillis();
            log.info("TIME TOOK TO query existing entries  " +idFetcher.getClass().getName() +" ..."+ (t3-t2)/1000 + " seconds");

            Map<UserGroupType, IndexEntry> existingEntryMap = convertToMap(existingIndexEntries);
            Map<UserGroupType, IndexEntry> newEntryMap = convertToMap(indexEntries);

            Set<UserGroupType> allRoles = new HashSet<UserGroupType>();
            if(!existingEntryMap.isEmpty()) allRoles.addAll(existingEntryMap.keySet());
            if(!newEntryMap.isEmpty()) allRoles.addAll(newEntryMap.keySet());

            updateAnIndex(userName, allRoles, existingEntryMap, newEntryMap, indexDao);

            long t4 = System.currentTimeMillis();
			log.info("TIME TOOK TO UPDATE INDEX ..."+ (t4-t3)/1000 + " seconds");


		}
		log.info("**END Updating Index for User " + userName + ", total time : " + (System.currentTimeMillis() - begin) /1000 +" seconds");

	}

    private Map<UserGroupType, IndexEntry> convertToMap(List<IndexEntry> entries){
        Map<UserGroupType, IndexEntry> entryMap = new HashMap<UserGroupType, IndexEntry>();
        if(entries != null){
            for(IndexEntry entry : entries){
                entryMap.put(entry.getRole(), entry);
            }
        }

        return entryMap;
    }


    /**
     * Will take care of refreshing the indexes
     * @param allRoles
     * @param existingMap
     * @param availableMap
     */
    //should run in a transaction. 
    @Transactional
	public void updateAnIndex(String userName, Set<UserGroupType> allRoles , Map<UserGroupType, IndexEntry> existingMap, Map<UserGroupType, IndexEntry> availableMap, AbstractIndexDao indexDao){
       for(UserGroupType ug: allRoles){
           long t1= System.currentTimeMillis();
           indexDao.updateIndex(userName, ug.getCode(), availableMap.get(ug), existingMap.get(ug));
           long t2 = System.currentTimeMillis();
           log.info("TIME TOOK to update index for Role [ " + ug.getDisplayName() +"] ..."+ (t2-t1)/1000 + " seconds");
       }
    }
	
	/**
	 * Sets the id fetchers to Cache ...
	 * 
	 */
	public void setIdFetchers(List<IdFetcher> idFetchers) {
		this.idFetchers = idFetchers;
	}
	public void setIdFetcherIndexDaoMap(LinkedHashMap idFetcherIndexDaoMap) {
		this.idFetcherIndexDaoMap = idFetcherIndexDaoMap;
	}
}
