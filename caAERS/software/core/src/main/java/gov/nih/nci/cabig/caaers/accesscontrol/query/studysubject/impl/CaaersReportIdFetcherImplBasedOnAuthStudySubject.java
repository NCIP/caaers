package gov.nih.nci.cabig.caaers.accesscontrol.query.studysubject.impl;

import gov.nih.nci.cabig.caaers.accesscontrol.query.impl.AbstractIdFetcher;

import com.semanticbits.security.contentfilter.IdFetcher;
import gov.nih.nci.cabig.caaers.domain.UserGroupType;
import gov.nih.nci.security.dao.hibernate.UserGroup;

public class CaaersReportIdFetcherImplBasedOnAuthStudySubject extends AbstractIdFetcher implements IdFetcher {


    //the query
    private final String baseHQL = "select distinct rep.id from  ExpeditedAdverseEventReportIndex ei " +
            " join ei.expeditedAdverseEventReport aer " +
            " join aer.reports rep " +
            " where ei.loginId = :LOGIN_ID" ;


    public String getSiteScopedHQL(UserGroupType role){
        if(role != null) return baseHQL + " and ei." + role.hqlAlias() + " = :" +role.hqlAlias();
        return baseHQL;
    }

    public String getStudyScopedHQL(UserGroupType role){
        return getSiteScopedHQL(role);
    }
    
}
