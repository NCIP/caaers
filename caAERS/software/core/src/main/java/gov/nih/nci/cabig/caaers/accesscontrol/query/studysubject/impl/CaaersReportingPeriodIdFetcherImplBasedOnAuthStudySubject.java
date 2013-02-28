/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.accesscontrol.query.studysubject.impl;

import com.semanticbits.security.contentfilter.IdFetcher;
import gov.nih.nci.cabig.caaers.accesscontrol.query.impl.AbstractIdFetcher;
import gov.nih.nci.cabig.caaers.domain.UserGroupType;

/**
 * Will find the Reporting Periods a logged-in person has access to.
 * This will work-off the Participant Index.
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
 * The Participant Index is available at the time for the given login-Id
 *
 *
 *
 * @author Srini Akkala
 * @author Biju Joseph
 *
 */


public class CaaersReportingPeriodIdFetcherImplBasedOnAuthStudySubject extends AbstractIdFetcher implements IdFetcher {


    //the query
    private final String baseHQL =   "select distinct rp.id from  ParticipantIndex pi "
            + " join pi.participant p "
            + " join p.assignments spa "
            + " join spa.reportingPeriods rp "
            + " where pi.loginId = :LOGIN_ID" ;



    public String getSiteScopedHQL(UserGroupType role){
        if(role != null) return baseHQL + " and pi." + role.hqlAlias() + " = :" + role.hqlAlias();
        return baseHQL;
    }

    public String getStudyScopedHQL(UserGroupType role){
        return getSiteScopedHQL(role);
    }

    
}

