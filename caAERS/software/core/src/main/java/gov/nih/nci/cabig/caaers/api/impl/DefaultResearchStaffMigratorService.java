package gov.nih.nci.cabig.caaers.api.impl;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.RoleMembership;
import gov.nih.nci.cabig.caaers.api.ResearchStaffMigratorService;
import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.domain.repository.PersonRepository;
import gov.nih.nci.cabig.caaers.domain.repository.UserRepository;
import gov.nih.nci.cabig.caaers.integration.schema.common.CaaersServiceResponse;
import gov.nih.nci.cabig.caaers.integration.schema.common.ServiceResponse;
import gov.nih.nci.cabig.caaers.integration.schema.common.Status;
import gov.nih.nci.cabig.caaers.integration.schema.common.WsError;
import gov.nih.nci.cabig.caaers.integration.schema.researchstaff.ResearchStaffType;
import gov.nih.nci.cabig.caaers.integration.schema.researchstaff.SiteResearchStaffRoleType;
import gov.nih.nci.cabig.caaers.integration.schema.researchstaff.SiteResearchStaffType;
import gov.nih.nci.cabig.caaers.integration.schema.researchstaff.Staff;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome.Severity;
import gov.nih.nci.cabig.caaers.tools.configuration.Configuration;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
import java.util.List;

@WebService(endpointInterface="gov.nih.nci.cabig.caaers.api.ResearchStaffMigratorService", serviceName="ResearchStaffMigratorService")
@SOAPBinding(parameterStyle=SOAPBinding.ParameterStyle.BARE)
public class DefaultResearchStaffMigratorService extends DefaultMigratorService implements ResearchStaffMigratorService {
	
	private static final Log logger = LogFactory.getLog(DefaultResearchStaffMigratorService.class);
    protected PersonRepository personRepository;
    protected UserRepository userRepository;
	
	/**
     * Fetches the research staff from the DB
     * @return
     */
    ResearchStaff fetchResearchStaff(String loginId) {
        Person p = personRepository.getByLoginId(loginId);
        if(p instanceof ResearchStaff) return (ResearchStaff) p;
        throw new CaaersSystemException("Login Id/e-mail address is already associated to an Investigator");
    }
    
    
    public DomainObjectImportOutcome<ResearchStaff> processResearchStaff(ResearchStaffType researchStaffType){
    	
    	DomainObjectImportOutcome<ResearchStaff> researchStaffImportOutcome = new DomainObjectImportOutcome<ResearchStaff>();
    	ResearchStaff xmlResearchStaff = null;
    	ResearchStaff dbResearchStaff = null;
		try {
			xmlResearchStaff = buildResearchStaff(researchStaffType);
			String email = researchStaffType.getEmailAddress();
            String loginId = researchStaffType.getLoginId();
            if (StringUtils.isEmpty(loginId)) {
          	  loginId = email;
            }
            dbResearchStaff = fetchResearchStaff(loginId);
			if(dbResearchStaff == null){
    			researchStaffImportOutcome.setImportedDomainObject(xmlResearchStaff);
			}else{
				syncPersonDetails(dbResearchStaff, xmlResearchStaff);
                syncUserDetails(dbResearchStaff, xmlResearchStaff);
    			researchStaffImportOutcome.setImportedDomainObject(dbResearchStaff);
			}
		} catch (CaaersSystemException e) {
			xmlResearchStaff = new LocalResearchStaff();
			xmlResearchStaff.setAddress(new Address());
			xmlResearchStaff.setNciIdentifier(researchStaffType.getNciIdentifier());
			xmlResearchStaff.setFirstName(researchStaffType.getFirstName());
			xmlResearchStaff.setLastName(researchStaffType.getLastName());
        	researchStaffImportOutcome = new DomainObjectImportOutcome<ResearchStaff>();
        	researchStaffImportOutcome.setImportedDomainObject(xmlResearchStaff);
        	researchStaffImportOutcome.addErrorMessage(e.getMessage(), Severity.ERROR);
		}
    	return researchStaffImportOutcome;
    }
    
	private List<Organization> checkAuthorizedOrganizations (ResearchStaffType researchStaffType) {
		List<SiteResearchStaffType> siteResearchStaffTypeList = researchStaffType.getSiteResearchStaffs().getSiteResearchStaff();
		String nciIntituteCode = "";
		List<Organization> orgs = new ArrayList<Organization>();
		for(SiteResearchStaffType siteResearchStaffType : siteResearchStaffTypeList){
			nciIntituteCode = siteResearchStaffType.getOrganizationRef().getNciInstituteCode();
			orgs.addAll(getAuthorizedOrganizationsByNameOrNciId(null, nciIntituteCode));
		}
		return orgs;
	}
	
    public CaaersServiceResponse saveResearchStaff(Staff staff) {
    	List<ResearchStaffType> researchStaffList = staff.getResearchStaff();
    	ResearchStaff xmlResearchStaff = null;
    	ResearchStaff dbResearchStaff = null;
    	
    	List<WsError> wsErrors = new ArrayList<WsError>();
    	CaaersServiceResponse caaersServiceResponse = new CaaersServiceResponse();
    	ServiceResponse serviceResponse = new ServiceResponse();
    	serviceResponse.setStatus(Status.FAILED_TO_PROCESS);
    	
    	DomainObjectImportOutcome<ResearchStaff> researchStaffImportOutcome = new DomainObjectImportOutcome<ResearchStaff>();
    	
    	for (ResearchStaffType researchStaffType:researchStaffList) {
    		if (checkAuthorizedOrganizations(researchStaffType).size() == 0){
    			WsError err = new WsError();
    			err.setErrorDesc("Failed to process ResearchStaff ::: nciIdentifier : "+researchStaffType.getNciIdentifier() + " ::: firstName : "+researchStaffType.getFirstName()+ " ::: lastName : "+researchStaffType.getLastName()) ;
    			err.setException("User not authorized on this Organization : " + researchStaffType.getNciIdentifier());
    			wsErrors.add(err);
    		}
    		
    		try {
    			xmlResearchStaff = buildResearchStaff(researchStaffType);
    			String email = researchStaffType.getEmailAddress();
                String loginId = researchStaffType.getLoginId();
                if (StringUtils.isEmpty(loginId)) {
              	  loginId = email;
                }
                dbResearchStaff = fetchResearchStaff(loginId);
    			if(dbResearchStaff == null){
    				if(wsErrors.size() == 0){
    					saveResearchStaff(xmlResearchStaff);
    				}
    			}else{
    				if(wsErrors.size() == 0){
                        syncPersonDetails(dbResearchStaff, xmlResearchStaff);
                        syncUserDetails(dbResearchStaff, xmlResearchStaff);
        				saveResearchStaff(dbResearchStaff);
    				}
    			}
    		} catch (CaaersSystemException e) {
    			xmlResearchStaff = new LocalResearchStaff();
    			xmlResearchStaff.setAddress(new Address());
    			xmlResearchStaff.setNciIdentifier(researchStaffType.getNciIdentifier());
    			xmlResearchStaff.setFirstName(researchStaffType.getFirstName());
    			xmlResearchStaff.setLastName(researchStaffType.getLastName());
            	researchStaffImportOutcome.setImportedDomainObject(xmlResearchStaff);
            	researchStaffImportOutcome.addErrorMessage(e.getMessage(), Severity.ERROR);
 
    			WsError err = new WsError();
    			err.setErrorDesc("Failed to process ResearchStaff ::: nciIdentifier : "+researchStaffType.getNciIdentifier() + " ::: firstName : "+researchStaffType.getFirstName()+ " ::: lastName : "+researchStaffType.getLastName()) ;
    			err.setException(e.getMessage());
    			wsErrors.add(err);           	
    		}
    	}
    	serviceResponse.setWsError(wsErrors);
    	if (wsErrors.size() == 0) {
    		serviceResponse.setStatus(Status.PROCESSED);
    	}
    	caaersServiceResponse.setServiceResponse(serviceResponse);
    	return caaersServiceResponse;
    }

    /**
     * Will use the email address as the loginId if loginId is empty. 
     * @param researchStaffDto
     * @return
     * @throws CaaersSystemException
     */
    private ResearchStaff buildResearchStaff(ResearchStaffType researchStaffDto) throws CaaersSystemException {
    	  try {
              logger.info("Begining of ResearchStaffMigrator : buildResearchStaff");
               
              String loginId = researchStaffDto.getLoginId();
              if (StringUtils.isEmpty(loginId)) {
            	  loginId = researchStaffDto.getEmailAddress();
              }
              //populate the user details
                final _User user = new _User();
                user.setLoginName(loginId);
                user.setFirstName(researchStaffDto.getFirstName());
                user.setLastName(researchStaffDto.getLastName());
                user.setMiddleName(researchStaffDto.getMiddleName());
                user.setPhoneNumber(researchStaffDto.getPhoneNumber());
                user.setFaxNumber(researchStaffDto.getFaxNumber());
                user.setEmailAddress(researchStaffDto.getEmailAddress());
              
              	final ResearchStaff researchStaff = new LocalResearchStaff();
                researchStaff.setCaaersUser(user);

                researchStaff.setCaaersUser(user);
              	researchStaff.setAddress(new Address());
              	researchStaff.setNciIdentifier(researchStaffDto.getNciIdentifier());
                researchStaff.setFirstName(researchStaffDto.getFirstName());
                researchStaff.setLastName(researchStaffDto.getLastName());
                researchStaff.setMiddleName(researchStaffDto.getMiddleName());              
                researchStaff.setFaxNumber(researchStaffDto.getFaxNumber());
                researchStaff.setPhoneNumber(researchStaffDto.getPhoneNumber());
                researchStaff.setEmailAddress(researchStaffDto.getEmailAddress());
                Address researchStaffAddress = new Address();
                researchStaffAddress.setStreet(researchStaffDto.getStreet());
                researchStaffAddress.setCity(researchStaffDto.getCity());
                researchStaffAddress.setState(researchStaffDto.getState());
                if(researchStaffDto.getZip() != null & !StringUtils.isEmpty(researchStaffDto.getZip())){
              	 researchStaffAddress.setZip(researchStaffDto.getZip());
                }
                researchStaff.setAddress(researchStaffAddress);
                
                List<SiteResearchStaffType> siteRsTypeList= researchStaffDto.getSiteResearchStaffs().getSiteResearchStaff();
                Address siteResearchStaffAddress = null;
                List<SiteResearchStaffRoleType> srsRoleTypes = null;
                for (SiteResearchStaffType siteResearchStaffType : siteRsTypeList) {
	              	SiteResearchStaff siteResearchStaff = new SiteResearchStaff();
	              	siteResearchStaff.setAssociateAllStudies(siteResearchStaffType.isAssociateAllStudies());
	              	siteResearchStaff.setEmailAddress(siteResearchStaffType.getEmailAddress());
	              	siteResearchStaff.setPhoneNumber(siteResearchStaffType.getPhoneNumber());
	              	siteResearchStaff.setFaxNumber(siteResearchStaffType.getFaxNumber());
	              	siteResearchStaffAddress = new Address();
	              	siteResearchStaffAddress.setStreet(siteResearchStaffType.getStreet());
	              	siteResearchStaffAddress.setCity(siteResearchStaffType.getCity());
	              	siteResearchStaffAddress.setState(siteResearchStaffType.getState());
	              	if(siteResearchStaffType.getZip() != null & !StringUtils.isEmpty(siteResearchStaffType.getZip())){
	              		siteResearchStaffAddress.setZip(siteResearchStaffType.getZip());
	              	}
	              	siteResearchStaff.setAddress(siteResearchStaffAddress);
	                	
	              	Organization org = fetchOrganization(siteResearchStaffType.getOrganizationRef().getNciInstituteCode());
	                siteResearchStaff.setOrganization(org);
	                siteResearchStaff.setResearchStaff(researchStaff);
	                researchStaff.addSiteResearchStaff(siteResearchStaff);

                    srsRoleTypes = 	siteResearchStaffType.getSiteResearchStaffRoles().getSiteResearchStaffRole();
	                for(SiteResearchStaffRoleType roleType : srsRoleTypes){
                       UserGroupType role = UserGroupType.valueOf(roleType.getRole().value());
                       RoleMembership roleMembership = user.findRoleMembership(role);
                       roleMembership.addOrganizationNCICode(org.getNciInstituteCode());
                       roleMembership.setAllStudy(siteResearchStaffType.isAssociateAllStudies());
                    }


                }              	
              
              
              return researchStaff;

          } catch (Exception e) {
              logger.error("Error while creating research staff", e);
              throw new CaaersSystemException(e.getMessage(), e);
          }	  	
    	
    }

    //will synchronize the DB fetched ResearchStaff with the XML supplied researchstaff
    private void syncPersonDetails(ResearchStaff dbRS, ResearchStaff xmlRS){
        dbRS.sync(xmlRS);

    }

    //will synchronize the user dtails of the DB fetched ResearchStaff with XML fetched ResearchStaff
    private void syncUserDetails(ResearchStaff dbRS, ResearchStaff xmlRS){
        if(dbRS.getCaaersUser() == null){
            dbRS.setCaaersUser(xmlRS.getCaaersUser());
        }else{
            dbRS.getCaaersUser().sync(xmlRS.getCaaersUser());
        }
    }

    //will save and provision the user details and save the person details.  
	private void saveResearchStaff(ResearchStaff researchStaff) throws CaaersSystemException {

        try {
            logger.info("Begining of ResearchStaffMigrator : saveResearchStaff");
            userRepository.createOrUpdateUser(researchStaff.getCaaersUser(), getChangePasswordUrl());
            userRepository.provisionUser(researchStaff.getCaaersUser());
            personRepository.save(researchStaff);

            logger.info("Created the research staff :" + researchStaff.getId());
            logger.info("End of ResearchStaffMigrator : saveResearchStaff");

        } catch (Exception e) {
            logger.error("Error while creating research staff", e);
            throw new CaaersSystemException("Unable to create research staff : "+ e.getMessage(), e);
        }	
        
	}


    private String getChangePasswordUrl(){
		String caAERSBaseUrl = Configuration.LAST_LOADED_CONFIGURATION.get(Configuration.CAAERS_BASE_URL);
		if(StringUtils.isEmpty(caAERSBaseUrl)){
			caAERSBaseUrl = "https://localhost:8443/caaers";
			logger.debug("CAAERS_BASE_URL is not configured, hence setting it to be running on localhost");
		}
        StringBuilder changePasswordUrl = new StringBuilder(caAERSBaseUrl);
        changePasswordUrl.append("/public/user/changePassword?");

        return changePasswordUrl.toString();
	}

	
	//CONFIGURATION

    public PersonRepository getPersonRepository() {
        return personRepository;
    }

    public void setPersonRepository(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}