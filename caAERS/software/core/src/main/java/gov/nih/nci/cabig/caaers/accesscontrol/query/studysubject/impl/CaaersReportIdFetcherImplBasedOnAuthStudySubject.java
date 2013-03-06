/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.accesscontrol.query.studysubject.impl;

import gov.nih.nci.cabig.caaers.accesscontrol.query.impl.AbstractIdFetcher;

import com.semanticbits.security.contentfilter.IdFetcher;

public class CaaersReportIdFetcherImplBasedOnAuthStudySubject extends AbstractIdFetcher implements IdFetcher {


    //the query
    private final String siteScopedHQL;
    private final String studyScopedHQL;

    public CaaersReportIdFetcherImplBasedOnAuthStudySubject(){

        //site query
       siteScopedHQL = null;

       StringBuilder query = new StringBuilder();

        //study query
       query.append("select distinct rep.id from  ExpeditedAdverseEventReportIndex ei ")
       .append(" join ei.expeditedAdverseEventReport aer ")
       .append(" join aer.reports rep ")
       .append(" where ei.roleCode = :ROLE_CODE ")
       .append(" and ei.loginId = :LOGIN_ID");
        
        studyScopedHQL = query.toString();

    }


    public String getSiteScopedHQL(){
        return siteScopedHQL;
    }

    public String getStudyScopedHQL(){
        return studyScopedHQL;
    }
    
}
