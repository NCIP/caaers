package gov.nih.nci.cabig.caaers.rules.common;

import junit.framework.TestCase;

/**
 * @author Biju Joseph
 */
public class RuleLevelTest extends TestCase {
    
    RuleLevel r1 = RuleLevel.Sponsor;
    RuleLevel r2 = RuleLevel.SponsorDefinedStudy;
    RuleLevel r3 = RuleLevel.Institution;
    RuleLevel r4 = RuleLevel.InstitutionDefinedStudy;
    
    public void testGetName() throws Exception {
       assertEquals("Sponsor", r1.getName());
       assertEquals("SponsorDefinedStudy", r2.getName());
       assertEquals("Institution", r3.getName());
       assertEquals("InstitutionDefinedStudy", r4.getName());
       assertEquals(4, RuleLevel.values().length);
    }

    public void testGetDescription() throws Exception {
        assertEquals("Rules for Sponsor", r1.getDescription());
        assertEquals("Rules for Institution", r3.getDescription());
        assertEquals("Rules for Sponsor Defined Study", r2.getDescription());
        assertEquals("Rules for Institution Defined Study", r4.getDescription());
    }

    public void testGetPackageName() throws Exception {
        assertEquals("sponsor", r1.getPackageName());
        assertEquals("institution", r3.getPackageName());
        assertEquals("sponsor.study", r2.getPackageName());
        assertEquals("institution.study", r4.getPackageName());
    }

    public void testGetByName() throws Exception {
       assertSame(r1, RuleLevel.getByName("Sponsor"));
       assertSame(r3, RuleLevel.getByName("Institution"));
       assertSame(r2, RuleLevel.getByName("SponsorDefinedStudy"));
       assertSame(r4, RuleLevel.getByName("InstitutionDefinedStudy"));
    }

    public void testIsSponsorBased() throws Exception {
       assertTrue(r1.isSponsorBased());
       assertTrue(r2.isSponsorBased());
       assertFalse(r3.isSponsorBased());
       assertTrue(r4.isInstitutionBased());
    }


    public void testIsStudyBased() throws Exception {
         assertTrue(r4.isStudyBased());
         assertTrue(r2.isStudyBased());
         assertFalse(r1.isStudyBased());
         assertFalse(r3.isStudyBased());
    }
}
