package gov.nih.nci.cabig.caaers.domain.repository;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.dao.OrganizationConverterDao;
import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.dao.ResearchStaffConverterDao;
import gov.nih.nci.cabig.caaers.dao.ResearchStaffDao;
import gov.nih.nci.cabig.caaers.dao.SiteResearchStaffDao;
import gov.nih.nci.cabig.caaers.dao.query.ResearchStaffQuery;
import gov.nih.nci.cabig.caaers.dao.query.SiteResearchStaffQuery;
import gov.nih.nci.cabig.caaers.domain.ConverterResearchStaff;
import gov.nih.nci.cabig.caaers.domain.LocalResearchStaff;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.RemoteOrganization;
import gov.nih.nci.cabig.caaers.domain.RemoteResearchStaff;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.caaers.domain.SiteInvestigator;
import gov.nih.nci.cabig.caaers.domain.SiteResearchStaff;
import gov.nih.nci.cabig.caaers.domain.SiteResearchStaffRole;
import gov.nih.nci.cabig.caaers.utils.DateUtils;
import gov.nih.nci.security.UserProvisioningManager;
import gov.nih.nci.security.util.StringUtilities;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.mail.MailException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * This is the repository class for managing the research staff domain object.
 *
 * @author Biju Joseph
 * @author Jared Flatow
 */
@Transactional(readOnly = true)
public class ResearchStaffRepository {

    private CSMUserRepository csmUserRepository;
    private ResearchStaffDao researchStaffDao;
    private SiteResearchStaffDao siteResearchStaffDao;
    private ResearchStaffConverterDao researchStaffConverterDao;
    private OrganizationConverterDao organizationConverterDao;
    private OrganizationRepository organizationRepository;
    private OrganizationDao organizationDao;
    private UserProvisioningManager userProvisioningManager;
    private String authenticationMode;
    private static final Log logger = LogFactory.getLog(ResearchStaffRepository.class);
    private StudyRepository studyRepository;
    private boolean coppaModeForAutoCompleters;
    
	public List<ResearchStaff> getAll() {
        ResearchStaffQuery researchStaffQuery = new ResearchStaffQuery();
        return getResearchStaff(researchStaffQuery);
    }
    
    /**
     * Saves or update the research staff.
     *
     * @param researchStaff the research staff
     * @throws CaaersSystemException if research staff can not be created.
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, noRollbackFor = MailException.class)
    public void save(ResearchStaff researchStaff, String changeURL) {
    	//authenticationMode = "webSSO";
    	boolean createMode = researchStaff.getId() == null;
    	boolean webSSOAuthentication = authenticationMode.equals("webSSO");
    	/*
    	if( webSSOAuthentication && StringUtils.isBlank(researchStaff.getLoginId())){
    		throw new CaaersSystemException("Login Id cannot be null in webSSO mode");
    	}*/
    	//update the loginId to email address if this is not webSSO mode
    	if(createMode && !webSSOAuthentication && StringUtilities.isBlank(researchStaff.getLoginId())) {
    		researchStaff.setLoginId(researchStaff.getEmailAddress());
    	}
    	MailException mailException = null;
    	
    	//RemoteResearchStaff fetched from PO will not have a loginId/Username.  
    	if (researchStaff.getLoginId() != null && StringUtils.isNotEmpty(researchStaff.getLoginId())) {
	    	try{
	    		 csmUserRepository.createOrUpdateCSMUserAndGroupsForResearchStaff(researchStaff, changeURL);
	    	}catch(MailException e){
	    		mailException = e;
	    	}
    	}
    	
    	try{
    		researchStaff = (ResearchStaff)researchStaffDao.merge(researchStaff);
    	}catch(Exception e){
    		e.printStackTrace();
			throw new CaaersSystemException("Failed to create researchstaff");
		}

		try{
			studyRepository.associateStudyPersonnel(researchStaff);
		}catch(Exception e){
			throw new CaaersSystemException("Failed to associte researchstaff to all studies");
		}
    	
        if(mailException != null) throw mailException;
        
    }
    
    public void unlockResearchStaff(ResearchStaff researchStaff) {
    	researchStaff.unlock();
    	researchStaffDao.save(researchStaff);
    }
    
    public void evict(ResearchStaff researchStaff){
    	researchStaffDao.evict(researchStaff);
    }
    
    @Transactional(readOnly = false)
    public void convertToRemote(ResearchStaff localResearchStaff, ResearchStaff remoteResearchStaff){
    	ConverterResearchStaff conRStaff = researchStaffConverterDao.getById(localResearchStaff.getId());
    	conRStaff.setType("REMOTE");
    	conRStaff.setExternalId(remoteResearchStaff.getExternalId());
    	conRStaff.setFirstName(remoteResearchStaff.getFirstName());
    	conRStaff.setLastName(remoteResearchStaff.getLastName());
    	conRStaff.setMiddleName(remoteResearchStaff.getMiddleName());
    	conRStaff.setPhoneNumber(remoteResearchStaff.getPhoneNumber());
    	conRStaff.setFaxNumber(remoteResearchStaff.getFaxNumber());
    	//Commented below 2 lines for ResearchStaff changes. Organization info is captured in SiteResearchStaff 
    	//ConverterOrganization conOrg = organizationConverterDao.getById(localResearchStaff.getOrganization().getId());
    	//conRStaff.setConverterOrganization(conOrg);
    	researchStaffConverterDao.save(conRStaff);
    }

    public ResearchStaff getById(final int id) {
        ResearchStaff researchStaff = researchStaffDao.getById(id);
        initialize(researchStaff);
        return researchStaff;
    }
    public List<ResearchStaff> searchResearchStaff(final ResearchStaffQuery query) {
        return researchStaffDao.searchResearchStaff(query);
    }

    @SuppressWarnings("unchecked")
    public ResearchStaff initialize(final ResearchStaff researchStaff) {
/*
        try {
            gov.nih.nci.security.authorization.domainobjects.User csmUser = userProvisioningManager.getUser(researchStaff.getLoginId());
            List<Group> groups = new ArrayList(userProvisioningManager.getGroups(String.valueOf(csmUser.getUserId())));
            for (Group group : groups) {
                UserGroupType userGroupType = UserGroupType.getByCode(Long.valueOf(group.getGroupId()).intValue());
                if (userGroupType != null) {
                    researchStaff.addUserGroupType(userGroupType);
                }
            }
        } catch (CSObjectNotFoundException e) {
            throw new CaaersSystemException("Error while retriving research staff", e);
        }
*/
        return researchStaff;
    }
    
    @Transactional(readOnly = false)
    public List<SiteResearchStaff> getSiteResearchStaffBySubnames(final String[] subnames, final int site) {
    	List<SiteResearchStaff> siteResearchStaffs = siteResearchStaffDao.getBySubnames(subnames, site);
    	
    	List<ResearchStaff> remoteResearchStaffs = new ArrayList<ResearchStaff>();
    	if (coppaModeForAutoCompleters) {
	    	RemoteResearchStaff searchCriteria = new RemoteResearchStaff(); 
	    	Organization org = organizationDao.getById(site);
	    	SiteResearchStaff sr = new SiteResearchStaff();
	    	sr.setOrganization(org);
	    	searchCriteria.addSiteResearchStaff(sr);
	    	remoteResearchStaffs = researchStaffDao.getRemoteResearchStaff(searchCriteria);
	    	
    	} else {
    		return siteResearchStaffs;
    	}
    	
    	return mergeLocalSiteResearchStaffAndRemoteResearchStaff(siteResearchStaffs,remoteResearchStaffs);
    }
    
    @Transactional(readOnly = false)
    public List<ResearchStaff> getBySubnames(final String[] subnames, final int site) {
    	List<ResearchStaff> researchStaffs = researchStaffDao.getBySubnames(subnames, site);
    	ResearchStaff searchCriteria = new RemoteResearchStaff();
    	Organization org = organizationDao.getById(site);
    	//Commented below line for ResearchStaff changes. Organization is captured in SiteResearchStaff
    	//searchCriteria.setOrganization(org);
    	List<ResearchStaff> remoteResearchStaffs = getRemoteResearchStaff(searchCriteria);

    	return merge (researchStaffs,remoteResearchStaffs);
    }

    @Transactional(readOnly = false)
    public List<ResearchStaff> getResearchStaff(final ResearchStaffQuery query){
    	//Get all the RS from caAERS DB
        List<ResearchStaff> researchStaffs = researchStaffDao.getLocalResearchStaff(query);
        //Get all the RS from External System
        ResearchStaff searchCriteria = new RemoteResearchStaff();
        Map<String, Object> queryParameterMap = query.getParameterMap();
        for (String key : queryParameterMap.keySet()) {
            Object value = queryParameterMap.get(key);
            if (key.equals("firstName")) {
				searchCriteria.setFirstName(value.toString());
			}
            if (key.equals("lastName")) {
				searchCriteria.setLastName(value.toString());
			}
			if (key.equals("organizationNciInstituteCode")) {
				Organization organization = new RemoteOrganization();
				organization.setNciInstituteCode(value.toString());
				//Commented below line for ResearchStaff changes. Organization is captured in SiteResearchStaff
				//searchCriteria.setOrganization(organization);
			}	
        }
    	List<ResearchStaff> remoteResearchStaffs = researchStaffDao.getRemoteResearchStaff(searchCriteria);
    	//Merge and Return
    	return merge (researchStaffs,remoteResearchStaffs);
    }
    
    @Transactional(readOnly = false)
    public List<SiteResearchStaff> getSiteResearchStaff(final SiteResearchStaffQuery query){
    	//Get all the RS from caAERS DB
        List<SiteResearchStaff> siteResearchStaffs = researchStaffDao.getSiteResearchStaff(query);
        return siteResearchStaffs;
    }
    
    
    @Transactional(readOnly = false)
    public List<SiteResearchStaff> getSiteResearchStaff(final SiteResearchStaffQuery query,String type,String text){
    	//Get all the RS from caAERS DB
        List<SiteResearchStaff> siteResearchStaffs = researchStaffDao.getSiteResearchStaff(query);
        
        StringTokenizer typeToken = new StringTokenizer(type, ",");
        StringTokenizer textToken = new StringTokenizer(text, ",");
        String sType, sText;
        String firstName = "";
        String lastName = "";
        String organization = "";

        while (typeToken.hasMoreTokens() && textToken.hasMoreTokens()) {
            sType = typeToken.nextToken();
            sText = textToken.nextToken();
            if (sType.equals("firstName")) {
                firstName = sText;
            } else if (sType.equals("lastName")) {
                lastName = sText;
            } else if (sType.equals("organization")) {
            	organization = sText;
            }
        }
    	
        if(StringUtils.isEmpty(firstName) && StringUtils.isEmpty(lastName) && StringUtils.isEmpty(organization)){
        	return siteResearchStaffs;
        }
    	if(StringUtils.isNotEmpty(firstName) && firstName.indexOf("%") != -1){
    		return siteResearchStaffs;
    	}

    	if(StringUtils.isNotEmpty(lastName) && lastName.indexOf("%") != -1){
    		return siteResearchStaffs;
    	}
        
        RemoteResearchStaff searchCriteria = new RemoteResearchStaff(); 
        searchCriteria.setFirstName(firstName);
        searchCriteria.setLastName(lastName);
        if(StringUtils.isNotEmpty(organization)){
        	Organization org = organizationDao.getById(Integer.parseInt(organization));
        	SiteResearchStaff sr = new SiteResearchStaff();
        	sr.setOrganization(org);
        	searchCriteria.addSiteResearchStaff(sr);
        }
        
        List<ResearchStaff> remoteResearchStaffs = null;
        try{
        	remoteResearchStaffs = researchStaffDao.getRemoteResearchStaff(searchCriteria);
        }catch(Exception e){
        	logger.warn("Error searching ResearchStaff from PO -- " + e.getMessage());
        }
        if(remoteResearchStaffs == null){
        	return siteResearchStaffs;
        }
    	//return (siteResearchStaffs);
        return mergeLocalSiteResearchStaffAndRemoteResearchStaff(siteResearchStaffs,remoteResearchStaffs);
    }
    

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, noRollbackFor = MailException.class)
    private List<SiteResearchStaff> mergeLocalSiteResearchStaffAndRemoteResearchStaff(List<SiteResearchStaff> localList , List<ResearchStaff> remoteList) {
		for (ResearchStaff remoteResearchStaff:remoteList) {
			//ResearchStaff rs = researchStaffDao.getByEmailAddress(remoteResearchStaff.getEmailAddress());

			ResearchStaff rs = researchStaffDao.getByExternalId(remoteResearchStaff.getExternalId());
    		if (rs == null ) {
    			try {
    				List<SiteResearchStaff> srList = remoteResearchStaff.getSiteResearchStaffs();
    				List<SiteResearchStaff> srDBList = new ArrayList<SiteResearchStaff>();
    				for (SiteResearchStaff sr:srList) {
    					Organization remoteOrganization = sr.getOrganization();
    					Organization organization = organizationDao.getByNCIcode(remoteOrganization.getNciInstituteCode());
    	    			if (organization == null) {
    	    				organizationRepository.create(remoteOrganization);
    	    				organization = organizationDao.getByNCIcode(remoteOrganization.getNciInstituteCode());
    	    			} 
    	    			SiteResearchStaff dbSR = new SiteResearchStaff();
    	    			dbSR.setOrganization(organization);
    	    			dbSR.setResearchStaff(remoteResearchStaff);
    	    			dbSR.setEmailAddress(remoteResearchStaff.getEmailAddress());
    	    			dbSR.setPhoneNumber(remoteResearchStaff.getPhoneNumber());
    	    			dbSR.setFaxNumber(remoteResearchStaff.getFaxNumber());
    	    			/*
    	    			SiteResearchStaffRole srs = new SiteResearchStaffRole();
    	    			srs.setRoleCode("caaers_study_cd");
    	    			srs.setStartDate(DateUtils.today());
    	    			srs.setSiteResearchStaff(dbSR);
    	    			dbSR.addSiteResearchStaffRole(srs);
    	    			*/
    	    			srDBList.add(dbSR);
    				}
    				remoteResearchStaff.getSiteResearchStaffs().clear();
    				remoteResearchStaff.setSiteResearchStaffs(srDBList);
    				save(remoteResearchStaff,"URL");
    				remoteResearchStaff = researchStaffDao.getByExternalId(remoteResearchStaff.getExternalId());
    			} catch (MailException e) {
    				logger.error("Mail send exception --" + e.getMessage());
    			}
    			localList.addAll(remoteResearchStaff.getSiteResearchStaffs());        		
        	} else {
        		// if it exist in local list , remote interceptor would have loaded the rest of the details .but need to add orgs manually 
        		List<SiteResearchStaff> srList = remoteResearchStaff.getSiteResearchStaffs();
				try {
					for (SiteResearchStaff sr:srList) {
						Organization remoteOrganization = sr.getOrganization();
						Organization organization = organizationDao.getByNCIcode(remoteOrganization.getNciInstituteCode());
		    			if (organization == null) {
		    				organizationRepository.create(remoteOrganization);
		    				organization = organizationDao.getByNCIcode(remoteOrganization.getNciInstituteCode());
		    			} 
		    			SiteResearchStaff siteResearchStaff = new SiteResearchStaff();
		    			siteResearchStaff.setOrganization(organization);
		    			siteResearchStaff.setResearchStaff(remoteResearchStaff);
		    			siteResearchStaff.setEmailAddress(remoteResearchStaff.getEmailAddress());
		    			siteResearchStaff.setPhoneNumber(remoteResearchStaff.getPhoneNumber());
		    			siteResearchStaff.setFaxNumber(remoteResearchStaff.getFaxNumber());
		    			/*
		    			SiteResearchStaffRole srs = new SiteResearchStaffRole();
		    			srs.setRoleCode("caaers_study_cd");
		    			srs.setStartDate(DateUtils.today());
		    			srs.setSiteResearchStaff(dbSR);
		    			dbSR.addSiteResearchStaffRole(srs);
		    			*/
		    			List<SiteResearchStaff> siDBList = rs.getSiteResearchStaffs();
		    			boolean exists = false;
		    			for (SiteResearchStaff srsd:siDBList){
		    				if (srsd.getOrganization().getNciInstituteCode().equals(organization.getNciInstituteCode())) {
		    					exists = true;
		    					break;
		    				}
		    			}
		    			if (!exists) {
		    				rs.addSiteResearchStaff(siteResearchStaff);
		    			}
		    			
		    			//srDBList.add(dbSR);
					}
					save(rs,"URL");
	        	} catch (MailException e) {
	        		logger.error("Mail send exception --" + e.getMessage());
				}
				
				
        		// local search may not find some the existing remote records , we make sure they are added to local list .
        		List<SiteResearchStaff> rsList = rs.getSiteResearchStaffs();
        		for (SiteResearchStaff srs:rsList) {
        			if (!localList.contains(srs)) {
            			localList.add(srs);
            		}
        		}
        	}
    	}
		return localList;
	}
  
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, noRollbackFor = MailException.class)
    private List<ResearchStaff> merge(List<ResearchStaff> localList , List<ResearchStaff> remoteList) {
		for (ResearchStaff remoteResearchStaff:remoteList) {
			ResearchStaff rs = researchStaffDao.getByEmailAddress(remoteResearchStaff.getEmailAddress());
    		if (rs == null ) {
    			try {
    				List<SiteResearchStaff> srList = remoteResearchStaff.getSiteResearchStaffs();
    				List<SiteResearchStaff> srDBList = new ArrayList<SiteResearchStaff>();
    				for (SiteResearchStaff sr:srList) {
    					Organization remoteOrganization = sr.getOrganization();
    					Organization organization = organizationDao.getByNCIcode(remoteOrganization.getNciInstituteCode());
    	    			if (organization == null) {
    	    				organizationRepository.create(remoteOrganization);
    	    				organization = organizationDao.getByNCIcode(remoteOrganization.getNciInstituteCode());
    	    			} 
    	    			SiteResearchStaff dbSR = new SiteResearchStaff();
    	    			dbSR.setOrganization(organization);
    	    			dbSR.setResearchStaff(remoteResearchStaff);
    	    			srDBList.add(dbSR);
    				}
    				remoteResearchStaff.getSiteResearchStaffs().clear();
    				remoteResearchStaff.setSiteResearchStaffs(srDBList);
    				save(remoteResearchStaff,"URL");
    			} catch (MailException e) {
    				logger.error("Mail send exception --" + e.getMessage());
    			}

        		//rs = researchStaffDao.getByEmailAddress(remoteResearchStaff.getEmailAddress());
        		//rs.setFirstName(remoteResearchStaff.getFirstName());
        		//rs.setLastName(remoteResearchStaff.getLastName());
        		//rs.setMiddleName(remoteResearchStaff.getMiddleName());
        		localList.add(remoteResearchStaff);
        	} else {
        		// if it exist in local list , remote interceptor would have loaded the rest of the details .
        		if (!localList.contains(rs)) {
        			localList.add(rs);
        		}
        	}
    	}
		return localList;
	}
    
    @Transactional(readOnly = false)
    public List<ResearchStaff> getByNciIdentifier(final String[] subnames, final int site){
    	List<ResearchStaff> researchStaffs = researchStaffDao.getByNciIdentifier(subnames,site);
    	ResearchStaff searchCriteria = new RemoteResearchStaff();
    	List<ResearchStaff> remoteResearchStaffs = getRemoteResearchStaff(searchCriteria); 
    	
    	return merge (researchStaffs,remoteResearchStaffs);
    }

    public List<ResearchStaff> getRemoteResearchStaff(final ResearchStaff researchStaff){
    	return researchStaffDao.getRemoteResearchStaff(researchStaff);
    }

    @Required
    public void setResearchStaffDao(final ResearchStaffDao researchStaffDao) {
        this.researchStaffDao = researchStaffDao;
    }

    @Required
    public void setCsmUserRepository(final CSMUserRepository csmUserRepository) {
        this.csmUserRepository = csmUserRepository;
    }

    @Required
    public void setUserProvisioningManager(final UserProvisioningManager userProvisioningManager) {
        this.userProvisioningManager = userProvisioningManager;
    }

    @Required
    public String getAuthenticationMode() {
        return authenticationMode;
    }

    public void setAuthenticationMode(String authenticationMode) {
        this.authenticationMode = authenticationMode;
    }

    @Required
	public void setResearchStaffConverterDao(
			ResearchStaffConverterDao researchStaffConverterDao) {
		this.researchStaffConverterDao = researchStaffConverterDao;
	}

	@Required
	public void setOrganizationConverterDao(
			OrganizationConverterDao organizationConverterDao) {
		this.organizationConverterDao = organizationConverterDao;
	}

	
	@Required
	public void setOrganizationRepository(
			OrganizationRepository organizationRepository) {
		this.organizationRepository = organizationRepository;
	}
	
	@Required
	public void setOrganizationDao(OrganizationDao organizationDao) {
		this.organizationDao = organizationDao;
	}

	@Required
	public void setStudyRepository(StudyRepository studyRepository) {
		this.studyRepository = studyRepository;
	}

    public SiteResearchStaffDao getSiteResearchStaffDao() {
        return siteResearchStaffDao;
    }

    public void setSiteResearchStaffDao(SiteResearchStaffDao siteResearchStaffDao) {
        this.siteResearchStaffDao = siteResearchStaffDao;
    }
    
    public void setCoppaModeForAutoCompleters(boolean coppaModeForAutoCompleters) {
		this.coppaModeForAutoCompleters = coppaModeForAutoCompleters;
	}
}