package gov.nih.nci.cabig.caaers.accesscontrol.aspects;

import gov.nih.nci.cabig.caaers.accesscontrol.BaseSecurityFilterer;
import gov.nih.nci.cabig.caaers.accesscontrol.dataproviders.FilteredDataLoader;
import gov.nih.nci.cabig.caaers.security.SecurityUtils;

import org.acegisecurity.Authentication;
import org.acegisecurity.context.SecurityContextHolder;

/**
 * 
 * @author akkalas
 *
 */
public class RefreshIndexAspect extends BaseSecurityFilterer{
	

	private FilteredDataLoader filteredDataLoader;
	
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
				
				filteredDataLoader.updateIndexByUserName(userName);
				
				/*
				for (IdFetcher idFetcher : idFetchers) {
					List listOfIds = idFetcher.fetch(userName);
					CaaersDao indexDao = (CaaersDao)idFetcherIndexDaoMap.get(idFetcher);
					indexDao.clearIndex(userName);
					indexDao.updateIndex(listOfIds, userName);
				}*/				
			}
			/*
			FilteredDataProvider filteredDataProvider = new FilteredDataProvider(authentication,idFetcher);
			// No filtering for super user . 
			if (filteredDataProvider.filteringNotRequired()) {
				return new Object[] {qry};	 
			}*/
		}
	}

	public void setFilteredDataLoader(FilteredDataLoader filteredDataLoader) {
		this.filteredDataLoader = filteredDataLoader;
	}
}
