package gov.nih.nci.cabig.caaers.resolver;

import edu.duke.cabig.c3pr.esb.Metadata;
import edu.duke.cabig.c3pr.esb.OperationNameEnum;
import edu.duke.cabig.c3pr.esb.ServiceTypeEnum;
import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.esb.client.MessageBroadcastService;
import gov.nih.nci.cabig.caaers.utils.XMLUtil;
import gov.nih.nci.coppa.po.Bl;
import gov.nih.nci.coppa.po.CorrelationNode;
import gov.nih.nci.coppa.po.IdentifiedOrganization;
import gov.nih.nci.coppa.po.IdentifiedPerson;
import gov.nih.nci.coppa.po.Organization;
import gov.nih.nci.coppa.po.Person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.iso._21090.DSETII;
import org.iso._21090.II;

import com.semanticbits.coppasimulator.util.CoppaObjectFactory;

public abstract class BaseResolver {
	
	private MessageBroadcastService messageBroadcastService;

	private static Log log = LogFactory.getLog(BaseResolver.class);
	private static final String CTEP_PERSON = "Cancer Therapy Evaluation Program Person Identifier";
	public static final String PERSON_ROOT = "2.16.840.1.113883.3.26.4.1";
	private static final String CTEP_ID = "CTEP ID";
	//private static final String NCI_ID = "NCI Research Organization identifier";
	//private static final String NCI_ROOT = "2.16.840.1.113883.3.26.4.4.5";

	/**
	 * 
	 * @param organizationNciIdentifier
	 * @param role
	 * @return
	 */
    protected List<Object> searchRoleBasedOnOrganization(String organizationNciIdentifier,gov.nih.nci.coppa.po.Correlation role){
        //Get IdentifiedOrganization by ctepId
        IdentifiedOrganization identifiedOrganizationSearchCriteria = CoppaObjectFactory.getCoppaIdentfiedOrganizationSearchCriteriaOnCTEPId(organizationNciIdentifier);
        String payload = CoppaObjectFactory.getCoppaIdentfiedOrganization(identifiedOrganizationSearchCriteria);
        String results = null;
        try {
            results = broadcastIdentifiedOrganizationSearch(payload);
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        //Assuming here that the ctepCode search yields exactly one organization
        List<String> resultObjects = XMLUtil.getObjectsFromCoppaResponse(results);
        if(resultObjects.size() == 0){
            return new ArrayList<Object>();
        }
        if(resultObjects.size() > 1){
            log.error("searchRoleBasedOnOrganization: The ctep code matches more than one organization. The current implementation uses only the first match as it" +
                    "assumes the ctep code search to always yield one exact match.");
        }
        
        IdentifiedOrganization coppaIdOrganization = CoppaObjectFactory.getCoppaIdentfiedOrganization(resultObjects.get(0));
        II organizationIdentifier = coppaIdOrganization.getPlayerIdentifier();
        gov.nih.nci.coppa.po.Organization organization = CoppaObjectFactory.getCoppaOrganizationFromII(organizationIdentifier);
        String correlationNodeXmlPayload = CoppaObjectFactory.getCorrelationNodePayload(role, null, organization);
        
        List<CorrelationNode> correlationNodeList = getCorrelationNodesFromPayloadXml(correlationNodeXmlPayload);
        List<Person> listOfAllPersons = new ArrayList<Person>();
        Person person = null;
        for(CorrelationNode cNode: correlationNodeList){
            person = getCoppaPersonFromCorrelationNode(cNode);
            if(person != null){
                listOfAllPersons.add(person);
            }
        }
        //Use the list of all persons to build a map of identified persons using the getByPlayerIds operation.
        Map<String, List<IdentifiedPerson>> personIdToIdentifiedPersonMap = 
                            getIdentifiedPersonsForPersonList(listOfAllPersons);
        
        List<Object> remoteRoleList = new ArrayList<Object>();
        Object populatedRole = null;
        Person coppaPerson = null;
        String assignedIdentifier;
        for(CorrelationNode cNode: correlationNodeList){
            assignedIdentifier = null;
            coppaPerson = getCoppaPersonFromCorrelationNode(cNode);
            assignedIdentifier = getAssignedIdentifierFromCorrelationNode(coppaPerson, personIdToIdentifiedPersonMap);
            if(assignedIdentifier == null){
                assignedIdentifier = coppaPerson.getIdentifier().getExtension();
            }
            
            populatedRole = populateRole(coppaPerson, assignedIdentifier, coppaIdOrganization);    
            if(populatedRole != null){
                remoteRoleList.add(populatedRole);
            }
        }
        return remoteRoleList;
    }
    
    /**
     * 
     * @param nciIdentifier
     * @param role
     * @return
     */
    protected List<Object> searchRoleBasedOnNciId(String nciIdentifier,gov.nih.nci.coppa.po.Correlation role) {
        List<Object> remoteRoleList = new ArrayList<Object>();
        List<IdentifiedPerson> identifiedPersonsList = new ArrayList<IdentifiedPerson>();
        if (nciIdentifier != null) {
             //get Identified Organization using the Identifier provided
             IdentifiedPerson identifiedPersonToSearch = CoppaObjectFactory.getCoppaIdentfiedPersonSearchCriteriaOnCTEPId(nciIdentifier);
             IdentifiedPerson exactMatchIdentifiedPersonResult = getIdentifiedPerson(identifiedPersonToSearch,nciIdentifier);
             if (exactMatchIdentifiedPersonResult == null) {
            	 return remoteRoleList;
             } else {
            	 identifiedPersonsList.add(exactMatchIdentifiedPersonResult);
             }

        }
        String personIiExtension = null;
        Person person = null;
        String correlationNodeXmlPayload = null;
        List<CorrelationNode> correlationNodeList = null;
        //Get the Role corresponding to every Identified Person fetched. Because the identifiedPerson search by CTEP code is a 
        //like match not exact match and can return more than one result.
        for(int i=0; i<identifiedPersonsList.size(); i++ ){
            personIiExtension = identifiedPersonsList.get(i).getPlayerIdentifier().getExtension();
            if(personIiExtension == null){
                return remoteRoleList;
            }
            person = CoppaObjectFactory.getCoppaPersonForExtension(personIiExtension);
            correlationNodeXmlPayload = CoppaObjectFactory.getCorrelationNodePayload(role, person, null);
            
            correlationNodeList = getCorrelationNodesFromPayloadXml(correlationNodeXmlPayload);
            remoteRoleList.addAll(getRemoteRolesFromCorrelationNodesList(correlationNodeList));
        }
        return remoteRoleList;
    }
    
	/**
	 * 
	 * @param firstName
	 * @param middleName
	 * @param lastName
	 * @param role (instance of HealthCareProvide or  ClinicalResearchStaff)
	 * @return
	 */
    protected List<Object> searchRoleBasedOnName(String firstName,String middleName, String lastName, gov.nih.nci.coppa.po.Correlation role) {
        Person person = CoppaObjectFactory.getCoppaPerson(firstName, middleName, lastName);
        String correlationNodeXmlPayload = CoppaObjectFactory.getCorrelationNodePayload(role, person, null);

        List<CorrelationNode> correlationNodeList = getCorrelationNodesFromPayloadXml(correlationNodeXmlPayload);
        List<Object> remoteRoleList = getRemoteRolesFromCorrelationNodesList(correlationNodeList);
        return remoteRoleList;
    }    
    /**
     * Gets the remote investigators/rs from correlation nodes list.
     * 
     * @param correlationNodeList the correlation node list
     * @return the remote investigators/rs from correlation nodes list
     */
    protected List<Object> getRemoteRolesFromCorrelationNodesList(
                                    List<CorrelationNode> correlationNodeList) {
        List<Object> remoteRoleList = new ArrayList<Object>();
        HashMap<String, List<gov.nih.nci.coppa.po.Organization>> personIdToCoppaOrganizationsHashMap = new HashMap<String, List<gov.nih.nci.coppa.po.Organization>>();
        List<gov.nih.nci.coppa.po.Organization> listOfAllOrganizations = new ArrayList<gov.nih.nci.coppa.po.Organization>();
        List<Person> listOfAllPersons = new ArrayList<Person>();
        Person tempPerson = null;
        //gov.nih.nci.coppa.po.Organization tempOrganization  = null;
        for(CorrelationNode cNode: correlationNodeList){
            tempPerson = getCoppaPersonFromCorrelationNode(cNode);
            List<gov.nih.nci.coppa.po.Organization> orgsFromCorrelation = getCoppaOrganizationFromCorrelationNode(cNode);
            
            //building a list of all organizations
            for (gov.nih.nci.coppa.po.Organization tempOrganization:orgsFromCorrelation) {
            	listOfAllOrganizations.add(tempOrganization);
            }
            

            List<gov.nih.nci.coppa.po.Organization> organizationList = null;
            if(personIdToCoppaOrganizationsHashMap.containsKey(tempPerson.getIdentifier().getExtension())){
                organizationList = personIdToCoppaOrganizationsHashMap.get(tempPerson.getIdentifier().getExtension());
                for (gov.nih.nci.coppa.po.Organization tempOrganization:orgsFromCorrelation) {
                	organizationList.add(tempOrganization);
                }
            } else {
                organizationList = new ArrayList<gov.nih.nci.coppa.po.Organization>();
                for (gov.nih.nci.coppa.po.Organization tempOrganization:orgsFromCorrelation) {
                	organizationList.add(tempOrganization);
                }
                personIdToCoppaOrganizationsHashMap.put(tempPerson.getIdentifier().getExtension(), organizationList);
                //building a list of all persons. This is in the else loop because different correlationNodes can have the same person.
                //So we only add when the personIdToCoppaOrganizationsHashMap does not contain the personId as the key.
                listOfAllPersons.add(tempPerson);
            }
        }
        
        Map<String, IdentifiedOrganization> organizationIdToIdentifiedOrganizationsMap = 
                                    getIdentifiedOrganizationsForOrganizationsList(listOfAllOrganizations);
        Map<String, List<IdentifiedPerson>> personIdToIdentifiedPersonMap = 
                                    getIdentifiedPersonsForPersonList(listOfAllPersons);
        
        Object populatedRole  = null;
        for(CorrelationNode cNode: correlationNodeList){
            Person coppaPerson = getCoppaPersonFromCorrelationNode(cNode);
            String assignedIdentifier = getAssignedIdentifierFromCorrelationNode(coppaPerson, personIdToIdentifiedPersonMap);
            if(assignedIdentifier == null){
                assignedIdentifier = coppaPerson.getIdentifier().getExtension();
            }
            
            populatedRole = populateRole(coppaPerson, assignedIdentifier, 
                                        personIdToCoppaOrganizationsHashMap.get(coppaPerson.getIdentifier().getExtension()), organizationIdToIdentifiedOrganizationsMap);    
            if(populatedRole != null){
            	remoteRoleList.add(populatedRole);
            }
        }
        return remoteRoleList;
    }
    
    /**
     * 
     * @param coppaPerson
     * @param staffAssignedIdentifier
     * @param coppaOrganizationList
     * @param organizationIdToIdentifiedOrganizationsMap
     * @return
     */
    public abstract Object populateRole(Person coppaPerson, String staffAssignedIdentifier, List<gov.nih.nci.coppa.po.Organization> coppaOrganizationList,
            Map<String, IdentifiedOrganization>    organizationIdToIdentifiedOrganizationsMap);
    
    /**
     * 
     * @param coppaPerson
     * @param staffAssignedIdentifier
     * @param identifiedOrganization
     * @return
     */
    public abstract Object populateRole(Person coppaPerson, String staffAssignedIdentifier, IdentifiedOrganization identifiedOrganization);
    
    /**
     * 
     * @param correlationNodeXml
     * @param player
     * @param scoper
     * @return
     */
	protected String broadcastSearchCorrelationsWithEntities(String correlationNodeXml, boolean player, boolean scoper) {
        
        Metadata mData = new Metadata("searchCorrelationsWithEntities",  "externalId", "PO_BUSINESS");
        
        List<String> cctsDomainObjectXMLList = new ArrayList<String>();
        cctsDomainObjectXMLList.add(correlationNodeXml);
        
        Bl playerBoolean = new Bl();
        playerBoolean.setValue(player);
        Bl scoperBoolean = new Bl();
        scoperBoolean.setValue(scoper);
        
        String playerBooleanXml = CoppaObjectFactory.getBooleanPayload(playerBoolean);
        String scoperBooleanXml = CoppaObjectFactory.getBooleanPayload(scoperBoolean);
        
        cctsDomainObjectXMLList.add(playerBooleanXml);
        cctsDomainObjectXMLList.add(scoperBooleanXml);
        
        return broadcastCOPPA(cctsDomainObjectXMLList, mData);
    }

	/**
	 * Gets the correlation nodes from payload xml.
	 * 
	 * @param correlationNodeXmlPayload the correlation node xml payload
	 * @return the correlation nodes from payload xml
	 */
	public List<CorrelationNode> getCorrelationNodesFromPayloadXml(String correlationNodeXmlPayload) {
		String correlationNodeArrayXml = "";
		try{
			correlationNodeArrayXml = broadcastSearchCorrelationsWithEntities(correlationNodeXmlPayload, true, true);
			//System.out.println(correlationNodeArrayXml);
		} catch(Exception e){
			log.error(e.getStackTrace());
		}
		List<String> correlationNodes = XMLUtil.getObjectsFromCoppaResponse(correlationNodeArrayXml);
		List<CorrelationNode> correlationNodeList = new ArrayList<CorrelationNode>();
		//creating a list of correlationNodes
		for(String correlationNode: correlationNodes){
			correlationNodeList.add(CoppaObjectFactory.getCorrelationNodeObjectFromXml(correlationNode));
		}
		return correlationNodeList;
	}

	/**
	 * Gets the coppa person from correlation node.
	 * 
	 * @param cNode the correlation node
	 * @return the coppa person from correlation node
	 */
	public Person getCoppaPersonFromCorrelationNode(CorrelationNode cNode) {
		Person person = null;
		for(int i = 0; i < cNode.getPlayer().getContent().size(); i++){
			Object object = cNode.getPlayer().getContent().get(i);
			if(object instanceof Person){
				person = (Person) object;
				break;
			}
		}
		return person;
	}

	
	/**
	 * Gets the coppa organization associated to investigator from correlation node.
	 * 
	 * @param cNode the correlation node
	 * @return the coppa organization associated to investigator from correlation node
	 */
	public List<Organization> getCoppaOrganizationFromCorrelationNode(CorrelationNode cNode) {
		Organization coppaOrganization = null;
		List<Organization> orgList = new ArrayList<Organization>();
		for(int i = 0; i < cNode.getScoper().getContent().size(); i++){
			Object object = cNode.getScoper().getContent().get(i);
			if(object instanceof Organization){
				coppaOrganization = (Organization)object;
				orgList.add(coppaOrganization);
			}
		}
		return orgList;
	}
	
	/**
	 * Gets the assigned identifier from correlation node.
	 * 
	 * @param coppaPerson the coppa person
	 * @param personIdToIdentifiedPersonMap the person id to identified person map
	 * @return the assigned identifier from correlation node
	 */
	public String getAssignedIdentifierFromCorrelationNode(Person coppaPerson, Map<String, List<IdentifiedPerson>> personIdToIdentifiedPersonMap) {
		String assignedIdentifier = null;
		if(personIdToIdentifiedPersonMap.containsKey(coppaPerson.getIdentifier().getExtension())){
			List<IdentifiedPerson> identifiedPersonList = personIdToIdentifiedPersonMap.get(coppaPerson.getIdentifier().getExtension());
    		for(IdentifiedPerson identifiedPerson: identifiedPersonList){
    			if(identifiedPerson != null && identifiedPerson.getAssignedId().getRoot().equalsIgnoreCase(CTEP_PERSON)){
    				assignedIdentifier = identifiedPerson.getAssignedId().getExtension();
        		}
    		}
		}
		return assignedIdentifier;
	}

	/**
	 * Gets the identifier organizations for organizations list.
	 * 
	 * @param coppaOrganizationsList the coppa organizations list
	 * 
	 * @return the identifier organizations for organizations list
	 */
	public Map<String, IdentifiedOrganization> getIdentifiedOrganizationsForOrganizationsList(List<gov.nih.nci.coppa.po.Organization> coppaOrganizationsList) {
		Map<String, IdentifiedOrganization> identifiedOrganizationsMap = new HashMap<String, IdentifiedOrganization>();
		
		try {
			//Build a list of orgId Xml
			List<String> organizationIdXmlList = new ArrayList<String>();
			DSETII dsetii = null;
			for(gov.nih.nci.coppa.po.Organization coppaOrganization : coppaOrganizationsList){
				dsetii = CoppaObjectFactory.getDSETIISearchCriteria(coppaOrganization.getIdentifier().getExtension());
				organizationIdXmlList.add(CoppaObjectFactory.getCoppaIIXml(dsetii));
			}
			
			//Coppa-call for Identifier Organizations getByIds
			String identifiedOrganizationsXml = broadcastIdentifiedOrganizationGetByPlayerIds(organizationIdXmlList);
			List<String> identifiedOrganizations = XMLUtil.getObjectsFromCoppaResponse(identifiedOrganizationsXml);
			
			//Build a map with orgId as key and identifiedOrganization as value. Only get IdOrgs that have CTEP ID
			if(identifiedOrganizations != null && identifiedOrganizations.size() > 0){
				IdentifiedOrganization identifiedOrganization = null;
				for(String identifiedOrganizationString : identifiedOrganizations){
					identifiedOrganization = CoppaObjectFactory.getCoppaIdentfiedOrganization(identifiedOrganizationString);
					if(identifiedOrganization != null && identifiedOrganization.getAssignedId().getIdentifierName().equals(CTEP_ID)){
						identifiedOrganizationsMap.put(identifiedOrganization.getPlayerIdentifier().getExtension(), identifiedOrganization);
					}
				}
			}
    	} catch(Exception e){
    		log.error(e.getMessage());
    	}
    	return identifiedOrganizationsMap;
	}

	/**
	 * Gets the nci ids for person list.
	 * Returns a map with personID as key and associated NciId as value.
	 * 
	 * @param coppaPersonsList the coppa persons list
	 * 
	 * @return the nci ids for person list
	 */
	public Map<String, List<IdentifiedPerson>> getIdentifiedPersonsForPersonList(List<Person> coppaPersonsList){
    	Map<String, List<IdentifiedPerson>> identifiedPersonMap = new HashMap<String, List<IdentifiedPerson>>();
		
    	try {
			//Build a list of personId Xml
			List<String> personIdXmlList = new ArrayList<String>();
			for(Person coppaPerson:coppaPersonsList){
				personIdXmlList.add(CoppaObjectFactory.getCoppaPersonIdXML(coppaPerson.getIdentifier().getExtension()));
			}
			//Coppa-call for Identifier Persons getByIds
			String identifiedPersonsXml = broadcastIdentifiedPersonGetByPlayerIds(personIdXmlList);
			List<String> identifiedPersons = XMLUtil.getObjectsFromCoppaResponse(identifiedPersonsXml);
			
			//Build a map with personId as key and sRole as value
			if(identifiedPersons != null && identifiedPersons.size() > 0){
				IdentifiedPerson identifiedPerson = null;
				for(String identifiedPersonString : identifiedPersons){
					identifiedPerson = CoppaObjectFactory.getCoppaIdentfiedPerson(identifiedPersonString);
					if(identifiedPerson != null){
						//identifiedPersonMap.put(identifiedPerson.getPlayerIdentifier().getExtension(), identifiedPerson);
						List<IdentifiedPerson> ipList = null;
						if(identifiedPersonMap.containsKey(identifiedPerson.getPlayerIdentifier().getExtension())){
							ipList  = identifiedPersonMap.get(identifiedPerson.getPlayerIdentifier().getExtension());
							ipList.add(identifiedPerson);
						} else {
							ipList = new ArrayList<IdentifiedPerson>();
							ipList.add(identifiedPerson);
							identifiedPersonMap.put(identifiedPerson.getPlayerIdentifier().getExtension(), ipList);
						}
					}
				}
			}
    	} catch(Exception e){
    		log.error(e.getMessage());
    	}
    	return identifiedPersonMap;
    }

	/**
	 * 
	 * @param personIdXmlList
	 * @return
	 * @throws Exception
	 */
	private String broadcastIdentifiedPersonGetByPlayerIds(List<String> personIdXmlList) throws Exception{
		//build metadata with operation name and the external Id and pass it to the broadcast method.
		//log.debug("Broadcasting : Operation --> "+ OperationNameEnum.getByPlayerIds.getName() + "   Service -->" +ServiceTypeEnum.IDENTIFIED_PERSON.getName());
        Metadata mData = new Metadata(OperationNameEnum.getByPlayerIds.getName(),  "externalId", ServiceTypeEnum.IDENTIFIED_PERSON.getName());
		return broadcastCOPPA(personIdXmlList, mData);
	}

	/**
	 * 
	 * @param organizationIdXmlList
	 * @return
	 * @throws Exception
	 */
	private String broadcastIdentifiedOrganizationGetByPlayerIds(List<String> organizationIdXmlList) throws Exception {
		//build metadata with operation name and the external Id and pass it to the broadcast method.
		//log.debug("Broadcasting : Operation --> "+ OperationNameEnum.getByPlayerIds.getName() + "   Service -->" +ServiceTypeEnum.IDENTIFIED_ORGANIZATION.getName());
        Metadata mData = new Metadata(OperationNameEnum.getByPlayerIds.getName(), "externalId", ServiceTypeEnum.IDENTIFIED_ORGANIZATION.getName());
		return broadcastCOPPA(organizationIdXmlList, mData);
	}


	/**
	 * Gets the identified person.
	 * Returns a list of IdentifiedPersons.
	 * 
	 * @param ip the ip
	 * 
	 * @return the identified person
	 */
	public IdentifiedPerson getIdentifiedPerson(IdentifiedPerson ip,String nciIdentifier) {
		//List<IdentifiedPerson> identifiedPersonsList = new ArrayList<IdentifiedPerson>();
        String ipPayload = CoppaObjectFactory.getCoppaIdentfiedPersonXml(ip);              
        String result = "";
		try {
			result = broadcastIdentifiedPersonSearch(ipPayload);
		} catch (Exception e) {
			log.error(e.getMessage());
		}

        List<String> identifiedPersons = XMLUtil.getObjectsFromCoppaResponse(result);    
        IdentifiedPerson identifiedPerson = null;
        for(String identifiedPersonXml: identifiedPersons){
                identifiedPerson = CoppaObjectFactory.getCoppaIdentfiedPerson(identifiedPersonXml);
                if (identifiedPerson.getAssignedId().getExtension().equals(nciIdentifier)) {
                	return identifiedPerson;
                }
                //identifiedPersonsList.add(identifiedPerson);
        }
        return null;
	}
	
	/**
	 * 
	 * @param ipXml
	 * @return
	 * @throws Exception
	 */
	private String broadcastIdentifiedPersonSearch(String ipXml) throws Exception{
		//build metadata with operation name and the external Id and pass it to the broadcast method.
		//log.debug("Broadcasting : Operation --> "+ OperationNameEnum.search.getName() + "   Service -->" +ServiceTypeEnum.IDENTIFIED_PERSON.getName());
		Metadata mData = new Metadata(OperationNameEnum.search.getName(),  "externalId", ServiceTypeEnum.IDENTIFIED_PERSON.getName());
		return broadcastCOPPA(ipXml, mData);
	}

	/**
	 * Gets the coppa person from correlation node.
	 * 
	 * @param cNode the correlation node
	 * @return the coppa person from correlation node
	 */
	public Person getCoppaPersonFromPlayerInCorrelationNode(CorrelationNode cNode) {
		Person person = null;
		for(int i = 0; i < cNode.getPlayer().getContent().size(); i++){
			Object object = cNode.getPlayer().getContent().get(i);
			if(object instanceof Person){
				person = (Person) object;
				break;
			}
		}
		return person;
	}
	
	/**
	 * 
	 */
	public String broadcastIdentifiedOrganizationSearch(String healthcareSiteXml) throws CaaersSystemException {
        Metadata mData = new Metadata(OperationNameEnum.search.getName(), "externalId", ServiceTypeEnum.IDENTIFIED_ORGANIZATION.getName());
        return broadcastCOPPA(healthcareSiteXml, mData);
	}

	/**
	 * 
	 * @param message
	 * @param metaData
	 * @return
	 * @throws gov.nih.nci.cabig.caaers.esb.client.BroadcastException
	 */
	public String broadcastCOPPA(String message,Metadata metaData) throws gov.nih.nci.cabig.caaers.esb.client.BroadcastException {    	
		return broadcastCOPPA(Arrays.asList(message), metaData);
    }
	
	/**
	 * 
	 * @param messages
	 * @param metaData
	 * @return
	 */
	public String broadcastCOPPA(List<String> messages,Metadata metaData)  {
		log.info("COPPA CALL :: SERVICETYPE-->" + metaData.getServiceType() + " ::  OPERATION-->" + metaData.getOperationName());
		//System.out.println("COPPA CALL :: SERVICETYPE-->" + metaData.getServiceType() + " ::  OPERATION-->" + metaData.getOperationName());
        String result = null;
        try {
        	result = messageBroadcastService.broadcastCOPPA(messages, metaData);
        } catch (Exception e) {
        	log.error("ERROR with COPPA BroadCast " + e.getMessage());
        	e.printStackTrace();
        }
		return result;
    }
	
	public MessageBroadcastService getMessageBroadcastService() {
		return messageBroadcastService;
	}
	
	public void setMessageBroadcastService(MessageBroadcastService messageBroadcastService) {
		this.messageBroadcastService = messageBroadcastService;
	}
}
