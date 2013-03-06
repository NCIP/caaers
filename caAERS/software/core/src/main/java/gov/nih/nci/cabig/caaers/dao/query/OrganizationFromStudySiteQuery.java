/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.dao.query;

public class OrganizationFromStudySiteQuery extends AbstractQuery {

    //private static String queryString = "SELECT distinct ss.organization from StudySite ss";//order by ss.organization.name";
    private static String queryString = "SELECT distinct organization from Organization organization ";
    private static String ORGANIZATION_NAME = "name";
    private static String ORGANIZATION_NAME_CODE = "nameOrCode";
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
    
	/**
	 * Filters organizations by occurrence of the given text string in
	 * organization name or NCI code.
	 * 
	 * @param text
	 */
	public void filterByOrganizationNameOrNciCode(String text) {
		String searchString = "%" + text.toLowerCase() + "%";
		andWhere("(lower(organization.name) LIKE :" + ORGANIZATION_NAME_CODE
				+ " OR lower(organization.nciInstituteCode) LIKE :"
				+ ORGANIZATION_NAME_CODE+")");
		setParameter(ORGANIZATION_NAME_CODE, searchString);
	}    

    public void filterByStudy(Integer studyId){
    	andWhere("ss.study.id = :" + STUDY_ID);
    	setParameter(STUDY_ID, studyId);
    }

}
