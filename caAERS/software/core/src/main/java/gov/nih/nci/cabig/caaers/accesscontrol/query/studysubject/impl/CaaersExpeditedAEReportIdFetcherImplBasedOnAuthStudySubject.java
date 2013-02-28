/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.accesscontrol.query.studysubject.impl;

import gov.nih.nci.cabig.caaers.accesscontrol.query.impl.AbstractIdFetcher;
import gov.nih.nci.cabig.caaers.dao.query.HQLQuery;
import gov.nih.nci.cabig.caaers.domain.Investigator;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;

import java.util.List;

import com.semanticbits.security.contentfilter.IdFetcher;
import gov.nih.nci.cabig.caaers.domain.UserGroupType;

/**
 * Will find the ExpeditedAdverseEventReport a logged-in person has access to.
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

public class CaaersExpeditedAEReportIdFetcherImplBasedOnAuthStudySubject extends AbstractIdFetcher implements IdFetcher {

    //the query
    private final String baseHQL =  "select distinct r.id from  ReportingPeriodIndex ri " +
             " join ri.reportingPeriod rp  " +
            " join rp.aeReports r " +
            " where ri.loginId = :LOGIN_ID" ;

    public String getSiteScopedHQL(UserGroupType role){
        if(role != null) return baseHQL + " and ri." + role.hqlAlias() + " = :" + role.hqlAlias();
        return baseHQL;
    }

    public String getStudyScopedHQL(UserGroupType role){
        return getSiteScopedHQL(role);
    }

    
}
