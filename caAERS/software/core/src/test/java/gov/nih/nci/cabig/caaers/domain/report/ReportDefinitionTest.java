package gov.nih.nci.cabig.caaers.domain.report;

import static gov.nih.nci.cabig.caaers.CaaersUseCase.CREATE_EXPEDITED_REPORT;
import static gov.nih.nci.cabig.caaers.CaaersUseCase.CREATE_REPORT_FORMAT;
import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.CaaersUseCases;
import gov.nih.nci.cabig.caaers.domain.ConfigProperty;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.ReportStatus;


/**
 * @author Rhett Sutphin
 */
@CaaersUseCases( { CREATE_EXPEDITED_REPORT, CREATE_REPORT_FORMAT })
public class ReportDefinitionTest extends AbstractTestCase {
    private ReportDefinition def;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        def = new ReportDefinition();
    }

    public void testCreateReport() throws Exception {
        Report created = def.createReport();
        assertNotNull(created);
        assertSame(def, created.getReportDefinition());
        assertEquals(ReportStatus.PENDING, created.getStatus());
    }

    // hibernate uses dynamic proxies for items in collections
    public void testEqualsIfOneIsSubclass() throws Exception {
        ReportDefinition def1 = new ReportDefinition();
        ReportDefinition def2 = new ReportDefinition() {

			private static final long serialVersionUID = 1L;
        	
        }; // anonymous subclass

        assertEquals(def1, def2);
    }
    
    public void testGetExpectedDisplayDueDate_OverDue(){
    	java.util.Calendar c = java.util.Calendar.getInstance();
    	c.add(c.DATE, -4);
    	
    	// 1-Days
    	def.setDuration(1);
    	def.setTimeScaleUnitType(TimeScaleUnit.DAY);
    	String displayDueDate = def.getExpectedDisplayDueDate(c.getTime());
    	assertEquals("3 days overdue", displayDueDate);
    	//3 Days
    	def.setDuration(3);
    	def.setTimeScaleUnitType(TimeScaleUnit.DAY);
    	displayDueDate = def.getExpectedDisplayDueDate(c.getTime());
    	assertEquals("1 day overdue", displayDueDate);
    	
    	
    	//4 Days
    	def.setDuration(4);
    	def.setTimeScaleUnitType(TimeScaleUnit.DAY);
    	displayDueDate = def.getExpectedDisplayDueDate(c.getTime());
    	assertEquals("0 day overdue", displayDueDate);
    	
    	//5 Days
    	def.setDuration(5);
    	def.setTimeScaleUnitType(TimeScaleUnit.DAY);
    	displayDueDate = def.getExpectedDisplayDueDate(c.getTime());
    	System.out.println(displayDueDate);
    	assertEquals("Due in 1 day", displayDueDate);
    	
    	//6 Days
    	def.setDuration(6);
    	def.setTimeScaleUnitType(TimeScaleUnit.DAY);
    	displayDueDate = def.getExpectedDisplayDueDate(c.getTime());
    	System.out.println(displayDueDate);
    	assertEquals("Due in 2 days", displayDueDate);
    	
    	
    }
    
    /**
     * This method tests the expectedDueDate display String for a 5 day report.
     */
    public void testGetExpectedDisplayDueDate(){
    	
    	java.util.Calendar c = java.util.Calendar.getInstance();
    	
    	// 1 day
    	def.setDuration(1);
    	def.setTimeScaleUnitType(TimeScaleUnit.DAY);
    	String displayDueDate = def.getExpectedDisplayDueDate();
    	assertNotNull("Error in generating expectedDisplayDueDate", displayDueDate);
    	assertEquals("Error in generating expectedDisplayDuedate", displayDueDate, "Due in 1 day");
    	// N days
    	def.setDuration(5);
    	def.setTimeScaleUnitType(TimeScaleUnit.DAY);
    	displayDueDate = def.getExpectedDisplayDueDate();
    	assertNotNull("Error in generating expectedDisplayDueDate", displayDueDate);
    	assertEquals("Error in generating expectedDisplayDuedate", displayDueDate, "Due in 5 days");
    	// 1 hour
    	def.setDuration(1);
    	def.setTimeScaleUnitType(TimeScaleUnit.HOUR);
    	displayDueDate = def.getExpectedDisplayDueDate();
    	assertNotNull("Error in generating expectedDisplayDueDate", displayDueDate);
    	assertEquals("Error in generating expectedDisplayDuedate", displayDueDate, "Due in 1 hour");
    	// N hours
    	def.setDuration(5);
    	def.setTimeScaleUnitType(TimeScaleUnit.HOUR);
    	displayDueDate = def.getExpectedDisplayDueDate();
    	assertNotNull("Error in generating expectedDisplayDueDate", displayDueDate);
    	assertEquals("Error in generating expectedDisplayDuedate", displayDueDate, "Due in 5 hours");
    	
    	
    	// 1 minute
    	def.setDuration(1);
    	def.setTimeScaleUnitType(TimeScaleUnit.MINUTE);
    	displayDueDate = def.getExpectedDisplayDueDate();
    	assertNotNull("Error in generating expectedDisplayDueDate", displayDueDate);
    	assertEquals("Error in generating expectedDisplayDuedate", displayDueDate, "Due in 1 minute");
    	// N minutes
    	def.setDuration(5);
    	def.setTimeScaleUnitType(TimeScaleUnit.MINUTE);
    	displayDueDate = def.getExpectedDisplayDueDate();
    	assertNotNull("Error in generating expectedDisplayDueDate", displayDueDate);
    	assertEquals("Error in generating expectedDisplayDuedate", displayDueDate, "Due in 5 minutes");
    	// 1 week
    	def.setDuration(1);
    	def.setTimeScaleUnitType(TimeScaleUnit.WEEK);
    	displayDueDate = def.getExpectedDisplayDueDate();
    	assertNotNull("Error in generating expectedDisplayDueDate", displayDueDate);
    	assertEquals("Error in generating expectedDisplayDuedate", displayDueDate, "Due in 1 week");
    	// N weeks
    	def.setDuration(5);
    	def.setTimeScaleUnitType(TimeScaleUnit.WEEK);
    	displayDueDate = def.getExpectedDisplayDueDate();
    	assertNotNull("Error in generating expectedDisplayDueDate", displayDueDate);
    	assertEquals("Error in generating expectedDisplayDuedate", displayDueDate, "Due in 5 weeks");
    	// 1 second
    	def.setDuration(1);
    	def.setTimeScaleUnitType(TimeScaleUnit.SECOND);
    	displayDueDate = def.getExpectedDisplayDueDate();
    	assertNotNull("Error in generating expectedDisplayDueDate", displayDueDate);
    	assertEquals("Error in generating expectedDisplayDuedate", displayDueDate, "Due in 1 second");
    	// N seconds
    	def.setDuration(5);
    	def.setTimeScaleUnitType(TimeScaleUnit.SECOND);
    	displayDueDate = def.getExpectedDisplayDueDate();
    	assertNotNull("Error in generating expectedDisplayDueDate", displayDueDate);
    	assertEquals("Error in generating expectedDisplayDuedate", displayDueDate, "Due in 5 seconds");
    }
    
    
    /**
     * RD0 grp1 org1
     * RD1 grp1 org1
     * RD2 grp2 org1
     * 
     * RD3 grp1 org2
     * RD3 grp2 org3
     * 
     * expected result
     * rd1<>rd0 = true
     * rd1<>rd1 = true;
     * rd1<>rd2 = false;
     * rd1<>rd3 = false;
     * rd1<>rd4 = false;
     */
    public void testIsOfSameReportTypeAndOrganization(){
    	Organization org1 = Fixtures.createOrganization("test1");
    	org1.setId(1);
    	Organization org2 = Fixtures.createOrganization("test2");
    	org2.setId(2);
    	
    	ConfigProperty cp1 = Fixtures.createConfigProperty("cp1");
    	cp1.setId(1);
    	ConfigProperty cp2 = Fixtures.createConfigProperty("cp2");
    	cp1.setId(2);
    	
    	ReportDefinition rd0 = Fixtures.createReportDefinition("rd0");
    	rd0.setGroup(cp1);
    	rd0.setOrganization(org1);
    	rd0.setId(0);
    	
    	ReportDefinition rd1 = Fixtures.createReportDefinition("rd1");
    	rd1.setGroup(cp1);
    	rd1.setOrganization(org1);
    	rd1.setId(1);
    	
    	ReportDefinition rd2 = Fixtures.createReportDefinition("rd2");
    	rd2.setGroup(cp2);
    	rd2.setOrganization(org1);
    	rd2.setId(2);
    	
    	ReportDefinition rd3 = Fixtures.createReportDefinition("rd3");
    	rd3.setGroup(cp1);
    	rd3.setOrganization(org2);
    	rd3.setId(3);
    	
    	ReportDefinition rd4 = Fixtures.createReportDefinition("rd4");
    	rd4.setGroup(cp2);
    	rd4.setOrganization(org2);
    	rd4.setId(4);
    	
    	
    	assertTrue(rd1.isOfSameReportTypeAndOrganization(rd0));
    	assertTrue(rd1.isOfSameReportTypeAndOrganization(rd1));
    	assertFalse(rd1.isOfSameReportTypeAndOrganization(rd2));
    	assertFalse(rd1.isOfSameReportTypeAndOrganization(rd3));
    	assertFalse(rd1.isOfSameReportTypeAndOrganization(rd4));
    }
    
    public void testHashCode(){
    	//CAAERS-3467
    	Organization org1 = Fixtures.createOrganization("test1");
    	org1.setId(1);
    	
    	ConfigProperty cp1 = Fixtures.createConfigProperty("cp1");
    	cp1.setId(1);
    	
    	ReportDefinition rd0 = Fixtures.createReportDefinition("rd0");
    	rd0.setGroup(cp1);
    	rd0.setOrganization(org1);
    	rd0.setId(0);
    	
    	int hashCode = rd0.hashCode();
    	assertTrue(hashCode != 0);
    	
    	rd0.getOrganization().setId(null);
    	hashCode = rd0.hashCode();
    	assertTrue(hashCode != 0);
    	
    	rd0.setOrganization(null);
    	int anotherHashCode = rd0.hashCode();
    	assertTrue(anotherHashCode != 0);
    	assertTrue(anotherHashCode == hashCode);
    	
    }
}
