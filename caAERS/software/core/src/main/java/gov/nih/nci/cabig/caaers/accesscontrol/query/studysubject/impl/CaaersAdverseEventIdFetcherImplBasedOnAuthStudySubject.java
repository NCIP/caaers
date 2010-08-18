package gov.nih.nci.cabig.caaers.accesscontrol.query.studysubject.impl;

import com.semanticbits.security.contentfilter.IdFetcher;
import gov.nih.nci.cabig.caaers.accesscontrol.query.impl.AbstractIdFetcher;
import gov.nih.nci.cabig.caaers.domain.UserGroupType;

/**
 * Will find the Adverse Events a logged-in person has access to.
 * This will work-off the ReportingPeriod Index. 
 *
 * Assumptions:-
 * =============
 *
 *  The roles requiring subject Indexing are :-
 * Site Scoped Roles:
 *     NONE
 *
 * Study Scoped Roles:
        UserGroupType.ae_reporter,
        UserGroupType.ae_expedited_report_reviewer,
        UserGroupType.ae_study_data_reviewer,
        UserGroupType.data_reader,
        UserGroupType.data_analyst
 *
 * The Reporting Period Index is available at the time for the given login-Id
 *
 *
 *
 * @author Srini Akkala
 * @author Biju Joseph
 *
 */

public class CaaersAdverseEventIdFetcherImplBasedOnAuthStudySubject extends AbstractIdFetcher implements IdFetcher {


    //the query
    private final String siteScopedHQL;
    private final String studyScopedHQL;

    public CaaersAdverseEventIdFetcherImplBasedOnAuthStudySubject(){

        //site query
       siteScopedHQL = null;

       StringBuilder query = new StringBuilder();

        //study query
       query.append("select distinct ae.id from  ReportingPeriodIndex ri ")
        .append(" join ri.reportingPeriod rp  ")
        .append(" join rp.adverseEvents ae ")
        .append(" where ri.roleCode = :ROLE_CODE ")
        .append(" and ri.loginId = :LOGIN_ID");
        
        studyScopedHQL = query.toString();

    }


    public String getSiteScopedHQL(){
        return siteScopedHQL;
    }

    public String getStudyScopedHQL(){
        return studyScopedHQL;
    }
    
}
