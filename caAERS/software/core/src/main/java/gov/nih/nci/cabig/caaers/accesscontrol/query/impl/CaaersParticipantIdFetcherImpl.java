/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.accesscontrol.query.impl;

import com.semanticbits.security.contentfilter.IdFetcher;

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
    private final String siteScopedSQL;
    private final String studyScopedSQL;

    public CaaersParticipantIdFetcherImpl(){


        //site scoped roles - can access all the participants of their site.
       StringBuilder siteSQL = new StringBuilder("select distinct a.participant_id as id from participant_assignments a ")
            .append("join study_organizations so on so.id = a.study_site_id ")
            .append("join organization_index oi on   so.site_id = oi.organization_id ")
            .append("where oi.login_id = :LOGIN_ID  ")
            .append("and oi.role_code = :ROLE_CODE ");
       siteScopedSQL = siteSQL.toString();

        //study scoped roles - can access all participants
        StringBuilder studySQL = new StringBuilder("select distinct a.participant_id as id from participant_assignments  a ")
            .append("join study_organizations so on so.id = a.study_site_id  ")
            .append("join organization_index oi on oi.organization_id = so.site_id ")
            .append("join study_index si on si.study_id =  so.study_id ")
            .append("where oi.login_id = :LOGIN_ID ")
            .append("and oi.role_code = :ROLE_CODE ")
            .append("and si.role_code = oi.role_code ")
            .append("and si.login_id = oi.login_id ");
        studyScopedSQL = studySQL.toString();

    }


    public String getSiteScopedSQL(){
        return siteScopedSQL;
    }

    public String getStudyScopedSQL(){
        return studyScopedSQL;
    }


}
