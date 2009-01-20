package gov.nih.nci.cabig.caaers.accesscontrol;

import gov.nih.nci.cabig.caaers.DaoTestCase;
import gov.nih.nci.cabig.caaers.dao.query.ajax.ParticipantAjaxableDomainObjectQuery;
import gov.nih.nci.cabig.caaers.domain.ajax.ParticipantAjaxableDomainObject;
import gov.nih.nci.cabig.caaers.domain.repository.ajax.ParticipantAjaxableDomainObjectRepository;
import gov.nih.nci.cabig.caaers.security.SecurityTestUtils;

import java.util.ArrayList;
import java.util.List;

import org.acegisecurity.Authentication;
import org.acegisecurity.context.SecurityContextHolder;

public class ParticipantSiteSecurityFiltererTest extends DaoTestCase {
	
	private ParticipantAjaxableDomainObjectRepository participantAjaxableDomainObjectRepository = 
		(ParticipantAjaxableDomainObjectRepository) getApplicationContext().getBean("participantAjaxableDomainObjectRepository");

	private ParticipantSiteSecurityFilterer participantSiteSecurityFilterer = 
		(ParticipantSiteSecurityFilterer) getApplicationContext().getBean("participantSiteSecurityFilterer");
	/**
	 * disable authorization before querying .
	 * Reason : Authorization checks for various roles to invoke search methods on various classes.
	 * For this we need to load whole CSM database. We are filtering the results after executing full query anyway,
	 * so , execute query without security and , then enable for filtering.
	 *
	 */
	private void disableAuthorization(){
		SecurityTestUtils.enableAuthorization(false, applicationContext);
	}
	private void enableAuthorization(){
		SecurityTestUtils.enableAuthorization(true, applicationContext);
	}
	
	public void testFilterAsStudyCoordinator() {
		//disable security before query 
		disableAuthorization();
		ParticipantAjaxableDomainObjectQuery query = new ParticipantAjaxableDomainObjectQuery();
		List<ParticipantAjaxableDomainObject> participants = participantAjaxableDomainObjectRepository.findParticipants(query);
		assertEquals(participants.size(),1);
		
		//enable security 
		enableAuthorization();		
		//login as Study Coordinator.
		SecurityTestUtils.switchUser("1000@def.com", "ROLE_caaers_study_cd");
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Filterer filterer = new CollectionFilterer(participants); 
		ArrayList filteredList = (ArrayList)participantSiteSecurityFilterer.filter(authentication, "ACCESS", filterer);
		assertEquals(filteredList.size(),1);
	}
	
	public void testFilterAsAdverseEventCoordinator() {
		//disable security before query 
		disableAuthorization();
		ParticipantAjaxableDomainObjectQuery query = new ParticipantAjaxableDomainObjectQuery();
		List<ParticipantAjaxableDomainObject> participants = participantAjaxableDomainObjectRepository.findParticipants(query);
		assertEquals(participants.size(),1);
		
		//enable security 
		enableAuthorization();		
		//login as Study Coordinator.
		SecurityTestUtils.switchUser("1000@def.com", "ROLE_caaers_ae_cd");
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Filterer filterer = new CollectionFilterer(participants); 
		ArrayList filteredList = (ArrayList)participantSiteSecurityFilterer.filter(authentication, "ACCESS", filterer);
		assertEquals(filteredList.size(),1);		
	}
	
}
