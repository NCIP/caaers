package gov.nih.nci.cabig.caaers.accesscontrol.aspects;

import gov.nih.nci.cabig.caaers.accesscontrol.BaseSecurityFilterer;
import gov.nih.nci.cabig.caaers.accesscontrol.dataproviders.FilteredDataProvider;
import gov.nih.nci.cabig.caaers.dao.query.AbstractQuery;

import org.acegisecurity.Authentication;
import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.context.SecurityContextHolder;
import org.springframework.beans.factory.annotation.Required;

import com.semanticbits.security.contentfilter.IdFetcher;

public abstract class AbstractFilterByLoginIdAspect extends BaseSecurityFilterer{
	//private IdFetcher idFetcher;

	//@Before("execution(* gov.nih.nci.cabig.caaers.domain.repository.ajax.ParticipantAjaxableDomainObjectRepository.findParticipants(..))" + 
		//			" && args(qry)")
	/**
	 * add IN Query with authorized IDs to parent Query. Ex : Where participant.in (1 , 2, 5) ; 
	 */
	public Object applyLoginFilter(AbstractQuery qry) throws Throwable {
		System.out.println(qry.getQueryString());
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication != null) {
			
			if (filteringNotRequired(authentication)) {
				return new Object[] {qry};	
			}
			//join with INDEX tables and return the modified query 
			
			
			/*
			FilteredDataProvider filteredDataProvider = new FilteredDataProvider(authentication,idFetcher);
			// No filtering for super user . 
			if (filteredDataProvider.filteringNotRequired()) {
				return new Object[] {qry};	 
			}
			
			
			
			/*
			List ids = filteredDataProvider.fetch();
	 		System.out.println(ids.size());
	 		
	 		if (ids.size() == 0) {
	 			// dummy negetive query , not to return any results .. 
	 			qry.filterByAnyAnd("1=2");
	 		} else {
	 			qry.filterINQuery(getInQuery() , ids);
	 		}
	 		*/
		}
		return new Object[] {qry};	
    }

	//public abstract String getInQuery();
	
	public abstract String getJoinQuery();
	
	/*
	@Required
	public void setIdFetcher(IdFetcher idFetcher) {
		this.idFetcher = idFetcher;
	}*/
	


}
