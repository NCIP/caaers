package gov.nih.nci.cabig.caaers.service.migrator;

import gov.nih.nci.cabig.caaers.domain.LocalOrganization;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.integration.schema.common.ActiveInactiveStatusType;
import gov.nih.nci.cabig.caaers.integration.schema.common.OrganizationType;

public class OrganizationConverter {

	public void convertOrganizationDtoToDomainOrganization(OrganizationType organizationDto, Organization organization){
		
		organization.setName(organizationDto.getName());
		organization.setNciInstituteCode(organizationDto.getNciInstituteCode());
		organization.setDescriptionText(organizationDto.getDescriptionText());
		if(organizationDto.getStatus() != null){
			organization.setRetiredIndicator(organizationDto.getStatus().equals(ActiveInactiveStatusType.ACTIVE) ? false:true);
		}
		
		if(organizationDto.getMergedOrganization() != null){
			Organization mergedOrganization = new LocalOrganization();
			convertOrganizationDtoToDomainOrganization(organizationDto.getMergedOrganization(), mergedOrganization);
			organization.setMergedOrganization(mergedOrganization);
		}
		
		organization.setCity(organizationDto.getCity());
		organization.setCountry(organizationDto.getCountry());
		organization.setState(organizationDto.getState());
		organization.setType(organizationDto.getType());
		
	}

}
