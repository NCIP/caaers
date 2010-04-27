package gov.nih.nci.cabig.caaers.dao.query;

/**
 * 
 * @author Monish Dombla
 *
 */
public class StudyOrganizationsQuery extends AbstractQuery{

	private static String ORGANIZATION_ID = "orgId";
	
	public StudyOrganizationsQuery(String queryString) {
		super(queryString);
	}

	public StudyOrganizationsQuery(){
		super(" select so from StudyOrganization so ");
	}
	
    public void filterByOrganizationId(int orgId) {
    	andWhere(" so.retiredIndicator <> true ");
    	andWhere(" so.organization.id = :"+ORGANIZATION_ID);
    	setParameter(ORGANIZATION_ID, orgId);
    }
}