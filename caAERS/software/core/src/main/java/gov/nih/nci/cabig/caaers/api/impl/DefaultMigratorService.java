package gov.nih.nci.cabig.caaers.api.impl;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.api.AbstractImportService;
import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.dao.query.OrganizationQuery;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.repository.OrganizationRepository;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Required;

public abstract class DefaultMigratorService extends AbstractImportService{
	
	private OrganizationDao organizationDao;

	private static final Log logger = LogFactory.getLog(DefaultMigratorService.class);
	
	
	/**
     * Fetches the organization from the DB
     * 
     * @param nciCode
     * @return
     */
    Organization fetchOrganization(String nciCode) throws CaaersSystemException{
        OrganizationQuery orgQuery = new OrganizationQuery();
        if (StringUtils.isNotEmpty(nciCode)) {
            orgQuery.filterByNciCodeExactMatch(nciCode);
        }
        List<Organization> orgList = organizationRepository.searchOrganization(orgQuery);
        if (orgList == null || orgList.isEmpty()) {
            logger.error("No organization exists  nciCode :" + nciCode);
            throw new CaaersSystemException("No organization exist with nciCode :" + nciCode);
        }
        if (orgList.size() > 1) {
            logger.error("Multiple organizations exist with same NCI code :" + nciCode);
        }
 
        return orgList.get(0);
    }
    
	@Required
	public OrganizationDao getOrganizationDao() {
		return organizationDao;
	}

	@Required
	public void setOrganizationDao(OrganizationDao organizationDao) {
		this.organizationDao = organizationDao;
	}

    
}
