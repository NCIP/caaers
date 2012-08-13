package gov.nih.nci.cabig.caaers.web.participant;

import gov.nih.nci.cabig.caaers.CaaersContextLoader;
import gov.nih.nci.cabig.caaers.dao.StudySiteDao;
import gov.nih.nci.cabig.caaers.domain.LocalOrganization;
import gov.nih.nci.cabig.caaers.domain.LocalStudy;
import gov.nih.nci.cabig.caaers.domain.OrganizationAssignedIdentifier;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.domain.SystemAssignedIdentifier;
import gov.nih.nci.cabig.caaers.domain.repository.StudyRepository;
import gov.nih.nci.cabig.caaers.security.CaaersSecurityFacade;
import gov.nih.nci.cabig.caaers.web.utils.ConfigPropertyHelper;

import java.util.Map;

import org.easymock.EasyMock;
import org.springframework.context.ApplicationContext;

/**
 * @author Ramakrishna
 */

@SuppressWarnings("unchecked")
public class SelectStudyForParticipantTabTest extends AbstractTabTestCase<SelectStudyForParticipantTab, ParticipantInputCommand> {

    protected SelectStudyForParticipantTab selectStudyForParticipantTab;
    protected ParticipantInputCommand newParticipantCommand;
    protected CaaersSecurityFacade facade;
    protected StudyRepository studyRepository;
    protected StudySiteDao studySiteDao;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        selectStudyForParticipantTab = createTab();
        ConfigPropertyHelper.putParticipantIdentifiersType(configProperty);
        newParticipantCommand.setStudy(new LocalStudy());
        newParticipantCommand.setAssignment(new StudyParticipantAssignment());
        studyRepository = registerMockFor(StudyRepository.class);
        selectStudyForParticipantTab.setStudyRepository(studyRepository);
        studySiteDao = registerDaoMockFor(StudySiteDao.class);
        selectStudyForParticipantTab.setStudySiteDao(studySiteDao);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    @Override
    protected SelectStudyForParticipantTab createTab() {
        selectStudyForParticipantTab = new SelectStudyForParticipantTab();
        selectStudyForParticipantTab.setListValues(listValues);
        selectStudyForParticipantTab.setStudyRepository(studyRepository);
        return selectStudyForParticipantTab;
    }

    @Override
    protected ParticipantInputCommand createCommand() {
        newParticipantCommand = new ParticipantInputCommand();
        newParticipantCommand.setParticipant(new Participant());
        newParticipantCommand.setOrganization(new LocalOrganization());
        newParticipantCommand.getOrganization().setId(-1);
        newParticipantCommand.setTargetPage(2);
        return newParticipantCommand;
    }


    public void testGroupDisplayNames() throws Exception {
        assertDisplayNameForFieldGroup(null, "studySubjectIdentifier");
    }

    public void testStudySubjectIdentifierFields() throws Exception {
        assertFieldProperties("studySubjectIdentifier", "assignment.studySubjectIdentifier");
    }


    @Override
    protected Map<String, Object> createReferenceData() {
        Map<String, Object> referenceData = getTab().referenceData(getCommand());
        return referenceData;
    }

    public void testOnBind() {
    	
    	Study s = new LocalStudy();
    	s.setId(2);
    	OrganizationAssignedIdentifier primaryId = new OrganizationAssignedIdentifier();
    	primaryId.setValue("val");
    	primaryId.setPrimaryIndicator(true);
    	s.getIdentifiers().add(primaryId);
    	request.setParameter("studyId", "2");
    	newParticipantCommand.setStudy(s);
    	StudySite ss = new StudySite();
    	ss.setStudy(s);
    	EasyMock.expect(studySiteDao.findByStudyAndOrganization(EasyMock.anyInt(), EasyMock.anyInt(), EasyMock.anyBoolean())).andReturn(ss);
    	replayMocks();
    	selectStudyForParticipantTab.onBind(request, newParticipantCommand, errors);
    	verifyMocks();
    }
    
    public void testReferenceData(){
    	
    	Study s = new LocalStudy();
    	OrganizationAssignedIdentifier primaryId = new OrganizationAssignedIdentifier();
    	primaryId.setValue("val");
    	primaryId.setPrimaryIndicator(true);
    	s.getIdentifiers().add(primaryId);
    	request.setParameter("studyId", "2");
    	EasyMock.expect(studyRepository.getById(2)).andReturn(s);
    	replayMocks();
    	
    	Map<Object,Object> refData = selectStudyForParticipantTab.referenceData(request, newParticipantCommand);
    	verifyMocks();
    	assertEquals("val",newParticipantCommand.getSearchText());
    	assertEquals("idtf",newParticipantCommand.getSearchType());
    }
    

    public synchronized ApplicationContext getDeployedApplicationContext() {
        return CaaersContextLoader.getApplicationContext();
    }
}
