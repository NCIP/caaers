/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.dao.query;

/**
 * @author Ion C. Olaru
 */
public class SiteResearchStaffQuery extends AbstractQuery {

    private static String queryString = "SELECT distinct srs from SiteResearchStaff srs " ;

    private static String FIRST_NAME = "firstName";
    private static String LAST_NAME = "lastName";
    private static String ORGANIZATION = "organization";
    private static String NCI_CODE = "nciIdentifier";
    private static String USER_NAME = "userName";

    public SiteResearchStaffQuery() {
        super(queryString);
        leftJoinFetch("srs.researchStaff rs");
        leftJoinFetch("srs.organization org");
        orderBy("srs.id");
    }

    public void filterByFirstName(final String firstName) {
        String searchString = "%" + firstName.toLowerCase() + "%";
        andWhere("lower(rs.firstName) LIKE :" + FIRST_NAME);
        setParameter(FIRST_NAME, searchString);
    }

    public void filterByLastName(final String lastName) {
        String searchString = "%" + lastName.toLowerCase() + "%";
        andWhere("lower(rs.lastName) LIKE :" + LAST_NAME);
        setParameter(LAST_NAME, searchString);
    }

    public void filterByOrganization(final String organization) {
        String searchString = organization.trim();
        andWhere("srs.organization.id =:" + ORGANIZATION);
        setParameter(ORGANIZATION, Integer.parseInt(searchString));
    }
    
    public void filterByNciIdentifier(final String value) {
        String searchString = "%" + value.toLowerCase() + "%";
        andWhere("lower(rs.nciIdentifier) LIKE :" + NCI_CODE);
        setParameter(NCI_CODE, searchString);
    }    
    
    public void filterByUserName(final String value) {
    	join("srs.researchStaff.caaersUser cu");
        String searchString = "%" + value.toLowerCase() + "%";
        andWhere("lower(cu.loginName) LIKE :" + USER_NAME);
        setParameter(USER_NAME, searchString);
    }

    public void excludeUsers(){
        andWhere("rs.caaersUser is null");
    }
}
