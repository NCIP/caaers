package gov.nih.nci.cabig.caaers.service.migrator.adverseevent;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.domain.Identifier;
import gov.nih.nci.cabig.caaers.domain.LocalOrganization;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.OrganizationAssignedIdentifier;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.webservice.adverseeventcriteria.OrganizationAssignedIdentifierType;
import gov.nih.nci.cabig.caaers.webservice.adverseeventcriteria.ParticipantType.Identifiers;

import java.util.List;

public class ParticipantCriteriaConverter {
	public void convertParticipantDtoToParticipantDomain(gov.nih.nci.cabig.caaers.webservice.adverseeventcriteria.ParticipantType participantDto, Participant participant) throws CaaersSystemException{
		if(participant == null){
			participant = new Participant();
		}
		
		try{
			//Populate Identifiers
			populateIdentifiers(participantDto,participant);
			

			
		}catch(Exception e){
			throw new CaaersSystemException("Exception while Participant Criteria Conversion",e);
		}
	}
	
	
	private void populateIdentifiers(gov.nih.nci.cabig.caaers.webservice.adverseeventcriteria.ParticipantType participantDto, Participant participant) throws Exception{
		
		Identifiers identifiers = participantDto.getIdentifiers();
		
		if(identifiers != null){
			List<Identifier> identifierList = participant.getIdentifiers();
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
					organization.setNciInstituteCode(organizationAssignedIdentifierType.getOrganization().getNciInstituteCode());
					orgIdentifier.setOrganization(organization);
					identifierList.add(orgIdentifier);
				}
				participant.setIdentifiers(identifierList);
			}
		}
		
	}

}
