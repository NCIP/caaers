package gov.nih.nci.cabig.caaers.api.impl;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.api.AbstractImportService;
import gov.nih.nci.cabig.caaers.api.StudyProcessor;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.query.StudyQuery;
import gov.nih.nci.cabig.caaers.domain.Identifier;
import gov.nih.nci.cabig.caaers.domain.LocalStudy;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.repository.StudyRepository;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.service.StudyImportServiceImpl;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome.Message;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome.Severity;
import gov.nih.nci.cabig.caaers.service.migrator.StudyConverter;
import gov.nih.nci.cabig.caaers.service.synchronizer.StudySynchronizer;
import gov.nih.nci.cabig.caaers.validation.validator.DomainObjectValidator;
import gov.nih.nci.cabig.caaers.webservice.OrganizationType;
import gov.nih.nci.cabig.caaers.webservice.Response;
import gov.nih.nci.cabig.caaers.webservice.StudySiteType;
import gov.nih.nci.cabig.caaers.webservice.Study.StudyOrganizations;
import gov.nih.nci.security.util.StringUtilities;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;

/**
 * The implementation will manage (create/update) {@link Study}
 * @author Monish Dombla
 * @author Biju Joseph
 *
 */
@WebService(endpointInterface="gov.nih.nci.cabig.caaers.api.StudyProcessor", serviceName="StudyService")
@SOAPBinding(parameterStyle=SOAPBinding.ParameterStyle.BARE)
public class StudyProcessorImpl extends AbstractImportService implements StudyProcessor,ApplicationContextAware,MessageSourceAware {
	
	
private static Log logger = LogFactory.getLog(StudyProcessorImpl.class);
	
	//Injected through spring
	private StudyImportServiceImpl studyImportService;
	private StudyDao studyDao;
	private StudyRepository studyRepository;
	private StudyConverter studyConverter;
	private StudySynchronizer studySynchronizer;
	private ApplicationContext applicationContext;
	private DomainObjectValidator domainObjectValidator;
	private MessageSource messageSource;
	
	public StudyProcessorImpl(){
		
	}
	
	public StudyImportServiceImpl getStudyImportService() {
		return studyImportService;
	}

	public void setStudyImportService(StudyImportServiceImpl studyImportService) {
		this.studyImportService = studyImportService;
	}

	public StudyDao getStudyDao() {
		return studyDao;
	}

	public void setStudyDao(StudyDao studyDao) {
		this.studyDao = studyDao;
	}

	public StudyConverter getStudyConverter() {
		return studyConverter;
	}

	public void setStudyConverter(StudyConverter studyConverter) {
		this.studyConverter = studyConverter;
	}

	public StudySynchronizer getStudySynchronizer() {
		return studySynchronizer;
	}

	public void setStudySynchronizer(StudySynchronizer studySynchronizer) {
		this.studySynchronizer = studySynchronizer;
	}

	/**
	 * Method exisits only to be called from ImportController 
	 * @param studyDto
	 */
	public DomainObjectImportOutcome<Study> processStudy(gov.nih.nci.cabig.caaers.webservice.Study studyDto){
		logger.info("Entering processStudy() in StudyProcessorImpl");
		
		DomainObjectImportOutcome<Study> studyImportOutcome = null;
		Study study = new LocalStudy();
		
		//Convert JAXB StudyType to Domain Study
		try{
			studyConverter.convertStudyDtoToStudyDomain(studyDto, study);
			logger.info("StudyDto converted to Study");
		}catch(CaaersSystemException caEX){
			studyImportOutcome = new DomainObjectImportOutcome<Study>();
			logger.error("StudyDto to StudyDomain Conversion Failed " , caEX);
			studyImportOutcome.addErrorMessage("StudyDto to StudyDomain Conversion Failed " , DomainObjectImportOutcome.Severity.ERROR);
		}
		
		if(studyImportOutcome == null){
			studyImportOutcome = studyImportService.importStudy(study);
			if(studyImportOutcome.isSavable()){
				//Check if Study Exists; If Exists then update
				Study dbStudy = fetchStudy(studyImportOutcome.getImportedDomainObject());
				if(dbStudy != null){
					logger.info("Study with Long Title -- "+ dbStudy.getLongTitle() + " -- Exists in caAERS trying to Update");
					studySynchronizer.migrate(dbStudy, studyImportOutcome.getImportedDomainObject(), studyImportOutcome);
					studyImportOutcome.setImportedDomainObject(dbStudy);
					logger.info("Study "+ dbStudy.getLongTitle() + " in caAERS Updated");
				}else{
					logger.info("New Study with Long Title -- "+ studyImportOutcome.getImportedDomainObject().getLongTitle() + " -- being Created");
				}
			}
		}
		logger.info("Leaving createStudy() in StudyProcessorImpl");
		return studyImportOutcome;
	}
	private List<Organization> searchForOrganization(gov.nih.nci.cabig.caaers.webservice.OrganizationType org) {
		if (!StringUtilities.isBlank(org.getNciInstituteCode())) {
			return getAuthorizedOrganizationsByNameOrNciId(null, org.getNciInstituteCode());
		} else {
			return getAuthorizedOrganizationsByNameOrNciId(org.getName(), null);
		}
	}
	private String checkAuthorizedOrganizations (gov.nih.nci.cabig.caaers.webservice.Study studyDto) {
		List<OrganizationType> orgs = new ArrayList<OrganizationType>();
		orgs.add(studyDto.getCoordinatingCenter().getStudyCoordinatingCenter().getOrganization());		
		orgs.add(studyDto.getFundingSponsor().getStudyFundingSponsor().getOrganization());		
		StudyOrganizations so = studyDto.getStudyOrganizations();
		for (StudySiteType sst: so.getStudySite()) {
			orgs.add(sst.getOrganization());
		}
		for (OrganizationType org:orgs) {
			if (searchForOrganization(org).size()<1) {
				return messageSource.getMessage("WS_AEMS_028", new String[]{org.getNciInstituteCode() + " : " + org.getName()},"",Locale.getDefault());
			}
		}
		return "ALL_ORGS_AUTH";
	}
	//if (!StringUtilities.isBlank(nciInstituteCode)) {
	public gov.nih.nci.cabig.caaers.webservice.CaaersServiceResponse createStudy(gov.nih.nci.cabig.caaers.webservice.Studies xmlStudies) {
		gov.nih.nci.cabig.caaers.webservice.Study studyDto = xmlStudies.getStudy().get(0);
		gov.nih.nci.cabig.caaers.webservice.CaaersServiceResponse caaersServiceResponse = new gov.nih.nci.cabig.caaers.webservice.CaaersServiceResponse();

		
		Response studyServiceResponse = new Response();
		
		logger.info("Swith User Done ");
		logger.info("Inside createStudy ");
		logger.info("Study Short Title --- " + studyDto.getShortTitle());
		logger.info("Study Long Title --- " + studyDto.getLongTitle());
		
		String errorMsg = checkAuthorizedOrganizations(studyDto);
		if (!errorMsg.equals("ALL_ORGS_AUTH")) {
			studyServiceResponse.setResponsecode("WS_AEMS_028");
			studyServiceResponse.setDescription(errorMsg);	
			caaersServiceResponse.setResponse(studyServiceResponse);
			return caaersServiceResponse;
		}
		DomainObjectImportOutcome<Study> studyImportOutcome = null;
		Study study = new LocalStudy();
		
		//Convert JAXB StudyType to Domain Study
		try{
			logger.info("Converting StudyDto to Study");
			studyConverter.convertStudyDtoToStudyDomain(studyDto, study);
			logger.info("StudyDto converted to Study");
		}catch(CaaersSystemException caEX){
			studyImportOutcome = new DomainObjectImportOutcome<Study>();
			logger.error("StudyDto to StudyDomain Conversion Failed " , caEX);
			studyImportOutcome.addErrorMessage("StudyDto to StudyDomain Conversion Failed " , DomainObjectImportOutcome.Severity.ERROR);
			studyServiceResponse.setResponsecode("1");
			studyServiceResponse.setDescription("StudyDto to StudyDomain Conversion Failed ");
		}
		
		if(studyImportOutcome == null){
			studyImportOutcome = studyImportService.importStudy(study);
			//Check if Study Exists
			Study dbStudy = checkDuplicateStudy(studyImportOutcome.getImportedDomainObject());
			if(dbStudy != null){
				studyImportOutcome.addErrorMessage(study.getClass().getSimpleName() + " identifier already exists. ", Severity.ERROR);
				studyServiceResponse.setResponsecode("1");
				studyServiceResponse.setDescription(messageSource.getMessage("WS_STU_001", 
						new Object[]{dbStudy.getShortTitle(), studyImportOutcome.getImportedDomainObject().getShortTitle()}, 
						"Another study is using the identifier provided", Locale.getDefault()));
			}else{
				
				List<String> errors = domainObjectValidator.validate(studyImportOutcome.getImportedDomainObject());
				if(studyImportOutcome.isSavable() && errors.size() == 0){
					try {
						studyRepository.synchronizeStudyPersonnel(studyImportOutcome.getImportedDomainObject());
						studyRepository.save(studyImportOutcome.getImportedDomainObject());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					studyServiceResponse.setResponsecode("0");
					studyServiceResponse.setDescription("Study with Short Title  \"" +  studyImportOutcome.getImportedDomainObject().getShortTitle() + "\" Created in caAERS");
					logger.info("Study Created");
				}else{
					for(String errMsg : errors){
		        		studyImportOutcome.addErrorMessage(errMsg, Severity.ERROR);
		        	}
					studyServiceResponse.setResponsecode("1");
					studyServiceResponse.setDescription("Study with Short Title \"" +  studyImportOutcome.getImportedDomainObject().getShortTitle() + "\" could not be created in caAERS");
					List<String> messages = new ArrayList<String>(); 
					for(Message message : studyImportOutcome.getMessages()){
						messages.add(message.getMessage());
					}
					for(String errMsg : errors){
						messages.add(errMsg);
		        	}
					studyServiceResponse.setMessage(messages);
				}
			}
			
		}

		logger.info("Leaving createStudy() in StudyProcessorImpl");
		caaersServiceResponse.setResponse(studyServiceResponse);
		return caaersServiceResponse;
	}

	public gov.nih.nci.cabig.caaers.webservice.CaaersServiceResponse updateStudy(gov.nih.nci.cabig.caaers.webservice.Studies xmlStudies) {
		gov.nih.nci.cabig.caaers.webservice.Study studyDto = xmlStudies.getStudy().get(0);
		gov.nih.nci.cabig.caaers.webservice.CaaersServiceResponse caaersServiceResponse = new gov.nih.nci.cabig.caaers.webservice.CaaersServiceResponse();

		
		Response studyServiceResponse = new Response();
		
		logger.info("Inside updateStudy ");
		logger.info("Study Short Title --- " + studyDto.getShortTitle());
		logger.info("Study Long Title --- " + studyDto.getLongTitle());

		String errorMsg = checkAuthorizedOrganizations(studyDto);
		if (!errorMsg.equals("ALL_ORGS_AUTH")) {
			studyServiceResponse.setResponsecode("WS_AEMS_028");
			studyServiceResponse.setDescription(errorMsg);	
			caaersServiceResponse.setResponse(studyServiceResponse);
			return caaersServiceResponse;
		}
		
		DomainObjectImportOutcome<Study> studyImportOutcome = null;
		Study study = new LocalStudy();
		
		//Convert JAXB StudyType to Domain Study
		try{
			studyConverter.convertStudyDtoToStudyDomain(studyDto, study);
			logger.info("StudyDto converted to Study");
		}catch(CaaersSystemException caEX){
			studyImportOutcome = new DomainObjectImportOutcome<Study>();
			logger.error("StudyDto to StudyDomain Conversion Failed " , caEX);
			studyImportOutcome.addErrorMessage("StudyDto to StudyDomain Conversion Failed " , DomainObjectImportOutcome.Severity.ERROR);
			studyServiceResponse.setResponsecode("1");
			studyServiceResponse.setDescription("StudyDto to StudyDomain Conversion Failed ");
		}
		
		if(studyImportOutcome == null){
			studyImportOutcome = studyImportService.importStudy(study);
			List<String> errors = domainObjectValidator.validate(studyImportOutcome.getImportedDomainObject());
			if(studyImportOutcome.isSavable() && errors.size() == 0){
				Study dbStudy = fetchStudy(studyImportOutcome.getImportedDomainObject());
				if(dbStudy != null){
					studySynchronizer.migrate(dbStudy, studyImportOutcome.getImportedDomainObject(), studyImportOutcome);
					studyImportOutcome.setImportedDomainObject(dbStudy);
					
					//check if another study exist?
					Study anotherStudy = checkDuplicateStudy(studyImportOutcome.getImportedDomainObject());
					if(anotherStudy == null){

						try {
							studyRepository.synchronizeStudyPersonnel(studyImportOutcome.getImportedDomainObject());
							studyRepository.save(studyImportOutcome.getImportedDomainObject());
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						studyServiceResponse.setResponsecode("0");
						studyServiceResponse.setDescription("Study with Short Title  \"" +  studyImportOutcome.getImportedDomainObject().getShortTitle() + "\" updated in caAERS");
						logger.info("Study Updated");
						
					}else{
						studyServiceResponse.setResponsecode("1");
						String errorDescription = messageSource.getMessage("WS_STU_001", new Object[]{anotherStudy.getShortTitle(), studyImportOutcome.getImportedDomainObject().getShortTitle()}, "Another study is using the identifier provided", Locale.getDefault());
						studyServiceResponse.setDescription(errorDescription);
						studyImportOutcome.addErrorMessage(errorDescription, DomainObjectImportOutcome.Severity.ERROR);
					}
					
				}else{
					studyServiceResponse.setResponsecode("1");
					studyServiceResponse.setDescription("Study with Short Title  \"" +  studyImportOutcome.getImportedDomainObject().getShortTitle() + "\" does not exist in caAERS");
					studyImportOutcome.addErrorMessage("Study with Short Title  \"" +  studyImportOutcome.getImportedDomainObject().getShortTitle() + "\" does not exist in caAERS" , DomainObjectImportOutcome.Severity.ERROR);
				}
			}else{
				studyServiceResponse.setResponsecode("1");
				studyServiceResponse.setDescription("Study with Short Title \"" +  studyImportOutcome.getImportedDomainObject().getShortTitle() + "\" could not be updated in caAERS");
				List<String> messages = new ArrayList<String>(); 
				for(Message message : studyImportOutcome.getMessages()){
					messages.add(message.getMessage());
				}
				for(String errMsg : errors){
					messages.add(errMsg);
	        	}
				studyServiceResponse.setMessage(messages);
			}
		}

		logger.info("Leaving updateStudy() in StudyProcessor");
		caaersServiceResponse.setResponse(studyServiceResponse);
		return caaersServiceResponse;
	}
	
	/**
	 * This method fetches a Study from the DB based identifiers.
	 * @param importedStudy
	 * @return
	 */
	private Study fetchStudy(Study importedStudy){
		Study dbStudy = null;
		for (Identifier identifier : importedStudy.getIdentifiers()) {
            dbStudy = studyDao.getStudyDesignByIdentifier(identifier);
            if(dbStudy != null){
            	break;
            }
            studyDao.evict(dbStudy);
        }
		return dbStudy;
	}
	
	/**
	 * Will retrieve from DB a study that is having the same identifier as that of the study parameter. 
	 * @param study
	 * @return
	 */
	public Study checkDuplicateStudy(Study study){
		
		for(Identifier id : study.getIdentifiers()){
			
			StudyQuery query = new StudyQuery();
			query.joinIdentifier();
			query.filterByIdentifier(id);
			if(study.getId() != null) query.ignoreStudyById(study.getId());
			
			List<Study> existingStudies = studyDao.find(query);
			if(CollectionUtils.isNotEmpty(existingStudies)){
				return existingStudies.get(0);
			}
			
		}
		
		return null;
	}


	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
		
	}

	public void setDomainObjectValidator(DomainObjectValidator domainObjectValidator) {
		this.domainObjectValidator = domainObjectValidator;
	}

	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	public void setStudyRepository(StudyRepository studyRepository) {
		this.studyRepository = studyRepository;
	}
}
