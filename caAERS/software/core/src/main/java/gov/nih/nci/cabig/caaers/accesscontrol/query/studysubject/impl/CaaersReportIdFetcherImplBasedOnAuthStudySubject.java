/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.accesscontrol.query.studysubject.impl;

import gov.nih.nci.cabig.caaers.accesscontrol.query.impl.AbstractIdFetcher;

import com.semanticbits.security.contentfilter.IdFetcher;
import gov.nih.nci.cabig.caaers.domain.UserGroupType;

public class CaaersReportIdFetcherImplBasedOnAuthStudySubject extends AbstractIdFetcher implements IdFetcher {


    //the query
    private final String baseHQL = "select distinct rep.id from  ExpeditedAdverseEventReportIndex ei " +
            " join ei.expeditedAdverseEventReport aer " +
            " join aer.reports rep " +
            " where ei.loginId = :LOGIN_ID" ;


    public String getSiteScopedHQL(UserGroupType role){
        return baseHQL + " and bitand(ei.role, " + role.getPrivilege() + ") > 0"  ;
    }

    public String getStudyScopedHQL(UserGroupType role){
        return getSiteScopedHQL(role);
    }
    
}
