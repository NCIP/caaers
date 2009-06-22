package gov.nih.nci.cabig.caaers.dao.query.ajax;

public class StudySiteAjaxableDomainObjectQuery extends AbstractAjaxableDomainObjectQuery {
	
	private static String query = "select site.id, site.organization.name from StudySite site order by site.organization.name";
	private static final String STUDY_ID ="STUDY_ID";
	public StudySiteAjaxableDomainObjectQuery() {
		super(query);
	}
	
	public void filterByStudy(Integer studyId){
		andWhere("study.id = :"  + STUDY_ID );
		setParameter(STUDY_ID, studyId);
	}
}
