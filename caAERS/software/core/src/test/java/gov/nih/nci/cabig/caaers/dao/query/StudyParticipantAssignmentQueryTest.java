package gov.nih.nci.cabig.caaers.dao.query;

import junit.framework.TestCase;

public class StudyParticipantAssignmentQueryTest extends TestCase {

    public void testQueryConstructor() throws Exception {
        StudyParticipantAssignmentQuery q = new StudyParticipantAssignmentQuery();
        assertEquals("Wrong Constructor Parameter", "SELECT spa FROM StudyParticipantAssignment spa", q.getQueryString());

    }

    public void testFilterByStudySiteId() throws Exception {
        StudyParticipantAssignmentQuery q = new StudyParticipantAssignmentQuery();
        q.filterByStudySiteId(10);
        assertEquals("Wrong Query String", "SELECT spa FROM StudyParticipantAssignment spa WHERE spa.studySite.id = :SS_ID", q.getQueryString());

    }

    public void testFilterByStudySubjectIdentifier() throws Exception {
        StudyParticipantAssignmentQuery q = new StudyParticipantAssignmentQuery();
        q.filterByStudySubjectIdentifier("S-90");
        assertEquals("Wrong Query String", "SELECT spa FROM StudyParticipantAssignment spa WHERE spa.studySubjectIdentifier = :SSI", q.getQueryString());
    }

    public void testFilterByParticipantExcluded() throws Exception {
        StudyParticipantAssignmentQuery q = new StudyParticipantAssignmentQuery();
        q.filterByParticipantExcluded(4);
        assertEquals("Wrong Query String", "SELECT spa FROM StudyParticipantAssignment spa WHERE spa.participant.id != :P_ID", q.getQueryString());
    }

    public void testFilterByAllFilters() throws Exception {
        StudyParticipantAssignmentQuery q = new StudyParticipantAssignmentQuery();
        q.filterByStudySubjectIdentifier("S-90");
        q.filterByStudySiteId(10);
        q.filterByParticipantExcluded(4);
        assertEquals("Wrong Query String", "SELECT spa FROM StudyParticipantAssignment spa WHERE spa.studySubjectIdentifier = :SSI AND spa.studySite.id = :SS_ID AND spa.participant.id != :P_ID", q.getQueryString());
    }

}