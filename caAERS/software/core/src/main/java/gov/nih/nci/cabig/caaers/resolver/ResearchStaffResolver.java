package gov.nih.nci.cabig.caaers.resolver;

import gov.nih.nci.cabig.caaers.domain.Address;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.RemoteOrganization;
import gov.nih.nci.cabig.caaers.domain.RemoteResearchStaff;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.caaers.domain.SiteResearchStaff;
import gov.nih.nci.cabig.caaers.domain.SiteResearchStaffRole;
import gov.nih.nci.cabig.caaers.utils.DateUtils;
import gov.nih.nci.coppa.po.ClinicalResearchStaff;
import gov.nih.nci.coppa.po.CorrelationNode;
import gov.nih.nci.coppa.po.IdentifiedOrganization;
import gov.nih.nci.coppa.po.Person;
import gov.nih.nci.security.util.StringUtilities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.iso._21090.ENXP;
import org.iso._21090.EntityNamePartType;
import org.iso._21090.II;
import org.iso._21090.TEL;

import com.semanticbits.coppa.infrastructure.service.RemoteResolver;
import com.semanticbits.coppasimulator.util.CoppaObjectFactory;

public class ResearchStaffResolver extends BaseResolver implements RemoteResolver{
	
	private static Logger logger = Logger.getLogger(ResearchStaffResolver.class);


	
	public List<Object> find(Object example) {
		RemoteResearchStaff remoteResearchStaffExample = (RemoteResearchStaff)example;

		if (!StringUtilities.isBlank( remoteResearchStaffExample.getNciIdentifier())) {
			return this.searchRoleBasedOnNciId(remoteResearchStaffExample.getNciIdentifier(),new ClinicalResearchStaff());
		}
		
		if(remoteResearchStaffExample.getSiteResearchStaffs().size() > 0 &&
				remoteResearchStaffExample.getSiteResearchStaffs().get(0).getOrganization() != null && 
				remoteResearchStaffExample.getSiteResearchStaffs().get(0).getOrganization().getNciInstituteCode() != null){
	         //search based on Organization
	         logger.debug("Searching based on Organization");
	         List<Object> results = searchRoleBasedOnOrganization(remoteResearchStaffExample.getSiteResearchStaffs().get(0).getOrganization().getNciInstituteCode() ,new ClinicalResearchStaff());
	         if (!StringUtilities.isBlank( remoteResearchStaffExample.getFirstName()) || 
	        		 !StringUtilities.isBlank( remoteResearchStaffExample.getLastName())) {
	        	 
	        	 return this.filterByName(results, remoteResearchStaffExample.getFirstName(), remoteResearchStaffExample.getLastName());
	         } 
	 		 return results;
         } 
		
		return searchRoleBasedOnName(remoteResearchStaffExample.getFirstName(),remoteResearchStaffExample.getMiddleName(),remoteResearchStaffExample.getLastName(),new ClinicalResearchStaff());
	}
	public Object getRemoteEntityByUniqueId(String externalId) {
		logger.debug("Entering getRemoteEntityByUniqueId() for:" + this.getClass() + " - ExtId: " +externalId);
		
		Person person = new Person();//CoppaObjectFactory.getCoppaPerson(null, null, null);
		II ii = new II();
		ii.setExtension(externalId);
		ii.setRoot(PERSON_ROOT);
		person.setIdentifier(ii);
		
		String correlationNodeXmlPayload = CoppaObjectFactory.getCorrelationNodePayload(new ClinicalResearchStaff(), person, null);
		List<CorrelationNode> correlationNodeList = getCorrelationNodesFromPayloadXml(correlationNodeXmlPayload);
		List<Object> remoteResearchStaffList = this.getRemoteRolesFromCorrelationNodesList(correlationNodeList);
		if(remoteResearchStaffList.size() > 0){
			return remoteResearchStaffList.get(0);
		}

		return null;
	}
	
	private List<Object> filterByName(List<Object> results,String firstName,String lastName) {
		List<Object> filteredList = new ArrayList<Object>();
		if (!StringUtilities.isBlank(firstName) && !StringUtilities.isBlank(lastName)) {
			for (int i=0 ; i<results.size(); i++) {
				ResearchStaff obj = (RemoteResearchStaff)results.get(i);
				if (obj.getFirstName().toLowerCase().indexOf(firstName.toLowerCase()) != -1 && obj.getLastName().toLowerCase().indexOf(lastName.toLowerCase()) != -1){
					filteredList.add(obj);
				}
			}	
			return filteredList;
		}
		if (!StringUtilities.isBlank(firstName)) {
			 //filter by first name ...
			for (int i=0 ; i<results.size(); i++) {
				ResearchStaff obj = (RemoteResearchStaff)results.get(i);
				if (obj.getFirstName().toLowerCase().indexOf(firstName.toLowerCase()) != -1){
					filteredList.add(obj);
				}
			}
			return filteredList;
		 }
		 if (!StringUtilities.isBlank(lastName)) {
			 //filter by last name ...
 			for (int i=0 ; i<results.size(); i++) {
				ResearchStaff obj = (RemoteResearchStaff)results.get(i);
				if (obj.getLastName().toLowerCase().indexOf(lastName.toLowerCase()) != -1){
					filteredList.add(obj);
				}
			}
 			return filteredList;
		 }	
		 return results;
	}
	
	@Override
	public RemoteResearchStaff populateRole(Person coppaPerson, String nciIdentifier, IdentifiedOrganization identifiedOrganization){		
		//String[] splitids = orgsIdsForPerson.split(IDSEPERATOR);
		RemoteResearchStaff remoteResearchStaff = setResearchStaffDetails(coppaPerson,nciIdentifier);		
		Organization site = new RemoteOrganization();
		site.setNciInstituteCode(identifiedOrganization.getAssignedId().getExtension());
		//site.setExternalId(splitids[1]);
		SiteResearchStaff siteResearchStaff = new SiteResearchStaff();
		siteResearchStaff.setResearchStaff(remoteResearchStaff);
		siteResearchStaff.setOrganization(site);
		SiteResearchStaffRole srs = new SiteResearchStaffRole();
		srs.setRoleCode("caaers_study_cd");
		srs.setStartDate(DateUtils.today());
		srs.setSiteResearchStaff(siteResearchStaff);
		siteResearchStaff.addSiteResearchStaffRole(srs);
		remoteResearchStaff.addSiteResearchStaff(siteResearchStaff);
		return remoteResearchStaff;
	}
	
	@Override
	public RemoteResearchStaff populateRole(Person coppaPerson, String staffAssignedIdentifier, List<gov.nih.nci.coppa.po.Organization> coppaOrganizationList,
            Map<String, IdentifiedOrganization>    organizationIdToIdentifiedOrganizationsMap) {		

		RemoteResearchStaff remoteResearchStaff = setResearchStaffDetails(coppaPerson,staffAssignedIdentifier);	
		Organization site = null;

		if (coppaOrganizationList != null && coppaOrganizationList.size()>0){
			IdentifiedOrganization identifiedOrganization = null;
			for(gov.nih.nci.coppa.po.Organization coppaOrganization: coppaOrganizationList){
				identifiedOrganization = organizationIdToIdentifiedOrganizationsMap.get(coppaOrganization.getIdentifier().getExtension());
				if(identifiedOrganization != null){
					site = new RemoteOrganization();
					site.setNciInstituteCode(identifiedOrganization.getAssignedId().getExtension());
					site.setName(CoppaObjectFactory.getName(coppaOrganization.getName()));
					site.setExternalId(coppaOrganization.getIdentifier().getExtension());
					SiteResearchStaff siteResearchStaff = new SiteResearchStaff();
					siteResearchStaff.setResearchStaff(remoteResearchStaff);
					siteResearchStaff.setOrganization(site);
					SiteResearchStaffRole srs = new SiteResearchStaffRole();
	    			srs.setRoleCode("caaers_study_cd");
	    			srs.setStartDate(DateUtils.today());
	    			srs.setSiteResearchStaff(siteResearchStaff);
	    			siteResearchStaff.addSiteResearchStaffRole(srs);
					remoteResearchStaff.addSiteResearchStaff(siteResearchStaff);
				}
			}

		}

		return remoteResearchStaff;
	}   
	private RemoteResearchStaff setResearchStaffDetails(Person coppaPerson,String nciIdentifier) {
		RemoteResearchStaff remoteResearchStaff = new RemoteResearchStaff();

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
        		remoteResearchStaff.setEmailAddress(nextTelStr);
    		}
        	if (nextTelStr.startsWith("x-text-fax:")) {
        		nextTelStr = nextTelStr.substring("x-text-fax:".length(), nextTelStr.length());
        		remoteResearchStaff.setFaxNumber(nextTelStr);
    		}
        	if (nextTelStr.startsWith("tel:")) {
        		nextTelStr = nextTelStr.substring("tel:".length(), nextTelStr.length());
        		remoteResearchStaff.setPhoneNumber(nextTelStr.replaceAll("-ext-", "x"));
    		}
        }
		
		
		
		
		remoteResearchStaff.setFirstName(firstName.trim());
		remoteResearchStaff.setMiddleName(middleName.trim());
		remoteResearchStaff.setLastName(lastName.trim());
		remoteResearchStaff.setAddress(new Address());
		//remoteResearchStaff.setLoginId("loginid");
		remoteResearchStaff.setExternalId(coppaPerson.getIdentifier().getExtension());
		if (nciIdentifier != null) {
			remoteResearchStaff.setNciIdentifier(nciIdentifier);
		}
		return remoteResearchStaff;

	}

	public Object saveOrUpdate(Object arg0) {
		// TODO Auto-generated method stub
		return null;
	}
}
