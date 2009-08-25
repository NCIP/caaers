package gov.nih.nci.cabig.caaers.domain;

import java.util.Date;

import junit.framework.TestCase;
import gov.nih.nci.cabig.caaers.utils.DateUtils;

/**
 *
 * @author Ion C. Olaru
 * @author Biju Joseph.
 *
 */
public class StudyInvestigatorTest extends TestCase {

    protected StudyInvestigator s1;
    protected StudyInvestigator s2;
    SiteInvestigator si;
    Investigator i;
    
	protected void setUp() throws Exception {
		super.setUp();
		i = Fixtures.createInvestigator("test");
		
        s1 = new StudyInvestigator();
        s1.setStartDate(DateUtils.yesterday());
        s1.setEndDate(DateUtils.tomorrow());

        s2 = new StudyInvestigator();
        
        si = new SiteInvestigator();
        si.setStartDate(DateUtils.yesterday());
        si.setEndDate(DateUtils.yesterday());

        s1.setSiteInvestigator(si);
        s2.setSiteInvestigator(si);
        
        si.setEmailAddress("si@si.com");
        si.setInvestigator(i);
        
	}

	public void testEqualsObject() {
	}

    public void testDeactivate() {

    }
    
    /**
     *  One Study Investigator has start and end date, but another dont have. 
     *  Makes sure the same date in site investigator is copied. 
     */
    public void testSyncDates(){
    	s1.syncDates();
    	s2.syncDates();
    	assertSame(si.getStartDate(), s1.getStartDate());
    	assertSame(si.getStartDate(), s2.getStartDate());
    	
    	assertSame(si.getEndDate(), s1.getEndDate());
    	assertSame(si.getEndDate(), s2.getEndDate());
    	
    	//site investigator is null. 
    	Date now = new Date();
    	StudyInvestigator s3 = new StudyInvestigator();
    	s3.setStartDate(now);
    	s3.syncDates();
    	assertSame(now, s3.getStartDate());
    	assertNull(s3.getEndDate());
    	
    	
    }
    
    
    public void testGetEmailAddress() {
		assertEquals("si@si.com", s1.getEmailAddress());
	}
	public void testGetEmailAddress_FromResearchStaff() {
		si.setEmailAddress(null);
		assertEquals("abc@kk.com", s1.getEmailAddress());
	}
	public void testGetEmailAddress_NoEmail() {
		s1.setSiteInvestigator(null);
		assertNull(s1.getEmailAddress());
	}

}