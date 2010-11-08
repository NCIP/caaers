package gov.nih.nci.cabig.caaers.accesscontrol.query.impl;

import gov.nih.nci.cabig.caaers.dao.query.HQLQuery;
import gov.nih.nci.cabig.caaers.domain.UserGroupType;
import gov.nih.nci.cabig.caaers.domain.index.IndexEntry;
import gov.nih.nci.cabig.caaers.security.SecurityUtils;

import java.util.List;
import java.util.Map;

import com.semanticbits.security.contentfilter.IdFetcher;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * Will return the ID of the studies that a particular user has access to.
 * @author Srini Akkala
 * @author Biju Joseph
 *
 */
public class CaaersStudyIdFetcherImpl extends AbstractIdFetcher implements IdFetcher {
    protected final Log log = LogFactory.getLog(CaaersStudyIdFetcherImpl.class);
    
	@Override
	public List fetch(String loginId) {

		List<IndexEntry> resultList = getCaaersSecurityFacade().getAccessibleStudyIds(loginId);
        List<Integer> studyIds;
        List<Integer> allStudyIds = null;
        for(IndexEntry entry : resultList){
            studyIds = entry.getEntityIds();
            if(studyIds != null && !studyIds.isEmpty()){
                if(studyIds.size() == 1 && studyIds.get(0).equals(Integer.MIN_VALUE)){ //all studies
                    if(allStudyIds == null ){
                        allStudyIds = (List<Integer>) search(new HQLQuery("select s.id from Study s"));
                    }
                    entry.setEntityIds(allStudyIds);

                }
            }
        }
        
        if(log.isInfoEnabled()){
            log.info("Study Fetcher fetched : " + String.valueOf(resultList) );
        }
        
		return resultList;
	}
}
