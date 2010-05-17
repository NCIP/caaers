package gov.nih.nci.cabig.caaers.accesscontrol.dataproviders;

import gov.nih.nci.cabig.caaers.dao.CaaersDao;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.semanticbits.security.contentfilter.IdFetcher;
import org.springframework.transaction.annotation.Transactional;

/**
 * Will take care of updating an index
 * @author Biju Joseph
 * @author Srini Akkala
 */
public class FilteredDataLoader {
	
	private List<IdFetcher> idFetchers = new ArrayList<IdFetcher>();
	private LinkedHashMap idFetcherIndexDaoMap;

	public void updateIndexByUserName(String userName){
		for (IdFetcher idFetcher : idFetchers) {
			List listOfIds = idFetcher.fetch(userName);
			CaaersDao indexDao = (CaaersDao)idFetcherIndexDaoMap.get(idFetcher);
			updateAnIndex(listOfIds, userName, indexDao);
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
	public void updateAnIndex(List ids, String userName, CaaersDao indexDao){
       indexDao.clearIndex(userName);
       if(ids != null && !ids.isEmpty()){
           indexDao.updateIndex(ids, userName);
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
