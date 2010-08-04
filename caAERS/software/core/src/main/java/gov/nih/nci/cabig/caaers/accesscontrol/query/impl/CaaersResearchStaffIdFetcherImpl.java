package gov.nih.nci.cabig.caaers.accesscontrol.query.impl;

import java.util.ArrayList;
import java.util.List;

import gov.nih.nci.cabig.caaers.dao.query.HQLQuery;
import gov.nih.nci.cabig.caaers.domain.UserGroupType;
import gov.nih.nci.cabig.caaers.domain.index.IndexEntry;

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
    private final String queryWithUserAccessToAllOrgs;


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
        
        queryWithUserAccessToAllOrgs = "select distinct si.researchStaff.id from SiteResearchStaff si";

    }

    
    @Override
	public List fetch(String loginId){

        List<IndexEntry> list = new ArrayList<IndexEntry>();
        
        long allOrgsCount = getAllOrgsCount();
        
        //for all site scoped roles
        for(UserGroupType role : getApplicableSiteScopedRoles()){
            long orgsCount = getOrgCountForRoleAndLogin(loginId,role.getCode());
            if (allOrgsCount == orgsCount) {
            	list.add(fetch(role, queryWithUserAccessToAllOrgs));
            } else {
            	list.add(fetch(loginId, role, getSiteScopedHQL()));
            }
        }

        //for all study scoped roles
        for(UserGroupType role : getApplicableStudyScopedRoles()){
            long orgsCount = getOrgCountForRoleAndLogin(loginId,role.getCode());
            if (allOrgsCount == orgsCount) {
            	list.add(fetch(role, queryWithUserAccessToAllOrgs));
            } else {
            	list.add(fetch(loginId, role, getStudyScopedHQL()));
            }            
        }

        return list;
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
