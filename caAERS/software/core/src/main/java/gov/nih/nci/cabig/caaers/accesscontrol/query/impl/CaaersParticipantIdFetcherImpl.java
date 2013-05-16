/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.accesscontrol.query.impl;

import com.semanticbits.security.contentfilter.IdFetcher;
import gov.nih.nci.cabig.caaers.domain.UserGroupType;

/**
 * Will find the subjects a logged-in person has access to.
 *
 * Assumptions:-
 * =============
 * 
 *  The roles requiring subject Indexing are :-
 * Site Scoped Roles:
 *      UserGroupType.subject_manager,
 *      UserGroupType.registration_qa_manager,
 *
 * Study Scoped Roles: 
        UserGroupType.registrar,
        UserGroupType.ae_reporter,
        UserGroupType.ae_expedited_report_reviewer,
        UserGroupType.ae_study_data_reviewer,
        UserGroupType.data_reader,
        UserGroupType.data_analyst
 *
 * The OrganizationIndex and StudyIndex is available at the time for the given login-Id
 *
 * 
 *
 * @author Srini Akkala
 * @author Biju Joseph
 *
 */
public class CaaersParticipantIdFetcherImpl extends AbstractIdFetcher implements IdFetcher {

    //the query
    private final String siteScopedSQL = "select distinct a.participant_id as id from participant_assignments a "
            + "join study_organizations so on so.id = a.study_site_id "
            + "join organization_index oi on   so.site_id = oi.organization_id "
            + "where oi.login_id = :LOGIN_ID " ;

    private final String studyScopedSQL = "select distinct a.participant_id as id from participant_assignments  a "
            + "join study_organizations so on so.id = a.study_site_id  "
            + "join organization_index oi on oi.organization_id = so.site_id "
            + "join study_index si on si.study_id =  so.study_id "
            + "where si.login_id = :LOGIN_ID "
            + "and oi.login_id = :LOGIN_ID " ;


    public String getSiteScopedSQL(UserGroupType role){
        if(isPostgeSQL()) return siteScopedSQL + " and oi.role & " + role.getPrivilege() + " > 0";
        return siteScopedSQL + " and bitand(oi.role, " + role.getPrivilege() + ") > 0";
    }

    public String getStudyScopedSQL(UserGroupType role){
        if(isPostgeSQL()) return studyScopedSQL +  " and si.role & " + role.getPrivilege() + " > 0 and  oi.role & " + role.getPrivilege()  + " > 0 ";
        return studyScopedSQL +  " and bitand(si.role , " + role.getPrivilege() + ") > 0 and  bitand(oi.role ," + role.getPrivilege()  + ") > 0 ";
    }

}
