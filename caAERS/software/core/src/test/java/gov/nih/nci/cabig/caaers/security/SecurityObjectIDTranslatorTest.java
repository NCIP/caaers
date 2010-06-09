package gov.nih.nci.cabig.caaers.security;

import gov.nih.nci.cabig.caaers.domain.*;
import junit.framework.TestCase;

/**
 * @author: Ion C. Olaru
 * Date: Jun 9, 2010
 * Time: 4:44:57 PM
 */
public class SecurityObjectIDTranslatorTest extends TestCase {

    SecurityObjectIDTranslator t = new SecurityObjectIDTranslator();

    Study localStudy;
    Study remoteStudy;
    Organization localOrganization;
    Organization remoteOrganization;
    Participant p; 

    @Override
    protected void setUp() throws Exception {
        localStudy = new LocalStudy();
        remoteStudy = new RemoteStudy();
        localOrganization = new LocalOrganization();
        remoteOrganization = new RemoteOrganization();
        p = new Participant();
    }

    public void testFromCaAERSToCSM() {
        assertEquals("HealthcareSite", t.fromCaAERSToCSM("gov.nih.nci.cabig.caaers.domain.Organization"));
        assertEquals("HealthcareSite", t.fromCaAERSToCSM("gov.nih.nci.cabig.caaers.domain.LocalOrganization"));
        assertEquals("HealthcareSite", t.fromCaAERSToCSM("gov.nih.nci.cabig.caaers.domain.RemoteOrganization"));
        assertEquals("Study", t.fromCaAERSToCSM("gov.nih.nci.cabig.caaers.domain.Study"));
        assertEquals("Study", t.fromCaAERSToCSM("gov.nih.nci.cabig.caaers.domain.LocalStudy"));
        assertEquals("Study", t.fromCaAERSToCSM("gov.nih.nci.cabig.caaers.domain.RemoteStudy"));
        assertEquals("electron", t.fromCaAERSToCSM("electron"));
    }

    public void testFromCSMToCaAERS() {
        assertEquals("gov.nih.nci.cabig.caaers.domain.Organization", t.fromCSMToCaAERS("HealthcareSite"));
        assertEquals("gov.nih.nci.cabig.caaers.domain.Study", t.fromCSMToCaAERS("Study"));
        assertEquals("proton", t.fromCSMToCaAERS("proton"));
    }

    public void testFromCaAERSObjectToCSM() {
        assertEquals("HealthcareSite", t.fromCaAERSObjectToCSM(localOrganization));
        assertEquals("HealthcareSite", t.fromCaAERSObjectToCSM(remoteOrganization));
        assertEquals("Study", t.fromCaAERSObjectToCSM(localStudy));
        assertEquals("Study", t.fromCaAERSObjectToCSM(remoteStudy));
        assertEquals("gov.nih.nci.cabig.caaers.domain.Participant", t.fromCaAERSObjectToCSM(p));
    }

}