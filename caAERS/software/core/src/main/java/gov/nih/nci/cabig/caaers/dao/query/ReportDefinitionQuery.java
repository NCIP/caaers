package gov.nih.nci.cabig.caaers.dao.query;
/**
 * This method, will list the report definitions. 
 * @author Biju Joseph
 *
 */
public class ReportDefinitionQuery extends AbstractQuery {
	//the base query.
	private static String queryString = "select rd from ReportDefinition rd ";
    private static String reducedQueryString = "select new ReportDefinition(rd.id, rd.name, rd.label) from ReportDefinition rd";
	
	public ReportDefinitionQuery() {
		super(queryString);
	}

    public ReportDefinitionQuery(boolean reduced){
        super(reduced? reducedQueryString :queryString);
    }
	
	public void filterByOrganizationId(Integer orgId){
		andWhere(" rd.organization.id = :orgId");
    	setParameter("orgId", orgId);
	}
	
	public void filterOffReportDefinitionId(Integer id){
		if(id == null) return;
    	andWhere(" rd.id <> :rdid");
    	setParameter("rdid", id);
	}
	
	public void filterByParent(Integer id){
		if(id == null) return;
		andWhere(" rd.parent.id = :parentId");
		setParameter("parentId" , id);
	}
}
