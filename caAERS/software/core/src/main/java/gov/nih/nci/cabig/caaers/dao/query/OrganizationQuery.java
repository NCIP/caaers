package gov.nih.nci.cabig.caaers.dao.query;

import edu.nwu.bioinformatics.commons.StringUtils;

public class OrganizationQuery extends AbstractQuery {

    private static String queryString = "SELECT distinct o from Organization o ";

    private static String ORGANIZATION_NAME = "name";

    private static String NCI_CODE = "nciInstituteCode";

    public OrganizationQuery() {

        super(queryString);
        orderBy("o.name");
    }

    public void filterByRetiredStatus(Boolean status) {
        super.filterByRetiredStatus("o", status);
    }

    public void filterByOrganizationName(final String name) {
        String searchString = "%" + name.toLowerCase() + "%";
        andWhere("lower(o.name) LIKE :" + ORGANIZATION_NAME);
        setParameter(ORGANIZATION_NAME, searchString);
    }

    /**
     * Folter by passed organization types,
     * it will also return organizations that have org_type as NULL
     * @param types
     */
    public void filterByOrganizationTypesOrNull(final String[] types) {
        String[] whereArray = new String[types.length + 1];
        String whereString = "";
        for (byte i=0; i<types.length; i++) {
            whereArray[i] = String.format("lower(o.type) = '%s'", types[i].toLowerCase());
        }
        whereArray[types.length] = "o.type IS NULL";
        whereString = "(" + StringUtils.join(whereArray, " or ") + ")";
        andWhere(whereString);
    }

    public void filterByNciInstituteCode(final String nciInstituteCode) {
        String searchString = "%" + nciInstituteCode.toLowerCase() + "%";
        andWhere("lower(o.nciInstituteCode) LIKE :" + NCI_CODE);
        setParameter(NCI_CODE, searchString);
    }
    
    public void filterByOrganizationNameOrNciCode(final String text) {
        String searchString = "%" + text.toLowerCase() + "%";
        andWhere("(lower(o.name) LIKE :" + ORGANIZATION_NAME + " or lower(o.nciInstituteCode) LIKE :" + NCI_CODE+")");
        setParameter(ORGANIZATION_NAME, searchString);
        setParameter(NCI_CODE, searchString);
    }

    public void filterByNciCodeExactMatch(final String nciCode) {
        andWhere("o.nciInstituteCode = :" + NCI_CODE);
        setParameter(NCI_CODE, nciCode);
    }

}