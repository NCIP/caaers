package gov.nih.nci.cabig.caaers.accesscontrol.dataproviders;

import gov.nih.nci.cabig.caaers.dao.CaaersDao;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.semanticbits.security.contentfilter.IdFetcher;

public class FilteredDataLoader {
	
	private List<IdFetcher> idFetchers = new ArrayList<IdFetcher>();
	private LinkedHashMap idFetcherIndexDaoMap;
	
	/**
	 * Cache Data , Call all filters based on user and session .
	 * @param userName
	 * @param sessionId
	 */
	/*
	public void loadByUserName(String userName , String sessionId){
		for (IdFetcher idFetcher : idFetchers) {
			List listOfIds = idFetcher.fetch(userName);
			String className = idFetcher.getClass().getName();
			QueryCacheManager.addDataToCache(sessionId, className, listOfIds);
		}
	}
	public void loadByUserName(String sessionId, String className , List ids){
		QueryCacheManager.addDataToCache(sessionId, className, ids);
	}*/
	
	public void updateIndexByUserName(String userName){
		for (IdFetcher idFetcher : idFetchers) {
			List listOfIds = idFetcher.fetch(userName);
			if (listOfIds == null) return;
			
			CaaersDao indexDao = (CaaersDao)idFetcherIndexDaoMap.get(idFetcher);
			indexDao.clearIndex(userName);
			indexDao.updateIndex(listOfIds, userName);
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
