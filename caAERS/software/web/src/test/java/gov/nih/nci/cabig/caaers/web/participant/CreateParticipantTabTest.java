package gov.nih.nci.cabig.caaers.web.participant;

import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.dao.query.OrganizationQuery;
import gov.nih.nci.cabig.caaers.domain.*;
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
        newParticipantCommand.setStudy(new LocalStudy());
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

/*
* The Participant has 2 organization identifiers with the same value, organization and type
* This should throw an error about duplicate identifiers
*
* */
    public void testValidateUniqueness1() throws Exception {
        OrganizationAssignedIdentifier i1 = new OrganizationAssignedIdentifier();
        i1.setOrganization(new LocalOrganization());
        i1.getOrganization().setNciInstituteCode("O1");
        i1.setType("T1");
        i1.setValue("V1");
        
        OrganizationAssignedIdentifier i2 = new OrganizationAssignedIdentifier();
        i2.setOrganization(new LocalOrganization());
        i2.getOrganization().setNciInstituteCode("O1");
        i2.setType("T1");
        i2.setValue("V1");

        newParticipantCommand.getParticipant().getIdentifiers().add(i1);
        newParticipantCommand.getParticipant().getIdentifiers().add(i2);

        doValidate();

        assertEquals("Wrong number of errors for " + "study.identifiersLazy[1].type", 1, errors.getFieldErrorCount("study.identifiersLazy[1].type"));
    }

/*
* The Participant has 2 organization identifiers with the same value, organization and different type
* This should not throw an error about duplicate identifiers
*
* */
    public void testValidateUniqueness2() throws Exception {
        OrganizationAssignedIdentifier i1 = new OrganizationAssignedIdentifier();
        i1.setOrganization(new LocalOrganization());
        i1.getOrganization().setNciInstituteCode("O1");
        i1.setType("T1");
        i1.setValue("V1");

        OrganizationAssignedIdentifier i2 = new OrganizationAssignedIdentifier();
        i2.setOrganization(new LocalOrganization());
        i2.getOrganization().setNciInstituteCode("O1");
        i2.setType("T2");
        i2.setValue("V1");

        newParticipantCommand.getParticipant().getIdentifiers().add(i1);
        newParticipantCommand.getParticipant().getIdentifiers().add(i2);

        doValidate();

        assertEquals("Wrong number of errors for " + "study.identifiersLazy[1].type", 0, errors.getFieldErrorCount("study.identifiersLazy[1].type"));
    }

/*
* The Participant has 2 system identifiers with the same value, system and type
* This should throw an error about duplicate identifiers
*
* */
    public void testValidateUniqueness3() throws Exception {
        SystemAssignedIdentifier i1 = new SystemAssignedIdentifier();
        i1.setSystemName("S1");
        i1.setType("T1");
        i1.setValue("V1");

        SystemAssignedIdentifier i2 = new SystemAssignedIdentifier();
        i2.setSystemName("S1");
        i2.setType("T1");
        i2.setValue("V1");

        newParticipantCommand.getParticipant().getIdentifiers().add(i1);
        newParticipantCommand.getParticipant().getIdentifiers().add(i2);

        doValidate();

        assertEquals("Wrong number of errors for " + "study.identifiersLazy[1].type", 1, errors.getFieldErrorCount("study.identifiersLazy[1].type"));
    }

/*
* The Participant has 2 system identifiers with the same value, different system and teh same type
* This should not throw an error about duplicate identifiers
*
* */
    public void testValidateUniqueness4() throws Exception {
        SystemAssignedIdentifier i1 = new SystemAssignedIdentifier();
        i1.setSystemName("S1");
        i1.setType("T1");
        i1.setValue("V1");

        SystemAssignedIdentifier i2 = new SystemAssignedIdentifier();
        i2.setSystemName("S2");
        i2.setType("T1");
        i2.setValue("V1");

        newParticipantCommand.getParticipant().getIdentifiers().add(i1);
        newParticipantCommand.getParticipant().getIdentifiers().add(i2);

        doValidate();

        assertEquals("Wrong number of errors for " + "study.identifiersLazy[1].type", 0, errors.getFieldErrorCount("study.identifiersLazy[1].type"));
    }

/*
* The Participant has 3 identifiers
* This should not throw an error about duplicate identifiers
*
* */
    public void testValidateUniqueness5() throws Exception {
        SystemAssignedIdentifier i1 = new SystemAssignedIdentifier();
        i1.setSystemName("S1");
        i1.setType("T1");
        i1.setValue("V1");

        SystemAssignedIdentifier i2 = new SystemAssignedIdentifier();
        i2.setSystemName("S2");
        i2.setType("T1");
        i2.setValue("V1");

        OrganizationAssignedIdentifier i3 = new OrganizationAssignedIdentifier();
        i3.setOrganization(new LocalOrganization());
        i3.getOrganization().setNciInstituteCode("O3");
        i3.setType("T1");
        i3.setValue("V1");

        newParticipantCommand.getParticipant().getIdentifiers().add(i1);
        newParticipantCommand.getParticipant().getIdentifiers().add(i2);
        newParticipantCommand.getParticipant().getIdentifiers().add(i3);

        doValidate();

        assertEquals("Wrong number of errors for " + "study.identifiersLazy[1].type", 0, errors.getFieldErrorCount("study.identifiersLazy[1].type"));
    }


}
