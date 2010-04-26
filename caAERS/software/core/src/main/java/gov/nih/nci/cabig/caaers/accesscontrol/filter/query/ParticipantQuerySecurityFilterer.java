package gov.nih.nci.cabig.caaers.accesscontrol.filter.query;

import gov.nih.nci.cabig.caaers.dao.query.AbstractQuery;

/**
 * Will modify the query to filter against the participant index.
 * 
 * @author: Biju Joseph
 */
public class ParticipantQuerySecurityFilterer extends QuerySecurityFilterer {

    /**
     * Will modify the query to join with Participant index
     * @param query
     * @param loginId - user name
     */
    @Override
    public void applyFilter(AbstractQuery query, String loginId) {
        /*
        "select s from Participant p, ParticipantIndex i where p = i.participant" takes longer time
        compared to "select p from ParticipantIndex i join i.participant p".
        */
       query.modifyQueryString("SELECT distinct p from ParticipantIndex pIndx");
       query.join("pIndx.participant p");
       query.andWhere("pIndx.loginId = :loginId");
       query.setParameter("loginId", loginId);
    }
}
