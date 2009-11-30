package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.domain.Investigator;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.RemoteInvestigator;
import gov.nih.nci.cabig.caaers.domain.SiteInvestigator;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.semanticbits.coppa.infrastructure.RemoteSession;

public class RemoteInvestigatorDaoHelper {
	private Logger log = Logger.getLogger(RemoteInvestigatorDaoHelper.class);
	private InvestigatorDao investigatorDao;
	
	private OrganizationDao organizationDao;
	
	private RemoteSession remoteSession;
	

	

	public List<SiteInvestigator> getSiteInvestigators(Integer site) {
		List<SiteInvestigator> siteInvestigators1 = new ArrayList<SiteInvestigator>();
		// get remote investigators
		Investigator searchCriteria = new RemoteInvestigator();
		Organization org = organizationDao.getById(site);
		SiteInvestigator si = new SiteInvestigator();
		si.setOrganization(org);
		searchCriteria.getSiteInvestigators().add(si);
		
    	List<Investigator> remoteInvestigators = new ArrayList<Investigator>();
		try {
			remoteInvestigators = (List)remoteSession.find(searchCriteria);
		} catch (Exception e) {
			log.warn("Error while invoking COPPA services", e);
		} 
    	
    	//check for these investigators in database and save if not available .
    	
    	for (Investigator remoteInvestigator:remoteInvestigators) {    		
			Investigator investigator = investigatorDao.getByEmailAddress(remoteInvestigator.getEmailAddress());

    		if (investigator == null ) {  
    			
    			List<SiteInvestigator> siteInvestigators = remoteInvestigator.getSiteInvestigators();
    			List<SiteInvestigator> newSiteInvestigators = new ArrayList<SiteInvestigator>();
    			for (SiteInvestigator siteInvestigator:siteInvestigators) {
    				//if associated organization is not there in our DB
    				Organization remoteOrganization = siteInvestigator.getOrganization();
    				Organization organization = organizationDao.getByNCIcode(remoteOrganization.getNciInstituteCode());
        			if (organization == null) {
        				// TODO : need to get the remote organozation from coppa and save it ..
        				organizationDao.save(remoteOrganization);
        				organization = organizationDao.getByNCIcode(remoteOrganization.getNciInstituteCode());
        			} 
        			

        			SiteInvestigator newSi = new SiteInvestigator();
        			newSi.setOrganization(organization);
        			//newSi.setInvestigator(investigator);
        			newSiteInvestigators.add(newSi);
        			
    			}
    			remoteInvestigator.getSiteInvestigators().clear();
    			investigatorDao.save(remoteInvestigator);
    			investigator = investigatorDao.getByEmailAddress(remoteInvestigator.getEmailAddress());
    			for (SiteInvestigator siteInvestigator:newSiteInvestigators) {
    				siteInvestigator.setInvestigator(investigator);
    				siteInvestigators1.add(siteInvestigator);
    			}

        	} else {
        		siteInvestigators1.addAll(investigator.getSiteInvestigators());
        	}
    	}
    	return siteInvestigators1;
	}


	public void setInvestigatorDao(InvestigatorDao investigatorDao) {
		this.investigatorDao = investigatorDao;
	}
	
	public void setRemoteSession(RemoteSession remoteSession) {
		this.remoteSession = remoteSession;
	}


	public void setOrganizationDao(OrganizationDao organizationDao) {
		this.organizationDao = organizationDao;
	}




}
