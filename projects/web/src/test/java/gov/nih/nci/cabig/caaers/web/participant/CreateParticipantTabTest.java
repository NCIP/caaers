package gov.nih.nci.cabig.caaers.web.participant;

import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.domain.DateValue;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.SystemAssignedIdentifier;
import gov.nih.nci.cabig.caaers.web.utils.ConfigPropertyHelper;
import org.easymock.classextension.EasyMock;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Biju Joseph
 */
public class CreateParticipantTabTest extends AbstractTabTest<CreateParticipantTab, NewParticipantCommand> {

    private CreateParticipantTab createParticipantTab;

    private NewParticipantCommand newParticipantCommand;
    private OrganizationDao organizationDao;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        ConfigPropertyHelper.putParticipantIdentifiersType(configProperty);

        organizationDao = registerDaoMockFor(OrganizationDao.class);
    }


    @Override
    protected CreateParticipantTab createTab() {
        createParticipantTab = new CreateParticipantTab();
        createParticipantTab.setOrganizationDao(organizationDao);
        createParticipantTab.setListValues(listValues);
        createParticipantTab.setConfigurationProperty(configProperty);

        return createParticipantTab;
    }

    @Override
    protected NewParticipantCommand createCommand() {
        newParticipantCommand = new NewParticipantCommand();

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
        EasyMock.expect(organizationDao.getOrganizationsHavingStudySites()).andReturn(organizations);
        replayMocks();
        Map<String, Object> referenceData = getTab().referenceData(getCommand());
        verifyMocks();
        return referenceData;
    }

    public void testValidateDateOfBirth() throws Exception {
        newParticipantCommand.getParticipant().setDateOfBirth(new DateValue(2009));
        doValidate();
        assertFieldRequiredCustomErrorMessageRaised("participant.dateOfBirth",
                "Incorrect Date Of Birth");
    }

    public void testValidateIdentifiers() throws Exception {
        newParticipantCommand.getParticipant().getIdentifiers().add(new SystemAssignedIdentifier());
        doValidate();
        assertFieldRequiredCustomErrorMessageRaised("participant.identifiers",
                "Please Include at least a single primary Identifier");
    }


    public void testInValidCommand() throws Exception {
        doValidate();
        assertFieldRequiredCustomErrorMessageRaised("participant.dateOfBirth",
                "Incorrect Date Of Birth");
        assertFieldRequiredCustomErrorMessageRaised("participant.identifiers",
                "Please Include at least a single primary Identifier");
    }

    public void testValidCommand() throws Exception {
        final SystemAssignedIdentifier systemAssignedIdentifier = Fixtures.createSystemAssignedIdentifier("value");
        systemAssignedIdentifier.setPrimaryIndicator(Boolean.TRUE);

        newParticipantCommand.getParticipant().getIdentifiers().add(systemAssignedIdentifier);
        newParticipantCommand.getParticipant().setDateOfBirth(new DateValue(19, 4, 1967));

        doValidate();
        assertTrue("There should not be any error ", errors.getAllErrors().isEmpty());
    }

}
