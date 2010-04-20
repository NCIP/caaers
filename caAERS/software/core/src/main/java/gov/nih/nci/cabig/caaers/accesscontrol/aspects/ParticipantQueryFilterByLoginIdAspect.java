package gov.nih.nci.cabig.caaers.accesscontrol.aspects;

import gov.nih.nci.cabig.caaers.accesscontrol.dataproviders.FilteredDataProvider;
import gov.nih.nci.cabig.caaers.dao.query.ajax.ParticipantAjaxableDomainObjectQuery;

import java.util.List;

import org.acegisecurity.Authentication;
import org.acegisecurity.context.SecurityContextHolder;
import org.springframework.beans.factory.annotation.Required;

import com.semanticbits.security.contentfilter.IdFetcher;


/**
 * This aspect is configured to invoke on ParticipantAjaxableDomainObjectRepository.findParticipants.
 * @author akkalas
 *
 */
public class ParticipantQueryFilterByLoginIdAspect {
	private IdFetcher idFetcher;
	//@Before("execution(* gov.nih.nci.cabig.caaers.domain.repository.ajax.ParticipantAjaxableDomainObjectRepository.findParticipants(..))" + 
		//			" && args(qry)")
	/**
	 * add IN Query with authorized IDs to parent Query. Ex : Where participant.in (1 , 2, 5) ; 
	 */
	public Object applyLoginFilter(ParticipantAjaxableDomainObjectQuery qry) throws Throwable {
		System.out.println(qry.getQueryString());
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication != null) {
			FilteredDataProvider filteredDataProvider = new FilteredDataProvider(authentication,idFetcher);
			// No filtering for super user . 
			if (filteredDataProvider.filteringNotRequired()) {
				return new Object[] {qry};	 
			}
			List participantIds = filteredDataProvider.fetch();
	 		System.out.println(participantIds.size());
	 		
	 		if (participantIds.size() == 0) {
	 			// dummy negetive query , not to return any results .. 
	 			qry.filterByAnyAnd("1=2");
	 		} else {
	 			qry.filterByParticipantsINQuery(participantIds);
	 		}
		}
		return new Object[] {qry};	
		
		
    }
	
	@Required
	public void setIdFetcher(IdFetcher idFetcher) {
		this.idFetcher = idFetcher;
	}
	

	
}
