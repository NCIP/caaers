package gov.nih.nci.cabig.caaers.dao.query;

public class OrganizationFromStudySiteQuery extends AbstractQuery {

    //private static String queryString = "SELECT distinct ss.organization from StudySite ss";//order by ss.organization.name";
    private static String queryString = "SELECT distinct organization from Organization organization ";

    private static String ORGANIZATION_NAME = "name";

    private static String STUDY_ID = "STUDY_ID";

    public OrganizationFromStudySiteQuery() {

        super(queryString);
        join("organization.studyOrganizations ss");
        andWhere("ss.class='SST'");
        orderBy("organization.name");
    }

    public void filterByOrganizationName(String name) {
        String searchString = "%" + name.toLowerCase() + "%";
        andWhere("lower(organization.name) LIKE :" + ORGANIZATION_NAME);
        setParameter(ORGANIZATION_NAME, searchString);
    }

    public void filterByStudy(Integer studyId){
    	andWhere("ss.study.id = :" + STUDY_ID);
    	setParameter(STUDY_ID, studyId);
    }

}