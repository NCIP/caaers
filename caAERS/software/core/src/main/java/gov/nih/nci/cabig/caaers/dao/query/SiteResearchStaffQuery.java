/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.dao.query;

import org.apache.commons.lang.StringUtils;

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

    public void filterByName(final String name) {
        String[] searchFields = StringUtils.split(name);
        int i = 0;
        if (searchFields != null) {
            for (String searchField : searchFields) {
                String searchString = "%" + StringUtils.lowerCase(searchField) + "%";
                String firstNameKey = "FIRST_NAME_" + i;
                String lastNameKey = "LAST_NAME_" + i;
                String middleNameKey = "MIDDLE_NAME_" + i;
                andWhere(String.format("(lower(rs.firstName) LIKE :%s OR lower(rs.lastName) LIKE :%s OR lower(rs.middleName) LIKE :%s)",
                        firstNameKey, lastNameKey, middleNameKey));
                setParameter(firstNameKey, searchString);
                setParameter(lastNameKey, searchString);
                setParameter(middleNameKey, searchString);
                i++;
            }
        }

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
