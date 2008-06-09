package gov.nih.nci.cabig.caaers.api.impl;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.api.InvestigatorMigratorService;
import gov.nih.nci.cabig.caaers.dao.InvestigatorDao;
import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.dao.query.InvestigatorQuery;
import gov.nih.nci.cabig.caaers.dao.query.OrganizationQuery;
import gov.nih.nci.cabig.caaers.domain.Investigator;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.SiteInvestigator;
import gov.nih.nci.cabig.caaers.integration.schema.common.OrganizationRefType;
import gov.nih.nci.cabig.caaers.integration.schema.investigator.InvestigatorType;
import gov.nih.nci.cabig.caaers.integration.schema.investigator.SiteInvestigatorType;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Required;

public class DefaultInvestigatorMigratorService implements InvestigatorMigratorService {
	private static final Log logger = LogFactory.getLog(DefaultInvestigatorMigratorService.class);
	private OrganizationDao organizationDao;
	private InvestigatorDao investigatorDao;

	/**
     * Fetches the research staff from the DB
     * 
     * @param nciCode
     * @return
     */
	Investigator fetchInvestigator(String nciIdentifier) {
    	InvestigatorQuery invQuery = new InvestigatorQuery();
        if (StringUtils.isNotEmpty(nciIdentifier)) {
        	invQuery.filterByNciIdentifier(nciIdentifier);
        }
        List<Investigator> rsList = investigatorDao.searchInvestigator(invQuery);
        
        if (rsList == null || rsList.isEmpty()) {
            return null;
        }
        return rsList.get(0);
    }

	/**
     * Fetches the organization from the DB
     * 
     * @param nciCode
     * @return
     */
    Organization fetchOrganization(String nciCode) {
        OrganizationQuery orgQuery = new OrganizationQuery();
        if (StringUtils.isNotEmpty(nciCode)) {
            orgQuery.filterByNciCodeExactMatch(nciCode);
        }
        List<Organization> orgList = organizationDao.searchOrganization(orgQuery);
        if (orgList == null || orgList.isEmpty()) {
            logger.error("No organization exists  nciCode :" + nciCode);
            throw new CaaersSystemException("No organization exist with nciCode :" + nciCode);
        }
        if (orgList.size() > 1) {
            logger.error("Multiple organizations exist with same NCI code :" + nciCode);
        }
 
        return orgList.get(0);
    }
	public void saveInvestigator(InvestigatorType investigatorDto) throws RemoteException {
		try {
            logger.info("Begining of DefaultInvestigatorMigratorService : saveInvestigator");
             
           // if (researchStaffDto == null) throw getInvalidResearchStaffException("null input");
            String nciIdentifier = investigatorDto.getNciIdentifier();
            Investigator investigator = fetchInvestigator(nciIdentifier);
            if (investigator == null ) {
            	// build new 
            	investigator = new Investigator();
            	investigator.setNciIdentifier(nciIdentifier);
            } 
            investigator.setFirstName(investigatorDto.getFirstName());
            investigator.setLastName(investigatorDto.getLastName());
            investigator.setMiddleName(investigatorDto.getMiddleName());
            investigator.setEmailAddress(investigatorDto.getEmailAddress());
            investigator.setFaxNumber(investigatorDto.getFaxNumber());
            investigator.setPhoneNumber(investigatorDto.getPhoneNumber());
            
            //get site investigaor
            List<SiteInvestigatorType> siteInvTypeList= investigatorDto.getSiteInvestigator();
            List<SiteInvestigator> siteInvList = new ArrayList<SiteInvestigator>();
            for (SiteInvestigatorType siteInvestigatorType : siteInvTypeList) {
            	// create site investigator and make the list 
            	SiteInvestigator siteInvestigator = new SiteInvestigator();
            	siteInvestigator.setEmailAddress(siteInvestigatorType.getEmailAddress());
            	siteInvestigator.setStatusCode(siteInvestigatorType.getStatusCode().toString());
            	siteInvestigator.setOrganization(fetchOrganization(siteInvestigatorType.getOrganizationRef().getNciInstituteCode()));
            	// ????? siteInvestigator.setStatusDate(siteInvestigatorType.ggetStatusDate());
            	siteInvList.add(siteInvestigator);
            }
            investigator.setSiteInvestigators(siteInvList);

            logger.info("Created the Investigator  :" + investigator.getId());
            logger.info("End of DefaultInvestigatorMigratorService : saveInvestigator");

        } catch (Exception e) {
            logger.error("Error while creating investigator", e);
            throw new RemoteException("Unable to create investigator", e);
        }	
	}
	
	//CONFIGURATION
	@Required
	public InvestigatorDao getInvestigatorDao() {
		return investigatorDao;
	}
	
	@Required
	public void setInvestigatorDao(InvestigatorDao investigatorDao) {
		this.investigatorDao = investigatorDao;
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
