package gov.nih.nci.cabig.caaers.domain.repository;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.dao.InvestigatorConverterDao;
import gov.nih.nci.cabig.caaers.dao.InvestigatorDao;
import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.dao.SiteInvestigatorDao;
import gov.nih.nci.cabig.caaers.dao.query.InvestigatorQuery;
import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.event.EventFactory;
import gov.nih.nci.cabig.caaers.security.CaaersSecurityFacadeImpl;
import gov.nih.nci.cabig.caaers.utils.DateUtils;
import gov.nih.nci.security.util.StringUtilities;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.mail.MailException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
/**
 * This is the repository class for managing investigators
 * @author Biju Joseph
 *
 */
@Transactional(readOnly = false)
public class InvestigatorRepositoryImpl implements InvestigatorRepository {
	private InvestigatorDao investigatorDao;
	private SiteInvestigatorDao siteInvestigatorDao;
	private InvestigatorConverterDao investigatorConverterDao;
	private CaaersSecurityFacadeImpl caaersSecurityFacade;
	private String authenticationMode;
	private OrganizationDao organizationDao;
	private OrganizationRepository organizationRepository;
    private EventFactory eventFactory;
        
	
	private static final Log logger = LogFactory.getLog(InvestigatorRepositoryImpl.class); 
	 
	 /**
	 * Creates a new investigator in the system 
	 * OR
	 * Updates and existing investigator details
	 * 
	 * Will also update the corresponding features associated to the CSM groups
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, noRollbackFor = MailException.class)
	public Investigator save(Investigator investigator, String changeURL) {
		if(investigator.getAllowedToLogin()){
	    	
	    	if (investigator.getEmailAddress() == null) {
	            throw new CaaersSystemException("Email address is required");
	        }
	    	
	    	if(investigator.getId() == null &&  StringUtilities.isBlank(investigator.getLoginId())) {
	    		investigator.setLoginId(investigator.getEmailAddress());
	    	}
		}
		
		//save the details in caAERS
		investigator = investigatorDao.merge(investigator);
	    
		//create the csm entries
		if(investigator.getAllowedToLogin()){
			caaersSecurityFacade.createOrUpdateCSMUser(investigator, changeURL);
		}
		
		return investigator;
	}
	
	public List<Investigator> searchInvestigator(final InvestigatorQuery query,String type,String text){
		
        StringTokenizer typeToken = new StringTokenizer(type, ",");
        StringTokenizer textToken = new StringTokenizer(text, ",");
        String sType, sText;
        String firstName = "";
        String lastName = "";
        String nciIdentifier = "";
        String organization = "";

        while (typeToken.hasMoreTokens() && textToken.hasMoreTokens()) {
            sType = typeToken.nextToken();
            sText = textToken.nextToken();
            if (sType.equals("firstName")) {
                firstName = sText;
            } else if (sType.equals("nciIdentifier")) {
            	nciIdentifier = sText;
            } else if (sType.equals("lastName")) {
                lastName = sText;
            } else if (sType.equals("organization")) {
            	organization = sText;
            }
        }
    	
        if(StringUtils.isEmpty(firstName) &&
        		StringUtils.isEmpty(lastName) &&
        			StringUtils.isEmpty(nciIdentifier) &&
        				StringUtils.isEmpty(organization)){
        	
        	return investigatorDao.getLocalInvestigator(query);
        }
        
    	if(StringUtils.isNotEmpty(firstName) && firstName.indexOf("%") != -1){
    		return investigatorDao.getLocalInvestigator(query);
    	}
    	if(StringUtils.isNotEmpty(lastName) && lastName.indexOf("%") != -1){
    		return investigatorDao.getLocalInvestigator(query);
    	}
    	if(StringUtils.isNotEmpty(nciIdentifier) && nciIdentifier.indexOf("%") != -1){
    		return investigatorDao.getLocalInvestigator(query);
    	}
		
        Map<String, Object> queryParameterMap = query.getParameterMap();
        for (String key : queryParameterMap.keySet()) {
			if (key.equals("loginId")) {
				return investigatorDao.getLocalInvestigator(query);
			}
        }
        
		//Populate searchCriteria 
		RemoteInvestigator searchCriteria = new RemoteInvestigator(); 
		searchCriteria.setFirstName(firstName);
		searchCriteria.setLastName(lastName);
		searchCriteria.setNciIdentifier(nciIdentifier);
		if(StringUtils.isNotEmpty(organization)){
			SiteInvestigator si = new SiteInvestigator();
			si.setOrganization(this.organizationDao.getById(Integer.parseInt(organization)));
			searchCriteria.addSiteInvestigator(si);
		}
		List<Investigator> remoteInvestigators = null;
		try{
			remoteInvestigators = investigatorDao.getRemoteInvestigators(searchCriteria);
		}catch(Exception e){
			logger.warn("Error searching Investiagators from PO -- " + e.getMessage());
		}
		if(remoteInvestigators == null){
			return investigatorDao.getLocalInvestigator(query);
		}else{
			saveRemoteInvestigators(remoteInvestigators);
			logger.info(remoteInvestigators.size() +" :::: Investigators fetched from PO");
		}
		List<Investigator> invList = investigatorDao.getLocalInvestigator(query);
		if(invList != null){
			logger.info(invList.size() +" :::: Investigators is being displayed");
		}
		return invList;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, noRollbackFor = MailException.class)
	private void saveRemoteInvestigators(List<Investigator> remoteList){
		for (Investigator remoteInvestigator:remoteList) {

			Investigator inv = investigatorDao.getByExternalId(remoteInvestigator.getExternalId());
    		if (inv == null ) {
    			try {
    				List<SiteInvestigator> siList = remoteInvestigator.getSiteInvestigators();
    				List<SiteInvestigator> siDBList = new ArrayList<SiteInvestigator>();
    				for (SiteInvestigator si:siList) {
    					Organization remoteOrganization = si.getOrganization();
    					Organization organization = organizationDao.getByNCIcode(remoteOrganization.getNciInstituteCode());
    	    			if (organization == null) {
    	    				organizationRepository.create(remoteOrganization);
    	    				organization = organizationDao.getByNCIcode(remoteOrganization.getNciInstituteCode());
    	    			} 
    	    			SiteInvestigator dbSI = new SiteInvestigator();
    	    			dbSI.setOrganization(organization);
    	    			dbSI.setStartDate(DateUtils.today());
    	    			dbSI.setInvestigator(remoteInvestigator);
    	    			siDBList.add(dbSI);
    	    			
    				}
    				remoteInvestigator.getSiteInvestigators().clear();
    				remoteInvestigator.setSiteInvestigators(siDBList);
    				
    				save(remoteInvestigator,"URL");
    				remoteInvestigator = investigatorDao.getByExternalId(remoteInvestigator.getExternalId());
    			} catch (MailException e) {
    				logger.error("Mail send exception --" + e.getMessage());
    			}
        	} else {
        		try {
    				List<SiteInvestigator> siList = remoteInvestigator.getSiteInvestigators();
    				for (SiteInvestigator si:siList) {
    					Organization remoteOrganization = si.getOrganization();
    					Organization organization = organizationDao.getByNCIcode(remoteOrganization.getNciInstituteCode());
    	    			if (organization == null) {
    	    				organizationRepository.create(remoteOrganization);
    	    				organization = organizationDao.getByNCIcode(remoteOrganization.getNciInstituteCode());
    	    			} 
    	    			SiteInvestigator siteInvestigator = new SiteInvestigator();
    	    			siteInvestigator.setOrganization(organization);
    	    			siteInvestigator.setStartDate(DateUtils.today());
    	    			siteInvestigator.setInvestigator(remoteInvestigator);
    	    			List<SiteInvestigator> siDBList = inv.getSiteInvestigators();
    	    			boolean exists = false;
    	    			for (SiteInvestigator sid:siDBList){
    	    				if (sid.getOrganization().getNciInstituteCode().equals(organization.getNciInstituteCode())) {
    	    					exists = true;
    	    					break;
    	    				}
    	    			}
    	    			if (!exists) {
    	    				inv.addSiteInvestigator(siteInvestigator);
    	    			}
    				}
    				save(inv,"URL");
    			} catch (MailException e) {
    				logger.error("Mail send exception --" + e.getMessage());
    			}
        	}
    	}

        eventFactory.publishEntityModifiedEvent(new LocalInvestigator(), false);

	}
	
	public List<Investigator> searchInvestigator(final InvestigatorQuery query){
		List<Investigator> localInvestigators = investigatorDao.getLocalInvestigator(query);
		return localInvestigators;
	}
	
	public List<SiteInvestigator> getBySubnames(String[] subnames, int siteId) {
		List<SiteInvestigator> siteInvestigators = siteInvestigatorDao.getBySubnames(subnames,siteId);
		return siteInvestigators;
	}
	
	/**
	 * @param id
	 * @return
	 */
	public Investigator getById(int id) {
		return getInvestigatorDao().getInvestigatorById(id);
	}

/* MERGING the search results in java, would not allow us to security filer the results. Hence commented the below method and introduced saveRemoteInvestigators method.
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, noRollbackFor = MailException.class)
    private List<Investigator> merge(List<Investigator> localList , List<Investigator> remoteList) {
		for (Investigator remoteInvestigator:remoteList) {
			//Investigator inv = investigatorDao.getByEmailAddress(remoteInvestigator.getEmailAddress());

			Investigator inv = investigatorDao.getByExternalId(remoteInvestigator.getExternalId());
			
    		if (inv == null ) {
    			try {
    				
    				List<SiteInvestigator> siList = remoteInvestigator.getSiteInvestigators();
    				List<SiteInvestigator> siDBList = new ArrayList<SiteInvestigator>();
    				for (SiteInvestigator si:siList) {
    					Organization remoteOrganization = si.getOrganization();
    					Organization organization = organizationDao.getByNCIcode(remoteOrganization.getNciInstituteCode());
    	    			if (organization == null) {
    	    				organizationRepository.create(remoteOrganization);
    	    				organization = organizationDao.getByNCIcode(remoteOrganization.getNciInstituteCode());
    	    			} 
    	    			SiteInvestigator dbSI = new SiteInvestigator();
    	    			dbSI.setOrganization(organization);
    	    			dbSI.setStartDate(DateUtils.today());
    	    			dbSI.setInvestigator(remoteInvestigator);
    	    			siDBList.add(dbSI);
    	    			
    				}
    				remoteInvestigator.getSiteInvestigators().clear();
    				remoteInvestigator.setSiteInvestigators(siDBList);
    				
    				save(remoteInvestigator,"URL");
    				remoteInvestigator = investigatorDao.getByExternalId(remoteInvestigator.getExternalId());
    			} catch (MailException e) {
    				e.printStackTrace();
    			}
        		//this.investigatorDao.save(remoteInvestigator);
        		localList.add(remoteInvestigator);
        	} else {
        		try {
    				List<SiteInvestigator> siList = remoteInvestigator.getSiteInvestigators();
    				
    				for (SiteInvestigator si:siList) {
    					Organization remoteOrganization = si.getOrganization();
    					Organization organization = organizationDao.getByNCIcode(remoteOrganization.getNciInstituteCode());
    	    			if (organization == null) {
    	    				organizationRepository.create(remoteOrganization);
    	    				organization = organizationDao.getByNCIcode(remoteOrganization.getNciInstituteCode());
    	    			} 
    	    			SiteInvestigator siteInvestigator = new SiteInvestigator();
    	    			siteInvestigator.setOrganization(organization);
    	    			siteInvestigator.setStartDate(DateUtils.today());
    	    			siteInvestigator.setInvestigator(remoteInvestigator);
    	    			List<SiteInvestigator> siDBList = inv.getSiteInvestigators();
    	    			boolean exists = false;
    	    			for (SiteInvestigator sid:siDBList){
    	    				if (sid.getOrganization().getNciInstituteCode().equals(organization.getNciInstituteCode())) {
    	    					exists = true;
    	    					break;
    	    				}
    	    			}
    	    			if (!exists) {
    	    				inv.addSiteInvestigator(siteInvestigator);
    	    			}
    	    			
    	    			//siDBList.add(dbSI);
    	    			
    				}
    				//inv.getSiteInvestigators().clear();
    				//inv.setSiteInvestigators(siDBList);
    				
    				save(inv,"URL");
    				
    			} catch (MailException e) {
    				e.printStackTrace();
    			}
        		// if it exist in local list , remote interceptor would have loaded the rest of the details .
        		if (!localList.contains(inv)) {
        			localList.add(inv);
        		}
        	}
    	}
		return localList;
	}
*/
	
	@Transactional(readOnly = false)
    public void convertToRemote(Investigator localInvestigator, Investigator remoteInvestigator){
    	ConverterInvestigator conInv = investigatorConverterDao.getById(localInvestigator.getId());
    	conInv.setType("REMOTE");
    	conInv.setExternalId(remoteInvestigator.getExternalId());
    	conInv.setFirstName(remoteInvestigator.getFirstName());
    	conInv.setLastName(remoteInvestigator.getLastName());
    	conInv.setMiddleName(remoteInvestigator.getMiddleName());
    	conInv.setPhoneNumber(remoteInvestigator.getPhoneNumber());
    	conInv.setFaxNumber(remoteInvestigator.getFaxNumber());
    	investigatorConverterDao.save(conInv);
    }
	
    public InvestigatorDao getInvestigatorDao() {
		return investigatorDao;
	}
    public void setInvestigatorDao(InvestigatorDao investigatorDao) {
		this.investigatorDao = investigatorDao;
	}
    public String getAuthenticationMode() {
		return authenticationMode;
	}
    public void setAuthenticationMode(String authenticationMode) {
		this.authenticationMode = authenticationMode;
	}

	public void setInvestigatorConverterDao(
			InvestigatorConverterDao investigatorConverterDao) {
		this.investigatorConverterDao = investigatorConverterDao;
	}

	public void setSiteInvestigatorDao(SiteInvestigatorDao siteInvestigatorDao) {
		this.siteInvestigatorDao = siteInvestigatorDao;
	}

	public void setOrganizationDao(OrganizationDao organizationDao) {
		this.organizationDao = organizationDao;
	}

	public void setOrganizationRepository(
			OrganizationRepository organizationRepository) {
		this.organizationRepository = organizationRepository;
	}

	public void setCaaersSecurityFacade(
			CaaersSecurityFacadeImpl caaersSecurityFacade) {
		this.caaersSecurityFacade = caaersSecurityFacade;
	}

    public EventFactory getEventFactory() {
        return eventFactory;
    }

    public void setEventFactory(EventFactory eventFactory) {
        this.eventFactory = eventFactory;
    }
}
