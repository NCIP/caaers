package gov.nih.nci.cabig.caaers.domain.report;

import static gov.nih.nci.cabig.caaers.CaaersUseCase.CREATE_EXPEDITED_REPORT;
import static gov.nih.nci.cabig.caaers.CaaersUseCase.CREATE_REPORT_FORMAT;
import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.CaaersUseCases;
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
}
