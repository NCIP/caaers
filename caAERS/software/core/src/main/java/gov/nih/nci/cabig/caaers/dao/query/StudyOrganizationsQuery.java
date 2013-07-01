/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.dao.query;

/**
 * 
 * @author Monish Dombla
 *
 */
public class StudyOrganizationsQuery extends AbstractQuery {

	private static String ORGANIZATION_ID = "orgId";
	private static String ORGANIZATION_NAME = "orgName";
	private static String STUDY_ID = "studyId";

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

    public void filterByStudyId(Integer studyId) {
    	andWhere(" so.retiredIndicator <> true ");
    	andWhere(" so.study.id = :" + STUDY_ID);
    	setParameter(STUDY_ID, studyId);
    }

    public void filterByOrganizationName(String name) {
        String searchString = "%" + name.toLowerCase() + "%";
        andWhere("lower(so.organization.name) LIKE :" + ORGANIZATION_NAME);
        setParameter(ORGANIZATION_NAME, searchString);
    }

    public void filterByOrganizationNameOrNciCode(final String text) {
        String searchString = "%" + text.toLowerCase() + "%";
        andWhere("(lower(so.organization.name) LIKE :TEXT or lower(so.organization.nciInstituteCode) LIKE :TEXT)");
        setParameter("TEXT", searchString);
    }

}
