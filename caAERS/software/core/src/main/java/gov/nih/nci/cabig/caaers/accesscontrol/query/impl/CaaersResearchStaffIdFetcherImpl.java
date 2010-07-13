package gov.nih.nci.cabig.caaers.accesscontrol.query.impl;

import gov.nih.nci.cabig.caaers.domain.UserGroupType;

import com.semanticbits.security.contentfilter.IdFetcher;


/**
 * Will find the research staff a logged-in person has access to.
 *
 * Assumptions:-
 * =============
 *
 *  The roles requiring research staff Indexing are :-
 * Site Scoped Roles:
 *      UserGroupType.person_and_organization_information_manager,
 *      UserGroupType.user_administrator,
 *      UserGroupType.study_team_administrator
 *
 * Study Scoped Roles:
        UserGroupType.data_reader,
        UserGroupType.data_analyst
 *
 * The OrganizationIndex is available at the time for the given login-Id
 *
 *
 *
 * @author Srini Akkala
 * @author Biju Joseph
 *
 */
public class CaaersResearchStaffIdFetcherImpl extends AbstractIdFetcher implements IdFetcher {

    //the query
    private final String siteScopedHQL;
    private final String studyScopedHQL;


    public CaaersResearchStaffIdFetcherImpl(){
        StringBuilder query = new StringBuilder();
        
        //site query
        query.append("select distinct si.researchStaff.id from SiteResearchStaff si ")
             .append(" where si.organization.id in ( " )
             .append(   "select distinct ss.organization.id from StudyOrganization so , StudySite ss " )
             .append(   "where so.study = ss.study and so.organization.id in (").append(ORG_INDEX_BASE_QUERY).append(" ) " )
             .append(" ) ")
             .append(" or si.organization.id in ( ").append(ORG_INDEX_BASE_QUERY).append(" )");
        siteScopedHQL = query.toString();

        query.setLength(0);

        //study query  (direct access)
        query.append("select distinct si.researchStaff.id from SiteResearchStaff si ")
             .append(" where si.organization.id in ( ").append(ORG_INDEX_BASE_QUERY).append(" )");
        studyScopedHQL = query.toString();

    }

    /**
     * Will return the Site scoped HQL query
     *
     * @return
     */
    @Override
    public String getSiteScopedHQL() {
        return siteScopedHQL;
    }

    /**
     * Will return the Study scoped HQL query
     *
     * @return
     */
    @Override
    public String getStudyScopedHQL() {
        return studyScopedHQL;
    }

    /**
     * All the Site scoped roles that require subject indexing
     * @return
     */
    public UserGroupType[] getApplicableSiteScopedRoles(){
        return new UserGroupType[]{
                UserGroupType.person_and_organization_information_manager,
                UserGroupType.user_administrator,
                UserGroupType.study_team_administrator};
    }


    /**
     * All the Study scoped roles that require subject indexing
     * @return
     */
    public UserGroupType[] getApplicableStudyScopedRoles(){
        return new UserGroupType[]{
                UserGroupType.data_reader,
                UserGroupType.data_analyst};
    }
	
 
}
