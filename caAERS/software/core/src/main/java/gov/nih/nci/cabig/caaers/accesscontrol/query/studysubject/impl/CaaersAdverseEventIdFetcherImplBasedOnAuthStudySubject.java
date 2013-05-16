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

    private String baseHQL = "select distinct ae.id from  ReportingPeriodIndex ri join ri.reportingPeriod rp join rp.adverseEvents ae where ri.loginId = :LOGIN_ID ";

    public String getSiteScopedHQL(UserGroupType role){
        return baseHQL + " and bitand(ri.role," + role.getPrivilege() + ") > 0";
    }

    public String getStudyScopedHQL(UserGroupType role){
        return getSiteScopedHQL(role);
    }
    
}
