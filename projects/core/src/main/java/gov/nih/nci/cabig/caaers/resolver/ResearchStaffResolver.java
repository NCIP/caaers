package gov.nih.nci.cabig.caaers.resolver;

import edu.duke.cabig.c3pr.esb.Metadata;
import edu.duke.cabig.c3pr.esb.OperationNameEnum;
import edu.duke.cabig.c3pr.esb.ServiceTypeEnum;
import edu.duke.cabig.c3pr.esb.impl.CaXchangeMessageBroadcasterImpl;
import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.RemoteOrganization;
import gov.nih.nci.cabig.caaers.domain.RemoteResearchStaff;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.caaers.utils.XMLUtil;
import gov.nih.nci.coppa.po.ClinicalResearchStaff;
import gov.nih.nci.coppa.po.IdentifiedOrganization;
import gov.nih.nci.coppa.po.IdentifiedPerson;
import gov.nih.nci.coppa.po.Person;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.iso._21090.ENXP;
import org.iso._21090.EntityNamePartType;
import org.iso._21090.II;
import org.iso._21090.TEL;

import com.semanticbits.coppa.infrastructure.service.RemoteResolver;
import com.semanticbits.coppasimulator.util.CoppaObjectFactory;

public class ResearchStaffResolver implements RemoteResolver{
	
	private static Logger logger = Logger.getLogger(ResearchStaffResolver.class);
	private static Log log = LogFactory.getLog(ResearchStaffResolver.class);



	private RemoteResearchStaff setResearchStaffDetails(Person coppaPerson,String nciIdentifier) {
		RemoteResearchStaff remoteResearchStaff = new RemoteResearchStaff();

		Iterator<ENXP> enxpItr = coppaPerson.getName().getPart().iterator();
		ENXP enxp;
		String firstName = "";
		String lastName = "";
		while(enxpItr.hasNext()){
			enxp = enxpItr.next();
			if(enxp.getType().equals(EntityNamePartType.GIV)){
				firstName = enxp.getValue();
			}

			if(enxp.getType().equals(EntityNamePartType.FAM)){
				lastName = enxp.getValue();
			}
		}        

        List<TEL> email = coppaPerson.getTelecomAddress().getItem();
        Iterator<TEL> emailItr = email.iterator();
        TEL nextTel = emailItr.next();
        String emailStr = "";
		emailStr = nextTel.getValue();
		//remove mailto: string from email 
		if (emailStr.startsWith("mailto:")) {
			emailStr = emailStr.substring("mailto:".length(), emailStr.length());
		}
		remoteResearchStaff.setFirstName(firstName);
		remoteResearchStaff.setLastName(lastName);
		remoteResearchStaff.setEmailAddress(emailStr);
		remoteResearchStaff.setExternalId(coppaPerson.getIdentifier().getExtension());
		if (nciIdentifier != null) {
			remoteResearchStaff.setNciIdentifier(nciIdentifier);
		}
		return remoteResearchStaff;

	}
	
	private IdentifiedOrganization getIdentifiedOrganization(gov.nih.nci.coppa.po.Organization coppaOrganization){
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
	
	public RemoteResearchStaff populateRemoteResearchStaff(Person coppaPerson, String nciIdentifier, List<gov.nih.nci.coppa.po.Organization> coppaOrganizationList){		

		RemoteResearchStaff remoteResearchStaff = setResearchStaffDetails(coppaPerson,nciIdentifier);	
		List<gov.nih.nci.coppa.po.IdentifiedOrganization> identifiedCoppaOrganizationList = new ArrayList<IdentifiedOrganization>();

		if(coppaOrganizationList != null && coppaOrganizationList.size()>0){
			for(gov.nih.nci.coppa.po.Organization coppaOrganization: coppaOrganizationList){
				IdentifiedOrganization identifiedOrganization = getIdentifiedOrganization(coppaOrganization);
				identifiedCoppaOrganizationList.add(identifiedOrganization);
			}
		}
		//Build HealthcareSite and HealthcareSiteInvestigator

		Organization site = null;

		if (identifiedCoppaOrganizationList != null && identifiedCoppaOrganizationList.size()>0){

			for(gov.nih.nci.coppa.po.IdentifiedOrganization identifiedOrganization: identifiedCoppaOrganizationList){
				site = new RemoteOrganization();
				site.setNciInstituteCode(identifiedOrganization.getAssignedId().getExtension());			
				remoteResearchStaff.setOrganization(site);

			}

		}

		return remoteResearchStaff;
	}
	public RemoteResearchStaff populateRemoteResearchStaff(Person coppaPerson, String nciIdentifier, IdentifiedOrganization identifiedOrganization){		
		RemoteResearchStaff remoteResearchStaff = setResearchStaffDetails(coppaPerson,nciIdentifier);		
		Organization site = new RemoteOrganization();
		site.setNciInstituteCode(identifiedOrganization.getAssignedId().getExtension());		
		remoteResearchStaff.setOrganization(site);

		return remoteResearchStaff;
	}
	private IdentifiedPerson getIdentifiedPerson(II personIdentifier) {
		Metadata mData = new Metadata(OperationNameEnum.search.getName(),  "externalId", "IDENTIFIED_PERSON");		
		IdentifiedPerson ip = CoppaObjectFactory.getCoppaIdentfiedPersonSearchCriteriaForCorrelation(personIdentifier);
		String ipPayload = CoppaObjectFactory.getCoppaIdentfiedPersonXml(ip);		
		
		String result = broadcastCoppaMessage(ipPayload, mData);
		List<String> identifiedPersons = XMLUtil.getObjectsFromCoppaResponse(result);	
		IdentifiedPerson identifiedPerson = null;
		for(String identifiedPersonXml: identifiedPersons){
			identifiedPerson = CoppaObjectFactory.getCoppaIdentfiedPerson(identifiedPersonXml);
		}
		return identifiedPerson;
	}
	private IdentifiedPerson getIdentifiedPerson(IdentifiedPerson ip) {
		Metadata mData = new Metadata(OperationNameEnum.search.getName(),  "externalId", "IDENTIFIED_PERSON");		
		
		String ipPayload = CoppaObjectFactory.getCoppaIdentfiedPersonXml(ip);		
		
		String result = broadcastCoppaMessage(ipPayload, mData);

		List<String> identifiedPersons = XMLUtil.getObjectsFromCoppaResponse(result);	
		IdentifiedPerson identifiedPerson = null;
		for(String identifiedPersonXml: identifiedPersons){
			identifiedPerson = CoppaObjectFactory.getCoppaIdentfiedPerson(identifiedPersonXml);
		}
		return identifiedPerson;
	}
	/**
	 * Find By Organization
	 */
	public List<Object> find(Object example) {	
		
		ResearchStaff remoteResearchStaffExample = (RemoteResearchStaff)example;
		List<Object> remoteResearchStaffList = new ArrayList<Object>();
		RemoteResearchStaff tempRemoteResearchStaff = null;
		
		if (remoteResearchStaffExample.getNciIdentifier() != null) {
			//get Identified Organization ... 
			IdentifiedPerson identifiedPersonToSearch = CoppaObjectFactory.getCoppaIdentfiedPersonSearchCriteriaOnCTEPId(remoteResearchStaffExample.getNciIdentifier());
			IdentifiedPerson identifiedPerson = getIdentifiedPerson(identifiedPersonToSearch);
			if (identifiedPerson == null) {
				return remoteResearchStaffList;
			}
			II ii = identifiedPerson.getPlayerIdentifier();
			String iiXml = CoppaObjectFactory.getCoppaIIXml(ii);
			try {
				String resultXml = broadcastPersonGetById(iiXml);
				tempRemoteResearchStaff = loadResearchStaffForPersonResult(resultXml);
				remoteResearchStaffList.add(tempRemoteResearchStaff);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return remoteResearchStaffList;
		}
		
		
		if (remoteResearchStaffExample.getOrganization() != null){
			//get Organization by ctepId 
			IdentifiedOrganization identifiedOrganizationSearchCriteria = CoppaObjectFactory.getCoppaIdentfiedOrganizationSearchCriteriaOnCTEPId(remoteResearchStaffExample.getOrganization().getNciInstituteCode());
			String payload = CoppaObjectFactory.getCoppaIdentfiedOrganization(identifiedOrganizationSearchCriteria);
			Metadata mData = new Metadata(OperationNameEnum.search.getName(),  "externalId", ServiceTypeEnum.IDENTIFIED_ORGANIZATION.getName());
			String results =broadcastCoppaMessage(payload, mData);//
			List<String> resultObjects = XMLUtil.getObjectsFromCoppaResponse(results);
			for (String resultObj:resultObjects) {
				IdentifiedOrganization coppaIdOrganization = CoppaObjectFactory.getCoppaIdentfiedOrganization(resultObj);
				II organizationIdentifier = coppaIdOrganization.getPlayerIdentifier();
				String iiXml = CoppaObjectFactory.getCoppaIIXml(organizationIdentifier);
				//Get Organization based on player id of above.
				mData = new Metadata(OperationNameEnum.getById.getName(),  "externalId", ServiceTypeEnum.ORGANIZATION.getName());
				String organizationResults = broadcastCoppaMessage(iiXml, mData);//
				List<String> organizationResultObjects = XMLUtil.getObjectsFromCoppaResponse(organizationResults);
				for (String organizationResultObject:organizationResultObjects) {
					gov.nih.nci.coppa.po.Organization coppaOrganizationResult = CoppaObjectFactory.getCoppaOrganization(organizationResultObject);
					II ii = coppaOrganizationResult.getIdentifier();
					//above ii is the scoper if clinical research staff...
					ClinicalResearchStaff clinicalResearchStaff = CoppaObjectFactory.getCoppaClinicalResearchStaffWithScoperIdAsSearchCriteria(ii);
					String coppaClinicalResearchStaffXml = CoppaObjectFactory.getClinicalResearchStaffXml(clinicalResearchStaff);
					String sRolesXml = "";
					try {
						sRolesXml = broadcastClinicalResearchStaffSearch(coppaClinicalResearchStaffXml);
					} catch (CaaersSystemException e) {
						System.out.print(e);
					}

					List<String> sRoles = XMLUtil.getObjectsFromCoppaResponse(sRolesXml);	
					
					for(String sRole: sRoles){
						ClinicalResearchStaff crs = CoppaObjectFactory.getCoppaClinicalResearchStaff(sRole);
						II pid = crs.getPlayerIdentifier();	
						String idXml = CoppaObjectFactory.getCoppaIIXml(pid);
						//above player id is the Id of a Person . 						
						// now get  the Person by Id ... 
						mData = new Metadata(OperationNameEnum.getById.getName(), "externalId", ServiceTypeEnum.PERSON.getName());
						String personResultXml = "";
						try {
							personResultXml = broadcastCoppaMessage(idXml, mData);//
							List<String> persons = XMLUtil.getObjectsFromCoppaResponse(personResultXml);	
							for(String personXml: persons){
								Person person = CoppaObjectFactory.getCoppaPerson(personXml);
								IdentifiedPerson identifiedPerson = getIdentifiedPerson(person.getIdentifier());
								String nciIdentifier = null;
								if (identifiedPerson != null ) {
									nciIdentifier = identifiedPerson.getAssignedId().getExtension();
								}
								tempRemoteResearchStaff = populateRemoteResearchStaff(person, nciIdentifier , coppaIdOrganization);
								remoteResearchStaffList.add(tempRemoteResearchStaff);
							}
							
						} catch (CaaersSystemException e) {
							System.out.print(e);
						}

					}
				}
			}
			return remoteResearchStaffList;
		}
		
		
		
		
		
		String personXml = CoppaObjectFactory.getCoppaPersonXml(CoppaObjectFactory.getCoppaPerson(remoteResearchStaffExample.getFirstName(), remoteResearchStaffExample.getMiddleName(), remoteResearchStaffExample.getLastName()));
		String resultXml = "";
		try {
			resultXml = broadcastPersonSearch(personXml);
		} catch (Exception e) {
			System.out.print(e);
		}
		List<String> coppaPersons = XMLUtil.getObjectsFromCoppaResponse(resultXml);
		

		Person coppaPerson = null;
		if (coppaPersons != null){
			for(String coppaPersonXml: coppaPersons){
				coppaPerson = CoppaObjectFactory.getCoppaPerson(coppaPersonXml);
				IdentifiedPerson identifiedPerson = getIdentifiedPerson(coppaPerson.getIdentifier());
				String nciIdentifier = null;
				if (identifiedPerson != null ) {
					nciIdentifier = identifiedPerson.getAssignedId().getExtension();
				}
				
				List<gov.nih.nci.coppa.po.Organization>  coppaOrganizationList = getOrganizationsForPerson(coppaPerson);
				tempRemoteResearchStaff = populateRemoteResearchStaff(coppaPerson, nciIdentifier, coppaOrganizationList);
				remoteResearchStaffList.add(tempRemoteResearchStaff);
			}
		}
		return remoteResearchStaffList;
	}
	private RemoteResearchStaff loadResearchStaffForPersonResult(String personResultXml) {
		List<String> results = XMLUtil.getObjectsFromCoppaResponse(personResultXml);
		List<gov.nih.nci.coppa.po.Organization> coppaOrganizationList = null;
		RemoteResearchStaff remoteResearchStaff = null;
		Person coppaPerson = null;
		String nciIdentifier = null;
		if (results.size() > 0) {
			coppaPerson = CoppaObjectFactory.getCoppaPerson(results.get(0));
			coppaOrganizationList = getOrganizationsForPerson(coppaPerson);
			IdentifiedPerson identifiedPerson = getIdentifiedPerson(coppaPerson.getIdentifier());
			
			if (identifiedPerson != null ) {
				nciIdentifier = identifiedPerson.getAssignedId().getExtension();
			}
			remoteResearchStaff =  this.populateRemoteResearchStaff(coppaPerson, nciIdentifier, coppaOrganizationList);
		}

		return remoteResearchStaff;		
	}
	public Object getRemoteEntityByUniqueId(String externalId) {
		II ii = CoppaObjectFactory.getIISearchCriteriaForPerson(externalId);		

		String iiXml = CoppaObjectFactory.getCoppaIIXml(ii);
		String resultXml = "";
		try {
			resultXml = broadcastPersonGetById(iiXml);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return loadResearchStaffForPersonResult(resultXml);
	}

	public String broadcastPersonGetById(String iiXml) throws Exception {
		//build metadata with operation name and the external Id and pass it to the broadcast method.

        Metadata mData = new Metadata(OperationNameEnum.getById.getName(), "externalId", ServiceTypeEnum.PERSON.getName());
		return broadcastCoppaMessage(iiXml, mData);

	}
	
	public String broadcastPersonSearch(String iiXml) throws Exception{
		//build metadata with operation name and the external Id and pass it to the broadcast method.
        Metadata mData = new Metadata(OperationNameEnum.search.getName(),  "externalId", ServiceTypeEnum.PERSON.getName());
		return broadcastCoppaMessage(iiXml, mData);

	}



	private List<gov.nih.nci.coppa.po.Organization> getOrganizationsForPerson(Person coppaPerson) {
		List<gov.nih.nci.coppa.po.Organization>  coppaOrganizationList = new ArrayList<gov.nih.nci.coppa.po.Organization>();

		ClinicalResearchStaff clinicalResearchStaff = CoppaObjectFactory.getCoppaClinicalResearchStaff(coppaPerson.getIdentifier());
		String coppaClinicalResearchStaffXml = CoppaObjectFactory.getClinicalResearchStaffXml(clinicalResearchStaff);

		String sRolesXml = "";
		try {
			sRolesXml = broadcastClinicalResearchStaffSearch(coppaClinicalResearchStaffXml);
		} catch (CaaersSystemException e) {
			System.out.print(e);
		}

		List<String> sRoles = XMLUtil.getObjectsFromCoppaResponse(sRolesXml);		

		for(String sRole: sRoles){

			String orgResultXml = "";

			ClinicalResearchStaff hcp = CoppaObjectFactory.getCoppaClinicalResearchStaff(sRole);
			String orgIiXml = CoppaObjectFactory.getCoppaIIXml(hcp.getScoperIdentifier());
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

	public String broadcastIdentifiedOrganizationSearch(String healthcareSiteXml) throws CaaersSystemException {
		//build metadata with operation name and the external Id and pass it to the broadcast method.|
        Metadata mData = new Metadata(OperationNameEnum.search.getName(), "externalId", ServiceTypeEnum.IDENTIFIED_ORGANIZATION.getName());
        return broadcastCoppaMessage(healthcareSiteXml, mData);
	}
	
	public String broadcastClinicalResearchStaffSearch(String personXml) throws CaaersSystemException {

		//build metadata with operation name and the external Id and pass it to the broadcast method.

        Metadata mData = new Metadata(OperationNameEnum.search.getName(), "externalId", ServiceTypeEnum.CLINICAL_RESEARCH_STAFF.getName());
		return broadcastCoppaMessage(personXml, mData);

	}

	public String broadcastCoppaMessage(String xml, Metadata mData) throws CaaersSystemException {
		String caXchangeResponseXml = null;
		try {
			CaXchangeMessageBroadcasterImpl broadCaster = new CaXchangeMessageBroadcasterImpl();
            broadCaster.setCaXchangeURL("https://cbvapp-d1017.nci.nih.gov:28445/wsrf-caxchange/services/cagrid/CaXchangeRequestProcessor");
            caXchangeResponseXml = broadCaster.broadcastCoppaMessage(xml, mData);
        }
        catch (Exception e) {
            log.error(e);
            throw new CaaersSystemException ("BROADCAST.SEND_ERROR", e);

        }

		return caXchangeResponseXml;

	}


	/**

	 * Broadcast Org getById.

	 * 

	 * @param iiXml the ii xml

	 * @return the string

	 * @throws Exception the exception

	 */

	public String broadcastOrganizationGetById(String iiXml) throws Exception{

		//build metadata with operation name and the external Id and pass it to the broadcast method.

        Metadata mData = new Metadata(OperationNameEnum.getById.getName(),  "extId", ServiceTypeEnum.ORGANIZATION.getName());

		return broadcastCoppaMessage(iiXml, mData);

	}


}
