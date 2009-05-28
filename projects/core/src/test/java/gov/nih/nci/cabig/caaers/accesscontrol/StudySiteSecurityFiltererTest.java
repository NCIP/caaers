package gov.nih.nci.cabig.caaers.accesscontrol;

import gov.nih.nci.cabig.caaers.DaoTestCase;
import gov.nih.nci.cabig.caaers.dao.query.ajax.StudySearchableAjaxableDomainObjectQuery;
import gov.nih.nci.cabig.caaers.domain.UserGroupType;
import gov.nih.nci.cabig.caaers.domain.ajax.StudySearchableAjaxableDomainObject;
import gov.nih.nci.cabig.caaers.domain.repository.ajax.StudySearchableAjaxableDomainObjectRepository;
import gov.nih.nci.cabig.caaers.security.SecurityTestUtils;
import gov.nih.nci.cabig.caaers.utils.CollectionFilterer;
import gov.nih.nci.cabig.caaers.utils.Filterer;

import java.util.ArrayList;
import java.util.List;

import org.acegisecurity.Authentication;
import org.acegisecurity.context.SecurityContextHolder;

public class StudySiteSecurityFiltererTest extends DaoTestCase {
	
	private StudySearchableAjaxableDomainObjectRepository studySearchableAjaxableDomainObjectRepository = 
		(StudySearchableAjaxableDomainObjectRepository) getApplicationContext().getBean("studySearchableAjaxableDomainObjectRepository");

	private StudySiteSecurityFilterer studySiteSecurityFilterer = 
		(StudySiteSecurityFilterer) getApplicationContext().getBean("studySiteSecurityFilterer");
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
		StudySearchableAjaxableDomainObjectQuery query = new StudySearchableAjaxableDomainObjectQuery();
		List<StudySearchableAjaxableDomainObject> studies = studySearchableAjaxableDomainObjectRepository.findStudies(query);
		assertEquals(studies.size(),3);
		
		//enable security 
		enableAuthorization();		
		//login as Study Coordinator.
		SecurityTestUtils.switchUser("1000@def.com", UserGroupType.caaers_study_cd.getSecurityRoleName());
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Filterer filterer = new CollectionFilterer(studies); 
		ArrayList filteredList = (ArrayList)studySiteSecurityFilterer.filter(authentication, "ACCESS", filterer);
		assertEquals(filteredList.size(),3);
	}

	public void testFilterAsPhysician() {
		//disable security before query 
		disableAuthorization();
		StudySearchableAjaxableDomainObjectQuery query = new StudySearchableAjaxableDomainObjectQuery();
		List<StudySearchableAjaxableDomainObject> studies = studySearchableAjaxableDomainObjectRepository.findStudies(query);
		assertEquals(studies.size(),3);
		
		//enable security 
		enableAuthorization();		
		//login as Study Coordinator.
		SecurityTestUtils.switchUser("1000@def.com", UserGroupType.caaers_physician.getSecurityRoleName());
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Filterer filterer = new CollectionFilterer(studies); 
		ArrayList filteredList = (ArrayList)studySiteSecurityFilterer.filter(authentication, "ACCESS", filterer);
		assertEquals(filteredList.size(),3);
	}
	
	public void testFilterAsAdverseEventCoordinator() {
		//disable security before query 
		disableAuthorization();
		StudySearchableAjaxableDomainObjectQuery query = new StudySearchableAjaxableDomainObjectQuery();
		List<StudySearchableAjaxableDomainObject> studies = studySearchableAjaxableDomainObjectRepository.findStudies(query);
		assertEquals(studies.size(),3);
		
		//enable security 
		enableAuthorization();		
		//login as AE Coordinator.
		SecurityTestUtils.switchUser("1000@def.com", UserGroupType.caaers_ae_cd.getSecurityRoleName());
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Filterer filterer = new CollectionFilterer(studies); 
		ArrayList filteredList = (ArrayList)studySiteSecurityFilterer.filter(authentication, "ACCESS", filterer);
		assertEquals(filteredList.size(),1);		
	}
	

	public void testFilterAsAdverseEventCoordinator_InactiveOnStudy() {
		//disable security before query 
		disableAuthorization();
		StudySearchableAjaxableDomainObjectQuery query = new StudySearchableAjaxableDomainObjectQuery();
		List<StudySearchableAjaxableDomainObject> studies = studySearchableAjaxableDomainObjectRepository.findStudies(query);
		assertEquals(studies.size(),3);
		
		//enable security 
		enableAuthorization();		
		//login as AE Coordinator.
		SecurityTestUtils.switchUser("1002@def.com", UserGroupType.caaers_ae_cd.getSecurityRoleName());
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Filterer filterer = new CollectionFilterer(studies); 
		ArrayList filteredList = (ArrayList)studySiteSecurityFilterer.filter(authentication, "ACCESS", filterer);
		System.out.println(filteredList);
		
		assertEquals(filteredList.size(),0);		
	}
	

	public void testFilterAsAdverseEventCoordinator_RetiredOnStudy() {
		//disable security before query 
		disableAuthorization();
		StudySearchableAjaxableDomainObjectQuery query = new StudySearchableAjaxableDomainObjectQuery();
		List<StudySearchableAjaxableDomainObject> studies = studySearchableAjaxableDomainObjectRepository.findStudies(query);
		assertEquals(studies.size(),3);
		
		//enable security 
		enableAuthorization();		
		//login as AE Coordinator.
		SecurityTestUtils.switchUser("1003@def.com", UserGroupType.caaers_ae_cd.getSecurityRoleName());
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Filterer filterer = new CollectionFilterer(studies); 
		ArrayList filteredList = (ArrayList)studySiteSecurityFilterer.filter(authentication, "ACCESS", filterer);
		System.out.println(filteredList);
		
		assertEquals(filteredList.size(),0);		
	}
	
}
