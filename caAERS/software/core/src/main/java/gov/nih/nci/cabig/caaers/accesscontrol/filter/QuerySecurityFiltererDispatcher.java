package gov.nih.nci.cabig.caaers.accesscontrol.filter;

import gov.nih.nci.cabig.caaers.accesscontrol.filter.query.QuerySecurityFilterer;
import gov.nih.nci.cabig.caaers.dao.query.AbstractQuery;

import java.util.Map;

/**
 * Will take care of applying the correct Security Filtering on the Query. 
 * @author: Biju Joseph
 */
public class QuerySecurityFiltererDispatcher {

      private Map<Class<? extends AbstractQuery>, QuerySecurityFilterer> filtererMap;

    /**
     * Will take care of applying the correct Query Filter
     * @param query
     * @param loginName
     */
    public void filter(AbstractQuery query){
        
    }


    public Map<Class<? extends AbstractQuery>, QuerySecurityFilterer> getFiltererMap() {
        return filtererMap;
    }

    public void setFiltererMap(Map<Class<? extends AbstractQuery>, QuerySecurityFilterer> filtererMap) {
        this.filtererMap = filtererMap;
    }
}
