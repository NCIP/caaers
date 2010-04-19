package gov.nih.nci.cabig.caaers.security.aspects;

import gov.nih.nci.cabig.caaers.accesscontrol.BaseSecurityFilterer;
import gov.nih.nci.cabig.caaers.dao.query.ajax.ParticipantAjaxableDomainObjectQuery;
import gov.nih.nci.cabig.caaers.security.SecurityUtils;

import java.util.List;

import org.acegisecurity.Authentication;
import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.context.SecurityContextHolder;
import org.springframework.beans.factory.annotation.Required;

import com.semanticbits.security.contentfilter.IdFetcher;
import com.semanticbits.security.contentfilter.cache.QueryCacheProvider;


//@Aspect
public class ParticipantQueryFilterByLoginIdAspect extends BaseSecurityFilterer{
	
	private IdFetcher idFetcher;
	
	//@Before("execution(* gov.nih.nci.cabig.caaers.domain.repository.ajax.ParticipantAjaxableDomainObjectRepository.findParticipants(..))" + 
		//			" && args(qry)")
	public Object applyLoginFilter(ParticipantAjaxableDomainObjectQuery qry) throws Throwable {
		System.out.println(qry.getQueryString());
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if(authentication != null){
			GrantedAuthority[] grantedAuthorities = getGrantedAuthorities(authentication);
			if (isSuperUser(grantedAuthorities)) {
				return new Object[] {qry};	
			}
	 	
	 		String userName = SecurityUtils.getUserLoginName();
	 		// BUILD IN QUERY 
	    	/**
	 		QueryCacheProvider s = new QueryCacheProvider();
	    	List participantIds = s.fetch("gov.nih.nci.cabig.caaers.accesscontrol.query.impl.CaaersParticipantIdFetcherImpl", userName);
	    	*/
	    	
	 		List participantIds = idFetcher.fetch(userName);
	 		System.out.println(participantIds.size());
	 		/*
	 		if (participantIds.size() == 0) {
	 			// dummy negetive query , not to return any results .. 
	 			qry.filterByAnyAnd("1=2");
	 		} else {
	 			qry.filterByParticipantsINQuery(participantIds);
	 		}*/
	 	}
		return new Object[] {qry};	
    }

	@Required
	public void setIdFetcher(IdFetcher idFetcher) {
		this.idFetcher = idFetcher;
	}
	
}
