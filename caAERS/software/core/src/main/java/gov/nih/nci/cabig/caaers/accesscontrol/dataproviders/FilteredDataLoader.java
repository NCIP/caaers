package gov.nih.nci.cabig.caaers.accesscontrol.dataproviders;

import java.util.ArrayList;
import java.util.List;

import com.semanticbits.security.contentfilter.IdFetcher;
import com.semanticbits.security.contentfilter.cache.QueryCacheManager;

public class FilteredDataLoader {
	
	private List<IdFetcher> idFetchers = new ArrayList<IdFetcher>();
	
	/**
	 * Cache Data , Call all filters based on user and session .
	 * @param userName
	 * @param sessionId
	 */
	public void loadByUserName(String userName , String sessionId){
		for (IdFetcher idFetcher : idFetchers) {
			List listOfIds = idFetcher.fetch(userName);
			String className = idFetcher.getClass().getName();
			QueryCacheManager.addDataToCache(sessionId, className, listOfIds);
		}
	}
	
	/**
	 * Sets the id fetchers to Cache ...
	 * 
	 */
	public void setIdFetchers(List<IdFetcher> idFetchers) {
		this.idFetchers = idFetchers;
	}
}
