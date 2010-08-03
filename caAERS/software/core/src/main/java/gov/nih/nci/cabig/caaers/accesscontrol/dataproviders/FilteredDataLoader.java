package gov.nih.nci.cabig.caaers.accesscontrol.dataproviders;

import gov.nih.nci.cabig.caaers.dao.index.AbstractIndexDao;
import gov.nih.nci.cabig.caaers.domain.index.IndexEntry;
import gov.nih.nci.cabig.caaers.security.SecurityUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

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
		for (IdFetcher idFetcher : idFetchers) {
			String userName = SecurityUtils.getUserLoginName(authentication);
			Date da = new Date();
			List<IndexEntry> indexEntries = idFetcher.fetch(userName);
			Date db = new Date();
			Long diff0 = db.getTime()-da.getTime();
			log.debug("TIME TOOK TO FETCH IDS " +idFetcher.getClass().getName() +" ..."+ diff0/1000 + " seconds");
			System.out.println("TIME TOOK TO FETCH IDS " +idFetcher.getClass().getName() +" ..."+ diff0/1000 + " seconds");
			AbstractIndexDao indexDao = (AbstractIndexDao)idFetcherIndexDaoMap.get(idFetcher);
			Date d1 = new Date();
			updateAnIndex(indexEntries, userName, indexDao);
			Date d2 = new Date();
			Long diff = d2.getTime()-d1.getTime();
			log.debug("TIME TOOK TO UPDATE INDEX ..."+ diff/1000 + " seconds");
			System.out.println("TIME TOOK TO UPDATE INDEX ..."+ diff/1000 + " seconds");

		}
	}

    /**
     * Will take care of refreshing a particular index. 
     * @param indexEntries
     * @param userName
     * @param indexDao
     */
    //should run in a transaction. 
    @Transactional
	public void updateAnIndex(List<IndexEntry> indexEntries, String userName, AbstractIndexDao indexDao){
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
