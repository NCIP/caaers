package gov.nih.nci.cabig.caaers.accesscontrol.aspects;

import gov.nih.nci.cabig.caaers.accesscontrol.BaseSecurityFilterer;
import gov.nih.nci.cabig.caaers.dao.CaaersDao;
import gov.nih.nci.cabig.caaers.security.SecurityUtils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.acegisecurity.Authentication;
import org.acegisecurity.context.SecurityContextHolder;

import com.semanticbits.security.contentfilter.IdFetcher;

/**
 * 
 * @author akkalas
 *
 */
public class RefreshIndexAspect extends BaseSecurityFilterer{
	
	private List<IdFetcher> idFetchers = new ArrayList<IdFetcher>();
	private LinkedHashMap idFetcherIndexDaoMap;
	
	/**After Aspect Configured in application Context
	 * 
	 *
	 */
	public void updateIndexByUserName(){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication != null) {
			String userName = SecurityUtils.getUserLoginName(authentication);
			// no need to refresh index for super user .. 
			if (filteringNotRequired(authentication)) {
				return ; 
			} else {
				for (IdFetcher idFetcher : idFetchers) {
					List listOfIds = idFetcher.fetch(userName);
					CaaersDao indexDao = (CaaersDao)idFetcherIndexDaoMap.get(idFetcher);
					indexDao.clearIndex(userName);
					indexDao.updateIndex(listOfIds, userName);
				}				
			}
			/*
			FilteredDataProvider filteredDataProvider = new FilteredDataProvider(authentication,idFetcher);
			// No filtering for super user . 
			if (filteredDataProvider.filteringNotRequired()) {
				return new Object[] {qry};	 
			}*/
		}
	}

	public void setIdFetcherIndexDaoMap(LinkedHashMap idFetcherIndexDaoMap) {
		this.idFetcherIndexDaoMap = idFetcherIndexDaoMap;
	}

	public void setIdFetchers(List<IdFetcher> idFetchers) {
		this.idFetchers = idFetchers;
	}
}
