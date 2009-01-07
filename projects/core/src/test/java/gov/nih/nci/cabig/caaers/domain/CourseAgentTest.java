package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import static gov.nih.nci.cabig.caaers.CaaersUseCase.CREATE_EXPEDITED_REPORT;
import gov.nih.nci.cabig.caaers.CaaersUseCases;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Rhett Sutphin
 */
@CaaersUseCases({CREATE_EXPEDITED_REPORT})
public class CourseAgentTest extends AbstractTestCase {
    private CourseAgent courseAgent;

    private TreatmentInformation treatmentInformation;

    private StudyAgent studyAgent;

    private Dose dose;

    private String durationAndSchedule;

    private BigDecimal administrationDelayAmount;

    private DelayUnits administrationDelayUnits;

    private Dose modifiedDose;

    private Date lastAdministeredDate;

    private BigDecimal totalDoseAdministeredThisCourse;

    private String comments;

    private String lotNumber;
    private String formulation;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        courseAgent = new CourseAgent();
        durationAndSchedule = "durationAndSchedule";
        administrationDelayAmount = new BigDecimal(4);
        administrationDelayUnits = DelayUnits.HOURS;
        comments = "comments";
        lotNumber = "lotNumber";
        formulation = "formulation";

        studyAgent = new StudyAgent();
        studyAgent.setId(1);
        /*
        modifiedDose = new Dose();
        modifiedDose.setAmount(new BigDecimal(4));
        modifiedDose.setRoute("route");
        modifiedDose.setUnits("units");
		*/
        dose = new Dose();
        dose.setAmount(new BigDecimal(4));
        dose.setRoute("route");
        dose.setUnits("units");

        treatmentInformation = new TreatmentInformation();
        treatmentInformation.setId(2);

        lastAdministeredDate = new Date();
        totalDoseAdministeredThisCourse = new BigDecimal(6);

        courseAgent.setDurationAndSchedule(durationAndSchedule);
        courseAgent.setAdministrationDelay(administrationDelayAmount);
        courseAgent.setAdministrationDelayUnits(administrationDelayUnits);
        courseAgent.setLotNumber(lotNumber);
        courseAgent.setFormulation(formulation);
        courseAgent.setComments(comments);
        courseAgent.setId(1);
        courseAgent.setGridId("grid id");
        courseAgent.setVersion(2);

    //    courseAgent.setModifiedDose(modifiedDose);
        courseAgent.setLastAdministeredDate(lastAdministeredDate);
        courseAgent.setTotalDoseAdministeredThisCourse(totalDoseAdministeredThisCourse);
        courseAgent.setStudyAgent(studyAgent);
        courseAgent.setTreatmentInformation(treatmentInformation);
        courseAgent.setAgentAdjustment(AgentAdjustment.DOSE_INCREASED);

        courseAgent.setDose(dose);


    }


    public void testCopyForBasicProperties() {


        CourseAgent copiedCourseAgent = courseAgent.copy();

        assertEquals("durationAndSchedule must be same", durationAndSchedule, copiedCourseAgent.getDurationAndSchedule());

        assertEquals("comments must be same", comments, copiedCourseAgent.getComments());
        assertEquals("administrationDelayAmount must be same", administrationDelayAmount, copiedCourseAgent.getAdministrationDelayAmount());
        assertEquals("lotNumber must be same", lotNumber, copiedCourseAgent.getLotNumber());
        assertEquals("administration delay must be same", courseAgent.getAdministrationDelay(), copiedCourseAgent.getAdministrationDelay());
        assertEquals("formulation must be same", formulation, copiedCourseAgent.getFormulation());
        assertEquals("solicited must be same", lastAdministeredDate, copiedCourseAgent.getLastAdministeredDate());
        assertEquals("totalDoseAdministeredThisCourse must be same", totalDoseAdministeredThisCourse, copiedCourseAgent.getTotalDoseAdministeredThisCourse());
 
    }

    public void testCopyTreatmentInformation() {

        CourseAgent copiedCourseAgent = courseAgent.copy();
        assertNotNull(courseAgent.getTreatmentInformation());
        assertNull("must not copy treatment information", copiedCourseAgent.getTreatmentInformation());


    }

    public void testCopyStudyAgent() {

        CourseAgent copiedCourseAgent = courseAgent.copy();
        assertNotNull(courseAgent.getStudyAgent());
        assertSame("study agents must refer to same objects", studyAgent, copiedCourseAgent.getStudyAgent());


    }
/*
    public void testCopyModifiedDose() {

        CourseAgent copiedCourseAgent = courseAgent.copy();


        assertNotSame("modifiedDose must not be refer same objects", modifiedDose, copiedCourseAgent.getModifiedDose());

        assertEquals("attributes of modifiedDose must be same", modifiedDose.getAmount(), copiedCourseAgent.getModifiedDose().getAmount());
        assertEquals("attributes of modifiedDose must be same", modifiedDose.getRoute(), copiedCourseAgent.getModifiedDose().getRoute());
        assertEquals("attributes of modifiedDose must be same", modifiedDose.getUnits(), copiedCourseAgent.getModifiedDose().getUnits());


    }
*/
    public void testCopyDose() {

        CourseAgent copiedCourseAgent = courseAgent.copy();


        assertNotSame("modifiedDose must not be refer same objects", dose, copiedCourseAgent.getDose());

        assertEquals("attributes of modifiedDose must be same", dose.getAmount(), copiedCourseAgent.getDose().getAmount());
        assertEquals("attributes of modifiedDose must be same", dose.getRoute(), copiedCourseAgent.getDose().getRoute());
        assertEquals("attributes of modifiedDose must be same", dose.getUnits(), copiedCourseAgent.getDose().getUnits());


    }

    public void testMustNotCopyIdGridIdAndVersionNumber() {

        CourseAgent copiedCourseAgent = courseAgent.copy();

        assertNotNull(courseAgent.getId());

        assertNotNull(courseAgent.getGridId());
        assertNotNull(courseAgent.getVersion());

        assertNull(copiedCourseAgent.getId());
        assertNull(copiedCourseAgent.getGridId());
        assertNull("version number must be null", copiedCourseAgent.getVersion());
    }

    public void testGetAdministrationDelayWithNoUnits() throws Exception {
        courseAgent.setAdministrationDelayAmount(new BigDecimal(14));
        courseAgent.setAdministrationDelayUnits(null);
        assertNull(courseAgent.getAdministrationDelay());
    }

    public void testGetAdministrationDelayWithNoAmount() throws Exception {
        courseAgent.setAdministrationDelayAmount(null);
        courseAgent.setAdministrationDelayUnits(DelayUnits.DAYS);
        assertNull(courseAgent.getAdministrationDelay());
    }

    public void testGetAdministrationDelayWithBothValues() throws Exception {
        courseAgent.setAdministrationDelayUnits(DelayUnits.HOURS);
        courseAgent.setAdministrationDelayAmount(new BigDecimal(34));
        assertEquals(new BigDecimal(34 * 60), courseAgent.getAdministrationDelay());
    }

    public void testSetAdministrationDelayNull() throws Exception {
        courseAgent.setAdministrationDelayUnits(DelayUnits.HOURS);
        courseAgent.setAdministrationDelayAmount(new BigDecimal(44));

        courseAgent.setAdministrationDelay(null);
        assertNull("Amount should be null", courseAgent.getAdministrationDelayAmount());
        assertNull("Units should be null", courseAgent.getAdministrationDelayUnits());
    }

    public void testSetAdministrationDelayExactDays() throws Exception {
        courseAgent.setAdministrationDelay(new BigDecimal(4320));
        assertEquals(DelayUnits.DAYS, courseAgent.getAdministrationDelayUnits());
        assertEquals(3, courseAgent.getAdministrationDelayAmount().intValueExact());
    }

    public void testSetAdministrationDelayExactHours() throws Exception {
        courseAgent.setAdministrationDelay(new BigDecimal(4260));
        assertEquals(DelayUnits.HOURS, courseAgent.getAdministrationDelayUnits());
        assertEquals(71, courseAgent.getAdministrationDelayAmount().intValueExact());
    }

    public void testSetAdministrationDelayExactMinutes() throws Exception {
        courseAgent.setAdministrationDelay(new BigDecimal(4259));
        assertEquals(DelayUnits.MINUTES, courseAgent.getAdministrationDelayUnits());
        assertEquals(4259, courseAgent.getAdministrationDelayAmount().intValueExact());
    }

    public void testSetAdministrationDelayFractional() throws Exception {
        courseAgent.setAdministrationDelay(new BigDecimal("4258.3"));
        assertEquals(DelayUnits.MINUTES, courseAgent.getAdministrationDelayUnits());
        assertEquals("4258.3", courseAgent.getAdministrationDelayAmount().toString());
    }
    
    public void testGetDisplayNameWhenBlank() throws Exception {
        courseAgent = new CourseAgent();
        assertEquals("[no agent]", courseAgent.getDisplayName());
    }

    public void testDisplayNameWithNoDose() throws Exception {
        courseAgent = new CourseAgent();

        courseAgent.setStudyAgent(Fixtures.createStudyAgent("Witch hazel"));
        assertEquals("Witch hazel", courseAgent.getDisplayName());
    }

    public void testDisplayName() throws Exception {
        courseAgent.setStudyAgent(Fixtures.createStudyAgent("Witch hazel"));
        courseAgent.getDose().setAmount(new BigDecimal("532.1"));
        courseAgent.getDose().setUnits("L");
        courseAgent.getDose().setRoute("oral");
        assertEquals("Witch hazel (532.1L oral)", courseAgent.getDisplayName());
    }
/*
    public void testIsModifiedDoseWhenNotModified() throws Exception {
        courseAgent.getDose().setAmount(new BigDecimal(37));
        courseAgent.getDose().setRoute("shampoo");
        courseAgent.getDose().setUnits("mg");
        courseAgent.getModifiedDose().setAmount(new BigDecimal(37));
        courseAgent.getModifiedDose().setRoute("shampoo");
        courseAgent.getModifiedDose().setUnits("mg");

        assertFalse(courseAgent.isDoseModified());
    }

    public void testIsModifiedDoseWhenNoModifiedValues() throws Exception {
        courseAgent.getDose().setAmount(new BigDecimal(37));
        courseAgent.getDose().setRoute("shampoo");
        courseAgent.getDose().setUnits("mg");
        courseAgent.getModifiedDose().setAmount(null);
        courseAgent.getModifiedDose().setRoute(null);
        courseAgent.getModifiedDose().setUnits(null);

        assertFalse(courseAgent.isDoseModified());
    }

    public void testIsModifiedDoseWhenAmountModified() throws Exception {
        courseAgent.getDose().setAmount(new BigDecimal(37));
        courseAgent.getDose().setRoute("shampoo");
        courseAgent.getDose().setUnits("mg");
        courseAgent.getModifiedDose().setAmount(new BigDecimal(55));
        courseAgent.getModifiedDose().setRoute("shampoo");
        courseAgent.getModifiedDose().setUnits("mg");

        assertTrue(courseAgent.isDoseModified());
    }

    public void testIsModifiedDoseWhenRouteModified() throws Exception {
        courseAgent.getDose().setAmount(new BigDecimal(37));
        courseAgent.getDose().setRoute("shampoo");
        courseAgent.getDose().setUnits("mg");
        courseAgent.getModifiedDose().setAmount(new BigDecimal(55));
        courseAgent.getModifiedDose().setRoute("soap");
        courseAgent.getModifiedDose().setUnits("mg");

        assertTrue(courseAgent.isDoseModified());
    }

    public void testIsModifiedDoseWhenUnitsModified() throws Exception {
        courseAgent.getDose().setAmount(new BigDecimal(37));
        courseAgent.getDose().setRoute("shampoo");
        courseAgent.getDose().setUnits("mg");
        courseAgent.getModifiedDose().setAmount(new BigDecimal(55));
        courseAgent.getModifiedDose().setRoute("shampoo");
        courseAgent.getModifiedDose().setUnits("g");

        assertTrue(courseAgent.isDoseModified());
    }
    */
    public void testGetAgentAdjustment() throws Exception {
        courseAgent.setAgentAdjustment(AgentAdjustment.DOSE_NOTCHANGED);
        assertEquals(AgentAdjustment.DOSE_NOTCHANGED,courseAgent.getAgentAdjustment());
    }
}
