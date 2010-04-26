package gov.nih.nci.cabig.caaers.accesscontrol.filter.query;

import gov.nih.nci.cabig.caaers.dao.query.AbstractQuery;

/**
 * This class knows how to filter based on StudyIndex
 * @author: Biju Joseph
 */
public class StudyQuerySecurityFilterer extends QuerySecurityFilterer {
    
    /**
     * Will modify the query to use StudyIndex
     * @param query
     * @param loginId - user name
     */
    @Override
    public void applyFilter(AbstractQuery query, String loginId) {
       /*
        *"select s from Study s, StudyIndex i where s = i.study" takes longer time
        * compared to "select s from StudyIndex i join i.study s".
        */
       query.modifyQueryString("select s from StudyIndex sIndx");
       query.join("sIndx.study s");
       query.andWhere("sIndx.loginId = :loginId");
       query.setParameter("loginId", loginId);
    }
}
