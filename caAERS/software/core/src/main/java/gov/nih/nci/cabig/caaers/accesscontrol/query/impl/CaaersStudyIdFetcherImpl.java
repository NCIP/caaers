/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.accesscontrol.query.impl;

import gov.nih.nci.cabig.caaers.dao.query.HQLQuery;
import gov.nih.nci.cabig.caaers.dao.query.NativeSQLQuery;
import gov.nih.nci.cabig.caaers.domain.UserGroupType;
import gov.nih.nci.cabig.caaers.domain.index.IndexEntry;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.semanticbits.security.contentfilter.IdFetcher;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.type.StandardBasicTypes;


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
		//what set by the Security Role Membership is the final one for Study.
        List<IndexEntry> resultList = getCaaersSecurityFacade().getAccessibleStudyIds(loginId);
		if(log.isDebugEnabled()) log.debug("Index Entries for Study : " + String.valueOf(resultList));
        return resultList;

	}
}
