package gov.nih.nci.cabig.caaers.dao.query;

public class OrganizationQuery extends AbstractQuery {

    private static String queryString = "SELECT distinct o from Organization o order by o.id";

    private static String ORGANIZATION_NAME = "name";

    private static String NCI_CODE = "nciInstituteCode";

    public OrganizationQuery() {

        super(queryString);
    }

    public void filterByOrganizationName(final String name) {
        String searchString = "%" + name.toLowerCase() + "%";
        andWhere("lower(o.name) LIKE :" + ORGANIZATION_NAME);
        setParameter(ORGANIZATION_NAME, searchString);
    }

    public void filterByNciInstituteCode(final String nciInstituteCode) {
        String searchString = "%" + nciInstituteCode.toLowerCase() + "%";
        andWhere("lower(o.nciInstituteCode) LIKE :" + NCI_CODE);
        setParameter(NCI_CODE, searchString);
    }

    public void filterByNciCodeExactMatch(final String nciCode) {
        andWhere("o.nciInstituteCode = :" + NCI_CODE);
        setParameter(NCI_CODE, nciCode);
    }

}