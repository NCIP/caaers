package gov.nih.nci.cabig.caaers.dao.query;

public class OrganizationFromStudySiteQuery extends AbstractQuery {

    private static String queryString = "SELECT distinct ss.organization from StudySite ss";//order by ss.organization.name";

    private static String ORGANIZATION_NAME = "name";

    private static String STUDY_ID = "STUDY_ID";

    public OrganizationFromStudySiteQuery() {

        super(queryString);
        orderBy("ss.organization.name");
    }

    public void filterByOrganizationName(String name) {
        String searchString = "%" + name.toLowerCase() + "%";
        andWhere("lower(name) LIKE :" + ORGANIZATION_NAME);
        setParameter(ORGANIZATION_NAME, searchString);
    }

    public void filterByStudy(Integer studyId){
    	andWhere("ss.study.id = :" + STUDY_ID);
    	setParameter(STUDY_ID, studyId);
    }

}