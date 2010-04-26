package gov.nih.nci.cabig.caaers.accesscontrol.filter.query;

import gov.nih.nci.cabig.caaers.dao.query.AbstractQuery;

import java.util.Map;

/**
 * This class will take care of applying the filter based on the indexes. 
 * @author: Biju Joseph
 */
public abstract class QuerySecurityFilterer {

  
    /**
     * Will take care of applying the filter
     * @param query
     * @param loginId - user name
     */
    public abstract void applyFilter(AbstractQuery query, String loginId);

}
