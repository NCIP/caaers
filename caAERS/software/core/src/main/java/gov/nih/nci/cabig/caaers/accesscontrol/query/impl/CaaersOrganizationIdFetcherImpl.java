/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.accesscontrol.query.impl;

import gov.nih.nci.cabig.caaers.CollectionUtil;
import gov.nih.nci.cabig.caaers.dao.index.StudyIndexDao;
import gov.nih.nci.cabig.caaers.dao.query.HQLQuery;
import gov.nih.nci.cabig.caaers.dao.query.NativeSQLQuery;
import gov.nih.nci.cabig.caaers.domain.UserGroupType;
import gov.nih.nci.cabig.caaers.domain.index.IndexEntry;

import java.util.*;

import com.semanticbits.security.contentfilter.IdFetcher;
import gov.nih.nci.cabig.caaers.security.SecurityUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.type.StandardBasicTypes;

/**
 * Will find the organizations that can be accessed by the user.
 *
 * In addition to what CSM has provided, organization hierarchy for Coordinating center and Sponsor should be honoured.
 * @author Biju Joseph
 *
 */
public class CaaersOrganizationIdFetcherImpl extends  AbstractIdFetcher implements IdFetcher{

    protected final Log log = LogFactory.getLog(CaaersOrganizationIdFetcherImpl.class);

    /**
     * Will figure out which organization the logged-in user can access.
     *
     * Note:- Study scoped roles of a ResearchStaff may have to honour the organization hierarchy rule.
     * i.e. A research staff belonging to Coordinating center can work under the same capacity, on the sites.
     *
     * @param loginId - username
     * @return List consisting of IndexEntry objects
     */
    @Override
    public List fetch(String loginId) {

        //find all the accessible organization ids
        List<IndexEntry> organizationIndexEntryList = getCaaersSecurityFacade().getAccessibleOrganizationIds(loginId);
        if(log.isInfoEnabled()) log.info(" Organization Index entries obtained {" + String.valueOf(organizationIndexEntryList) + "}");
        return organizationIndexEntryList;
    }


}
