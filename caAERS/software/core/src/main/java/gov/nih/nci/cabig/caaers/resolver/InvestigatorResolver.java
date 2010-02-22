package gov.nih.nci.cabig.caaers.resolver;

import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.RemoteInvestigator;
import gov.nih.nci.cabig.caaers.domain.RemoteOrganization;
import gov.nih.nci.cabig.caaers.domain.SiteInvestigator;
import gov.nih.nci.cabig.caaers.utils.DateUtils;
import gov.nih.nci.coppa.po.CorrelationNode;
import gov.nih.nci.coppa.po.HealthCareProvider;
import gov.nih.nci.coppa.po.IdentifiedOrganization;
import gov.nih.nci.coppa.po.Person;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.iso._21090.ENXP;
import org.iso._21090.EntityNamePartType;
import org.iso._21090.II;
import org.iso._21090.TEL;

import com.semanticbits.coppa.infrastructure.service.RemoteResolver;
import com.semanticbits.coppasimulator.util.CoppaObjectFactory;

public class InvestigatorResolver extends BaseResolver implements RemoteResolver {
	
	private static Log log = LogFactory.getLog(InvestigatorResolver.class);

	/**
	 * 
	 */
	public List<Object> find(Object example) {
		RemoteInvestigator remoteInvestigatorExample = (RemoteInvestigator)example;
		if (remoteInvestigatorExample.getNciIdentifier() != null) {
			return searchRoleBasedOnNciId(remoteInvestigatorExample.getNciIdentifier(),new HealthCareProvider());
		}
		
		if(remoteInvestigatorExample.getSiteInvestigators().size() > 0 &&
				remoteInvestigatorExample.getSiteInvestigators().get(0).getOrganization() != null && 
				remoteInvestigatorExample.getSiteInvestigators().get(0).getOrganization().getNciInstituteCode() != null){
         //search based on Organization
         log.debug("Searching based on Organization");
         return searchRoleBasedOnOrganization(remoteInvestigatorExample.getSiteInvestigators().get(0).getOrganization().getNciInstituteCode(),new HealthCareProvider());
		} 
		
		return searchRoleBasedOnName(remoteInvestigatorExample.getFirstName(),remoteInvestigatorExample.getMiddleName(),remoteInvestigatorExample.getLastName(),new HealthCareProvider());
	}

	/* 
	 * @see com.semanticbits.coppa.infrastructure.service.RemoteResolver#getRemoteEntityByUniqueId(java.lang.String)
	 */
	public Object getRemoteEntityByUniqueId(String externalId) {
		log.debug("Entering getRemoteEntityByUniqueId() for:" + this.getClass() + " - ExtId: " +externalId);
		
		Person person = new Person();//CoppaObjectFactory.getCoppaPerson(null, null, null);
		II ii = new II();
		ii.setExtension(externalId);
		ii.setRoot(PERSON_ROOT);
		person.setIdentifier(ii);
		
		String correlationNodeXmlPayload = CoppaObjectFactory.getCorrelationNodePayload(new HealthCareProvider(), person, null);
		List<CorrelationNode> correlationNodeList = getCorrelationNodesFromPayloadXml(correlationNodeXmlPayload);
		List<Object> remoteInvestigatorList = getRemoteRolesFromCorrelationNodesList(correlationNodeList);
		if(remoteInvestigatorList.size() > 0){
			return remoteInvestigatorList.get(0);
		}
//		}
		return null;
	}
	
    @Override
    public RemoteInvestigator populateRole(Person coppaPerson, String staffAssignedIdentifier, List<gov.nih.nci.coppa.po.Organization> coppaOrganizationList,
            Map<String, IdentifiedOrganization>    organizationIdToIdentifiedOrganizationsMap){
    	RemoteInvestigator remoteInvestigator = setInvestigatorDetails(coppaPerson,staffAssignedIdentifier);
    	
    	Organization healthcareSite = null;
        if(coppaOrganizationList != null && coppaOrganizationList.size()>0){
            IdentifiedOrganization identifiedOrganization = null;
            for(gov.nih.nci.coppa.po.Organization coppaOrganization: coppaOrganizationList){
                identifiedOrganization = organizationIdToIdentifiedOrganizationsMap.get(coppaOrganization.getIdentifier().getExtension());
                if(identifiedOrganization != null){
                    healthcareSite = new RemoteOrganization();
                    healthcareSite.setNciInstituteCode(identifiedOrganization.getAssignedId().getExtension());
                    healthcareSite.setName(CoppaObjectFactory.getName(coppaOrganization.getName()));
                    healthcareSite.setExternalId(coppaOrganization.getIdentifier().getExtension());
                    SiteInvestigator si = new SiteInvestigator();
                    si.setOrganization(healthcareSite);
                    si.setInvestigator(remoteInvestigator);
                    si.setStartDate(DateUtils.today());
            		remoteInvestigator.addSiteInvestigator(si)	;

                } else {
                    log.error("IdentifiedOrganization is null for Organization with coppaId: "+coppaOrganization.getIdentifier().getExtension());
                }
            }
        } else {
        	return null;
        }
    	
    	
    	return remoteInvestigator;
    }
    
    /**
     * Populate remote investigator. Populate remote research staff. Called from searchStaffByrganization() 
     * Wont load HCSI if identifiedOrganization is passed in as null (for getRemoteEntityByUniqueId()).
     * 
     * @param coppaPerson the coppa person
     * @param staffAssignedIdentifier the staff assigned identifier
     * @param identifiedOrganization the identified organization
     * @return the remote investigator
     */
    @Override
    public RemoteInvestigator populateRole(Person coppaPerson, String staffAssignedIdentifier, IdentifiedOrganization identifiedOrganization){
    	RemoteInvestigator remoteInvestigator = setInvestigatorDetails(coppaPerson,staffAssignedIdentifier);

            remoteInvestigator.setNciIdentifier(staffAssignedIdentifier);
            remoteInvestigator.setExternalId(coppaPerson.getIdentifier().getExtension());
            
            //Build HealthcareSite
            if(identifiedOrganization != null){
            	Organization healthcareSite = new RemoteOrganization();
            	healthcareSite.setNciInstituteCode(identifiedOrganization.getAssignedId().getExtension());
                
            	 SiteInvestigator si = new SiteInvestigator();
                 si.setOrganization(healthcareSite);
                 si.setInvestigator(remoteInvestigator);
                 si.setStartDate(DateUtils.today());
         		remoteInvestigator.addSiteInvestigator(si)	;
            }
            return remoteInvestigator;
        
    }
    
	private RemoteInvestigator setInvestigatorDetails(Person coppaPerson,String nciIdentifier) {
		RemoteInvestigator remoteInvestigator = new RemoteInvestigator();

		Iterator<ENXP> enxpItr = coppaPerson.getName().getPart().iterator();
		ENXP enxp;
		String firstName = null;
		String lastName = "";
		String middleName = "";

		while(enxpItr.hasNext()){
			enxp = enxpItr.next();
			if(enxp.getType().equals(EntityNamePartType.GIV)){
				if(firstName == null){
					firstName = enxp.getValue();
				} else {
					middleName += enxp.getValue() + " ";
				}
			}

			if(enxp.getType().equals(EntityNamePartType.FAM)){
				lastName = enxp.getValue();
			}
		}        

        List<TEL> telecomAddress = coppaPerson.getTelecomAddress().getItem();
        Iterator<TEL> telecomAddressItr = telecomAddress.iterator();
        while (telecomAddressItr.hasNext()) {
        	TEL nextTel = telecomAddressItr.next();
        	String nextTelStr = nextTel.getValue();
        	if (nextTelStr.startsWith("mailto:")) {
        		nextTelStr = nextTelStr.substring("mailto:".length(), nextTelStr.length());
        		remoteInvestigator.setEmailAddress(nextTelStr);
    		}
        	if (nextTelStr.startsWith("x-text-fax:")) {
        		nextTelStr = nextTelStr.substring("x-text-fax:".length(), nextTelStr.length());
        		remoteInvestigator.setFaxNumber(nextTelStr);
    		}
        	if (nextTelStr.startsWith("tel:")) {
        		nextTelStr = nextTelStr.substring("tel:".length(), nextTelStr.length());
        		remoteInvestigator.setPhoneNumber(nextTelStr);
    		}
        }

		remoteInvestigator.setFirstName(firstName.trim());
		remoteInvestigator.setMiddleName(middleName.trim());
		remoteInvestigator.setLastName(lastName.trim());
		remoteInvestigator.setExternalId(coppaPerson.getIdentifier().getExtension());
		remoteInvestigator.setAllowedToLogin(Boolean.FALSE);
		//remoteInvestigator.setLoginId("loginid");
		if (nciIdentifier != null) {
			remoteInvestigator.setNciIdentifier(nciIdentifier);
		}
		return remoteInvestigator;

	}

	public Object saveOrUpdate(Object arg0) {
		// TODO Auto-generated method stub
		return null;
	}


}