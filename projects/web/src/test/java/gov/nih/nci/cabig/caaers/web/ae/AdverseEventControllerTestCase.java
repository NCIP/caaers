package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.web.WebTestCase;
import gov.nih.nci.cabig.caaers.dao.AnatomicSiteDao;
import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.StudyDiseaseDao;
import gov.nih.nci.cabig.caaers.dao.StudyParticipantAssignmentDao;
import gov.nih.nci.cabig.caaers.dao.CtcDao;
import gov.nih.nci.cabig.caaers.dao.CtcTermDao;
import gov.nih.nci.cabig.caaers.dao.AdverseEventReportDao;
import gov.nih.nci.cabig.caaers.dao.AgentDao;
import gov.nih.nci.cabig.caaers.dao.StudyAgentDao;
import gov.nih.nci.cabig.caaers.rules.runtime.RuleExecutionService;
import gov.nih.nci.cabig.caaers.dao.CtepStudyDiseaseDao;
import gov.nih.nci.cabig.caaers.dao.PriorTherapyDao;

/**
 * @author Rhett Sutphin
 */
public abstract class AdverseEventControllerTestCase extends WebTestCase {
    protected TabAutowirer autowirer;

    protected ParticipantDao participantDao;
    protected StudyDao studyDao;
    protected CtcDao ctcDao;
    protected CtcTermDao ctcTermDao;
    protected StudyParticipantAssignmentDao assignmentDao;
    protected AdverseEventReportDao adverseEventReportDao;
    protected RuleExecutionService ruleExecutionService;
    protected StudyAgentDao studyAgentDao;
    protected AgentDao agentDao;
    protected CtepStudyDiseaseDao ctepStudyDiseaseDao;
    protected AnatomicSiteDao anatomicSiteDao;
    protected PriorTherapyDao priorTherapyDao;
 	
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        participantDao = registerDaoMockFor(ParticipantDao.class);
        studyDao = registerDaoMockFor(StudyDao.class);
        assignmentDao = registerDaoMockFor(StudyParticipantAssignmentDao.class);
        ctcDao = registerDaoMockFor(CtcDao.class);
        ctcTermDao = registerDaoMockFor(CtcTermDao.class);
        adverseEventReportDao = registerDaoMockFor(AdverseEventReportDao.class);
        ruleExecutionService = registerMockFor(RuleExecutionService.class);
        agentDao = registerDaoMockFor(AgentDao.class);
        studyAgentDao = registerDaoMockFor(StudyAgentDao.class);
        ctepStudyDiseaseDao = registerDaoMockFor(CtepStudyDiseaseDao.class);
        anatomicSiteDao = registerDaoMockFor(AnatomicSiteDao.class);
        priorTherapyDao = registerDaoMockFor(PriorTherapyDao.class);
     
        autowirer = new TabAutowirer();
        autowirer.setAdverseEventReportDao(adverseEventReportDao);
        autowirer.setAssignmentDao(assignmentDao);
        autowirer.setCtcDao(ctcDao);
        autowirer.setCtcTermDao(ctcTermDao);
        autowirer.setParticipantDao(participantDao);
        autowirer.setRuleExecutionService(ruleExecutionService);
        autowirer.setStudyDao(studyDao);
    }
}
