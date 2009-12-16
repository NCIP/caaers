package gov.nih.nci.cabig.caaers.dao.query;

import java.util.List;

public class StudyParticipantAssignmentQuery extends AbstractQuery {

    public StudyParticipantAssignmentQuery(String s) {
        super(s);
    }

    public StudyParticipantAssignmentQuery() {
        super("SELECT spa FROM StudyParticipantAssignment spa");
    }

    public void filterByStudySubjectIdentifier(String s) {
        andWhere("spa.studySubjectIdentifier = :SSI");
        setParameter("SSI", s);
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