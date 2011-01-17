package gov.nih.nci.cabig.caaers.dao.query;

public class StudyParticipantAssignmentQuery extends AbstractQuery {

    public StudyParticipantAssignmentQuery() {
        super("SELECT spa FROM StudyParticipantAssignment spa");
    }

    public void filterByStudySubjectIdentifier(String s) {
        andWhere("lower(spa.studySubjectIdentifier) = :SSI");
        setParameter("SSI", s.toLowerCase());
    }

    public void joinParticipant() {
        leftJoinFetch("spa.participant p");
    }

    public void joinStudySite() {
        leftJoinFetch("spa.studySite ss");
        leftJoinFetch("ss.study s");
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