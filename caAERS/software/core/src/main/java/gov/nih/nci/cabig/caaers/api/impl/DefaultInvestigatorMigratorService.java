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
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome.Severity;
import gov.nih.nci.cabig.caaers.tools.configuration.Configuration;
import gov.nih.nci.cabig.caaers.utils.DateUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.BooleanUtils;
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

	/**
	 * This method processes an Investigator xml imported from UI
	 */
	public DomainObjectImportOutcome<Investigator> processInvestigator(InvestigatorType investigatorType){
    	DomainObjectImportOutcome<Investigator> investigatorImportOutcome = new DomainObjectImportOutcome<Investigator>();
    	Investigator xmlInvestigator = null;
    	Investigator dbInvestigator = null;
    	
		try {
			xmlInvestigator = buildInvestigator(investigatorType);
			String email = investigatorType.getEmailAddress();
            String loginId = investigatorType.getLoginId();
            if (StringUtils.isEmpty(loginId)) {
          	  loginId = email;
            }
            dbInvestigator = fetchInvestigator(loginId);
			if(dbInvestigator == null){
				validateInvestigator(xmlInvestigator,investigatorImportOutcome,null,false);
    			investigatorImportOutcome.setImportedDomainObject(xmlInvestigator);
			}else{
				validateInvestigator(xmlInvestigator,investigatorImportOutcome,null,true);
				syncInvestigator(xmlInvestigator,dbInvestigator);
    			investigatorImportOutcome.setImportedDomainObject(dbInvestigator);
			}
		} catch (CaaersSystemException e) {
			xmlInvestigator = new LocalInvestigator();
			xmlInvestigator.setNciIdentifier(investigatorType.getNciIdentifier());
			xmlInvestigator.setFirstName(investigatorType.getFirstName());
			xmlInvestigator.setLastName(investigatorType.getLastName());
			investigatorImportOutcome = new DomainObjectImportOutcome<Investigator>();
			investigatorImportOutcome.setImportedDomainObject(xmlInvestigator);
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
	
	/**
	 * This method creates or updates an Investigator (Called from WebService)
	 */
    public CaaersServiceResponse saveInvestigator(Staff staff) {//throws RemoteException {
    	List<InvestigatorType> investigatorTypeList = staff.getInvestigator();
    	
    	Investigator xmlInvestigator = null;
    	Investigator dbInvestigator = null;
    	
    	getImportableInvestigators().clear();
    	getNonImportableInvestigators().clear();
    	
    	List<WsError> wsErrors = new ArrayList<WsError>();
    	CaaersServiceResponse caaersServiceresponse = new CaaersServiceResponse();
    	ServiceResponse serviceResponse = new ServiceResponse();
    	serviceResponse.setStatus(Status.FAILED_TO_PROCESS);
    	DomainObjectImportOutcome<Investigator> investigatorImportOutcome = new DomainObjectImportOutcome<Investigator>();
    	
    	for (InvestigatorType investigatorType:investigatorTypeList) {
    		String orgCheck = checkAuthorizedOrganizations(investigatorType);
    		if (!orgCheck.equals("ALL_ORGS_AUTH")){
    			WsError err = new WsError();
       			err.setErrorDesc("Failed to process Investigator ::: nciIdentifier : "+investigatorType.getNciIdentifier() + " ::: firstName : "+investigatorType.getFirstName()+ " ::: lastName : "+investigatorType.getLastName()) ;
       			err.setException("User not authorized on this Organization : " + orgCheck);
    			wsErrors.add(err);
    		}
    		
    		try {
    			xmlInvestigator = buildInvestigator(investigatorType);
    			String email = investigatorType.getEmailAddress();
                String loginId = investigatorType.getLoginId();
                if (StringUtils.isEmpty(loginId)) {
              	  loginId = email;
                }
                dbInvestigator = fetchInvestigator(loginId);
    			if(dbInvestigator == null){
    				validateInvestigator(xmlInvestigator,investigatorImportOutcome,wsErrors,false);
    				if(wsErrors.size() == 0){
    					saveInvestigator(xmlInvestigator);
    				}
    			}else{
    				validateInvestigator(dbInvestigator,investigatorImportOutcome,wsErrors,true);
    				if(wsErrors.size() == 0){
        				syncInvestigator(xmlInvestigator,dbInvestigator);
        				saveInvestigator(dbInvestigator);
    				}
    			}
    		} catch (CaaersSystemException e) {
    			xmlInvestigator = new LocalInvestigator();
    			xmlInvestigator.setNciIdentifier(investigatorType.getNciIdentifier());
    			xmlInvestigator.setFirstName(investigatorType.getFirstName());
    			xmlInvestigator.setLastName(investigatorType.getLastName());
    			investigatorImportOutcome.setImportedDomainObject(xmlInvestigator);
    			investigatorImportOutcome.addErrorMessage(e.getMessage(), Severity.ERROR);
 
    			WsError err = new WsError();
    			err.setErrorDesc("Failed to process Investigator ::: nciIdentifier : "+investigatorType.getNciIdentifier() + " ::: firstName : "+investigatorType.getFirstName()+ " ::: lastName : "+investigatorType.getLastName()) ;
    			err.setException(e.getMessage());
    			wsErrors.add(err);           	
    		}
    	}
    	serviceResponse.setWsError(wsErrors);
    	if (wsErrors.size() == 0) {
    		serviceResponse.setStatus(Status.PROCESSED);
    	}
    	caaersServiceresponse.setServiceResponse(serviceResponse);
    	return caaersServiceresponse;
    }
    
    /**
     * This method creates a new Investigator object and populates values from the XML.
     * @param investigatorDto
     * @return
     */
    private Investigator buildInvestigator(InvestigatorType investigatorDto) {
		try {
            logger.info("Begining of DefaultInvestigatorMigratorService : buildInvestigator");
             
            String nciIdentifier = investigatorDto.getNciIdentifier();
            String email = investigatorDto.getEmailAddress();
            String loginId = investigatorDto.getLoginId();
            if (StringUtils.isEmpty(loginId)) {
          	  loginId = email;
            }
        	// build new 
        	Investigator investigator = new LocalInvestigator();
        	investigator.setNciIdentifier(nciIdentifier);
            investigator.setLoginId(loginId);
            investigator.setFirstName(investigatorDto.getFirstName());
            investigator.setLastName(investigatorDto.getLastName());
            investigator.setMiddleName(investigatorDto.getMiddleName());
            investigator.setEmailAddress(investigatorDto.getEmailAddress());
            investigator.setFaxNumber(investigatorDto.getFaxNumber());
            investigator.setPhoneNumber(investigatorDto.getPhoneNumber());
            investigator.setAllowedToLogin(investigatorDto.isAllowedToLogin());
            
            //get site investigaor
            List<SiteInvestigatorType> siteInvTypeList= investigatorDto.getSiteInvestigator();
            SiteInvestigator siteInvestigator  = null;
            for (SiteInvestigatorType siteInvestigatorType : siteInvTypeList) {
            	// create site investigator and make the list 
            	siteInvestigator = new SiteInvestigator();
            	siteInvestigator.setEmailAddress(siteInvestigatorType.getEmailAddress());
            	siteInvestigator.setStartDate(siteInvestigatorType.getStartDate().toGregorianCalendar().getTime());
            	if(siteInvestigatorType.getEndDate() != null){
            		siteInvestigator.setEndDate(siteInvestigatorType.getEndDate().toGregorianCalendar().getTime());
            	}
            	Organization org = fetchOrganization(siteInvestigatorType.getOrganizationRef().getNciInstituteCode());
            	siteInvestigator.setOrganization(org);
            	investigator.addSiteInvestigator(siteInvestigator);
            }
            return investigator;
        } catch (Exception e) {
            logger.error("Error while building investigator", e);
            throw new CaaersSystemException(e.getMessage(), e);
        }   	
    }
	private void saveInvestigator(Investigator investigator) throws CaaersSystemException {
		try {
            logger.info("Begining of DefaultInvestigatorMigratorService : saveInvestigator");
            
            String caAERSBaseUrl = Configuration.LAST_LOADED_CONFIGURATION.get(Configuration.CAAERS_BASE_URL);
            StringBuilder changePasswordUrl = new StringBuilder();
            if(StringUtils.isNotEmpty(caAERSBaseUrl)){
                changePasswordUrl.append(caAERSBaseUrl);
                changePasswordUrl.append("/public/user/changePassword?");
            }else{
            	logger.info("caAERS base URL is not set");
            }
            
            investigatorRepository.save(investigator,changePasswordUrl.toString());

            logger.info("Created the Investigator  :" + investigator.getId());
            logger.info("End of DefaultInvestigatorMigratorService : saveInvestigator");
            
         } catch (Exception e) {
            logger.error("Error while creating investigator", e);
            throw new CaaersSystemException("Unable to create investigator", e);
        }	
	}
	
	/**
	 * This method updates the values of Investigator. It will update the existing Investigators values with the values from the Xml.
	 * @param xmlInvestigator
	 * @param dbInvestigator
	 */
	private void syncInvestigator(Investigator xmlInvestigator, Investigator dbInvestigator){
		//do the basic property sync
		dbInvestigator.setFirstName(xmlInvestigator.getFirstName());
		dbInvestigator.setMiddleName(xmlInvestigator.getMiddleName());
		dbInvestigator.setLastName(xmlInvestigator.getLastName());
		dbInvestigator.setEmailAddress(xmlInvestigator.getEmailAddress());
		dbInvestigator.setPhoneNumber(xmlInvestigator.getPhoneNumber());
		dbInvestigator.setFaxNumber(xmlInvestigator.getFaxNumber());
		dbInvestigator.setNciIdentifier(xmlInvestigator.getNciIdentifier());
		if(BooleanUtils.isFalse(dbInvestigator.getAllowedToLogin())){
			dbInvestigator.setAllowedToLogin(xmlInvestigator.getAllowedToLogin());
		}
		//do the site research staff sync
		if(CollectionUtils.isEmpty(xmlInvestigator.getSiteInvestigators())) return;  //nothing provided in xml input
		
		List<SiteInvestigator> newSiteInvestigators = new ArrayList<SiteInvestigator>();
		for(SiteInvestigator xmlSiteInvestigator : xmlInvestigator.getSiteInvestigators()){
			SiteInvestigator existing = dbInvestigator.findSiteInvestigator(xmlSiteInvestigator);
			if(existing != null){
				existing.setStartDate(xmlSiteInvestigator.getStartDate());
				existing.setEndDate(xmlSiteInvestigator.getEndDate());
			}else {
				newSiteInvestigators.add(xmlSiteInvestigator);
			}
		}
		//add the items in new
		for(SiteInvestigator si : newSiteInvestigators){
			dbInvestigator.addSiteInvestigator(si);
		}
	}
	
	/**
	 * This method validates the Investigator values. 
	 * @param investigator
	 * @param investigatorImportOutcome
	 * @param wsErrors
	 * @param isExisting
	 * @throws CaaersSystemException
	 */
	private void validateInvestigator(Investigator investigator,DomainObjectImportOutcome<Investigator> investigatorImportOutcome, List<WsError> wsErrors, boolean isExisting) throws CaaersSystemException{
        List<SiteInvestigator> investigators = investigator.getSiteInvestigators();
        Date now = new Date();
        WsError err = null;
        for (int i = 0; i < investigators.size(); i++) {
            SiteInvestigator siteInvestigator = investigators.get(i);
            
            if(siteInvestigator.getId() == null & !isExisting){
            	//startdate cannot be less than today's date
                if(siteInvestigator.getStartDate() != null){
                	if(DateUtils.compareDate(siteInvestigator.getStartDate(),now) < 0){
                		investigatorImportOutcome.addErrorMessage("Start date cannot be before today's date, at " +siteInvestigator.getOrganization().getNciInstituteCode(), Severity.ERROR);
                		if(wsErrors != null){
                			err = new WsError();
                			err.setErrorDesc("Start date cannot be before today's date, at " +siteInvestigator.getOrganization().getNciInstituteCode());
                			wsErrors.add(err);
                		}                		
                	}
                }
            }
            
            if(siteInvestigator.getEndDate() != null){
            	if(DateUtils.compareDate(siteInvestigator.getEndDate(),now) < 0){
            		investigatorImportOutcome.addErrorMessage("End date cannot be before today's date at " +siteInvestigator.getOrganization().getNciInstituteCode(), Severity.ERROR);
            		if(wsErrors != null){
            			err = new WsError();
            			err.setErrorDesc("End date cannot be before today's date at " +siteInvestigator.getOrganization().getNciInstituteCode());
            			wsErrors.add(err);
            		}            		
                }
            }
            if(siteInvestigator.getStartDate() != null && siteInvestigator.getEndDate() != null){
            	if(DateUtils.compareDate(siteInvestigator.getEndDate(), siteInvestigator.getStartDate()) < 0){
            		investigatorImportOutcome.addErrorMessage("End date cannot be before Start date at " +siteInvestigator.getOrganization().getNciInstituteCode(), Severity.ERROR);
            		if(wsErrors != null){
            			err = new WsError();
            			err.setErrorDesc("End date cannot be before Start date at " +siteInvestigator.getOrganization().getNciInstituteCode());
            			wsErrors.add(err);
            		}
            	}
            }
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
	
	public InvestigatorRepository getInvestigatorRepository() {
		return investigatorRepository;
	}
	
	@Required
	public void setInvestigatorRepository(
			InvestigatorRepository investigatorRepository) {
		this.investigatorRepository = investigatorRepository;
	}
}
