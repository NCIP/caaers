package gov.nih.nci.cabig.caaers.domain;

import junit.framework.Test;
import junit.framework.TestSuite;
import junit.framework.TestCase;
import gov.nih.nci.cabig.caaers.domain.PersonRole;

/**
 * PersonRole Tester.
 *
 * @author Biju Joseph
 * @since <pre>02/16/2010</pre>
 * 
 */
public class PersonRoleTest extends TestCase {

    PersonRole spi;
    PersonRole si;
    PersonRole pi;
    PersonRole pc;
    PersonRole sc;
    PersonRole ac;
    PersonRole rep;
    PersonRole phy;
    PersonRole cae;
    PersonRole dc;


    public void setUp() throws Exception {
        super.setUp();
        spi = PersonRole.SITE_PRINCIPAL_INVESTIGATOR;
        si = PersonRole.SITE_INVESTIGATOR;
        pi = PersonRole.PRINCIPAL_INVESTIGATOR;
        pc = PersonRole.PARTICIPANT_COORDINATOR;
        sc = PersonRole.STUDY_COORDINATOR;
        ac = PersonRole.ADVERSE_EVENT_COORDINATOR;
        rep = PersonRole.REPORTER;
        phy = PersonRole.PHYSICIAN;
        cae = PersonRole.CENTRAL_OFFICE_SAE_COORDINATOR;
        dc = PersonRole.DATA_COORDINATOR;
    }

    public void tearDown() throws Exception {
        super.tearDown();
    }

    // Will test if all attributes of PersonRole are correct.
    public void testCorrectAttributes() throws Exception {
        
        assertCorrectAttributes(spi,1, "Site Principal Investigator","SPI", UserGroupType.caaers_physician);
	    assertCorrectAttributes(si,2, "Site Investigator","SI", UserGroupType.caaers_physician);
	    assertCorrectAttributes(pi,3, "Principal Investigator","PI", UserGroupType.caaers_physician);
	    assertCorrectAttributes(pc, 4, "Participant Coordinator","caaers_participant_cd", UserGroupType.caaers_participant_cd);
	    assertCorrectAttributes(sc, 5, "Study Coordinator","caaers_study_cd", UserGroupType.caaers_study_cd);
        assertCorrectAttributes(ac, 6, "Adverse Event Coordinator", "caaers_ae_cd", UserGroupType.caaers_ae_cd);
	    assertCorrectAttributes(rep, 7, "Reporter", "PC", UserGroupType.caaers_participant_cd);
        assertCorrectAttributes (phy, 8, "Physician", "SI", UserGroupType.caaers_physician);
	    assertCorrectAttributes(cae, 9, "Central Office Report Reviewer","caaers_central_office_sae_cd", UserGroupType.caaers_central_office_sae_cd);
        assertCorrectAttributes(dc, 10, "Data Coordinator", "caaers_data_cd", UserGroupType.caaers_data_cd);

    }

    public void assertCorrectAttributes(PersonRole pr, Integer code, String displayName, String roleCode, UserGroupType... groups){
        assertEquals("Incorrect Code", code, pr.getCode());
        assertEquals("Incorrect displayName ", displayName, pr.getDisplayName());
        assertEquals("Incorrect roleCode", roleCode, pr.getRoleCode());
    }
}
