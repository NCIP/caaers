package gov.nih.nci.cabig.caaers.dao.query;

import java.util.List;

public class StudyParticipantAssignmentQuery extends AbstractQuery {

    public StudyParticipantAssignmentQuery() {
        super("SELECT spa FROM StudyParticipantAssignment spa");
    }

    public void filterByStudySubjectIdentifier(String s) {
        andWhere("lower(spa.studySubjectIdentifier) = :SSI");
        setParameter("SSI", s.toLowerCase());
    }

    public void filterByStudySiteId(Integer i) {
        andWhere("spa.studySite.id = :SS_ID");
        setParameter("SS_ID", i);
    }

    public void filterByParticipantExcluded(Integer i) {
        andWhere("spa.participant.id != :P_ID");
        setParameter("P_ID", i);
    }
    
}