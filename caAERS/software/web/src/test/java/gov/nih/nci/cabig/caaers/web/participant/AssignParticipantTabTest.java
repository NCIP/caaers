package gov.nih.nci.cabig.caaers.web.participant;

import gov.nih.nci.cabig.caaers.CaaersContextLoader;
import gov.nih.nci.cabig.caaers.domain.DateValue;
import gov.nih.nci.cabig.caaers.domain.LocalOrganization;
import gov.nih.nci.cabig.caaers.domain.LocalStudy;
import gov.nih.nci.cabig.caaers.domain.OrganizationAssignedIdentifier;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.SystemAssignedIdentifier;
import gov.nih.nci.cabig.caaers.security.CaaersSecurityFacade;
import gov.nih.nci.cabig.caaers.web.utils.ConfigPropertyHelper;

import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Biju Joseph
 * @author Ion C. Olaru
 */

@SuppressWarnings("unchecked")
public class AssignParticipantTabTest extends AbstractTabTestCase<AssignParticipantTab, AssignParticipantStudyCommand> {

    private AssignParticipantTab assignParticipantTab;
    private AssignParticipantStudyCommand assignParticipantStudyCommand;
    private CaaersSecurityFacade facade;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        assignParticipantTab = createTab();
        ConfigPropertyHelper.putParticipantIdentifiersType(configProperty);
        assignParticipantStudyCommand.setStudy(new LocalStudy());
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    @Override
    protected AssignParticipantTab createTab() {
        assignParticipantTab = new AssignParticipantTab();
        assignParticipantTab.setListValues(listValues);
        return assignParticipantTab;
    }

    @Override
    protected AssignParticipantStudyCommand createCommand() {
    	assignParticipantStudyCommand = new AssignParticipantStudyCommand();
    	assignParticipantStudyCommand.setParticipant(new Participant());
    	assignParticipantStudyCommand.setOrganization(new LocalOrganization());
    	assignParticipantStudyCommand.getOrganization().setId(-1);
    	assignParticipantStudyCommand.setTargetPage(2);
        return assignParticipantStudyCommand;
    }

    
    public void testValidateDateOfBirth() throws Exception {
    	assignParticipantStudyCommand.getParticipant().setDateOfBirth(new DateValue(2011));
        doValidate();
        assertEquals("Wrong number of errors for " + "participant.dateOfBirth", 0, errors.getFieldErrorCount("participant.dateOfBirth"));
    }

    public void testValidateIdentifiers() throws Exception {
    	assignParticipantStudyCommand.getParticipant().getIdentifiers().add(new SystemAssignedIdentifier());
        doValidate();
        assertEquals("Wrong number of errors for " + "participant.identifiers", 0, errors.getFieldErrorCount("participant.identifiers"));
    }
    
    public void testValidateNullParticipant() throws Exception {
    	assertFalse(errors.hasErrors());
    	assignParticipantStudyCommand.setParticipant(null);
    	assignParticipantTab.validate(assignParticipantStudyCommand, null, null, errors);
        assertEquals(1,errors.getErrorCount());
        assertEquals("PT_001",errors.getAllErrors().get(0).getCode());
    }
    
    public void testSearchSubjects() throws Exception{
    	request.setParameter("_asyncViewName", "subject_results");
    	ModelAndView mv = assignParticipantTab.searchSubjects(request, assignParticipantStudyCommand, errors);
    	assertNotNull(mv);
    	assertEquals("subject_results",mv.getViewName());
    }

    
    public synchronized ApplicationContext getDeployedApplicationContext() {
        return CaaersContextLoader.getApplicationContext();
    }

	@Override
	protected Map<String, Object> createReferenceData() {
		return null;
	}
}
