package gov.nih.nci.cabig.caaers.api.impl;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.api.InvestigatorMigratorService;
import gov.nih.nci.cabig.caaers.dao.InvestigatorDao;
import gov.nih.nci.cabig.caaers.dao.query.InvestigatorQuery;
import gov.nih.nci.cabig.caaers.domain.Investigator;
import gov.nih.nci.cabig.caaers.domain.LocalInvestigator;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.SiteInvestigator;
import gov.nih.nci.cabig.caaers.domain.repository.InvestigatorRepository;
import gov.nih.nci.cabig.caaers.integration.schema.common.CaaersServiceResponse;
import gov.nih.nci.cabig.caaers.integration.schema.common.ServiceResponse;
import gov.nih.nci.cabig.caaers.integration.schema.common.Status;
import gov.nih.nci.cabig.caaers.integration.schema.common.WsError;
import gov.nih.nci.cabig.caaers.integration.schema.investigator.InvestigatorType;
import gov.nih.nci.cabig.caaers.integration.schema.investigator.SiteInvestigatorType;
import gov.nih.nci.cabig.caaers.integration.schema.investigator.Staff;
import gov.nih.nci.cabig.caaers.integration.schema.researchstaff.ResearchStaffType;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome.Severity;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Required;

@WebService(endpointInterface="gov.nih.nci.cabig.caaers.api.InvestigatorMigratorService", serviceName="InvestigatorMigratorService")
@SOAPBinding(parameterStyle=SOAPBinding.ParameterStyle.BARE)
public class DefaultInvestigatorMigratorService extends DefaultMigratorService implements InvestigatorMigratorService {
	private static final Log logger = LogFactory.getLog(DefaultInvestigatorMigratorService.class);
	
	private InvestigatorDao investigatorDao;
	private InvestigatorRepository investigatorRepository;
	private List<DomainObjectImportOutcome<Investigator>> importableInvestigators = new ArrayList<DomainObjectImportOutcome<Investigator>>();
	private List<DomainObjectImportOutcome<Investigator>> nonImportableInvestigators = new ArrayList<DomainObjectImportOutcome<Investigator>>();
	

	/**
     * Fetches the research staff from the DB
     * 
     * @param nciCode
     * @return
     */
	Investigator fetchInvestigator(String loginId) {
    	InvestigatorQuery invQuery = new InvestigatorQuery();
        if (StringUtils.isNotEmpty(loginId)) {
        	invQuery.filterByExactLoginId(loginId);
        }
        List<Investigator> rsList = investigatorRepository.searchInvestigator(invQuery);
        
        if (rsList == null || rsList.isEmpty()) {
            return null;
        }
        return rsList.get(0);
    }

	public DomainObjectImportOutcome<Investigator> processInvestigator(InvestigatorType xmlInvestigator){
    	DomainObjectImportOutcome<Investigator> investigatorImportOutcome = null;
    	Investigator investigator = null;
		try {
			investigator = buildInvestigator(xmlInvestigator);
			investigatorImportOutcome = new DomainObjectImportOutcome<Investigator>();
			investigatorImportOutcome.setImportedDomainObject(investigator);
		} catch (CaaersSystemException e) {
			investigator = new LocalInvestigator();
			investigator.setNciIdentifier(xmlInvestigator.getNciIdentifier());
			investigator.setFirstName(xmlInvestigator.getFirstName());
			investigator.setLastName(xmlInvestigator.getLastName());
			investigatorImportOutcome = new DomainObjectImportOutcome<Investigator>();
			investigatorImportOutcome.setImportedDomainObject(investigator);
			investigatorImportOutcome.addErrorMessage(e.getMessage(), Severity.ERROR);
		}
    	return investigatorImportOutcome;
	}
	private String checkAuthorizedOrganizations (InvestigatorType investigatorType) {
		for (SiteInvestigatorType si:investigatorType.getSiteInvestigator()){
			String nciIntituteCode = si.getOrganizationRef().getNciInstituteCode();
			List<Organization> orgs = getAuthorizedOrganizationsByNameOrNciId(null, nciIntituteCode);
			if (orgs.size() == 0 ) {
				return nciIntituteCode;
			}
		}
		return "ALL_ORGS_AUTH";
	}
	
    public CaaersServiceResponse saveInvestigator(Staff staff) {//throws RemoteException {
    	List<InvestigatorType> investigatorTypeList = staff.getInvestigator();
    	Investigator investigator = null;
    	getImportableInvestigators().clear();
    	getNonImportableInvestigators().clear();
    	
    	List<WsError> wsErrors = new ArrayList<WsError>();
    	CaaersServiceResponse caaersServiceresponse = new CaaersServiceResponse();
    	ServiceResponse serviceResponse = new ServiceResponse();
    	serviceResponse.setStatus(Status.FAILED_TO_PROCESS);
    	
    	for (InvestigatorType investigatorType:investigatorTypeList) {
    		String orgCheck = checkAuthorizedOrganizations(investigatorType);
    		if (!orgCheck.equals("ALL_ORGS_AUTH")){
    			WsError err = new WsError();
       			err.setErrorDesc("Failed to process Investigator ::: nciIdentifier : "+investigatorType.getNciIdentifier() + " ::: firstName : "+investigatorType.getFirstName()+ " ::: lastName : "+investigatorType.getLastName()) ;
       			err.setException("User not authorized on this Organization : " + orgCheck);
    			wsErrors.add(err);
    		}
     		try {
    			investigator = buildInvestigator(investigatorType);
    			saveInvestigator(investigator);
    			DomainObjectImportOutcome<Investigator> investigatorImportOutcome = new DomainObjectImportOutcome<Investigator>();
    			investigatorImportOutcome.setImportedDomainObject(investigator);
    			addImportableInvestigators(investigatorImportOutcome);
    		} catch (CaaersSystemException e) {
    			investigator = new LocalInvestigator();
            	investigator.setNciIdentifier(investigatorType.getNciIdentifier());
            	investigator.setFirstName(investigatorType.getFirstName());
            	investigator.setLastName(investigatorType.getLastName());
            	DomainObjectImportOutcome<Investigator> investigatorImportOutcome = new DomainObjectImportOutcome<Investigator>();
    			investigatorImportOutcome.setImportedDomainObject(investigator);
    			investigatorImportOutcome.addErrorMessage(e.getMessage(), Severity.ERROR);
    			addNonImportableInvestigators(investigatorImportOutcome);
    			
    			WsError err = new WsError();
    			err.setErrorDesc("Failed to process Investigator ::: nciIdentifier : "+investigatorType.getNciIdentifier() + " ::: firstName : "+investigatorType.getFirstName()+ " ::: lastName : "+investigatorType.getLastName()) ;
    			err.setException(e.getMessage());
    			wsErrors.add(err);
    			//throw new RemoteException("Unable to import investigator", e);
    		}
    	}
    	serviceResponse.setWsError(wsErrors);
    	if (wsErrors.size() == 0) {
    		serviceResponse.setStatus(Status.PROCESSED);
    	}
    	caaersServiceresponse.setServiceResponse(serviceResponse);
    	return caaersServiceresponse;
    }
    
    private Investigator buildInvestigator(InvestigatorType investigatorDto) {
		try {
            logger.info("Begining of DefaultInvestigatorMigratorService : buildInvestigator");
             
            String nciIdentifier = investigatorDto.getNciIdentifier();
            String email = investigatorDto.getEmailAddress();
            String loginId = investigatorDto.getLoginId();
            
             if (StringUtils.isEmpty(loginId)) {
          	  loginId = email;
            }
            Investigator investigator = null;

            if (StringUtils.isEmpty(nciIdentifier)){
            	investigator = new LocalInvestigator();
            } else {
            	investigator = fetchInvestigator(loginId);
                if (investigator == null ) {
                	// build new 
                	investigator = new LocalInvestigator();
                	investigator.setNciIdentifier(nciIdentifier);

                }
            }
            investigator.setLoginId(loginId);
            investigator.setFirstName(investigatorDto.getFirstName());
            investigator.setLastName(investigatorDto.getLastName());
            investigator.setMiddleName(investigatorDto.getMiddleName());
            investigator.setEmailAddress(investigatorDto.getEmailAddress());
            investigator.setFaxNumber(investigatorDto.getFaxNumber());
            investigator.setPhoneNumber(investigatorDto.getPhoneNumber());
            //get site investigaor
            
            List<SiteInvestigatorType> siteInvTypeList= investigatorDto.getSiteInvestigator();
            List<SiteInvestigator> siteInvList = new ArrayList<SiteInvestigator>();
            for (SiteInvestigatorType siteInvestigatorType : siteInvTypeList) {
            	// create site investigator and make the list 
            	SiteInvestigator siteInvestigator = new SiteInvestigator();
            	siteInvestigator.setEmailAddress(siteInvestigatorType.getEmailAddress());
            	
            	Organization org = fetchOrganization(siteInvestigatorType.getOrganizationRef().getNciInstituteCode());
            	siteInvestigator.setOrganization(org);
            	siteInvestigator.setInvestigator(investigator);
            	// ????? siteInvestigator.setStatusDate(siteInvestigatorType.ggetStatusDate());
            	siteInvList.add(siteInvestigator);
            }
            investigator.getSiteInvestigatorsInternal().clear();
            investigator.getSiteInvestigators().addAll(siteInvList);
            
            return investigator;

        } catch (Exception e) {
            logger.error("Error while building investigator", e);
            throw new CaaersSystemException(e.getMessage(), e);
        }   	
    }
	private void saveInvestigator(Investigator investigator) throws CaaersSystemException {
		try {
            logger.info("Begining of DefaultInvestigatorMigratorService : saveInvestigator");
            investigatorRepository.save(investigator,"URL");

            logger.info("Created the Investigator  :" + investigator.getId());
            logger.info("End of DefaultInvestigatorMigratorService : saveInvestigator");
            
         } catch (Exception e) {
            logger.error("Error while creating investigator", e);
            throw new CaaersSystemException("Unable to create investigator", e);
        }	
	}
	
	//CONFIGURATION
	@Required
	public InvestigatorDao getInvestigatorDao() {
		return investigatorDao;
	}
	
	@Required
	public void setInvestigatorDao(InvestigatorDao investigatorDao) {
		this.investigatorDao = investigatorDao;
	}

	

	public List<DomainObjectImportOutcome<Investigator>> getImportableInvestigators() {
		return importableInvestigators;
	}

	public List<DomainObjectImportOutcome<Investigator>> getNonImportableInvestigators() {
		return nonImportableInvestigators;
	}
	
	private void addImportableInvestigators(DomainObjectImportOutcome<Investigator> domainObjectImportInvestigator) {
			this.getImportableInvestigators().add(domainObjectImportInvestigator);
	}
	
	private void addNonImportableInvestigators(DomainObjectImportOutcome<Investigator> domainObjectImportInvestigator) {
		this.getNonImportableInvestigators().add(domainObjectImportInvestigator);
	}

	public InvestigatorRepository getInvestigatorRepository() {
		return investigatorRepository;
	}
	
	@Required
	public void setInvestigatorRepository(
			InvestigatorRepository investigatorRepository) {
		this.investigatorRepository = investigatorRepository;
	}
}
