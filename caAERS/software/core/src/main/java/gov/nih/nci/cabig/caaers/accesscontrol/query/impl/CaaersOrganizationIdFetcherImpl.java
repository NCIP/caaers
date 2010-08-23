package gov.nih.nci.cabig.caaers.accesscontrol.query.impl;

import gov.nih.nci.cabig.caaers.domain.index.IndexEntry;

import java.util.List;

import com.semanticbits.security.contentfilter.IdFetcher;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Will find the organizations that can be accessed by the user.
 *
 * Rules :
 *  Investigator         - Study Assignment + all study organizations belong to his organization for those studies
 *  AE Coordinator       - Study Assignment + all study organizations belong to his organization for those studies
 *  Subject Coordinator  - Study Assignment + all study organizations belong to his organization for those studies
 *  Data Coordinator     - Study Assignment + all study organizations belong to his organization for those studies
 *  Report Reviewer      - Study Assignment + all study organizations belong to his organization for those studies
 *
 *  Study Coordinator  - No filtering needed
 *  Site Coordinator   - No filtering needed
 *
 * @author Biju Joseph
 *
 */
public class CaaersOrganizationIdFetcherImpl extends  AbstractIdFetcher implements IdFetcher{
    protected final Log log = LogFactory.getLog(CaaersOrganizationIdFetcherImpl.class);
	@Override
	public List fetch(String loginId) {
		List<IndexEntry> resultList = getCaaersSecurityFacade().getAccessibleOrganizationIds(loginId);
        log.info("Organization Fetcher fetched : " + String.valueOf(resultList) );
		return resultList;
	}
}
