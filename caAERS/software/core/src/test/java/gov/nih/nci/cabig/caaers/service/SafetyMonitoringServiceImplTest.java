package gov.nih.nci.cabig.caaers.service;

import gov.nih.nci.cabig.caaers.CaaersDbTestCase;
import gov.nih.nci.cabig.caaers.dao.AdverseEventDao;
import gov.nih.nci.cabig.caaers.dao.NotificationDao;
import gov.nih.nci.cabig.caaers.dao.ObservedAdverseEventProfileDao;
import gov.nih.nci.cabig.caaers.dao.RuleSetDao;
import gov.nih.nci.cabig.caaers.dao.StudyParticipantAssignmentDao;
import gov.nih.nci.cabig.caaers.dao.query.RuleSetQuery;
import gov.nih.nci.cabig.caaers.domain.Grade;
import gov.nih.nci.cabig.caaers.domain.ObservedAdverseEventProfile;
import gov.nih.nci.cabig.caaers.domain.RuleSet;
import gov.nih.nci.cabig.caaers.domain.TreatmentAssignment;
import gov.nih.nci.cabig.caaers.rules.business.service.CaaersRulesEngineService;
import gov.nih.nci.cabig.caaers.rules.common.RuleType;
import gov.nih.nci.cabig.caaers.tools.mail.CaaersJavaMailSender;
import gov.nih.nci.cabig.ctms.domain.DomainObject;

import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.springframework.mail.SimpleMailMessage;

import com.semanticbits.rules.utils.RuleUtil;

public class SafetyMonitoringServiceImplTest extends CaaersDbTestCase {
	
	private AdverseEventDao adverseEventDao;
	
	private ObservedAdverseEventProfileDao observedAdverseEventProfileDao;
	
	private EvaluationService evaluationService;
	
	private FreeMarkerService freeMarkerService;
	
	private CaaersJavaMailSender mailer;
	
	private NotificationDao notificationDao;
	
	private RuleSetDao ruleSetDao;
	
	private SafetyMonitoringService safetyMonitoringService;
	
	private StudyParticipantAssignmentDao studyParticipantAssignmentDao;
	
	private CaaersRulesEngineService caaersRulesEngineService;
	
	private List<ObservedAdverseEventProfile> observedAdverseEventProfiles;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		adverseEventDao = (AdverseEventDao)getDeployedApplicationContext().getBean("adverseEventDao");
		observedAdverseEventProfileDao = (ObservedAdverseEventProfileDao)getDeployedApplicationContext().getBean("observedAdverseEventProfileDao");
		evaluationService = (EvaluationService)getDeployedApplicationContext().getBean("evaluationService");
		freeMarkerService = (FreeMarkerService)getDeployedApplicationContext().getBean("freeMarkerService");
		//mailer = (CaaersJavaMailSender)getDeployedApplicationContext().getBean("mailer");
		mailer = mailer = new MailerStub(){
			
			@Override
			public void sendMail(String[] to, String subject, String content,
		    		String[] attachmentFilePaths) {
				assertNotNull(to);
				assertEquals(1, to.length);
				assertEquals("EF@no-email.com", to[0]);
				assertEquals("no-subject1", subject);
				assertEquals("This is test", content);
				assertEquals(0, attachmentFilePaths.length);
			}
		};
		notificationDao = (NotificationDao)getDeployedApplicationContext().getBean("notificationDao");
		ruleSetDao = (RuleSetDao)getDeployedApplicationContext().getBean("ruleSetDao");
		studyParticipantAssignmentDao = (StudyParticipantAssignmentDao)getDeployedApplicationContext().getBean("studyParticipantAssignmentDao");
		SafetyMonitoringServiceImpl safetyMonitoringServiceImpl = new SafetyMonitoringServiceImpl();
		safetyMonitoringServiceImpl.setAdverseEventDao(adverseEventDao);
		safetyMonitoringServiceImpl.setEvaluationService(evaluationService);
		safetyMonitoringServiceImpl.setFreeMarkerService(freeMarkerService);
		safetyMonitoringServiceImpl.setMailer(mailer);
		safetyMonitoringServiceImpl.setNotificationDao(notificationDao);
		safetyMonitoringServiceImpl.setObservedAdverseEventProfileDao(observedAdverseEventProfileDao);
		safetyMonitoringServiceImpl.setStudyParticipantAssignmentDao(studyParticipantAssignmentDao);
		safetyMonitoringServiceImpl.setRuleSetDao(ruleSetDao);
		safetyMonitoringService = safetyMonitoringServiceImpl;
		caaersRulesEngineService = (CaaersRulesEngineService) getDeployedApplicationContext().getBean("caaersRulesEngineService");
		setupRules();
	}
	
	private void setupRules() throws Exception{
		InputStream in = RuleUtil.getResouceAsStream("safety_signalling_rules_study_1A.xml");
        String xml = RuleUtil.getFileContext(in);
        System.out.println(xml);
        File f = File.createTempFile("r_"+ System.currentTimeMillis(), "sae.xml");
        System.out.println(f.getAbsolutePath());
        FileWriter fw = new FileWriter(f);
        IOUtils.write(xml, fw);
        IOUtils.closeQuietly(fw);
        assertTrue(f.exists());
        assertTrue(findRuleSets().isEmpty());
        caaersRulesEngineService.importRules(f.getAbsolutePath());
        f.delete();
        List<RuleSet> ruleSets = findRuleSets();
        assertFalse(ruleSets.isEmpty());
        RuleSet rs = ruleSets.get(0);
        assertTrue(rs.isEnabled());
	}
	
	public void testGenerateSafetyAlerts() throws Exception {
		assertEquals(0, observedAdverseEventProfileDao.getAll().size());
		safetyMonitoringService.generateSafetyAlerts();
		observedAdverseEventProfiles = observedAdverseEventProfileDao.getAll();
		for(ObservedAdverseEventProfile observedAdverseEventProfile : observedAdverseEventProfiles){
			System.out.println(observedAdverseEventProfile);
		}
		assertEquals(6, observedAdverseEventProfiles.size());
		ObservedAdverseEventProfile observedAdverseEventProfile = getObservedAdverseEventProfile(-1, -1, Grade.getByCode(1));
		assertEquals(20.0, observedAdverseEventProfile.getExpectedFrequency());
		assertEquals(0.0, observedAdverseEventProfile.getObservedFrequency());
		assertEquals(0, observedAdverseEventProfile.getObservedNoOfAE().intValue());
		assertEquals(5, observedAdverseEventProfile.getTotalNoOfRegistrations().intValue());
		
		observedAdverseEventProfile = getObservedAdverseEventProfile(-1, -1, Grade.getByCode(2));
		assertEquals(10.0, observedAdverseEventProfile.getExpectedFrequency());
		assertEquals(0.0, observedAdverseEventProfile.getObservedFrequency());
		assertEquals(0, observedAdverseEventProfile.getObservedNoOfAE().intValue());
		assertEquals(5, observedAdverseEventProfile.getTotalNoOfRegistrations().intValue());
		
		observedAdverseEventProfile = getObservedAdverseEventProfile(-1, -1, Grade.getByCode(3));
		assertEquals("4.34", format(observedAdverseEventProfile.getExpectedFrequency()));
		assertEquals(0.0, observedAdverseEventProfile.getObservedFrequency());
		assertEquals(0, observedAdverseEventProfile.getObservedNoOfAE().intValue());
		assertEquals(5, observedAdverseEventProfile.getTotalNoOfRegistrations().intValue());
		
		observedAdverseEventProfile = getObservedAdverseEventProfile(-1, -1, Grade.getByCode(4));
		assertEquals("6.23", format(observedAdverseEventProfile.getExpectedFrequency()));
		assertEquals(0.0, observedAdverseEventProfile.getObservedFrequency());
		assertEquals(0, observedAdverseEventProfile.getObservedNoOfAE().intValue());
		assertEquals(5, observedAdverseEventProfile.getTotalNoOfRegistrations().intValue());
		
		observedAdverseEventProfile = getObservedAdverseEventProfile(-1, -1, Grade.getByCode(5));
		assertEquals("20.00", format(observedAdverseEventProfile.getExpectedFrequency()));
		assertEquals("40.00", format(observedAdverseEventProfile.getObservedFrequency()));
		assertEquals(2, observedAdverseEventProfile.getObservedNoOfAE().intValue());
		assertEquals(5, observedAdverseEventProfile.getTotalNoOfRegistrations().intValue());
		
		observedAdverseEventProfile = getObservedAdverseEventProfile(-1, -1, null);
		assertEquals("60.00", format(observedAdverseEventProfile.getExpectedFrequency()));
		assertEquals("40.00", format(observedAdverseEventProfile.getObservedFrequency()));
		assertEquals(2, observedAdverseEventProfile.getObservedNoOfAE().intValue());
		assertEquals(5, observedAdverseEventProfile.getTotalNoOfRegistrations().intValue());
	}
	
	public List<RuleSet> findRuleSets(){
        RuleSetQuery ruleSetQuery = new RuleSetQuery();
        ruleSetQuery.filterByRuleType(RuleType.SAFETY_SIGNALLING_RULES);
        ruleSetQuery.filterByStatus(RuleSet.STATUS_ENABLED);
        ruleSetQuery.filterByStudyId(1);
        return (List<RuleSet>)caaersRulesEngineService.getRuleSetDao().search(ruleSetQuery);
    }
	
	private ObservedAdverseEventProfile getObservedAdverseEventProfile(Integer termId, Integer tacId, Grade grade){
		for(ObservedAdverseEventProfile observedAdverseEventProfile : observedAdverseEventProfiles){
			boolean flag = (grade == null && observedAdverseEventProfile.getGrade() == null) || (grade != null && observedAdverseEventProfile.getGrade() != null && grade == observedAdverseEventProfile.getGrade());
			if(flag && observedAdverseEventProfile.getTreatmentAssignment().getId().equals(tacId)
					&& observedAdverseEventProfile.getTerm().getId().equals(termId)){
				return observedAdverseEventProfile;
			}
		}
		return null;
	}
	
	private String format(Double d){
		if (d == null) return null;
        DecimalFormat f = new DecimalFormat("##.00");  // this will help always keeps in two decimal places
        return f.format(d); 
	}

}
