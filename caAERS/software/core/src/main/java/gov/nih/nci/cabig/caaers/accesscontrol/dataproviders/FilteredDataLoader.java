package gov.nih.nci.cabig.caaers.accesscontrol.dataproviders;

import gov.nih.nci.cabig.caaers.dao.index.AbstractIndexDao;
import gov.nih.nci.cabig.caaers.domain.UserGroupType;
import gov.nih.nci.cabig.caaers.domain.index.IndexEntry;
import gov.nih.nci.cabig.caaers.security.SecurityUtils;

import java.util.*;

import org.acegisecurity.Authentication;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
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


    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED)
	public void updateIndexByUserName(Authentication authentication){
		String userName = SecurityUtils.getUserLoginName(authentication);
        long begin = System.currentTimeMillis();
        
		log.info("**BEGIN Updating Index for User " + userName);
		for (IdFetcher idFetcher : idFetchers) {
			long t1 = System.currentTimeMillis();
			List<IndexEntry> indexEntries = idFetcher.fetch(userName);
            long t2 = System.currentTimeMillis();
			log.info("TIME TOOK TO FETCH IDS " +idFetcher.getClass().getName() +" ..."+ (t2-t1)/1000 + " seconds");
			
			long t3 = System.currentTimeMillis();
			AbstractIndexDao indexDao = (AbstractIndexDao)idFetcherIndexDaoMap.get(idFetcher);
			indexDao.updateIndex(userName, indexEntries);
            long t4 = System.currentTimeMillis();
			log.info("TIME TOOK TO UPDATE INDEX ..."+ (t4-t3)/1000 + " seconds");
		}
		log.info("**END Updating Index for User " + userName + ", total time : " + (System.currentTimeMillis() - begin) /1000 +" seconds");

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
