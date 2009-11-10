package gov.nih.nci.cabig.caaers.resolver;

import edu.duke.cabig.c3pr.esb.Metadata;
import edu.duke.cabig.c3pr.esb.OperationNameEnum;
import edu.duke.cabig.c3pr.esb.ServiceTypeEnum;
import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.domain.Investigator;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.RemoteInvestigator;
import gov.nih.nci.cabig.caaers.domain.RemoteOrganization;
import gov.nih.nci.cabig.caaers.domain.SiteInvestigator;
import gov.nih.nci.cabig.caaers.utils.XMLUtil;
import gov.nih.nci.coppa.po.HealthCareProvider;
import gov.nih.nci.coppa.po.IdentifiedOrganization;
import gov.nih.nci.coppa.po.IdentifiedPerson;
import gov.nih.nci.coppa.po.Person;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.iso._21090.ENXP;
import org.iso._21090.EntityNamePartType;
import org.iso._21090.II;
import org.iso._21090.TEL;

import com.semanticbits.coppa.infrastructure.service.RemoteResolver;
import com.semanticbits.coppasimulator.util.CoppaObjectFactory;

public class InvestigatorResolver extends BaseResolver implements RemoteResolver {
	
	private static Logger logger = Logger.getLogger(InvestigatorResolver.class);
	private static Log log = LogFactory.getLog(InvestigatorResolver.class);

	private RemoteInvestigator setInvestigatorDetails(Person coppaPerson,String nciIdentifier) {
		RemoteInvestigator remoteInvestigator = new RemoteInvestigator();

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
		remoteInvestigator.setFirstName(firstName);
		remoteInvestigator.setLastName(lastName);
		remoteInvestigator.setEmailAddress(emailStr);
		remoteInvestigator.setExternalId(coppaPerson.getIdentifier().getExtension());
		remoteInvestigator.setAllowedToLogin(Boolean.FALSE);
		remoteInvestigator.setLoginId("loginid");
		if (nciIdentifier != null) {
			remoteInvestigator.setNciIdentifier(nciIdentifier);
		}
		return remoteInvestigator;

	}
	
	/**
	 * Find By Organization
	 */
	public List<Object> find(Object example) {
		log.info("Entering InvestigatorResolver.find()");
		Investigator remoteInvestigatorExample = (RemoteInvestigator)example;
		List<Object> remoteInvestigatorList = new ArrayList<Object>();
		RemoteInvestigator tempRemoteInvestigator = null;
		Organization org = remoteInvestigatorExample.getSiteInvestigators().get(0).getOrganization();
		
		
		if (remoteInvestigatorExample.getNciIdentifier() != null) {
			//get Identified Organization ... 
			IdentifiedPerson identifiedPersonToSearch = CoppaObjectFactory.getCoppaIdentfiedPersonSearchCriteriaOnCTEPId(remoteInvestigatorExample.getNciIdentifier());
			IdentifiedPerson identifiedPerson = getIdentifiedPerson(identifiedPersonToSearch);
			if (identifiedPerson == null) {
				log.info("Exiting InvestigatorResolver.find() - Identified person is NULL");
				return remoteInvestigatorList;
			}
			II ii = identifiedPerson.getPlayerIdentifier();
			String iiXml = CoppaObjectFactory.getCoppaIIXml(ii);
			try {
				String resultXml = broadcastPersonGetById(iiXml);
				tempRemoteInvestigator = loadInvestigatorForPersonResult(resultXml);
				remoteInvestigatorList.add(tempRemoteInvestigator);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			log.info("Exiting InvestigatorResolver.find()");
			return remoteInvestigatorList;
		}
		
		
		
		if (org != null){
			//get Organization by ctepId 
			IdentifiedOrganization identifiedOrganizationSearchCriteria = CoppaObjectFactory.getCoppaIdentfiedOrganizationSearchCriteriaOnCTEPId(org.getNciInstituteCode());
			String payload = CoppaObjectFactory.getCoppaIdentfiedOrganization(identifiedOrganizationSearchCriteria);
			Metadata mData = new Metadata(OperationNameEnum.search.getName(),  "externalId", ServiceTypeEnum.IDENTIFIED_ORGANIZATION.getName());
			String results =broadcastCOPPA(payload, mData);//
			List<String> resultObjects = XMLUtil.getObjectsFromCoppaResponse(results);
			for (String resultObj:resultObjects) {
				IdentifiedOrganization coppaIdOrganization = CoppaObjectFactory.getCoppaIdentfiedOrganization(resultObj);
				II organizationIdentifier = coppaIdOrganization.getPlayerIdentifier();
		//		String iiXml = CoppaObjectFactory.getCoppaIIXml(organizationIdentifier);
				//Get Organization based on player id of above.
			//	mData = new Metadata(OperationNameEnum.getById.getName(),  "externalId", ServiceTypeEnum.ORGANIZATION.getName());
				//String organizationResults = broadcastCOPPA(iiXml, mData);//
				//List<String> organizationResultObjects = XMLUtil.getObjectsFromCoppaResponse(organizationResults);
			//	for (String organizationResultObject:organizationResultObjects) {
					//gov.nih.nci.coppa.po.Organization coppaOrganizationResult = CoppaObjectFactory.getCoppaOrganization(organizationResultObject);
					//II ii = coppaOrganizationResult.getIdentifier();
					//above ii is the scoper if health care provider...
					HealthCareProvider healthCareProvider = CoppaObjectFactory.getCoppaHealthCareProviderWithScoperIdAsSearchCriteria(organizationIdentifier);
					String coppaHealthCareProviderXml = CoppaObjectFactory.getCoppaHealthCareProviderXml(healthCareProvider);
					String sRolesXml = "";
					try {
						sRolesXml = broadcastRoleSearch(coppaHealthCareProviderXml,ServiceTypeEnum.HEALTH_CARE_PROVIDER);
					} catch (CaaersSystemException e) {
						System.out.print(e);
					}

					List<String> sRoles = XMLUtil.getObjectsFromCoppaResponse(sRolesXml);	
					
					for(String sRole: sRoles){
						HealthCareProvider hcp = CoppaObjectFactory.getCoppaHealthCareProvider(sRole);
						II pid = hcp.getPlayerIdentifier();	
						String idXml = CoppaObjectFactory.getCoppaIIXml(pid);
						//above player id is the Id of a Person . 						
						// now get  the Person by Id ... 
						mData = new Metadata(OperationNameEnum.getById.getName(), "externalId", ServiceTypeEnum.PERSON.getName());
						String personResultXml = "";
						try {
							personResultXml = broadcastCOPPA(idXml, mData);//
							List<String> persons = XMLUtil.getObjectsFromCoppaResponse(personResultXml);	
							for(String personXml: persons){
								Person person = CoppaObjectFactory.getCoppaPerson(personXml);
								IdentifiedPerson identifiedPerson = getIdentifiedPerson(person.getIdentifier());
								String nciIdentifier = null;
								if (identifiedPerson != null ) {
									nciIdentifier = identifiedPerson.getAssignedId().getExtension();
								}
								tempRemoteInvestigator = populateRemoteInvestigator(person, nciIdentifier , coppaIdOrganization);
								remoteInvestigatorList.add(tempRemoteInvestigator);
							}
							
						} catch (CaaersSystemException e) {
							System.out.print(e);
						}

					}
				//}
			}
			log.info("Exiting InvestigatorResolver.find()");
			return remoteInvestigatorList;
		}
		
		String personXml = CoppaObjectFactory.getCoppaPersonXml(CoppaObjectFactory.getCoppaPerson(remoteInvestigatorExample.getFirstName(), remoteInvestigatorExample.getMiddleName(), remoteInvestigatorExample.getLastName()));
		String resultXml = "";
		try {
			resultXml = broadcastPersonSearch(personXml);
		} catch (Exception e) {
			System.out.print(e);
		}
		List<String> coppaPersons = XMLUtil.getObjectsFromCoppaResponse(resultXml);
		
		/*
		Person coppaPerson = null;
		if (coppaPersons != null){
			for(String coppaPersonXml: coppaPersons){
				coppaPerson = CoppaObjectFactory.getCoppaPerson(coppaPersonXml);
				IdentifiedPerson identifiedPerson = getIdentifiedPerson(coppaPerson.getIdentifier());
				String nciIdentifier = null;
				if (identifiedPerson != null ) {
					nciIdentifier = identifiedPerson.getAssignedId().getExtension();
				}
				
				List<gov.nih.nci.coppa.po.Organization>  coppaOrganizationList = getOrganizationsForPerson(coppaPerson,ServiceTypeEnum.HEALTH_CARE_PROVIDER);
				tempRemoteInvestigator = populateRemoteInvestigator(coppaPerson, nciIdentifier, coppaOrganizationList);
				remoteInvestigatorList.add(tempRemoteInvestigator);
			}
		}
		*/
		Person coppaPerson = null;
		List<Person>  resultPersons = new ArrayList<Person>();
		List<String> resultPersonIds = new ArrayList<String>();
		Map<String,IdentifiedPerson> IdentifiedPersonMap = new HashMap<String,IdentifiedPerson>();
		if (coppaPersons != null){
			for(String coppaPersonXml: coppaPersons){
				coppaPerson = CoppaObjectFactory.getCoppaPerson(coppaPersonXml);
				resultPersons.add(coppaPerson);
				resultPersonIds.add(CoppaObjectFactory.getCoppaIIXml(coppaPerson.getIdentifier()));
			}
			//			get identified persons based on playerIds...
			Metadata mData = new Metadata("getByPlayerIds", "externalId", ServiceTypeEnum.IDENTIFIED_PERSON.getName());
			String IdentifiedPersonsResultXml = broadcastCOPPA(resultPersonIds, mData);
			//System.out.println(IdentifiedPersonsResultXml);
			List<String> identifiedPersons = XMLUtil.getObjectsFromCoppaResponse(IdentifiedPersonsResultXml);
			IdentifiedPerson identifiedPerson = null;
			for(String identifiedPersonXml: identifiedPersons){
				identifiedPerson = CoppaObjectFactory.getCoppaIdentfiedPerson(identifiedPersonXml);
				if (identifiedPerson.getAssignedId().getRoot().equals("Cancer Therapy Evaluation Program Person Identifier")) {
					IdentifiedPersonMap.put(identifiedPerson.getPlayerIdentifier().getExtension(), identifiedPerson);
				}
			}
			
			//get roles based on playerIds...
			mData = new Metadata("getByPlayerIds", "externalId", ServiceTypeEnum.HEALTH_CARE_PROVIDER.getName());
			String sRolesXml = broadcastCOPPA(resultPersonIds,mData);
			List<String> coppaRoles = XMLUtil.getObjectsFromCoppaResponse(sRolesXml);	

			HealthCareProvider role = null;
			Map<String,List<gov.nih.nci.coppa.po.Organization>> roleToOrgMap = new HashMap<String,List<gov.nih.nci.coppa.po.Organization>>();
			List<String>  coppaOrgsIdsForAllRoles = new ArrayList<String>();
			for(String coppaRoleXml: coppaRoles){
				role = CoppaObjectFactory.getCoppaHealthCareProvider(coppaRoleXml);
				//roleMap.put(role.getPlayerIdentifier().getExtension(), role);
				
				//get orgnizations for each role... 
				//******bottleneck , making call for each role , not method get by scoperIds on ORGS or IDENT-ORGS
			    //role scoper id is org id .. so based on scoper id get org 

				String orgIiXml = CoppaObjectFactory.getCoppaIIXml(role.getScoperIdentifier());
				String orgResultXml = "";
				try {
					orgResultXml = broadcastOrganizationGetById(orgIiXml);
				} catch (Exception e) {
					System.out.print(e);
				}
				List<String> orgForEachRoleResults = XMLUtil.getObjectsFromCoppaResponse(orgResultXml);

				List<gov.nih.nci.coppa.po.Organization>  coppaOrganizationForEachRoleList = new ArrayList<gov.nih.nci.coppa.po.Organization>();
				if (orgForEachRoleResults.size() > 0) {
					for(String orgXml:orgForEachRoleResults) {
						gov.nih.nci.coppa.po.Organization organization =CoppaObjectFactory.getCoppaOrganization(orgXml) ;
						coppaOrganizationForEachRoleList.add(organization);
						coppaOrgsIdsForAllRoles.add(CoppaObjectFactory.getCoppaIIXml(organization.getIdentifier()));
					}
					
				}
				roleToOrgMap.put(role.getPlayerIdentifier().getExtension(), coppaOrganizationForEachRoleList);				
			}
			//get identified organizations for all collected orgs above ..
			mData = new Metadata("getByPlayerIds", "externalId", ServiceTypeEnum.IDENTIFIED_ORGANIZATION.getName());
			String identifiedOrgsResultXml = broadcastCOPPA(coppaOrgsIdsForAllRoles,mData);
			List<String> identifiedOrgsResult = XMLUtil.getObjectsFromCoppaResponse(identifiedOrgsResultXml);
			gov.nih.nci.coppa.po.IdentifiedOrganization identifiedOrganization = null;
			Map<String,gov.nih.nci.coppa.po.IdentifiedOrganization> orgToIdentifiedOrgMap = new HashMap<String,gov.nih.nci.coppa.po.IdentifiedOrganization>();
			for (String identifiedOrgsResult1:identifiedOrgsResult) {
				identifiedOrganization = CoppaObjectFactory.getCoppaIdentfiedOrganization(identifiedOrgsResult1);
				orgToIdentifiedOrgMap.put(identifiedOrganization.getPlayerIdentifier().getExtension(), identifiedOrganization);
			}
			
			// we have three maps  ...
			// IdentifiedPersonMap : identified-person-player-Id = person-id , obtain IP for each person from this map based on person Id
			// roleToOrgMap : role-player-id = person-id , obtain list of organization for each person based on person id , these organizatios are derived using role .
			// from above map orgs are no use , we need identified orgs , go for each org get identified org from next map
			// orgToIdentifiedOrgMap : identified-org-playerId = org Id , get Identified org based on org id. 
			
			
			// NOW USING ABOVE MAPS , POPULATE DATA...
			// START FROM roleToOrgMap , beacuse we only care for person who has role of HEALTH_CARE_PROVIDER.
			for(Person person: resultPersons){
				List<gov.nih.nci.coppa.po.Organization> orgs = roleToOrgMap.get(person.getIdentifier().getExtension());
				//we ignore if there are no orgs .., 
				if (orgs != null && orgs.size() > 0) {
					//get Identifed Orgs based on org Id
					List<gov.nih.nci.coppa.po.IdentifiedOrganization> orgsForPerson = new ArrayList<gov.nih.nci.coppa.po.IdentifiedOrganization>();
					for (gov.nih.nci.coppa.po.Organization organization:orgs) {
						gov.nih.nci.coppa.po.IdentifiedOrganization iorg = orgToIdentifiedOrgMap.get(organization.getIdentifier().getExtension());
						if (iorg != null) {
							orgsForPerson.add(iorg);
						}
					}
					//get Identified person .
					IdentifiedPerson ip = IdentifiedPersonMap.get(person.getIdentifier().getExtension());
					if (ip != null  & orgsForPerson.size()>0) {
						tempRemoteInvestigator = populateRemoteInvestigatorWithIdentfiedOrgs(person, identifiedPerson.getAssignedId().getExtension(), orgsForPerson);
						remoteInvestigatorList.add(tempRemoteInvestigator);
					}
					
				}
			}
		}
		log.info("Exiting InvestigatorResolver.find()");
		return remoteInvestigatorList;
	}
	

	
	public RemoteInvestigator populateRemoteInvestigator(Person coppaPerson, String nciIdentifier, IdentifiedOrganization identifiedOrganization){		
		RemoteInvestigator remoteInvestigator = setInvestigatorDetails(coppaPerson,nciIdentifier);		
		Organization site = new RemoteOrganization();
		site.setNciInstituteCode(identifiedOrganization.getAssignedId().getExtension());		
		SiteInvestigator si = new SiteInvestigator();
		si.setOrganization(site);
		si.setInvestigator(remoteInvestigator);
		remoteInvestigator.addSiteInvestigator(si)	;

		return remoteInvestigator;
	}

	public Object getRemoteEntityByUniqueId(String externalId) {
		log.info("Entering InvestigatorResolver.getRemoteEntityByUniqueId(externalId : " + externalId + ") ");
		II ii = CoppaObjectFactory.getIISearchCriteriaForPerson(externalId);		

		String iiXml = CoppaObjectFactory.getCoppaIIXml(ii);
		String resultXml = "";
		try {
			resultXml = broadcastPersonGetById(iiXml);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		log.info("Exiting InvestigatorResolver.getRemoteEntityByUniqueId()");
		return loadInvestigatorForPersonResult(resultXml);
	}
	
	public RemoteInvestigator loadInvestigatorForPersonResult(String personResultXml) {
		List<String> results = XMLUtil.getObjectsFromCoppaResponse(personResultXml);
		List<gov.nih.nci.coppa.po.Organization> coppaOrganizationList = null;
		RemoteInvestigator remoteInvestigator = null;
		Person coppaPerson = null;
		String nciIdentifier = null;
		if (results.size() > 0) {
			coppaPerson = CoppaObjectFactory.getCoppaPerson(results.get(0));
			coppaOrganizationList = getOrganizationsForPerson(coppaPerson,ServiceTypeEnum.HEALTH_CARE_PROVIDER);
			IdentifiedPerson identifiedPerson = getIdentifiedPerson(coppaPerson.getIdentifier());
			
			if (identifiedPerson != null ) {
				nciIdentifier = identifiedPerson.getAssignedId().getExtension();
			}
			remoteInvestigator =  this.populateRemoteInvestigator(coppaPerson, nciIdentifier, coppaOrganizationList);
		}

		return remoteInvestigator;		
	}
	
	public RemoteInvestigator populateRemoteInvestigatorWithIdentfiedOrgs(Person coppaPerson, String nciIdentifier, List<gov.nih.nci.coppa.po.IdentifiedOrganization> identifiedCoppaOrganizationList){		

		RemoteInvestigator remoteInvestigator = setInvestigatorDetails(coppaPerson,nciIdentifier);	
		/*
		List<gov.nih.nci.coppa.po.IdentifiedOrganization> identifiedCoppaOrganizationList = new ArrayList<IdentifiedOrganization>();

		if(coppaOrganizationList != null && coppaOrganizationList.size()>0){
			for(gov.nih.nci.coppa.po.Organization coppaOrganization: coppaOrganizationList){
				IdentifiedOrganization identifiedOrganization = getIdentifiedOrganization(coppaOrganization);
				identifiedCoppaOrganizationList.add(identifiedOrganization);
			}
		}
		//Build HealthcareSite and HealthcareSiteInvestigator
*/
		Organization site = null;

		if (identifiedCoppaOrganizationList != null && identifiedCoppaOrganizationList.size()>0){

			for(gov.nih.nci.coppa.po.IdentifiedOrganization identifiedOrganization: identifiedCoppaOrganizationList){
				site = new RemoteOrganization();
				site.setNciInstituteCode(identifiedOrganization.getAssignedId().getExtension());	
				SiteInvestigator si = new SiteInvestigator();
				si.setOrganization(site);
				si.setInvestigator(remoteInvestigator);
				remoteInvestigator.addSiteInvestigator(si)	;
				

			}

		}

		return remoteInvestigator;
	}
	public RemoteInvestigator populateRemoteInvestigator(Person coppaPerson, String nciIdentifier, List<gov.nih.nci.coppa.po.Organization> coppaOrganizationList){		

		RemoteInvestigator remoteInvestigator = setInvestigatorDetails(coppaPerson,nciIdentifier);	
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
				SiteInvestigator si = new SiteInvestigator();
				si.setOrganization(site);
				si.setInvestigator(remoteInvestigator);
				remoteInvestigator.addSiteInvestigator(si)	;
				

			}

		}

		return remoteInvestigator;
	}

	public Object saveOrUpdate(Object arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	private List<gov.nih.nci.coppa.po.Organization> getOrganizationsForPerson(Person coppaPerson) {
		List<gov.nih.nci.coppa.po.Organization>  coppaOrganizationList = new ArrayList<gov.nih.nci.coppa.po.Organization>();

		HealthCareProvider healthCareProvider = CoppaObjectFactory.getCoppaHealthCareProvider(coppaPerson.getIdentifier());
		String coppaHealthCareProviderXml = CoppaObjectFactory.getCoppaHealthCareProviderXml(healthCareProvider);

		String sRolesXml = "";
		try {
			sRolesXml = broadcastRoleSearch(coppaHealthCareProviderXml,ServiceTypeEnum.HEALTH_CARE_PROVIDER);
		} catch (CaaersSystemException e) {
			System.out.print(e);
		}

		List<String> sRoles = XMLUtil.getObjectsFromCoppaResponse(sRolesXml);		

		for(String sRole: sRoles){

			String orgResultXml = "";

			HealthCareProvider hcp = CoppaObjectFactory.getCoppaHealthCareProvider(sRole);
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
	*/




}