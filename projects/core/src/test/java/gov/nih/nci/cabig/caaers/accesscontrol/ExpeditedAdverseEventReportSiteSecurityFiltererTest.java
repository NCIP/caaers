package gov.nih.nci.cabig.caaers.accesscontrol;

import gov.nih.nci.cabig.caaers.DaoTestCase;
import gov.nih.nci.cabig.caaers.dao.ExpeditedAdverseEventReportDao;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.security.SecurityTestUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.acegisecurity.Authentication;
import org.acegisecurity.context.SecurityContextHolder;

public class ExpeditedAdverseEventReportSiteSecurityFiltererTest extends DaoTestCase {
	
	private ExpeditedAdverseEventReportDao expeditedAdverseEventReportDao = 
		(ExpeditedAdverseEventReportDao) getApplicationContext().getBean("expeditedAdverseEventReportDao");

	private ExpeditedAdverseEventReportSiteSecurityFilterer expeditedAdverseEventReportSiteSecurityFilterer = 
		(ExpeditedAdverseEventReportSiteSecurityFilterer) getApplicationContext().getBean("expeditedAdverseEventReportSiteSecurityFilterer");
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
	/*
	public void testFilterAsStudyCoordinator() throws Exception {
		//disable security before query 
		disableAuthorization();
		Map<String, String> propValue = new HashMap<String, String>();
		propValue.put("studyShortTitle", "%");
		List<ExpeditedAdverseEventReport> expeditedAdverseEventReports = expeditedAdverseEventReportDao.searchExpeditedReports(propValue);
		assertEquals(expeditedAdverseEventReports.size(),1);
		
		//enable security 
		enableAuthorization();		
		//login as Study Coordinator.
		SecurityTestUtils.switchUser("1000@def.com", "ROLE_caaers_study_cd");
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Filterer filterer = new CollectionFilterer(expeditedAdverseEventReports); 
		ArrayList filteredList = (ArrayList)expeditedAdverseEventReportSiteSecurityFilterer.filter(authentication, "ACCESS", filterer);
		assertEquals(filteredList.size(),1);
	}
	
	public void testFilterAsAdverseEventCoordinator() throws Exception {
		//disable security before query 
		disableAuthorization();
		Map<String, String> propValue = new HashMap<String, String>();
		propValue.put("studyShortTitle", "%");
		List<ExpeditedAdverseEventReport> expeditedAdverseEventReports = expeditedAdverseEventReportDao.searchExpeditedReports(propValue);
		assertEquals(expeditedAdverseEventReports.size(),1);
		
		//enable security 
		enableAuthorization();		
		//login as Study Coordinator.
		SecurityTestUtils.switchUser("1000@def.com", "ROLE_caaers_ae_cd");
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Filterer filterer = new CollectionFilterer(expeditedAdverseEventReports); 
		ArrayList filteredList = (ArrayList)expeditedAdverseEventReportSiteSecurityFilterer.filter(authentication, "ACCESS", filterer);
		assertEquals(filteredList.size(),1);		
	}*/
	public void testTemp(){
		System.out.println("test");
	}
	
}
