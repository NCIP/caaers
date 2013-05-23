/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
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
    private final String siteScopedSQL = "select distinct sr.researchstaff_id as id from site_research_staffs  sr " +
            " join organization_index oi on  oi.organization_id = sr.site_id " +
            " where oi.login_id = :LOGIN_ID  ";

    private final String studyScopedSQL = "select distinct sr.researchstaff_id as id from site_research_staffs  sr " +
            " join study_organizations so on sr.site_id = so.site_id " +
            " join study_index si on so.study_id = si.study_id " +
            " join organization_index oi on  oi.organization_id = sr.site_id  " +
            " where si.login_id = :LOGIN_ID " +
            " and oi.login_id = :LOGIN_ID "  ;


    /**
     * Will return the Site scoped SQL query
     *
     * @return
     */
    @Override
    public String getSiteScopedSQL(UserGroupType role) {
        if(isPostgeSQL()) return siteScopedSQL + " and oi.role & " + role.getPrivilege() + " > 0";
        return siteScopedSQL + " and bitand(oi.role , " + role.getPrivilege() + ") > 0";
    }

    /**
     * Will return the Study scoped SQL query
     *
     * @return
     */
    @Override
    public String getStudyScopedSQL(UserGroupType role) {
        if(isPostgeSQL()) return studyScopedSQL +  " and si.role & " + role.getPrivilege() + " > 0 and oi.role & " + role.getPrivilege()  + " > 0 ";
        return studyScopedSQL +  " and bitand(si.role, " + role.getPrivilege() + ") > 0 and bitand(oi.role, " + role.getPrivilege()  + ") > 0 ";
    }

	
 
}
