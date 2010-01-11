package gov.nih.nci.cabig.caaers.web.participant;

import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.dao.query.OrganizationQuery;
import gov.nih.nci.cabig.caaers.domain.DateValue;
import gov.nih.nci.cabig.caaers.domain.LocalOrganization;
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
 * @author Ion C. Olaru
 */
public class CreateParticipantTabTest extends AbstractTabTestCase<CreateParticipantTab, ParticipantInputCommand> {

    private CreateParticipantTab createParticipantTab;
    private ParticipantInputCommand newParticipantCommand;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        ConfigPropertyHelper.putParticipantIdentifiersType(configProperty);
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
			public void convertToRemote(Organization localOrganization,
					Organization remoteOrganization) {
				// TODO Auto-generated method stub
				
			}
			public List<Organization> getAll() {
				// TODO Auto-generated method stub
				return null;
			}
			public List<Organization> restrictBySubnames(String[] subnames) {
				// TODO Auto-generated method stub
				return null;
			}
			public List<Organization> searchOrganization(OrganizationQuery query) {
				// TODO Auto-generated method stub
				return null;
			}
			public List<Organization> searchRemoteOrganization(String coppaSearchText, String sType) {
				// TODO Auto-generated method stub
				return null;
			}
			public List<Organization> getLocalOrganizations(OrganizationQuery query) {
				// TODO Auto-generated method stub
				return null;
			}
			public List<Organization> getAllOrganizations() {
				// TODO Auto-generated method stub
				return null;
			}
			public List<Organization> getAllNciInstitueCodes() {
				// TODO Auto-generated method stub
				return null;
			}
        });
        createParticipantTab.setListValues(listValues);
        createParticipantTab.setConfigurationProperty(configProperty);
        createParticipantTab.setParticipantDao((ParticipantDao)getDeployedApplicationContext().getBean("participantDao"));
        return createParticipantTab;
    }

    @Override
    protected ParticipantInputCommand createCommand() {
        newParticipantCommand = new ParticipantInputCommand();
        newParticipantCommand.setParticipant(new Participant());
        newParticipantCommand.setOrganization(new LocalOrganization());
        newParticipantCommand.getOrganization().setId(-1);
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
        Map<String, Object> referenceData = getTab().referenceData(getCommand());
        return referenceData;
    }

    public void testValidateDateOfBirth() throws Exception {
        newParticipantCommand.getParticipant().setDateOfBirth(new DateValue(2011));
        doValidate();
        assertEquals("Wrong number of errors for " + "participant.dateOfBirth", 1, errors.getFieldErrorCount("participant.dateOfBirth"));
    }

    public void testValidateIdentifiers() throws Exception {
    	// BJ:	 CAAERS-3293 - relaxed the primary identifier restriction.
        newParticipantCommand.getParticipant().getIdentifiers().add(new SystemAssignedIdentifier());
        doValidate();
        assertEquals("Wrong number of errors for " + "participant.identifiers", 0, errors.getFieldErrorCount("participant.identifiers"));
        
    }
  

}
