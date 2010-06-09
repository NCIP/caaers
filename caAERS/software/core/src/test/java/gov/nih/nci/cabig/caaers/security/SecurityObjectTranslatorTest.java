package gov.nih.nci.cabig.caaers.security;

import junit.framework.TestCase;

/**
 * @author: Ion C. Olaru
 * Date: Jun 9, 2010
 * Time: 4:44:57 PM
 */
public class SecurityObjectTranslatorTest extends TestCase {

    SecurityObjectTranslator t = new SecurityObjectTranslator();
    
    public void testFromCaAERSToCSM() {
        assertEquals("HealthcareSite", t.fromCaAERSToCSM("gov.nih.nci.cabig.caaers.domain.Organization"));
        assertEquals("HealthcareSite", t.fromCaAERSToCSM("gov.nih.nci.cabig.caaers.domain.LocalOrganization"));
        assertEquals("HealthcareSite", t.fromCaAERSToCSM("gov.nih.nci.cabig.caaers.domain.RemoteOrganization"));
        assertEquals("Study", t.fromCaAERSToCSM("gov.nih.nci.cabig.caaers.domain.Study"));
        assertEquals("Study", t.fromCaAERSToCSM("gov.nih.nci.cabig.caaers.domain.LocalStudy"));
        assertEquals("Study", t.fromCaAERSToCSM("gov.nih.nci.cabig.caaers.domain.RemoteStudy"));
        assertEquals("ion", t.fromCaAERSToCSM("ion"));
    }

    public void testFromCSMToCaAERS() {
        assertEquals("gov.nih.nci.cabig.caaers.domain.Organization", t.fromCSMToCaAERS("HealthcareSite"));
        assertEquals("gov.nih.nci.cabig.caaers.domain.Study", t.fromCSMToCaAERS("Study"));
        assertEquals("cation", t.fromCSMToCaAERS("cation"));
    }

}