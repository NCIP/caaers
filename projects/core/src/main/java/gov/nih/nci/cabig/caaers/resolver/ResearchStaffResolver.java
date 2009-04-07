package gov.nih.nci.cabig.caaers.resolver;

import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.RemoteOrganization;
import gov.nih.nci.cabig.caaers.domain.RemoteResearchStaff;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.coppa.iso.Enxp;
import gov.nih.nci.coppa.iso.Ii;
import gov.nih.nci.coppa.iso.Tel;
import gov.nih.nci.coppa.iso.Util.IiConverter;
import gov.nih.nci.services.correlation.ClinicalResearchStaffCorrelationServiceRemote;
import gov.nih.nci.services.correlation.ClinicalResearchStaffDTO;
import gov.nih.nci.services.correlation.IdentifiedOrganizationCorrelationServiceRemote;
import gov.nih.nci.services.correlation.IdentifiedOrganizationDTO;
import gov.nih.nci.services.correlation.IdentifiedPersonCorrelationServiceRemote;
import gov.nih.nci.services.correlation.IdentifiedPersonDTO;
import gov.nih.nci.services.person.PersonDTO;
import gov.nih.nci.services.person.PersonEntityServiceRemote;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import com.semanticbits.coppa.infrastructure.service.RemoteResolver;

public class ResearchStaffResolver implements RemoteResolver{
	
	private static Logger logger = Logger.getLogger(ResearchStaffResolver.class);

	private IdentifiedOrganizationCorrelationServiceRemote  identifiedOrganizationCorrelationServiceRemote;
	private ClinicalResearchStaffCorrelationServiceRemote clinicalResearchStaffCorrelationServiceRemote;
	private PersonEntityServiceRemote personEntityServiceRemote;
	private IdentifiedPersonCorrelationServiceRemote identifiedPersonCorrelationServiceRemote;

	
	public ResearchStaff populateRemoteResearchStaff(PersonDTO personDTO,String orgCtepId,String coppaOrgId){
		ResearchStaff remoteResearchStaff = new RemoteResearchStaff();
		Iterator<Enxp> enxpItr = personDTO.getName().getPart().iterator();
        Enxp next = enxpItr.next();
        String firstName = next.getValue();
        next = enxpItr.next();
        String lastName = next.getValue();
        
        String externalId = personDTO.getIdentifier().getExtension();
        Set<Tel> email = personDTO.getTelecomAddress().getItem();
        Iterator<Tel> emailItr = email.iterator();
        Tel nextTel = emailItr.next();
        String emailStr = "";
		try {
			emailStr = nextTel.getValue().toURL().toString();
			//remove mailto: string from email 
			if (emailStr.startsWith("mailto:")) {
				emailStr = emailStr.substring("mailto:".length(), emailStr.length());
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		remoteResearchStaff.setFirstName(firstName);
		remoteResearchStaff.setLastName(lastName);
		
		// DO I NEED NCI ID ? 
		Ii ii = new Ii();
		ii.setExtension(externalId);
		try {
			IdentifiedPersonDTO identifiedPersonDTO = identifiedPersonCorrelationServiceRemote.getCorrelation(ii);
			remoteResearchStaff.setNciIdentifier(identifiedPersonDTO.getAssignedId().getExtension());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		remoteResearchStaff.setEmailAddress(emailStr);
		remoteResearchStaff.setExternalId(externalId);
		//build org
		Organization organization = new RemoteOrganization();
		organization.setNciInstituteCode(orgCtepId);

		remoteResearchStaff.setOrganization(organization)	;
		return remoteResearchStaff;
	}
	/**
	 * Find By Organization
	 */
	public List<Object> find(Object example) {
		List<Object> remoteResearchStaffs = new ArrayList<Object>();
		
		ResearchStaff remoteResearchStaffExample = (RemoteResearchStaff)example;
		//organization ctep-id
		Organization org = remoteResearchStaffExample.getOrganization();
		//if organization is null get all research staffs
		if (org == null) {
			PersonDTO personDTO = new PersonDTO();
			Ii ii = new Ii();
			ii.setRoot(IiConverter.CLINICAL_RESEARCH_STAFF_ROOT);
			personDTO.setIdentifier(ii);
			
			List<PersonDTO> allPersons = personEntityServiceRemote.search(personDTO);
			//need to get organization for each person . 
			
			for (PersonDTO person:allPersons) {
				String ctepOrgId = "";
				String coppaOrgId = "";
				try {
					ClinicalResearchStaffDTO ctDto = clinicalResearchStaffCorrelationServiceRemote.getCorrelation(person.getIdentifier()); //player id
					Ii scoperId = ctDto.getScoperIdentifier();
					IdentifiedOrganizationDTO organizationDto = identifiedOrganizationCorrelationServiceRemote.getCorrelation(scoperId);
					ctepOrgId = organizationDto.getAssignedId().getExtension();		
					coppaOrgId = organizationDto.getPlayerIdentifier().getExtension();
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				remoteResearchStaffs.add(this.populateRemoteResearchStaff(person,ctepOrgId,coppaOrgId));
			}
			return remoteResearchStaffs;
		}
		String ctepOrgId = org.getNciInstituteCode();
		//build IdentifiedOrganizationDTO
		IdentifiedOrganizationDTO idOrgDto = new IdentifiedOrganizationDTO();
		Ii ii = new Ii();
		ii.setExtension(ctepOrgId);
		ii.setRoot(IiConverter.CTEP_ORG_IDENTIFIER_ROOT);
		idOrgDto.setAssignedId(ii);
		// get List of Organizations , in this case it should be only one ...
		List <IdentifiedOrganizationDTO> idOrgDtos = identifiedOrganizationCorrelationServiceRemote.search(idOrgDto);
		IdentifiedOrganizationDTO identifiedOrganizationDTO = idOrgDtos.get(0);
		// grab coppa-id of organization from above object
		Ii playerId = identifiedOrganizationDTO.getPlayerIdentifier();
		
		// using above id get research staffs
		ClinicalResearchStaffDTO crDto = new ClinicalResearchStaffDTO();
		crDto.setScoperIdentifier(playerId);
		List<ClinicalResearchStaffDTO> clinicalResearchStaffs = clinicalResearchStaffCorrelationServiceRemote.search(crDto);
		
		//now we need to get the details of each person from person service .
		
		for (ClinicalResearchStaffDTO clinicalResearchStaff:clinicalResearchStaffs) {
			Ii crPlayerId = clinicalResearchStaff.getPlayerIdentifier();
			try {
				PersonDTO personDTO = personEntityServiceRemote.getPerson(crPlayerId);
				ResearchStaff remoteResearchStaff = populateRemoteResearchStaff(personDTO,ctepOrgId,playerId.getExtension());
				remoteResearchStaffs.add(remoteResearchStaff);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		/*
		List<ResearchStaffDTO> ResearchStaffDTOs = 
			researchStaffRemoteService.searchClinicalResearchStaffPerson(remoteResearchStaffExample.getEmailAddress());
		for (ResearchStaffDTO researchStaffDTO:ResearchStaffDTOs) {
			ResearchStaff remoteResearchStaff = populateRemoteResearchStaff(researchStaffDTO);
			remoteResearchStaffs.add(remoteResearchStaff);
		}*/
		return remoteResearchStaffs;
	}

	public Object getRemoteEntityByUniqueId(String externalId) {
		Ii ii = new Ii();
		ii.setExtension(externalId);
		PersonDTO person = null;
		String ctepOrgId = "";
		String coppaOrgId = "";
		try {
			person = personEntityServiceRemote.getPerson(ii);
			ClinicalResearchStaffDTO ctDto = clinicalResearchStaffCorrelationServiceRemote.getCorrelation(person.getIdentifier()); //player id
			Ii scoperId = ctDto.getScoperIdentifier();
			IdentifiedOrganizationDTO organizationDto = identifiedOrganizationCorrelationServiceRemote.getCorrelation(scoperId);
			ctepOrgId = organizationDto.getAssignedId().getExtension();	
			coppaOrgId = organizationDto.getPlayerIdentifier().getExtension();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//ResearchStaffDTO researchStaffDTO = researchStaffRemoteService.getClinicalResearchStaffPerson(email);
		
		return populateRemoteResearchStaff(person,ctepOrgId,coppaOrgId);
		//return null;
	}



	public void setClinicalResearchStaffCorrelationServiceRemote(
			ClinicalResearchStaffCorrelationServiceRemote clinicalResearchStaffCorrelationServiceRemote) {
		this.clinicalResearchStaffCorrelationServiceRemote = clinicalResearchStaffCorrelationServiceRemote;
	}
	public void setIdentifiedOrganizationCorrelationServiceRemote(
			IdentifiedOrganizationCorrelationServiceRemote identifiedOrganizationCorrelationServiceRemote) {
		this.identifiedOrganizationCorrelationServiceRemote = identifiedOrganizationCorrelationServiceRemote;
	}
	public void setPersonEntityServiceRemote(
			PersonEntityServiceRemote personEntityServiceRemote) {
		this.personEntityServiceRemote = personEntityServiceRemote;
	}
	public void setIdentifiedPersonCorrelationServiceRemote(
			IdentifiedPersonCorrelationServiceRemote identifiedPersonCorrelationServiceRemote) {
		this.identifiedPersonCorrelationServiceRemote = identifiedPersonCorrelationServiceRemote;
	}


}
