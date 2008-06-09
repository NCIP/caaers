package gov.nih.nci.cabig.caaers.api.impl;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.api.ResearchStaffMigratorService;
import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.dao.ResearchStaffDao;
import gov.nih.nci.cabig.caaers.dao.query.OrganizationQuery;
import gov.nih.nci.cabig.caaers.dao.query.ResearchStaffQuery;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.caaers.integration.schema.common.OrganizationRefType;
import gov.nih.nci.cabig.caaers.integration.schema.researchstaff.ResearchStaffType;
import gov.nih.nci.cabig.caaers.integration.schema.researchstaff.Staff;

import java.rmi.RemoteException;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Required;

public class DefaultResearchStaffMigratorService implements
		ResearchStaffMigratorService {
	
	private static final Log logger = LogFactory.getLog(DefaultResearchStaffMigratorService.class);
	private OrganizationDao organizationDao;
	private ResearchStaffDao researchStaffDao;
	
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

	/**
     * Fetches the research staff from the DB
     * 
     * @param nciCode
     * @return
     */
    ResearchStaff fetchResearchStaff(String nciIdentifier) {
    	ResearchStaffQuery rsQuery = new ResearchStaffQuery();
        if (StringUtils.isNotEmpty(nciIdentifier)) {
        	rsQuery.filterByNciIdentifier(nciIdentifier);
        }
        List<ResearchStaff> rsList = researchStaffDao.searchResearchStaff(rsQuery);
        
        if (rsList == null || rsList.isEmpty()) {
            return null;
        }
        return rsList.get(0);
    }
    public void saveResearchStaff(Staff staff) throws RemoteException {
    	List<ResearchStaffType> researchStaff = staff.getResearchStaff();
    	for (ResearchStaffType researchStaffType:researchStaff) {
    		saveResearchStaff(researchStaffType);
    	}
    }
	public void saveResearchStaff(ResearchStaffType researchStaffDto) throws CaaersSystemException {

        try {
            logger.info("Begining of ResearchStaffMigrator : saveResearchStaff");
             
           // if (researchStaffDto == null) throw getInvalidResearchStaffException("null input");
            String nciIdentifier = researchStaffDto.getNciIdentifier();
            ResearchStaff researchStaff = fetchResearchStaff(nciIdentifier);
            if (researchStaff == null ) {
            	// build new 
            	researchStaff = new ResearchStaff();
            	researchStaff.setNciIdentifier(nciIdentifier);
            } 
            researchStaff.setFirstName(researchStaffDto.getFirstName());
            researchStaff.setLastName(researchStaffDto.getLastName());
            researchStaff.setMiddleName(researchStaffDto.getMiddleName());
            researchStaff.setEmailAddress(researchStaffDto.getEmailAddress());
            researchStaff.setFaxNumber(researchStaffDto.getFaxNumber());
            researchStaff.setPhoneNumber(researchStaffDto.getPhoneNumber());
            
            //get Organizations 
            OrganizationRefType organizationRef = researchStaffDto.getOrganizationRef();
            String nciInstituteCode = organizationRef.getNciInstituteCode();
            Organization organization = fetchOrganization(nciInstituteCode);
            researchStaff.setOrganization(organization);
            
            //save 
            researchStaffDao.save(researchStaff);
            logger.info("Created the research staff :" + researchStaff.getId());
            logger.info("End of ResearchStaffMigrator : saveResearchStaff");

        } catch (Exception e) {
            logger.error("Error while creating research staff", e);
            throw new CaaersSystemException("Unable to create research staff", e);
        }	
        
	}
	
	//CONFIGURATION

    @Required
    public void setOrganizationDao(OrganizationDao organizationDao) {
        this.organizationDao = organizationDao;
    }

    @Required
    public OrganizationDao getOrganizationDao() {
        return organizationDao;
    }

    @Required
    public void setResearchStaffDao(ResearchStaffDao researchStaffDao) {
		this.researchStaffDao = researchStaffDao;
	}

    @Required
	public ResearchStaffDao getResearchStaffDao() {
		return researchStaffDao;
	}	

}
