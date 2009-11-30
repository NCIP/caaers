package gov.nih.nci.cabig.caaers.domain.repository;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.dao.InvestigatorConverterDao;
import gov.nih.nci.cabig.caaers.dao.InvestigatorDao;
import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.dao.SiteInvestigatorDao;
import gov.nih.nci.cabig.caaers.dao.query.InvestigatorQuery;
import gov.nih.nci.cabig.caaers.domain.ConverterInvestigator;
import gov.nih.nci.cabig.caaers.domain.Investigator;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.RemoteInvestigator;
import gov.nih.nci.cabig.caaers.domain.SiteInvestigator;
import gov.nih.nci.cabig.caaers.domain.UserGroupType;
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
	private CSMUserRepository csmUserRepository;
	private String authenticationMode;
	private OrganizationDao organizationDao;
	private OrganizationRepository organizationRepository;
	
	private static final Log logger = LogFactory.getLog(InvestigatorRepositoryImpl.class); 
	 
	 /**
	 * Creates a new investigator in the system 
	 * OR
	 * Updates and existing investigator details
	 * 
	 * Will also update the corresponding features associated to the CSM groups
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, noRollbackFor = MailException.class)
	public void save(Investigator investigator, String changeURL) {
		MailException mailException = null;
		if(investigator.getAllowedToLogin()){
	    	
	    	if (investigator.getEmailAddress() == null) {
	            throw new CaaersSystemException("Email address is required");
	        }
	    	
	    	//note - to support allowLogin of investigators added via COPPA, we shoudld set the default groups if they do not exist
	    	if(!investigator.getUserGroupTypes().contains(UserGroupType.caaers_physician)){
	    		investigator.addUserGroupType(UserGroupType.caaers_physician);
	    	}
	    	
	    	if(!investigator.getUserGroupTypes().contains(UserGroupType.caaers_user)){
	    		investigator.addUserGroupType(UserGroupType.caaers_user);
	    	}
	    	
	    	if(investigator.getId() == null &&  StringUtilities.isBlank(investigator.getLoginId())) {
	    		investigator.setLoginId(investigator.getEmailAddress());
	    	}
	    	
		    try {
				csmUserRepository.createOrUpdateCSMUserAndGroupsForInvestigator(investigator, changeURL);
		    } catch (MailException e) {
				mailException = e;
			}
		}
		
		investigator = investigatorDao.merge(investigator);
        if(mailException != null) throw mailException;
	}
	
	public List<Investigator> searchInvestigator(final InvestigatorQuery query,String type,String text){
		List<Investigator> localInvestigators = investigatorDao.getLocalInvestigator(query);
		
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
        	
        	return localInvestigators;
        }
        
    	if(StringUtils.isNotEmpty(firstName) && firstName.indexOf("%") != -1){
    		return localInvestigators;
    	}
    	if(StringUtils.isNotEmpty(lastName) && lastName.indexOf("%") != -1){
    		return localInvestigators;
    	}
    	if(StringUtils.isNotEmpty(nciIdentifier) && nciIdentifier.indexOf("%") != -1){
    		return localInvestigators;
    	}
		
        Map<String, Object> queryParameterMap = query.getParameterMap();
        for (String key : queryParameterMap.keySet()) {
			if (key.equals("loginId")) {
				return localInvestigators;
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
		List<Investigator> remoteInvestigators = investigatorDao.getRemoteInvestigators(searchCriteria);
		return merge(localInvestigators,remoteInvestigators);
	}
	
	
	public List<Investigator> searchInvestigator(final InvestigatorQuery query){
		List<Investigator> localInvestigators = investigatorDao.getLocalInvestigator(query);
		return localInvestigators;
	}
	
	public List<SiteInvestigator> getBySubnames(String[] subnames, int siteId) {
		List<SiteInvestigator> siteInvestigators = siteInvestigatorDao.getBySubnames(subnames,siteId);
		//RemoteInvestigator searchCriteria = new RemoteInvestigator(); 
		//List<SiteInvestigator> remoteInvestigators = siteInvestigatorDao.getRemoteInvestigators(searchCriteria);
		//return merge(localInvestigators,remoteInvestigators);
		return siteInvestigators;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, noRollbackFor = MailException.class)
    private List<Investigator> merge(List<Investigator> localList , List<Investigator> remoteList) {
		for (Investigator remoteInvestigator:remoteList) {
			//Investigator inv = investigatorDao.getByEmailAddress(remoteInvestigator.getEmailAddress());
			if (StringUtils.isBlank(remoteInvestigator.getNciIdentifier())) {
				continue;
			}
			Investigator inv = investigatorDao.getByNciIdentfier(remoteInvestigator.getNciIdentifier());
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
    				remoteInvestigator = investigatorDao.getByNciIdentfier(remoteInvestigator.getNciIdentifier());
    			} catch (MailException e) {
    				e.printStackTrace();
    			}
        		//this.investigatorDao.save(remoteInvestigator);
        		localList.add(remoteInvestigator);
        	} else {
        		// if it exist in local list , remote interceptor would have loaded the rest of the details .
        		if (!localList.contains(inv)) {
        			localList.add(inv);
        		}
        	}
    	}
		return localList;
	}
	
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
    public CSMUserRepository getCsmUserRepository() {
		return csmUserRepository;
	}
    public void setCsmUserRepository(CSMUserRepository csmUserRepository) {
		this.csmUserRepository = csmUserRepository;
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
}
