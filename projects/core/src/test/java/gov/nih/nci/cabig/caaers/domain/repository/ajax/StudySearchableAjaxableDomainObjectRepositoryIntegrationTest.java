package gov.nih.nci.cabig.caaers.domain.repository.ajax;

import gov.nih.nci.cabig.caaers.CaaersDbTestCase;
import gov.nih.nci.cabig.caaers.dao.query.ajax.StudySearchableAjaxableDomainObjectQuery;
import gov.nih.nci.cabig.caaers.dao.query.ajax.StudyAjaxableDomainObjectQuery;
import gov.nih.nci.cabig.caaers.domain.ajax.StudySearchableAjaxableDomainObject;
import gov.nih.nci.cabig.caaers.domain.ajax.StudyAjaxableDomainObject;
import gov.nih.nci.cabig.caaers.domain.DateValue;
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
    public void testFilterStudiesByParticipantDayOfDateOfBirth()throws ParseException {

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
    }

    public void testMatchStudyByIdentifier() throws Exception {
        studySearchableAjaxableDomainObjectQuery = new StudySearchableAjaxableDomainObjectQuery();
        studySearchableAjaxableDomainObjectQuery.filterStudiesWithMatchingIdentifierOnly("1138-43");
        List<StudySearchableAjaxableDomainObject> studySearchableAjaxableDomainObjects = studySearchableAjaxableDomainObjectRepository.findStudies(studySearchableAjaxableDomainObjectQuery);

        validateResults(studySearchableAjaxableDomainObjects);

        // Partial  Identifier Value

        studySearchableAjaxableDomainObjectQuery = new StudySearchableAjaxableDomainObjectQuery();
        studySearchableAjaxableDomainObjectQuery.filterStudiesWithMatchingIdentifierOnly("-43");
        studySearchableAjaxableDomainObjects = studySearchableAjaxableDomainObjectRepository.findStudies(studySearchableAjaxableDomainObjectQuery);

        validateResults(studySearchableAjaxableDomainObjects);


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


        validateResults(studySearchableAjaxableDomainObjects);
        studySearchableAjaxableDomainObjectQuery = new StudySearchableAjaxableDomainObjectQuery();
        studySearchableAjaxableDomainObjectQuery.filterStudiesWithMatchingShortTitleOnly("-43");

        studySearchableAjaxableDomainObjects = studySearchableAjaxableDomainObjectRepository.findStudies(studySearchableAjaxableDomainObjectQuery);


        assertNotNull(studySearchableAjaxableDomainObjects);
        assertTrue(studySearchableAjaxableDomainObjects.isEmpty());

    }


}