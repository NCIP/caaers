package gov.nih.nci.cabig.caaers.resolver;

import edu.duke.cabig.c3pr.esb.Metadata;
import edu.duke.cabig.c3pr.esb.OperationNameEnum;
import edu.duke.cabig.c3pr.esb.ServiceTypeEnum;
import edu.duke.cabig.c3pr.esb.impl.CaXchangeMessageBroadcasterImpl;
import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.utils.XMLUtil;
import gov.nih.nci.coppa.po.ClinicalResearchStaff;
import gov.nih.nci.coppa.po.HealthCareProvider;
import gov.nih.nci.coppa.po.IdentifiedOrganization;
import gov.nih.nci.coppa.po.IdentifiedPerson;
import gov.nih.nci.coppa.po.Person;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.iso._21090.II;

import com.semanticbits.coppasimulator.util.CoppaObjectFactory;

public abstract class BaseResolver {
	//private MessageBroadcastService coppaMessageBroadcastService;
	private static Log log = LogFactory.getLog(BaseResolver.class);
	
	public String broadcastPersonSearch(String iiXml) throws Exception{
		//build metadata with operation name and the external Id and pass it to the broadcast method.
        Metadata mData = new Metadata(OperationNameEnum.search.getName(),  "externalId", ServiceTypeEnum.PERSON.getName());
		return broadcastCOPPA(iiXml, mData);

	}
	
	public IdentifiedOrganization getIdentifiedOrganization(gov.nih.nci.coppa.po.Organization coppaOrganization){
		if(coppaOrganization != null){
			//using coppa organization identier and previously obtained id of CTEP (hard coded in CoppaObjectFactory.getIIOfCTEP) get Identified organization 

			IdentifiedOrganization identifiedOrganization = CoppaObjectFactory.getCoppaIdentfiedOrganizationSearchCriteriaForCorrelation(coppaOrganization.getIdentifier());
			String identifiedOrganizationXml = CoppaObjectFactory.getCoppaIdentfiedOrganization(identifiedOrganization);		
			String resultXml = "";
			try {
				resultXml = broadcastIdentifiedOrganizationSearch(identifiedOrganizationXml);
			} catch (CaaersSystemException e) {
				log.error(e.getMessage());
			}
			List<String> results = XMLUtil.getObjectsFromCoppaResponse(resultXml);
			if (results.size() > 0) {
				identifiedOrganization = CoppaObjectFactory.getCoppaIdentfiedOrganization(results.get(0));
			}
			return identifiedOrganization;
		}
		return null;
	}
	
	public IdentifiedPerson getIdentifiedPerson(IdentifiedPerson ip) {
		Metadata mData = new Metadata(OperationNameEnum.search.getName(),  "externalId", "IDENTIFIED_PERSON");		
		
		String ipPayload = CoppaObjectFactory.getCoppaIdentfiedPersonXml(ip);		
		
		String result = broadcastCOPPA(ipPayload, mData);

		List<String> identifiedPersons = XMLUtil.getObjectsFromCoppaResponse(result);	
		IdentifiedPerson identifiedPerson = null;
		for(String identifiedPersonXml: identifiedPersons){
			identifiedPerson = CoppaObjectFactory.getCoppaIdentfiedPerson(identifiedPersonXml);
		}
		return identifiedPerson;
	}
	
	public IdentifiedPerson getIdentifiedPerson(II personIdentifier) {
		Metadata mData = new Metadata(OperationNameEnum.search.getName(),  "externalId", "IDENTIFIED_PERSON");		
		IdentifiedPerson ip = CoppaObjectFactory.getCoppaIdentfiedPersonSearchCriteriaForCorrelation(personIdentifier);
		String ipPayload = CoppaObjectFactory.getCoppaIdentfiedPersonXml(ip);		
		
		String result = broadcastCOPPA(ipPayload, mData);
		List<String> identifiedPersons = XMLUtil.getObjectsFromCoppaResponse(result);	
		IdentifiedPerson identifiedPerson = null;
		for(String identifiedPersonXml: identifiedPersons){
			identifiedPerson = CoppaObjectFactory.getCoppaIdentfiedPerson(identifiedPersonXml);
		}
		return identifiedPerson;
	}
	
	public List<gov.nih.nci.coppa.po.Organization> getOrganizationsForPerson(Person coppaPerson,ServiceTypeEnum role) {
		List<gov.nih.nci.coppa.po.Organization>  coppaOrganizationList = new ArrayList<gov.nih.nci.coppa.po.Organization>();
		String coppaRoleXml = null;
		
		if (role.equals(ServiceTypeEnum.HEALTH_CARE_PROVIDER)) {
			HealthCareProvider healthCareProvider = CoppaObjectFactory.getCoppaHealthCareProvider(coppaPerson.getIdentifier());
			coppaRoleXml = CoppaObjectFactory.getCoppaHealthCareProviderXml(healthCareProvider);			
		}
		if (role.equals(ServiceTypeEnum.CLINICAL_RESEARCH_STAFF)) {
			ClinicalResearchStaff clinicalResearchStaff = CoppaObjectFactory.getCoppaClinicalResearchStaff(coppaPerson.getIdentifier());
			coppaRoleXml = CoppaObjectFactory.getClinicalResearchStaffXml(clinicalResearchStaff);			
		}		
		

		String sRolesXml = "";
		try {
			sRolesXml = broadcastRoleSearch(coppaRoleXml,role);
		} catch (CaaersSystemException e) {
			System.out.print(e);
		}

		List<String> sRoles = XMLUtil.getObjectsFromCoppaResponse(sRolesXml);		

		for(String sRole: sRoles){
			String orgResultXml = "";
			II scoperId = null;

			if (role.equals(ServiceTypeEnum.CLINICAL_RESEARCH_STAFF)) {
				ClinicalResearchStaff crs = CoppaObjectFactory.getCoppaClinicalResearchStaff(sRole);	
				scoperId = crs.getScoperIdentifier();
			}
			if (role.equals(ServiceTypeEnum.HEALTH_CARE_PROVIDER)) {
				HealthCareProvider hcp = CoppaObjectFactory.getCoppaHealthCareProvider(sRole);	
				scoperId = hcp.getScoperIdentifier();
			}			
			String orgIiXml = CoppaObjectFactory.getCoppaIIXml(scoperId);
			try {
				orgResultXml = broadcastOrganizationGetById(orgIiXml);
			} catch (Exception e) {
				System.out.print(e);
			}

			List<String> orgResults = XMLUtil.getObjectsFromCoppaResponse(orgResultXml);
			if (orgResults.size() > 0) {
				coppaOrganizationList.add(CoppaObjectFactory.getCoppaOrganization(orgResults.get(0)));
			}

		}

		return coppaOrganizationList;

	}

	public String broadcastRoleSearch(String personXml,ServiceTypeEnum role) throws CaaersSystemException {
	       Metadata mData = new Metadata(OperationNameEnum.search.getName(), "externalId", role.getName());
		   return broadcastCOPPA(personXml, mData);
	}

	public String broadcastOrganizationGetById(String iiXml) throws Exception{
        Metadata mData = new Metadata(OperationNameEnum.getById.getName(),  "externalId", ServiceTypeEnum.ORGANIZATION.getName());
		return broadcastCOPPA(iiXml, mData);
	}
	
	public String broadcastPersonGetById(String iiXml) throws Exception {
        Metadata mData = new Metadata(OperationNameEnum.getById.getName(), "externalId", ServiceTypeEnum.PERSON.getName());
		return broadcastCOPPA(iiXml, mData);
	}
	public String broadcastIdentifiedOrganizationSearch(String healthcareSiteXml) throws CaaersSystemException {
        Metadata mData = new Metadata(OperationNameEnum.search.getName(), "externalId", ServiceTypeEnum.IDENTIFIED_ORGANIZATION.getName());
        return broadcastCOPPA(healthcareSiteXml, mData);
	}

/*
	public MessageBroadcastService getCoppaMessageBroadcastService() {
		return coppaMessageBroadcastService;
	}

	public void setCoppaMessageBroadcastService(
			MessageBroadcastService coppaMessageBroadcastService) {
		this.coppaMessageBroadcastService = coppaMessageBroadcastService;
	}*/
	
	public String broadcastCOPPA(String message,Metadata metaData) throws gov.nih.nci.cabig.caaers.esb.client.BroadcastException {    	
        System.out.println("Broadcasting to coppa ");
		String result = null;
        try {
        	CaXchangeMessageBroadcasterImpl broadCaster = new CaXchangeMessageBroadcasterImpl();
        //	System.out.println("ca exchage URL + " + configuration.get(Configuration.CAEXCHANGE_URL));
            broadCaster.setCaXchangeURL("https://ncias-c278-v.nci.nih.gov:58445/wsrf-caxchange/services/cagrid/CaXchangeRequestProcessor");

        	result = broadCaster.broadcastCoppaMessage(message, metaData);
		} catch (edu.duke.cabig.c3pr.esb.BroadcastException e) {

            throw new gov.nih.nci.cabig.caaers.esb.client.BroadcastException(e);
		}
    	return result;
    }
	
	
	public String broadcastCOPPA(List<String> messages,Metadata metaData) throws gov.nih.nci.cabig.caaers.esb.client.BroadcastException {    	
        String result = null;
        try {
        	CaXchangeMessageBroadcasterImpl broadCaster = new CaXchangeMessageBroadcasterImpl();
        //	System.out.println("ca exchage URL + " + configuration.get(Configuration.CAEXCHANGE_URL));
            broadCaster.setCaXchangeURL("https://ncias-c278-v.nci.nih.gov:58445/wsrf-caxchange/services/cagrid/CaXchangeRequestProcessor");

        	result = broadCaster.broadcastCoppaMessage(messages, metaData);
		} catch (edu.duke.cabig.c3pr.esb.BroadcastException e) {

            throw new gov.nih.nci.cabig.caaers.esb.client.BroadcastException(e);
		}
    	return result;
    }
	
	
}
