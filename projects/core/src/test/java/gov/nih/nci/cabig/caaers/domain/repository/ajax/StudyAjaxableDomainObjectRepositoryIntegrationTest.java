package gov.nih.nci.cabig.caaers.domain.repository.ajax;

import gov.nih.nci.cabig.caaers.CaaersDbTestCase;
import gov.nih.nci.cabig.caaers.dao.query.ajax.StudySearchableAjaxableDomainObjectQuery;
import gov.nih.nci.cabig.caaers.domain.ajax.StudySearchableAjaxableDomainObject;

import java.util.List;

/**
 * @author Biju Joseph
 */
public class StudyAjaxableDomainObjectRepositoryIntegrationTest extends CaaersDbTestCase {
    private StudySearchableAjaxableDomainObjectQuery query;

    private StudySearchableAjaxableDomainObjectRepository studySearchableAjaxableDomainObjectRepository = (StudySearchableAjaxableDomainObjectRepository)

            getApplicationContext().getBean("studySearchableAjaxableDomainObjectRepository");

    public void testFilterStudiesByParticipantId() {
        Integer participantId = -100;

        query = new StudySearchableAjaxableDomainObjectQuery();
        query.filterByParticipant(participantId);

        List<StudySearchableAjaxableDomainObject> studyAjaxableDomainObjects = studySearchableAjaxableDomainObjectRepository.findStudies(query);

        assertNotNull(studyAjaxableDomainObjects);
        assertFalse(studyAjaxableDomainObjects.isEmpty());
        assertEquals("Wrong number of results", 1, studyAjaxableDomainObjects.size());
        assertEquals("Wrong match", "Short Title", studyAjaxableDomainObjects.get(0).getShortTitle());


    }


    public void testMatchStudyByParticipantByIdentifier() throws Exception {
        Integer participantId = -100;
        query = new StudySearchableAjaxableDomainObjectQuery();
        query.filterStudiesWithMatchingText("1138-43");
        query.filterByParticipant(participantId);
        List<StudySearchableAjaxableDomainObject> studyAjaxableDomainObjects = studySearchableAjaxableDomainObjectRepository.findStudies(query);

        assertNotNull(studyAjaxableDomainObjects);
        assertFalse(studyAjaxableDomainObjects.isEmpty());
        assertEquals("Wrong number of results", 1, studyAjaxableDomainObjects.size());
        assertEquals("Wrong match", "Short Title", studyAjaxableDomainObjects.get(0).getShortTitle());

        // Partial  Identifier Value

        query = new StudySearchableAjaxableDomainObjectQuery();
        query.filterStudiesWithMatchingText("-43");
        query.filterByParticipant(participantId);
        studyAjaxableDomainObjects = studySearchableAjaxableDomainObjectRepository.findStudies(query);

        assertEquals("Wrong number of results", 1, studyAjaxableDomainObjects.size());
        assertEquals("Wrong match", "Short Title", studyAjaxableDomainObjects.get(0).getShortTitle());


        // Partial  Identifier type
        query = new StudySearchableAjaxableDomainObjectQuery();
        query.filterStudiesWithMatchingText("lo");
        query.filterByParticipant(participantId);
        studyAjaxableDomainObjects = studySearchableAjaxableDomainObjectRepository.findStudies(query);

        assertEquals("Wrong number of results", 1, studyAjaxableDomainObjects.size());
        assertEquals("Wrong match", "Short Title", studyAjaxableDomainObjects.get(0).getShortTitle());


        // Full  Identifier type
        query = new StudySearchableAjaxableDomainObjectQuery();
        query.filterStudiesWithMatchingText("local");
        query.filterByParticipant(participantId);
        studyAjaxableDomainObjects = studySearchableAjaxableDomainObjectRepository.findStudies(query);

        assertEquals("Wrong number of results", 1, studyAjaxableDomainObjects.size());
        assertEquals("Wrong match", "Short Title", studyAjaxableDomainObjects.get(0).getShortTitle());


    }

    public void testFindStudies() {
        query = new StudySearchableAjaxableDomainObjectQuery();

        List<StudySearchableAjaxableDomainObject> studyAjaxableDomainObjects = studySearchableAjaxableDomainObjectRepository.findStudies(query);

        assertNotNull(studyAjaxableDomainObjects);
        assertFalse(studyAjaxableDomainObjects.isEmpty());


    }

    public void testFilterStudiesIfMatchingTextIsNotNull() {
        query = new StudySearchableAjaxableDomainObjectQuery();
        query.filterStudiesWithMatchingText("1138-43");

        List<StudySearchableAjaxableDomainObject> studyAjaxableDomainObjects = studySearchableAjaxableDomainObjectRepository.findStudies(query);


        assertNotNull(studyAjaxableDomainObjects);
        assertFalse(studyAjaxableDomainObjects.isEmpty());
        assertEquals(1, studyAjaxableDomainObjects.size());

        assertEquals("Wrong match", "Short Title", studyAjaxableDomainObjects.get(0).getShortTitle());

        query = new StudySearchableAjaxableDomainObjectQuery();
        query.filterStudiesWithMatchingText("-43");

        studyAjaxableDomainObjects = studySearchableAjaxableDomainObjectRepository.findStudies(query);


        assertNotNull(studyAjaxableDomainObjects);
        assertFalse(studyAjaxableDomainObjects.isEmpty());
        assertEquals(1, studyAjaxableDomainObjects.size());
        assertEquals("Wrong match", "Short Title", studyAjaxableDomainObjects.get(0).getShortTitle());

    }


    public void testFilterStudiesByTrueStatus() {
        query = new StudySearchableAjaxableDomainObjectQuery();
        query.filterByStudyStatus(true);

        List<StudySearchableAjaxableDomainObject> studyAjaxableDomainObjects = studySearchableAjaxableDomainObjectRepository.findStudies(query);

        assertNotNull(studyAjaxableDomainObjects);
        assertFalse(studyAjaxableDomainObjects.isEmpty());


    }

    public void testFilterStudiesByFalseStatus() {
        query = new StudySearchableAjaxableDomainObjectQuery();
        query.filterByStudyStatus(false);

        List<StudySearchableAjaxableDomainObject> studyAjaxableDomainObjects = studySearchableAjaxableDomainObjectRepository.findStudies(query);

        assertNotNull(studyAjaxableDomainObjects);
        assertFalse(studyAjaxableDomainObjects.isEmpty());


    }

}
