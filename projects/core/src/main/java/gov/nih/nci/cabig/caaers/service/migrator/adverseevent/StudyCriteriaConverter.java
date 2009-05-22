package gov.nih.nci.cabig.caaers.service.migrator.adverseevent;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.domain.Identifier;
import gov.nih.nci.cabig.caaers.domain.LocalOrganization;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.OrganizationAssignedIdentifier;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.webservice.adverseeventcriteria.OrganizationAssignedIdentifierType;


import java.util.List;


public class StudyCriteriaConverter {
	public void convertStudyDtoToStudyDomain(gov.nih.nci.cabig.caaers.webservice.adverseeventcriteria.StudyType studyDto, Study study) throws CaaersSystemException{
		
		if(study == null){
			study = new Study();
		}
		try{

			//Populate Identifiers
			populateIdentifiers(studyDto, study);

			
		}catch(Exception e){
			throw new CaaersSystemException("Exception while StudyDto Conversion",e);
		}
	}
	
	private void populateIdentifiers(gov.nih.nci.cabig.caaers.webservice.adverseeventcriteria.StudyType studyDto, Study study) throws Exception{
		
		gov.nih.nci.cabig.caaers.webservice.adverseeventcriteria.StudyType.Identifiers identifiers = studyDto.getIdentifiers();
		if(identifiers != null){
			List<Identifier> identifierList = study.getIdentifiers();
			List<OrganizationAssignedIdentifierType> orgAssignedIdList = identifiers.getOrganizationAssignedIdentifier();
			if(orgAssignedIdList != null && !orgAssignedIdList.isEmpty()){
				OrganizationAssignedIdentifier orgIdentifier;
				Organization organization = new LocalOrganization();
				for(OrganizationAssignedIdentifierType organizationAssignedIdentifierType : orgAssignedIdList){
					orgIdentifier = new OrganizationAssignedIdentifier();
					orgIdentifier.setType(organizationAssignedIdentifierType.getType().value());
					orgIdentifier.setValue(organizationAssignedIdentifierType.getValue());
					orgIdentifier.setPrimaryIndicator(organizationAssignedIdentifierType.isPrimaryIndicator());
					organization.setName(organizationAssignedIdentifierType.getOrganization().getName());
					orgIdentifier.setOrganization(organization);
					identifierList.add(orgIdentifier);
				}
				study.setIdentifiers(identifierList);
			}
		}
		
	}
}
