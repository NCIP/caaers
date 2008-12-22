package gov.nih.nci.cabig.caaers.dao.query;
/**
 * 
 * @author biju
 *
 */
public class AdverseEventReportingPeriodForReviewQuery extends AbstractQuery {
	 private static String queryString = "select rp from AdverseEventReportingPeriod rp " +
	 		" join rp.assignment as spa " +
	 		" join spa.studySite as ss " +
	 		" join ss.study as s " + 
	 		" join spa.participant as p " +
	 		"order by rp.id";
	 
	 private static final String PARTICIPANT = "participantId";
	 private static final String STUDY = "studyId";
	 private static final String STUDY_SITE = "studySiteId";
	 
	 public AdverseEventReportingPeriodForReviewQuery() {
		super(queryString);
	 }
	 
	 public void filterByStudy(Integer studyId) {
		 andWhere("s.id =:" + STUDY);
		 setParameter(STUDY , studyId);
	 }
	 
	 public void filterByStudySite(Integer studySiteId){
		 andWhere("ss.id =:" + STUDY_SITE);
		 setParameter(STUDY_SITE, studySiteId);
	 }
	 
	 public void filterByParticipant(Integer participantId){
		 andWhere("p.id =:" + PARTICIPANT);
		 setParameter(PARTICIPANT, participantId);
	 }
}
