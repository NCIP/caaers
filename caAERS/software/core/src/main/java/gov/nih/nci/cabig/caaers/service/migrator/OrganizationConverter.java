package gov.nih.nci.cabig.caaers.service.migrator;

import gov.nih.nci.cabig.caaers.domain.LocalOrganization;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.integration.schema.common.OrganizationType;

public class OrganizationConverter extends DomainObjectConverter{

	public void convertOrganizationDtoToDomainOrganization(OrganizationType organizationDto, Organization organization){
		
		organization.setName(organizationDto.getName());
		organization.setNciInstituteCode(organizationDto.getNciInstituteCode());
		organization.setDescriptionText(organizationDto.getDescriptionText());
		organization.setRetiredIndicator(organizationDto.getStatus().name().equals("AC") ? false:true);
		
		if(organizationDto.getMergedOrganization() != null){
			Organization mergedOrganization = new LocalOrganization();
			convertOrganizationDtoToDomainOrganization(organizationDto.getMergedOrganization(), mergedOrganization);
			organization.setMergedOrganization(mergedOrganization);
		}
		
		organization.setCity(organizationDto.getCity());
		organization.setCountry(organizationDto.getCountry());
		organization.setState(organizationDto.getState());
		
	}

}
