package gov.nih.nci.cabig.caaers.domain.repository;

import gov.nih.nci.cabig.caaers.CaaersDbTestCase;
import gov.nih.nci.cabig.caaers.dao.query.StudyAjaxableDomainObjectQuery;
import gov.nih.nci.cabig.caaers.domain.StudyAjaxableDomainObject;

import java.util.List;

/**
 * @author Biju Joseph
 */
public class StudyAjaxableDomainObjectRepositoryIntegrationTest extends CaaersDbTestCase {
    private StudyAjaxableDomainObjectQuery studyAjaxableDomainObjectQuery;

    private StudyAjaxableDomainObjectRepository studyAjaxableDomainObjectRepository = (StudyAjaxableDomainObjectRepository)
            getApplicationContext().getBean("studyAjaxableDomainObjectRepository");

    public void testFilterStudiesByParticipantId() {
        Integer participantId = -100;

        studyAjaxableDomainObjectQuery = new StudyAjaxableDomainObjectQuery();
        studyAjaxableDomainObjectQuery.filterByParticipant(participantId);

        List<StudyAjaxableDomainObject> studyAjaxableDomainObjects = studyAjaxableDomainObjectRepository.findStudies(studyAjaxableDomainObjectQuery);

        assertNotNull(studyAjaxableDomainObjects);
        assertFalse(studyAjaxableDomainObjects.isEmpty());
        assertEquals("Wrong number of results", 1, studyAjaxableDomainObjects.size());
        assertEquals("Wrong match", "Short Title", studyAjaxableDomainObjects.get(0).getShortTitle());


    }


    public void testMatchStudyByParticipantByIdentifier() throws Exception {
        Integer participantId = -100;
        studyAjaxableDomainObjectQuery = new StudyAjaxableDomainObjectQuery();
        studyAjaxableDomainObjectQuery.filterStudiesWithMatchingText("1138-43");
        studyAjaxableDomainObjectQuery.filterByParticipant(participantId);
        List<StudyAjaxableDomainObject> studyAjaxableDomainObjects = studyAjaxableDomainObjectRepository.findStudies(studyAjaxableDomainObjectQuery);

        assertNotNull(studyAjaxableDomainObjects);
        assertFalse(studyAjaxableDomainObjects.isEmpty());
        assertEquals("Wrong number of results", 1, studyAjaxableDomainObjects.size());
        assertEquals("Wrong match", "Short Title", studyAjaxableDomainObjects.get(0).getShortTitle());

        // Partial  Identifier Value

        studyAjaxableDomainObjectQuery = new StudyAjaxableDomainObjectQuery();
        studyAjaxableDomainObjectQuery.filterStudiesWithMatchingText("-43");
        studyAjaxableDomainObjectQuery.filterByParticipant(participantId);
        studyAjaxableDomainObjects = studyAjaxableDomainObjectRepository.findStudies(studyAjaxableDomainObjectQuery);

        assertEquals("Wrong number of results", 1, studyAjaxableDomainObjects.size());
        assertEquals("Wrong match", "Short Title", studyAjaxableDomainObjects.get(0).getShortTitle());


        // Partial  Identifier type
        studyAjaxableDomainObjectQuery = new StudyAjaxableDomainObjectQuery();
        studyAjaxableDomainObjectQuery.filterStudiesWithMatchingText("lo");
        studyAjaxableDomainObjectQuery.filterByParticipant(participantId);
        studyAjaxableDomainObjects = studyAjaxableDomainObjectRepository.findStudies(studyAjaxableDomainObjectQuery);

        assertEquals("Wrong number of results", 1, studyAjaxableDomainObjects.size());
        assertEquals("Wrong match", "Short Title", studyAjaxableDomainObjects.get(0).getShortTitle());


        // Full  Identifier type
        studyAjaxableDomainObjectQuery = new StudyAjaxableDomainObjectQuery();
        studyAjaxableDomainObjectQuery.filterStudiesWithMatchingText("local");
        studyAjaxableDomainObjectQuery.filterByParticipant(participantId);
        studyAjaxableDomainObjects = studyAjaxableDomainObjectRepository.findStudies(studyAjaxableDomainObjectQuery);

        assertEquals("Wrong number of results", 1, studyAjaxableDomainObjects.size());
        assertEquals("Wrong match", "Short Title", studyAjaxableDomainObjects.get(0).getShortTitle());


    }

    public void testFindStudies() {
        studyAjaxableDomainObjectQuery = new StudyAjaxableDomainObjectQuery();

        List<StudyAjaxableDomainObject> studyAjaxableDomainObjects = studyAjaxableDomainObjectRepository.findStudies(studyAjaxableDomainObjectQuery);

        assertNotNull(studyAjaxableDomainObjects);
        assertFalse(studyAjaxableDomainObjects.isEmpty());


    }

    public void testFilterStudiesIfMatchingTextIsNotNull() {
        studyAjaxableDomainObjectQuery = new StudyAjaxableDomainObjectQuery();
        studyAjaxableDomainObjectQuery.filterStudiesWithMatchingText("s");

        List<StudyAjaxableDomainObject> studyAjaxableDomainObjects = studyAjaxableDomainObjectRepository.findStudies(studyAjaxableDomainObjectQuery);

        assertNotNull(studyAjaxableDomainObjects);
        assertFalse(studyAjaxableDomainObjects.isEmpty());


    }


    public void testFilterStudiesByTrueStatus() {
        studyAjaxableDomainObjectQuery = new StudyAjaxableDomainObjectQuery();
        studyAjaxableDomainObjectQuery.filterByStudyStatus(true);

        List<StudyAjaxableDomainObject> studyAjaxableDomainObjects = studyAjaxableDomainObjectRepository.findStudies(studyAjaxableDomainObjectQuery);

        assertNotNull(studyAjaxableDomainObjects);
        assertFalse(studyAjaxableDomainObjects.isEmpty());


    }

    public void testFilterStudiesByFalseStatus() {
        studyAjaxableDomainObjectQuery = new StudyAjaxableDomainObjectQuery();
        studyAjaxableDomainObjectQuery.filterByStudyStatus(false);

        List<StudyAjaxableDomainObject> studyAjaxableDomainObjects = studyAjaxableDomainObjectRepository.findStudies(studyAjaxableDomainObjectQuery);

        assertNotNull(studyAjaxableDomainObjects);
        assertFalse(studyAjaxableDomainObjects.isEmpty());


    }

}
