package gov.nih.nci.cabig.caaers.domain.repository.ajax;

import gov.nih.nci.cabig.caaers.CaaersDbTestCase;
import gov.nih.nci.cabig.caaers.dao.query.ajax.StudySearchableAjaxableDomainObjectQuery;
import gov.nih.nci.cabig.caaers.domain.ajax.StudySearchableAjaxableDomainObject;
import gov.nih.nci.cabig.caaers.domain.ajax.StudySiteAjaxableDomainObject;
import static gov.nih.nci.cabig.caaers.domain.DateValue.stringToDateValue;

import java.util.List;
import java.text.ParseException;

/**
 * @author Biju Joseph
 */
public class StudySearchableAjaxableDomainObjectRepositoryIntegrationTest extends CaaersDbTestCase {
    private StudySearchableAjaxableDomainObjectQuery studySearchableAjaxableDomainObjectQuery;

    private StudySearchableAjaxableDomainObjectRepository studySearchableAjaxableDomainObjectRepository = (StudySearchableAjaxableDomainObjectRepository)
            getApplicationContext().getBean("studySearchableAjaxableDomainObjectRepository");


    public void testFilterStudiesByParticipantFirstName() {

        studySearchableAjaxableDomainObjectQuery = new StudySearchableAjaxableDomainObjectQuery();

        studySearchableAjaxableDomainObjectQuery.filterByParticipant("Dilbert", null, null, null, null, null);

        List<StudySearchableAjaxableDomainObject> studySearchableAjaxableDomainObjects =
                studySearchableAjaxableDomainObjectRepository.findStudies(studySearchableAjaxableDomainObjectQuery);


        validateResults(studySearchableAjaxableDomainObjects);


    }

    public void testSearchStudyByParticipantIdentifierANDStudyIdentifier() throws Exception {
        studySearchableAjaxableDomainObjectQuery = new StudySearchableAjaxableDomainObjectQuery();
        studySearchableAjaxableDomainObjectQuery.filterByParticipant("Dilbert", "Scott", null, "13js77", null, null);
        studySearchableAjaxableDomainObjectQuery.filterStudiesWithMatchingIdentifierOnly("1138-43");
        List<StudySearchableAjaxableDomainObject> studySearchableAjaxableDomainObjects =
                studySearchableAjaxableDomainObjectRepository.findStudies(studySearchableAjaxableDomainObjectQuery);


        validateResults(studySearchableAjaxableDomainObjects);


    }

    public void testSearchStudyWithNoParticipantAssignedByStudyShortTitle() throws Exception {

        studySearchableAjaxableDomainObjectQuery = new StudySearchableAjaxableDomainObjectQuery();

        studySearchableAjaxableDomainObjectQuery.filterStudiesWithMatchingShortTitleOnly("No participant");
        List<StudySearchableAjaxableDomainObject> studySearchableAjaxableDomainObjects =
                studySearchableAjaxableDomainObjectRepository.findStudies(studySearchableAjaxableDomainObjectQuery);

        assertNotNull(studySearchableAjaxableDomainObjects);
        assertFalse(studySearchableAjaxableDomainObjects.isEmpty());
        assertEquals("Wrong number of results", 1, studySearchableAjaxableDomainObjects.size());
        assertEquals("Wrong match", "No participant assigned to this study", studySearchableAjaxableDomainObjects.get(0).getShortTitle());

    }

    public void testExactSearchStudyWithNoParticipantByStudyIdentifier() throws Exception {

        studySearchableAjaxableDomainObjectQuery = new StudySearchableAjaxableDomainObjectQuery();
        studySearchableAjaxableDomainObjectQuery.filterStudiesWithMatchingIdentifierOnly("908-23153-221");
        List<StudySearchableAjaxableDomainObject> studySearchableAjaxableDomainObjects =
                studySearchableAjaxableDomainObjectRepository.findStudies(studySearchableAjaxableDomainObjectQuery);


        assertNotNull(studySearchableAjaxableDomainObjects);
        assertFalse(studySearchableAjaxableDomainObjects.isEmpty());
        assertEquals("Wrong number of results", 1, studySearchableAjaxableDomainObjects.size());
        assertEquals("Wrong match", "No participant assigned to this study", studySearchableAjaxableDomainObjects.get(0).getShortTitle());


    }

    public void testPartialSearchStudyWithNoParticipantByStudyIdentifier() throws Exception {

        studySearchableAjaxableDomainObjectQuery = new StudySearchableAjaxableDomainObjectQuery();
        studySearchableAjaxableDomainObjectQuery.filterStudiesWithMatchingIdentifierOnly("-23153-");
        List<StudySearchableAjaxableDomainObject> studySearchableAjaxableDomainObjects =
                studySearchableAjaxableDomainObjectRepository.findStudies(studySearchableAjaxableDomainObjectQuery);


        assertNotNull(studySearchableAjaxableDomainObjects);
        assertFalse(studySearchableAjaxableDomainObjects.isEmpty());
        assertEquals("Wrong number of results", 1, studySearchableAjaxableDomainObjects.size());
        assertEquals("Wrong match", "No participant assigned to this study", studySearchableAjaxableDomainObjects.get(0).getShortTitle());

    }


    public void testFilterStudiesByParticipantLastName() {

        studySearchableAjaxableDomainObjectQuery = new StudySearchableAjaxableDomainObjectQuery();

        studySearchableAjaxableDomainObjectQuery.filterByParticipant("Dilbert", "Scott", null, null, null, null);

        List<StudySearchableAjaxableDomainObject> studySearchableAjaxableDomainObjects =
                studySearchableAjaxableDomainObjectRepository.findStudies(studySearchableAjaxableDomainObjectQuery);


        validateResults(studySearchableAjaxableDomainObjects);


    }

    public void testFilterStudiesByParticipantGender() {

        studySearchableAjaxableDomainObjectQuery = new StudySearchableAjaxableDomainObjectQuery();

        studySearchableAjaxableDomainObjectQuery.filterByParticipant("Dilbert", "Scott", null, null, "Female", null);

        List<StudySearchableAjaxableDomainObject> studySearchableAjaxableDomainObjects =
                studySearchableAjaxableDomainObjectRepository.findStudies(studySearchableAjaxableDomainObjectQuery);


        validateResults(studySearchableAjaxableDomainObjects);


    }

    public void testFilterStudiesByParticipantEthinicity() {

        studySearchableAjaxableDomainObjectQuery = new StudySearchableAjaxableDomainObjectQuery();

        studySearchableAjaxableDomainObjectQuery.filterByParticipant("Dilbert", "Scott", "ethnicity", null, "Female", null);

        List<StudySearchableAjaxableDomainObject> studySearchableAjaxableDomainObjects =
                studySearchableAjaxableDomainObjectRepository.findStudies(studySearchableAjaxableDomainObjectQuery);


        validateResults(studySearchableAjaxableDomainObjects);


    }

    public void testFilterStudiesByParticipantIdentifier() {

        studySearchableAjaxableDomainObjectQuery = new StudySearchableAjaxableDomainObjectQuery();

        studySearchableAjaxableDomainObjectQuery.filterByParticipant("Dilbert", "Scott", "ethnicity", "13js77", "Female", null);

        List<StudySearchableAjaxableDomainObject> studySearchableAjaxableDomainObjects =
                studySearchableAjaxableDomainObjectRepository.findStudies(studySearchableAjaxableDomainObjectQuery);


        validateResults(studySearchableAjaxableDomainObjects);


    }

    public void testFilterStudiesByParticipantYearOfDateOfBirth() throws ParseException {

        studySearchableAjaxableDomainObjectQuery = new StudySearchableAjaxableDomainObjectQuery();

        studySearchableAjaxableDomainObjectQuery.filterByParticipant("Dilbert", "Scott", "ethnicity", "13js77", "Female", stringToDateValue("0/0/2006"));

        List<StudySearchableAjaxableDomainObject> studySearchableAjaxableDomainObjects =
                studySearchableAjaxableDomainObjectRepository.findStudies(studySearchableAjaxableDomainObjectQuery);


        validateResults(studySearchableAjaxableDomainObjects);


    }

    public void testFilterStudiesByParticipantMonthOfDateOfBirth() throws ParseException {

        studySearchableAjaxableDomainObjectQuery = new StudySearchableAjaxableDomainObjectQuery();

        studySearchableAjaxableDomainObjectQuery.filterByParticipant("Dilbert", "Scott", "ethnicity", "13js77", "Female", stringToDateValue("01/0/2006"));

        List<StudySearchableAjaxableDomainObject> studySearchableAjaxableDomainObjects =
                studySearchableAjaxableDomainObjectRepository.findStudies(studySearchableAjaxableDomainObjectQuery);


        validateResults(studySearchableAjaxableDomainObjects);


    }

    public void testFilterStudiesByParticipantDayOfDateOfBirth() throws ParseException {

        studySearchableAjaxableDomainObjectQuery = new StudySearchableAjaxableDomainObjectQuery();

        studySearchableAjaxableDomainObjectQuery.filterByParticipant("Dilbert", "Scott", "ethnicity", "13js77", "Female", stringToDateValue("01/02/2006"));

        List<StudySearchableAjaxableDomainObject> studySearchableAjaxableDomainObjects =
                studySearchableAjaxableDomainObjectRepository.findStudies(studySearchableAjaxableDomainObjectQuery);


        validateResults(studySearchableAjaxableDomainObjects);


    }

    private void validateResults(List<StudySearchableAjaxableDomainObject> studySearchableAjaxableDomainObjects) {
        assertNotNull(studySearchableAjaxableDomainObjects);
        assertFalse(studySearchableAjaxableDomainObjects.isEmpty());
        assertEquals("Wrong number of results", 1, studySearchableAjaxableDomainObjects.size());
        assertEquals("Wrong match", "Short Title", studySearchableAjaxableDomainObjects.get(0).getShortTitle());
        assertEquals("Wrong match", "Phase II Trial", studySearchableAjaxableDomainObjects.get(0).getPhaseCode());
        assertEquals("Wrong match", "Active - Trial is open to accrual", studySearchableAjaxableDomainObjects.get(0).getStatus());
        assertEquals("Wrong match", "NCI", studySearchableAjaxableDomainObjects.get(0).getPrimarySponsorCode());
        List<StudySiteAjaxableDomainObject> studySites = studySearchableAjaxableDomainObjects.get(0).getStudySites();
        assertEquals("Wrong number of study sites will be returend if you are searching by participant also", 1, studySites.size());
        assertEquals("must return id of study site not the id of organization", Integer.valueOf(-1000), studySites.get(0).getId());

    }

    public void testMatchStudyByIdentifier() throws Exception {
        studySearchableAjaxableDomainObjectQuery = new StudySearchableAjaxableDomainObjectQuery();
        studySearchableAjaxableDomainObjectQuery.filterStudiesWithMatchingIdentifierOnly("1138-43");
        List<StudySearchableAjaxableDomainObject> studySearchableAjaxableDomainObjects = studySearchableAjaxableDomainObjectRepository.findStudies(studySearchableAjaxableDomainObjectQuery);

        assertNotNull(studySearchableAjaxableDomainObjects);
        assertFalse(studySearchableAjaxableDomainObjects.isEmpty());
        assertEquals("Wrong number of results", 1, studySearchableAjaxableDomainObjects.size());
        assertEquals("Wrong match", "Short Title", studySearchableAjaxableDomainObjects.get(0).getShortTitle());
        assertEquals("Wrong match", "Phase II Trial", studySearchableAjaxableDomainObjects.get(0).getPhaseCode());
        assertEquals("Wrong match", "Active - Trial is open to accrual", studySearchableAjaxableDomainObjects.get(0).getStatus());
        assertEquals("Wrong match", "NCI", studySearchableAjaxableDomainObjects.get(0).getPrimarySponsorCode());
        assertEquals("query works if you are not filtering by participanst also", 2, studySearchableAjaxableDomainObjects.get(0).getStudySites().size());

        // Partial  Identifier Value

        studySearchableAjaxableDomainObjectQuery = new StudySearchableAjaxableDomainObjectQuery();
        studySearchableAjaxableDomainObjectQuery.filterStudiesWithMatchingIdentifierOnly("-43");
        studySearchableAjaxableDomainObjects = studySearchableAjaxableDomainObjectRepository.findStudies(studySearchableAjaxableDomainObjectQuery);

        assertNotNull(studySearchableAjaxableDomainObjects);
        assertFalse(studySearchableAjaxableDomainObjects.isEmpty());
        assertEquals("Wrong number of results", 1, studySearchableAjaxableDomainObjects.size());
        assertEquals("Wrong match", "Short Title", studySearchableAjaxableDomainObjects.get(0).getShortTitle());
        assertEquals("Wrong match", "Phase II Trial", studySearchableAjaxableDomainObjects.get(0).getPhaseCode());
        assertEquals("Wrong match", "Active - Trial is open to accrual", studySearchableAjaxableDomainObjects.get(0).getStatus());
        assertEquals("Wrong match", "NCI", studySearchableAjaxableDomainObjects.get(0).getPrimarySponsorCode());
        assertEquals("query works if you are not filtering by participanst also", 2, studySearchableAjaxableDomainObjects.get(0).getStudySites().size());


        // Partial  Identifier type
        studySearchableAjaxableDomainObjectQuery = new StudySearchableAjaxableDomainObjectQuery();
        studySearchableAjaxableDomainObjectQuery.filterStudiesWithMatchingIdentifierOnly("lo");
        studySearchableAjaxableDomainObjects = studySearchableAjaxableDomainObjectRepository.findStudies(studySearchableAjaxableDomainObjectQuery);

        assertEquals("Wrong number of results", 3, studySearchableAjaxableDomainObjects.size());


        // Full  Identifier type
        studySearchableAjaxableDomainObjectQuery = new StudySearchableAjaxableDomainObjectQuery();
        studySearchableAjaxableDomainObjectQuery.filterStudiesWithMatchingIdentifierOnly("local");
        studySearchableAjaxableDomainObjects = studySearchableAjaxableDomainObjectRepository.findStudies(studySearchableAjaxableDomainObjectQuery);

        assertEquals("Wrong number of results", 3, studySearchableAjaxableDomainObjects.size());


    }

    public void testFindStudies() {
        studySearchableAjaxableDomainObjectQuery = new StudySearchableAjaxableDomainObjectQuery();

        List<StudySearchableAjaxableDomainObject> studySearchableAjaxableDomainObjects = studySearchableAjaxableDomainObjectRepository.findStudies(studySearchableAjaxableDomainObjectQuery);

        assertNotNull(studySearchableAjaxableDomainObjects);
        assertFalse(studySearchableAjaxableDomainObjects.isEmpty());


    }

    public void testFilterStudiesIfMatchingTextIsNotNull() {
        studySearchableAjaxableDomainObjectQuery = new StudySearchableAjaxableDomainObjectQuery();
        studySearchableAjaxableDomainObjectQuery.filterStudiesWithMatchingShortTitleOnly("Short Title");

        List<StudySearchableAjaxableDomainObject> studySearchableAjaxableDomainObjects = studySearchableAjaxableDomainObjectRepository.findStudies(studySearchableAjaxableDomainObjectQuery);


        assertNotNull(studySearchableAjaxableDomainObjects);
        assertFalse(studySearchableAjaxableDomainObjects.isEmpty());
        assertEquals("Wrong number of results", 1, studySearchableAjaxableDomainObjects.size());
        assertEquals("Wrong match", "Short Title", studySearchableAjaxableDomainObjects.get(0).getShortTitle());
        assertEquals("Wrong match", "Phase II Trial", studySearchableAjaxableDomainObjects.get(0).getPhaseCode());
        assertEquals("Wrong match", "Active - Trial is open to accrual", studySearchableAjaxableDomainObjects.get(0).getStatus());
        assertEquals("Wrong match", "NCI", studySearchableAjaxableDomainObjects.get(0).getPrimarySponsorCode());
        assertEquals("query works if you are not filtering by participanst also", 2, studySearchableAjaxableDomainObjects.get(0).getStudySites().size());

        studySearchableAjaxableDomainObjectQuery = new StudySearchableAjaxableDomainObjectQuery();
        studySearchableAjaxableDomainObjectQuery.filterStudiesWithMatchingShortTitleOnly("-43");

        studySearchableAjaxableDomainObjects = studySearchableAjaxableDomainObjectRepository.findStudies(studySearchableAjaxableDomainObjectQuery);


        assertNotNull(studySearchableAjaxableDomainObjects);
        assertTrue(studySearchableAjaxableDomainObjects.isEmpty());

    }

    public void testFilterStudiesByStudySiteBySiteId() {
        studySearchableAjaxableDomainObjectQuery = new StudySearchableAjaxableDomainObjectQuery();
        studySearchableAjaxableDomainObjectQuery.filterStudiesByStudySiteBySiteId(-1003);

        List<StudySearchableAjaxableDomainObject> studySearchableAjaxableDomainObjects = studySearchableAjaxableDomainObjectRepository.findStudies(studySearchableAjaxableDomainObjectQuery);


        assertNotNull(studySearchableAjaxableDomainObjects);
        assertFalse(studySearchableAjaxableDomainObjects.isEmpty());
        assertEquals("Wrong number of results", 3, studySearchableAjaxableDomainObjects.size());
        


    }


}