package gov.nih.nci.cabig.caaers.accesscontrol.filter;

import gov.nih.nci.cabig.caaers.accesscontrol.filter.query.QuerySecurityFilterer;
import gov.nih.nci.cabig.caaers.dao.query.AbstractQuery;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

/**
 * Will take care of applying the correct Security Filtering on the Query. 
 * @author: Biju Joseph
 */
public class QuerySecurityFiltererDispatcher {

      protected final Log log = LogFactory.getLog(QuerySecurityFiltererDispatcher.class);

      private Map<Class<? extends AbstractQuery>, QuerySecurityFilterer> filterContextMap;

    /**
     * Will take care of applying the correct Query Filter
     * @param query
     */
    public void filter(AbstractQuery query,String loginId){

        //find the filter to use
        QuerySecurityFilterer filterer = filterContextMap.get(query.getClass());

        if(filterer == null) {
            if(log.isInfoEnabled()) log.info("no filter found for " + query.getClass().getName());
            return;
        }
        
        //apply the filtering
        if(log.isInfoEnabled()){
            log.info("applying filter :" + String.valueOf(filterer));
        }
        
        filterer.filter(query);
       
    }


    public Map<Class<? extends AbstractQuery>, QuerySecurityFilterer> getFiltererMap() {
        return filterContextMap;
    }

    public void setFiltererMap(Map<Class<? extends AbstractQuery>, QuerySecurityFilterer> filtererMap) {
        this.filterContextMap = filtererMap;
    }
}
