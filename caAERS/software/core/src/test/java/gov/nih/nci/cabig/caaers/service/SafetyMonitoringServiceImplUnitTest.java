/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.service;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.dao.AdverseEventDao;
import gov.nih.nci.cabig.caaers.dao.NotificationDao;
import gov.nih.nci.cabig.caaers.dao.ObservedAdverseEventProfileDao;
import gov.nih.nci.cabig.caaers.dao.RuleSetDao;
import gov.nih.nci.cabig.caaers.dao.StudyParticipantAssignmentDao;
import gov.nih.nci.cabig.caaers.dao.query.NotificationQuery;
import gov.nih.nci.cabig.caaers.domain.AbstractStudyInterventionExpectedAE;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.CtcTerm;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.Grade;
import gov.nih.nci.cabig.caaers.domain.Notification;
import gov.nih.nci.cabig.caaers.domain.NotificationStatus;
import gov.nih.nci.cabig.caaers.domain.ObservedAdverseEventProfile;
import gov.nih.nci.cabig.caaers.domain.RuleSet;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.TreatmentAssignment;
import gov.nih.nci.cabig.caaers.domain.dto.SafetyRuleEvaluationResultDTO;
import gov.nih.nci.cabig.caaers.rules.common.RuleType;
import gov.nih.nci.cabig.caaers.tools.mail.CaaersJavaMailSender;
import gov.nih.nci.cabig.ctms.domain.DomainObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.easymock.classextension.EasyMock;

public class SafetyMonitoringServiceImplUnitTest extends AbstractTestCase {
	
	private AdverseEventDao adverseEventDao;
	
	private ObservedAdverseEventProfileDao observedAdverseEventProfileDao;
	
	private EvaluationService evaluationService;
	
	private FreeMarkerService freeMarkerService;
	
	private CaaersJavaMailSender mailer;
	
	private NotificationDao notificationDao;
	
	private RuleSetDao ruleSetDao;
	
	private SafetyMonitoringService safetyMonitoringService;
	
	private Study study;
	
	private List<RuleSet> ruleSets = new ArrayList<RuleSet>();
	
	private List<Object[]> aeList = new ArrayList<Object[]>();
	
	private TreatmentAssignment[] tacs;
	
	private List<ObservedAdverseEventProfile> observed = new ArrayList<ObservedAdverseEventProfile>();
	
	private SafetyRuleEvaluationResultDTO dto = new SafetyRuleEvaluationResultDTO();
	
	private Notification notification;
	
	private StudyParticipantAssignmentDao studyParticipantAssignmentDao;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		adverseEventDao = registerMockFor(AdverseEventDao.class);
		observedAdverseEventProfileDao = registerMockFor(ObservedAdverseEventProfileDao.class);
		evaluationService = registerMockFor(EvaluationService.class);
		freeMarkerService = registerMockFor(FreeMarkerService.class);
		mailer = registerMockFor(CaaersJavaMailSender.class);
		notificationDao = registerMockFor(NotificationDao.class);
		ruleSetDao = registerMockFor(RuleSetDao.class);
		studyParticipantAssignmentDao = registerMockFor(StudyParticipantAssignmentDao.class);
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
		setupStudy();
	}
	
	private void setupStudy(){
		study = Fixtures.createStudy("some short title");
		TreatmentAssignment tac1 = Fixtures.createTreatmentAssignment();
		TreatmentAssignment tac2 = Fixtures.createTreatmentAssignment();
		tac1.setId(1);
		tac2.setId(2);
		tacs = new TreatmentAssignment[]{tac1, tac2};
		RuleSet ruleSet = Fixtures.createRuleSet(RuleType.SAFETY_SIGNALLING_RULES);
		ruleSet.setStudy(study);
		ruleSets.add(ruleSet);
		CtcTerm term = Fixtures.createCtcTerm("ctc1", "ctc_code1");
		term.setId(1);
		AbstractStudyInterventionExpectedAE exp = Fixtures.createAbstractStudyInterventionExpectedAE();
		exp.setTerm(term);
		StudyParticipantAssignment spa1 = new StudyParticipantAssignment();
		spa1.setId(1);
		StudyParticipantAssignment spa2 = new StudyParticipantAssignment();
		spa2.setId(2);
		AdverseEvent ae1a = createAE(term, Grade.getByCode(1));
		AdverseEvent ae1b = createAE(term, Grade.getByCode(1));
		AdverseEvent ae1c = createAE(term, Grade.getByCode(1));
		AdverseEvent ae2 = createAE(term, Grade.getByCode(2));
		AdverseEvent ae3 = createAE(term, Grade.getByCode(3));
		AdverseEvent ae4 = createAE(term, Grade.getByCode(4));
		AdverseEvent ae5 = createAE(term, Grade.getByCode(5));
		aeList.add(new Object[]{ae1a, exp, tac1, spa1});
		aeList.add(new Object[]{ae1b, exp, tac1, spa1});
		aeList.add(new Object[]{ae1c, exp, tac1, spa1});
		aeList.add(new Object[]{ae2, exp, tac1, spa1});
		aeList.add(new Object[]{ae3, exp, tac1, spa1});
		aeList.add(new Object[]{ae4, exp, tac1, spa1});
		aeList.add(new Object[]{ae5, exp, tac1, spa1});
		
		AdverseEvent ae1 = createAE(term, Grade.getByCode(1));
		ae2 = createAE(term, Grade.getByCode(2));
		ae3 = createAE(term, Grade.getByCode(3));
		AdverseEvent ae4a = createAE(term, Grade.getByCode(4));
		AdverseEvent ae4b = createAE(term, Grade.getByCode(4));
		AdverseEvent ae5a = createAE(term, Grade.getByCode(5));
		AdverseEvent ae5b = createAE(term, Grade.getByCode(5));
		aeList.add(new Object[]{ae1, exp, tac2, spa2});
		aeList.add(new Object[]{ae2, exp, tac2, spa2});
		aeList.add(new Object[]{ae3, exp, tac2, spa2});
		aeList.add(new Object[]{ae4a, exp, tac2, spa2});
		aeList.add(new Object[]{ae4b, exp, tac2, spa2});
		aeList.add(new Object[]{ae5a, exp, tac2, spa2});
		aeList.add(new Object[]{ae5b, exp, tac2, spa2});
		
		ObservedAdverseEventProfile o1 = new ObservedAdverseEventProfile(tac1, term, Grade.getByCode(1));
		ObservedAdverseEventProfile o2 = new ObservedAdverseEventProfile(tac1, term, null);
		observed.add(o1);
		observed.add(o2);
		
		notification = new Notification();
		notification.setRecipientEmails(Arrays.asList(new String[]{"ab@cd.com"}));
		notification.setContent("Some freemarker content");
		notification.setSubject("Some subject");
	}
	
	private AdverseEvent createAE(DomainObject term, Grade grade){
		AdverseEvent ae = Fixtures.createAdverseEvent(term.getId(), grade);
		ae.getAdverseEventTerm().setTerm(term);
		return ae;
	}
	
	
	public void testGenerateSafetyAlerts() throws Exception {
		dto.setNotificationStatus(NotificationStatus.NOTIFY);
		EasyMock.expect(ruleSetDao.getRuleSetForSafetySignalling()).andReturn(ruleSets);
		EasyMock.expect(adverseEventDao.getAllAEsForSafetySignaling(study)).andReturn(aeList);
		EasyMock.expect(observedAdverseEventProfileDao.getByTACs(EasyMock.isA(TreatmentAssignment[].class))).andReturn(observed); 
		EasyMock.expect(freeMarkerService.applyRuntimeReplacementsForReport(EasyMock.isA(String.class), EasyMock.isA(HashMap.class))).andReturn("Parsed Content");
		observedAdverseEventProfileDao.save(EasyMock.isA(ObservedAdverseEventProfile.class));
		EasyMock.expectLastCall().times(12);
		EasyMock.expect(evaluationService.evaluateSafetySignallingRules(EasyMock.isA(ObservedAdverseEventProfile.class))).andReturn(dto).times(12);
		EasyMock.expect(notificationDao.search(EasyMock.isA(NotificationQuery.class))).andReturn((List)Arrays.asList(new Notification[]{notification}));
		EasyMock.expect(studyParticipantAssignmentDao.getCountByTAC(EasyMock.isA(Integer.class))).andReturn(7).times(2);
		mailer.sendMail(EasyMock.isA(String[].class), EasyMock.isA(String.class), EasyMock.isA(String.class), EasyMock.isA(String[].class));
		replayMocks();
		safetyMonitoringService.generateSafetyAlerts();
		verifyMocks();
	}

}
