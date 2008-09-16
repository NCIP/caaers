package gov.nih.nci.cabig.caaers.domain.repository;

import gov.nih.nci.cabig.caaers.CaaersDbTestCase;
import gov.nih.nci.cabig.caaers.dao.query.StudyAjaxableDomainObjectQuery;
import gov.nih.nci.cabig.caaers.domain.StudyAjaxableDomainObject;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: saurabhagrawal
 * Date: Sep 15, 2008
 * Time: 10:54:30 AM
 * To change this template use File | Settings | File Templates.
 */
public class StudyAjaxableDomainObjectRepositoryIntegrationTest extends CaaersDbTestCase {
    private StudyAjaxableDomainObjectQuery studyAjaxableDomainObjectQuery;

    private StudyAjaxableDomainObjectRepository studyAjaxableDomainObjectRepository = (StudyAjaxableDomainObjectRepository)
            getApplicationContext().getBean("studyAjaxableDomainObjectRepository");

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

    public void testFilterStudiesByParticipantId() {
        studyAjaxableDomainObjectQuery = new StudyAjaxableDomainObjectQuery();
        studyAjaxableDomainObjectQuery.filterByParticipant(1);

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
