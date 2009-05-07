package gov.nih.nci.cabig.caaers.api.impl;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.api.ResearchStaffMigratorService;
import gov.nih.nci.cabig.caaers.dao.ResearchStaffDao;
import gov.nih.nci.cabig.caaers.dao.query.ResearchStaffQuery;
import gov.nih.nci.cabig.caaers.domain.LocalResearchStaff;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.caaers.domain.UserGroupType;
import gov.nih.nci.cabig.caaers.domain.repository.ResearchStaffRepository;
import gov.nih.nci.cabig.caaers.integration.schema.common.CaaersServiceResponse;
import gov.nih.nci.cabig.caaers.integration.schema.common.OrganizationRefType;
import gov.nih.nci.cabig.caaers.integration.schema.common.ServiceResponse;
import gov.nih.nci.cabig.caaers.integration.schema.common.Status;
import gov.nih.nci.cabig.caaers.integration.schema.common.WsError;
import gov.nih.nci.cabig.caaers.integration.schema.researchstaff.ResearchStaffType;
import gov.nih.nci.cabig.caaers.integration.schema.researchstaff.Role;
import gov.nih.nci.cabig.caaers.integration.schema.researchstaff.Staff;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome.Severity;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

@WebService(endpointInterface="gov.nih.nci.cabig.caaers.api.ResearchStaffMigratorService", serviceName="ResearchStaffMigratorService")
@SOAPBinding(parameterStyle=SOAPBinding.ParameterStyle.BARE)
public class DefaultResearchStaffMigratorService extends DefaultMigratorService implements
		ResearchStaffMigratorService,ApplicationContextAware {
	
	private static final Log logger = LogFactory.getLog(DefaultResearchStaffMigratorService.class);
	private ResearchStaffDao researchStaffDao;
	private ApplicationContext applicationContext;
	protected ResearchStaffRepository researchStaffRepository;
	
	
	/**
     * Fetches the research staff from the DB
     * 
     * @param nciCode
     * @return
     */
    ResearchStaff fetchResearchStaff(String loginId) {//String nciIdentifier) {
    	ResearchStaffQuery rsQuery = new ResearchStaffQuery();
        if (StringUtils.isNotEmpty(loginId)) {
        	rsQuery.filterByLoginId(loginId);
        	//rsQuery.filterByEmailAddress(email);
        }
        List<ResearchStaff> rsList = researchStaffRepository.searchResearchStaff(rsQuery);
        
        if (rsList == null || rsList.isEmpty()) {
            return null;
        }
        return rsList.get(0);
    }
    
    
    public DomainObjectImportOutcome<ResearchStaff> processResearchStaff(ResearchStaffType xmlResearchStaff){
    	
    	DomainObjectImportOutcome<ResearchStaff> researchStaffImportOutcome = null;
    	ResearchStaff researchStaff = null;
		try {
			researchStaff = buildResearchStaff(xmlResearchStaff);
			researchStaffImportOutcome = new DomainObjectImportOutcome<ResearchStaff>();
			researchStaffImportOutcome.setImportedDomainObject(researchStaff);
		} catch (CaaersSystemException e) {
			researchStaff = new LocalResearchStaff();
			researchStaff.setNciIdentifier(xmlResearchStaff.getNciIdentifier());
			researchStaff.setFirstName(xmlResearchStaff.getFirstName());
			researchStaff.setLastName(xmlResearchStaff.getLastName());
        	researchStaffImportOutcome = new DomainObjectImportOutcome<ResearchStaff>();
        	researchStaffImportOutcome.setImportedDomainObject(researchStaff);
        	researchStaffImportOutcome.addErrorMessage(e.getMessage(), Severity.ERROR);
		}
    	return researchStaffImportOutcome;
    }
    
    
    public CaaersServiceResponse saveResearchStaff(Staff staff) {
    	List<ResearchStaffType> researchStaffList = staff.getResearchStaff();
    	ResearchStaff researchStaff = null;//buildInvestigator(investigatorType);
    	
    	List<WsError> wsErrors = new ArrayList<WsError>();
    	CaaersServiceResponse caaersServiceResponse = new CaaersServiceResponse();
    	ServiceResponse serviceResponse = new ServiceResponse();
    	serviceResponse.setStatus(Status.FAILED_TO_PROCESS);
    	
    	for (ResearchStaffType researchStaffType:researchStaffList) {

    		try {
    			researchStaff = buildResearchStaff(researchStaffType);
    			saveResearchStaff(researchStaff);
    			DomainObjectImportOutcome<ResearchStaff> researchStaffImportOutcome = new DomainObjectImportOutcome<ResearchStaff>();
    			researchStaffImportOutcome.setImportedDomainObject(researchStaff);
    		} catch (CaaersSystemException e) {
    			researchStaff = new LocalResearchStaff();
    			researchStaff.setNciIdentifier(researchStaffType.getNciIdentifier());
    			researchStaff.setFirstName(researchStaffType.getFirstName());
    			researchStaff.setLastName(researchStaffType.getLastName());
            	DomainObjectImportOutcome<ResearchStaff> researchStaffImportOutcome = new DomainObjectImportOutcome<ResearchStaff>();
            	researchStaffImportOutcome.setImportedDomainObject(researchStaff);
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
    
    public ResearchStaff buildResearchStaff(ResearchStaffType researchStaffDto) throws CaaersSystemException {
    	  try {
              logger.info("Begining of ResearchStaffMigrator : buildResearchStaff");
               
              String nciIdentifier = researchStaffDto.getNciIdentifier();
              String email = researchStaffDto.getEmailAddress();
              String loginId = researchStaffDto.getLoginId();
              if (StringUtils.isEmpty(loginId)) {
            	  loginId = email;
              }
              ResearchStaff researchStaff = fetchResearchStaff(loginId);
              if (researchStaff == null ) {
              	// build new 
              	researchStaff = new LocalResearchStaff();
              	researchStaff.setNciIdentifier(nciIdentifier); 
              	if (StringUtils.isEmpty(loginId)) {
              		researchStaff.setLoginId(researchStaffDto.getEmailAddress());
              	} else {
              		researchStaff.setLoginId(loginId);
              	}              	
              } 
              researchStaff.setFirstName(researchStaffDto.getFirstName());
              researchStaff.setLastName(researchStaffDto.getLastName());
              researchStaff.setMiddleName(researchStaffDto.getMiddleName());              
              researchStaff.setFaxNumber(researchStaffDto.getFaxNumber());
              researchStaff.setPhoneNumber(researchStaffDto.getPhoneNumber());
              researchStaff.setEmailAddress(researchStaffDto.getEmailAddress());
              researchStaff.getUserGroupTypes().clear();
              
              List<Role> roles = researchStaffDto.getRole();
              
              for (Role role:roles) {
            	  researchStaff.addUserGroupType(UserGroupType.valueOf(role.value()));
              }
              
              //get Organizations 
              OrganizationRefType organizationRef = researchStaffDto.getOrganizationRef();
              String nciInstituteCode = organizationRef.getNciInstituteCode();
              Organization organization = fetchOrganization(nciInstituteCode);
              researchStaff.setOrganization(organization);
              
              return researchStaff;

          } catch (Exception e) {
              logger.error("Error while creating research staff", e);
              throw new CaaersSystemException(e.getMessage(), e);
          }	  	
    	
    }
	public void saveResearchStaff(ResearchStaff researchStaff) throws CaaersSystemException {

        try {
            logger.info("Begining of ResearchStaffMigrator : saveResearchStaff");             
            
            //save 
            researchStaffRepository.save(researchStaff,"URL");
            logger.info("Created the research staff :" + researchStaff.getId());
            logger.info("End of ResearchStaffMigrator : saveResearchStaff");

        } catch (Exception e) {
            logger.error("Error while creating research staff", e);
            throw new CaaersSystemException("Unable to create research staff : "+ e.getMessage(), e);
        }	
        
	}
	
	//CONFIGURATION

    @Required
    public void setResearchStaffDao(ResearchStaffDao researchStaffDao) {
		this.researchStaffDao = researchStaffDao;
	}

    @Required
	public ResearchStaffDao getResearchStaffDao() {
		return researchStaffDao;
	}

	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
		
	}
	public void setResearchStaffRepository(
			ResearchStaffRepository researchStaffRepository) {
		this.researchStaffRepository = researchStaffRepository;
	}

}
