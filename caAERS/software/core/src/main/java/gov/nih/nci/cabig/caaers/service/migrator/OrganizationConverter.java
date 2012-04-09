package gov.nih.nci.cabig.caaers.service.migrator;

import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.integration.schema.common.OrganizationType;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class OrganizationConverter extends DomainObjectConverter{
	
	private static Log logger = LogFactory.getLog(OrganizationConverter.class);
	
	private OrganizationDao organizationDao;
	
	public void setOrganizationDao(OrganizationDao organizationDao) {
		this.organizationDao = organizationDao;
	}

	public void convertOrganizationDtoToDomainOrganization(OrganizationType organizationDto, Organization organization){
		
		organization.setName(organizationDto.getName());
		organization.setNciInstituteCode(organizationDto.getNciInstituteCode());
		organization.setDescriptionText(organizationDto.getDescriptionText());
		organization.setRetiredIndicator(organizationDto.getStatus().name().equals("AC") ? false:true);
		
		// Get merged organization by NCI code
		if(organizationDto.getMergedOrganization() != null){
			Organization mergedDbOrganization = organizationDao.getByNCIcode(organizationDto.getMergedOrganization().getNciInstituteCode());
			organization.setMergedOrganization(mergedDbOrganization);
			if(mergedDbOrganization == null){
				logger.debug("merged organization with NCI code :" + organizationDto.getMergedOrganization().getNciInstituteCode() +
						" is not found in the database" );
			}
		}
		
		organization.setCity(organizationDto.getCity());
		organization.setCountry(organizationDto.getCountry());
		organization.setState(organizationDto.getState());
		
	}

}
