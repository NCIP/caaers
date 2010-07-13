package gov.nih.nci.cabig.caaers.accesscontrol.query.impl;

import gov.nih.nci.cabig.caaers.domain.UserGroupType;

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
    private final String siteScopedHQL;
    private final String studyScopedHQL;

    public CaaersParticipantIdFetcherImpl(){

        //site query
       StringBuilder query = new StringBuilder().append("select distinct a.participant.id from  StudyOrganization so ,StudyParticipantAssignment a ")
        .append("join a.studySite ss where ss.study = so.study  ")
        .append("and ( ss.organization.id  in ( ").append(ORG_INDEX_BASE_QUERY).append(" ) ")
        .append("or ( so.organization.id in ( ").append(ORG_INDEX_BASE_QUERY).append(") and so.class in ('SFS', 'SCC') ) )");
       siteScopedHQL = query.toString();

       query.setLength(0);

        //study query
       query.append("select distinct a.participant.id from  StudyOrganization so ,StudyParticipantAssignment a ")
        .append("join a.studySite ss where ss.study = so.study  ")
        .append("and ss.study.id in (").append(STUDY_INDEX_BASE_QUERY).append(") ")
        .append("and ( ss.organization.id  in ( ").append(ORG_INDEX_BASE_QUERY).append(" ) ")
        .append("or ( so.organization.id in ( ").append(ORG_INDEX_BASE_QUERY).append(") and so.class in ('SFS', 'SCC') ) )");

        studyScopedHQL = query.toString();

    }

    /**
     * All the Site scoped roles that require subject indexing
     * @return
     */
    public UserGroupType[] getApplicableSiteScopedRoles(){
        return new UserGroupType[]{UserGroupType.subject_manager,
                UserGroupType.registration_qa_manager};
    }


    /**
     * All the Study scoped roles that require subject indexing
     * @return
     */
    public UserGroupType[] getApplicableStudyScopedRoles(){
        return new UserGroupType[]{UserGroupType.registrar,
                UserGroupType.ae_reporter,
                UserGroupType.ae_expedited_report_reviewer,
                UserGroupType.ae_study_data_reviewer,
                UserGroupType.data_reader,
                UserGroupType.data_analyst};
    }

    public String getSiteScopedHQL(){
        return siteScopedHQL;
    }

    public String getStudyScopedHQL(){
        return studyScopedHQL;
    }


}
