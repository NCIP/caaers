package gov.nih.nci.cabig.caaers.web.participant;

import gov.nih.nci.cabig.caaers.domain.DateValue;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.SystemAssignedIdentifier;
import gov.nih.nci.cabig.caaers.domain.repository.OrganizationRepository;
import gov.nih.nci.cabig.caaers.web.utils.ConfigPropertyHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Biju Joseph
 */
public class CreateParticipantTabTest extends AbstractTabTestCase<CreateParticipantTab, ParticipantInputCommand> {

    private CreateParticipantTab createParticipantTab;

    private ParticipantInputCommand newParticipantCommand;
    private OrganizationRepository organizationRepository;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        ConfigPropertyHelper.putParticipantIdentifiersType(configProperty);
/*
        organizationDao = registerDaoMockFor(OrganizationDao.class);
        organizationRepository = registerDaoMockFor(OrganizationDao.class);
*/
    }


    @Override
    protected CreateParticipantTab createTab() {
        createParticipantTab = new CreateParticipantTab();
        createParticipantTab.setOrganizationRepository(new OrganizationRepository(){
        	public void create(Organization organization) {
        		// TODO Auto-generated method stub
        		
        	}
        	public void createOrUpdate(Organization organization) {
        		// TODO Auto-generated method stub
        		
        	}
        	public List<Organization> getOrganizationsHavingStudySites() {
        		return new ArrayList<Organization>();
        	}
        	
        });
        createParticipantTab.setListValues(listValues);
        createParticipantTab.setConfigurationProperty(configProperty);

        return createParticipantTab;
    }

    @Override
    protected ParticipantInputCommand createCommand() {
        newParticipantCommand = new ParticipantInputCommand();
        newParticipantCommand.setParticipant(new Participant());

        return newParticipantCommand;
    }


    public void testGroupDisplayNames() throws Exception {
        assertDisplayNameForFieldGroup(null, "site");
        assertDisplayNameForFieldGroup(null, "participant");
    }

    public void testSiteFields() throws Exception {
        assertFieldProperties("site", "organization");
    }

    public void testParticipantFields() throws Exception {
        assertFieldProperties("participant",
                "participant.firstName",
                "participant.lastName",
                "participant.maidenName",
                "participant.middleName",
                "participant.dateOfBirth",
                "participant.gender",
                "participant.ethnicity",
                "participant.race"
        );
    }


    @Override
    protected Map<String, Object> createReferenceData() {
        List<Organization> organizations = new ArrayList<Organization>();
//        EasyMock.expect(organizationRepository.getOrganizationsHavingStudySites()).andReturn(organizations);
//        replayMocks();
        Map<String, Object> referenceData = getTab().referenceData(getCommand());
//        verifyMocks();
        return referenceData;
    }

    public void testValidateDateOfBirth() throws Exception {
        newParticipantCommand.getParticipant().setDateOfBirth(new DateValue(2011));
        doValidate();
        assertFieldRequiredCustomErrorMessageRaised("participant.dateOfBirth",
                "Incorrect Date Of Birth");
    }

    public void testValidateIdentifiers() throws Exception {
        newParticipantCommand.getParticipant().getIdentifiers().add(new SystemAssignedIdentifier());
        doValidate();
        assertFieldRequiredCustomErrorMessageRaised("participant.identifiers",
                "Please Include exactly One Primary Identifier");
    }
  

}
