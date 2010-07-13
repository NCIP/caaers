package gov.nih.nci.cabig.caaers.accesscontrol.dataproviders;

import gov.nih.nci.cabig.caaers.dao.CaaersDao;
import gov.nih.nci.cabig.caaers.domain.index.IndexEntry;
import gov.nih.nci.cabig.caaers.security.SecurityUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.acegisecurity.Authentication;
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

	public void updateIndexByUserName(Authentication authentication){
		for (IdFetcher idFetcher : idFetchers) {
			String userName = SecurityUtils.getUserLoginName(authentication);
			List<IndexEntry> indexEntries = idFetcher.fetch(userName);
			CaaersDao indexDao = (CaaersDao)idFetcherIndexDaoMap.get(idFetcher);
			updateAnIndex(indexEntries, userName, indexDao);
		}
	}

    /**
     * Will take care of refreshing a particular index. 
     * @param ids
     * @param userName
     * @param indexDao
     */
    //should run in a transaction. 
    @Transactional
	public void updateAnIndex(List<IndexEntry> indexEntries, String userName, CaaersDao indexDao){
       indexDao.clearIndex(userName);
       for (IndexEntry ie:indexEntries) {
    	   indexDao.updateIndex(ie.getEntityIds(), userName,ie.getRoleCode());
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
