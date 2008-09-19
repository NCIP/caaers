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
public class TreatmentInformationTest extends AbstractTestCase {
    private TreatmentInformation treatmentInformation;



    private CourseAgent courseAgent1, courseAgent2;
    private Date firstCourseDate;

    private CourseDate adverseEventCourse;

    private Integer totalCourses;

    private TreatmentAssignment treatmentAssignment;

    private String treatmentDescription;

    private ExpeditedAdverseEventReport report;
   

    @Override
    protected void setUp() throws Exception {
        super.setUp();
       
       
        treatmentDescription = "treatmentDescription";
        totalCourses = 3;
        firstCourseDate = new Date();
        adverseEventCourse = new CourseDate(3);
        adverseEventCourse.setDate(new Date());
        report = new ExpeditedAdverseEventReport();

        treatmentAssignment = new TreatmentAssignment();
        treatmentAssignment.setCode("code");
        courseAgent1 = new CourseAgent();
        courseAgent1.setAdministrationDelay(new BigDecimal(4));

        treatmentInformation = new TreatmentInformation();
        treatmentInformation.setId(1);
        treatmentInformation.setGridId("grid id");
        treatmentInformation.setVersion(2);
        treatmentInformation.setTreatmentDescription(treatmentDescription);
        treatmentInformation.setTotalCourses(totalCourses);
        treatmentInformation.setAdverseEventCourse(adverseEventCourse);
        treatmentInformation.setFirstCourseDate(firstCourseDate);
        treatmentInformation.setTreatmentAssignment(treatmentAssignment);
        treatmentInformation.setReport(report);

    }

    public void testCopyCourseAgent() {
        treatmentInformation.addCourseAgent(courseAgent1);

        TreatmentInformation copiedTreatmentInformation = treatmentInformation.copy();
        assertNotSame("course agent must not be refer same objects", courseAgent1, copiedTreatmentInformation.getCourseAgents().get(0));
        assertNotEquals("course agent must not be refer same objects", courseAgent1, copiedTreatmentInformation.getCourseAgents().get(0));

        assertSame("treatment information must be same", copiedTreatmentInformation, copiedTreatmentInformation.getCourseAgents().get(0).getTreatmentInformation());


    }

    public void testCopyReport() {

        TreatmentInformation copiedTreatmentInformation = treatmentInformation.copy();
        assertNotNull(treatmentInformation.getReport());
        assertNull("report must be null", copiedTreatmentInformation.getReport());


    }

    public void testCopyTreatmentAssignment() {

        TreatmentInformation copiedTreatmentInformation = treatmentInformation.copy();
        assertSame("treatmentAssignment must refer same objects", treatmentAssignment, copiedTreatmentInformation.getTreatmentAssignment());
        assertEquals("treatmentAssignment must refer same object ", treatmentAssignment, copiedTreatmentInformation.getTreatmentAssignment());


    }

    public void testCopyAdverseEventCourse() {

        TreatmentInformation copiedTreatmentInformation = treatmentInformation.copy();


        assertSame("adverseEventCourse must  refer same objects", adverseEventCourse, copiedTreatmentInformation.getAdverseEventCourse());

        assertEquals("adverseEventCourse must  refer same object ", adverseEventCourse, copiedTreatmentInformation.getAdverseEventCourse());


    }

    public void testCopyForBasicProperties() {
        TreatmentInformation copiedTreatmentInformation = treatmentInformation.copy();
        assertEquals("totalCourses must be same", totalCourses, copiedTreatmentInformation.getTotalCourses());
        assertEquals("firstCourseDate must be same", firstCourseDate, copiedTreatmentInformation.getFirstCourseDate());

    }

    public void testInvestigationDrugUsedWhenUsed() throws Exception {
        treatmentInformation.addCourseAgent(createCourseAgent(true));
        treatmentInformation.addCourseAgent(createCourseAgent(false));
        treatmentInformation.addCourseAgent(createCourseAgent(null));

        assertTrue(treatmentInformation.isInvestigationalAgentAdministered());
    }

    public void testInvestigationalDrugUsedWhenNotUsed() throws Exception {
        treatmentInformation.addCourseAgent(createCourseAgent(false));
        treatmentInformation.addCourseAgent(createCourseAgent(false));
        treatmentInformation.addCourseAgent(createCourseAgent(null));

        assertFalse(treatmentInformation.isInvestigationalAgentAdministered());
    }

    public void testInvestigationalDrugUsedWhenIndicatorNull() throws Exception {
        treatmentInformation.addCourseAgent(createCourseAgent(null));

        assertFalse(treatmentInformation.isInvestigationalAgentAdministered());
    }

    public void testInvestigationalDrugUsedWhenNone() throws Exception {
        assertFalse(treatmentInformation.isInvestigationalAgentAdministered());
    }

    private CourseAgent createCourseAgent(Boolean newDrug) {
        CourseAgent ca = new CourseAgent();
        StudyAgent sa = new StudyAgent();
        if (newDrug != null && newDrug.booleanValue()) {
            sa.getStudyAgentINDAssociations().get(0);
        }
        // sa.setInvestigationalNewDrugIndicator(newDrug);
        ca.setStudyAgent(sa);

        return ca;
    }

    public void testMustNotCopyIdGridIdAndVersionNumber() {

        TreatmentInformation copiedTreatmentInformation = treatmentInformation.copy();

        assertNotNull(treatmentInformation.getId());

        assertNotNull(treatmentInformation.getGridId());
        assertNotNull(treatmentInformation.getVersion());

        assertNull(copiedTreatmentInformation.getId());
        assertNull(copiedTreatmentInformation.getGridId());
        assertNull("version number must be null", copiedTreatmentInformation.getVersion());
    }

   
}
