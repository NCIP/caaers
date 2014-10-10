/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.api.impl;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.api.AbstractImportService;
import gov.nih.nci.cabig.caaers.api.ProcessingOutcome;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.query.StudyQuery;
import gov.nih.nci.cabig.caaers.domain.Identifier;
import gov.nih.nci.cabig.caaers.domain.LocalOrganization;
import gov.nih.nci.cabig.caaers.domain.LocalStudy;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.OrganizationAssignedIdentifier;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.repository.StudyRepository;
import gov.nih.nci.cabig.caaers.integration.schema.common.CaaersServiceResponse;
import gov.nih.nci.cabig.caaers.integration.schema.common.OrganizationType;
import gov.nih.nci.cabig.caaers.integration.schema.study.StudySiteType;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome.Message;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome.Severity;
import gov.nih.nci.cabig.caaers.service.StudyImportServiceImpl;
import gov.nih.nci.cabig.caaers.service.migrator.OrganizationConverter;
import gov.nih.nci.cabig.caaers.service.migrator.StudyConverter;
import gov.nih.nci.cabig.caaers.service.synchronizer.StudySynchronizer;
import gov.nih.nci.cabig.caaers.validation.validator.DomainObjectValidator;
import gov.nih.nci.security.util.StringUtilities;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.transaction.annotation.Transactional;

/**
 * The implementation will manage (create/update) {@link Study}
 * @author Monish Dombla
 * @author Biju Joseph
 * @author Ion C. Olaru
 */

public class StudyProcessorImpl extends AbstractImportService implements ApplicationContextAware,MessageSourceAware {
	
	
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
    private OrganizationManagementServiceImpl oms;
    private OrganizationConverter organizationConverter;

    public StudyProcessorImpl() {
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
	public DomainObjectImportOutcome<Study> importStudy(gov.nih.nci.cabig.caaers.integration.schema.study.Study studyDto) {
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
		return studyImportOutcome;
	}

    private List<Organization> searchForOrganization(OrganizationType org) {
        if (!StringUtilities.isBlank(org.getNciInstituteCode())) {
            return getAuthorizedOrganizationsByNameOrNciId(null, org.getNciInstituteCode());
        } else {
            return getAuthorizedOrganizationsByNameOrNciId(org.getName(), null);
        }
    }

    private String checkAuthorizedOrganizations(gov.nih.nci.cabig.caaers.integration.schema.study.Study studyDto) {

		List<OrganizationType> orgs = new ArrayList<OrganizationType>();
		orgs.add(studyDto.getCoordinatingCenter().getStudyCoordinatingCenter().getOrganization());		
		orgs.add(studyDto.getFundingSponsor().getStudyFundingSponsor().getOrganization());		
		gov.nih.nci.cabig.caaers.integration.schema.study.Study.StudyOrganizations so = studyDto.getStudyOrganizations();

        if (so != null) {
            for (StudySiteType sst: so.getStudySite()) {
                orgs.add(sst.getOrganization());
            }
        }

        // ToDo Code that persists the new organization should be moved to StudyOrganizationMigrator class
		for (OrganizationType org:orgs) {
            List foundOrgs = searchForOrganization(org);
            if (foundOrgs.size() < 1) {
                // Persist the organization
                Organization domainOrganization = new LocalOrganization();
                organizationConverter.convertOrganizationDtoToDomainOrganization(org, domainOrganization);
                oms.createOrUpdateOrganization(domainOrganization);
//                return messageSource.getMessage("WS_AEMS_028", new String[]{org.getNciInstituteCode() + " : " + org.getName()}, "", Locale.getDefault());
            }
        }
		return "ALL_ORGS_AUTH";
	}

    public CaaersServiceResponse createOrUpdate(gov.nih.nci.cabig.caaers.integration.schema.study.Studies xmlStudies) {
        gov.nih.nci.cabig.caaers.integration.schema.study.Study studyDto = xmlStudies.getStudy().get(0);
        CaaersServiceResponse caaersServiceResponse = Helper.createResponse();
        // ToDo Merge createStudy & updateStudy into this method
        return caaersServiceResponse;
    }

	public CaaersServiceResponse createStudy(gov.nih.nci.cabig.caaers.integration.schema.study.Studies xmlStudies) {
        gov.nih.nci.cabig.caaers.integration.schema.study.Study studyDto = xmlStudies.getStudy().get(0);

        CaaersServiceResponse caaersServiceResponse = Helper.createResponse();

		logger.info("Study Short Title: " + studyDto.getShortTitle());
		logger.info("Study Long Title:" + studyDto.getLongTitle());
		
		String errorMsg = checkAuthorizedOrganizations(studyDto);
		if (!errorMsg.equals("ALL_ORGS_AUTH")) {
            return Helper.populateError(caaersServiceResponse, "WS_AEMS_028", errorMsg);
		}
		DomainObjectImportOutcome<Study> studyImportOutcome = null;
		Study study = new LocalStudy();

        //Convert JAXB StudyType to Domain Study
        try {
            logger.info("Converting StudyDto to Study");
            studyConverter.convertStudyDtoToStudyDomain(studyDto, study);
            logger.info("StudyDto converted to Study");
        } catch (CaaersSystemException caEX) {
            studyImportOutcome = new DomainObjectImportOutcome<Study>();
            logger.error("StudyDto to StudyDomain Conversion Failed ", caEX);
            studyImportOutcome.addErrorMessage("StudyDto to StudyDomain Conversion Failed ", DomainObjectImportOutcome.Severity.ERROR);
            Helper.populateError(caaersServiceResponse, "WS_GEN_000", "StudyDto to StudyDomain Conversion Failed");
        }

        if(studyImportOutcome == null){
			studyImportOutcome = studyImportService.importStudy(study);
			//Check if Study Exists
			Study dbStudy = checkDuplicateStudyBasedOnProcolAuthorityIdentifier(studyImportOutcome.getImportedDomainObject());
			if(dbStudy != null){
				studyImportOutcome.addErrorMessage(study.getClass().getSimpleName() + " identifier already exists. ", Severity.ERROR);
                Helper.populateError(caaersServiceResponse, "WS_STU_001",
                        messageSource.getMessage("WS_STU_001",
                                new Object[]{dbStudy.getPrimaryIdentifierValue(),studyImportOutcome.getImportedDomainObject().getPrimaryIdentifierValue()},
                                "Another study is using the identifier provided", Locale.getDefault()));
			}else{
				
				List<String> errors = domainObjectValidator.validate(studyImportOutcome.getImportedDomainObject());
				if(studyImportOutcome.isSavable() && errors.size() == 0){
					try {
                        Study theStudy =  studyImportOutcome.getImportedDomainObject();
						studyRepository.synchronizeStudyPersonnel(theStudy);
						studyRepository.save(theStudy);

                        ProcessingOutcome processingOutcome = Helper.createOutcome(Study.class, theStudy.getFundingSponsorIdentifierValue(),
                                String.valueOf(theStudy.getId()), false,"Study \""
                                +  theStudy.getPrimaryIdentifierValue()
                                + "\" Created in caAERS" ) ;
                        Helper.populateProcessingOutcome(caaersServiceResponse, processingOutcome);
                        Helper.populateMessage(caaersServiceResponse, "Study with Short Title  \""
                                +  theStudy.getPrimaryIdentifierValue()
                                + "\" Created in caAERS");

						logger.info("Study Created");
					} catch (Exception e) {
                        Helper.populateError(caaersServiceResponse, "WS_GEN_000", "Study Creation Failed " +  e.getMessage());
						logger.error("Error processing study : " + e.getMessage(), e);
					}
					
				}else{
					for(String errMsg : errors){
		        		studyImportOutcome.addErrorMessage(errMsg, Severity.ERROR);
		        	}

					List<String> messages = new ArrayList<String>();
					for(Message message : studyImportOutcome.getMessages()){
						messages.add(message.getMessage());
					}
					for(String errMsg : errors){
						messages.add(errMsg);
		        	}
					
					String msg = "Study \"" +  studyImportOutcome.getImportedDomainObject().getPrimaryIdentifierValue() + "\" could not be created in caAERS. "
                            + messages.toString();
                            
                    Helper.populateError(caaersServiceResponse, "WS_GEN_000", msg);
                    
                    Helper.populateProcessingOutcome(caaersServiceResponse, Helper.createOutcome(Study.class, 
                    		studyImportOutcome.getImportedDomainObject().getFundingSponsorIdentifierValue(), true, msg ));
                    logger.debug(">>> ERR:" + msg);
				}
			}
			
		}

		logger.info("Leaving createStudy() in StudyProcessorImpl");
		return caaersServiceResponse;
	}

    @Transactional(readOnly = false)
	public CaaersServiceResponse updateStudy(gov.nih.nci.cabig.caaers.integration.schema.study.Studies xmlStudies) {
		gov.nih.nci.cabig.caaers.integration.schema.study.Study studyDto = xmlStudies.getStudy().get(0);
		CaaersServiceResponse caaersServiceResponse = Helper.createResponse();

		logger.info("Study Short Title --- " + studyDto.getShortTitle());
		logger.info("Study Long Title --- " + studyDto.getLongTitle());

		String errorMsg = checkAuthorizedOrganizations(studyDto);
		if (!errorMsg.equals("ALL_ORGS_AUTH")) {
            Helper.populateError(caaersServiceResponse, "WS_AEMS_028", errorMsg);
			return caaersServiceResponse;
		}
		
		DomainObjectImportOutcome<Study> studyImportOutcome = null;
		Study study = new LocalStudy();

        //Convert JAXB StudyType to Domain Study
        try {
            studyConverter.convertStudyDtoToStudyDomain(studyDto, study);
            logger.info("StudyDto converted to Study");
        } catch (CaaersSystemException caEX) {
            studyImportOutcome = new DomainObjectImportOutcome<Study>();
            logger.error("StudyDto to StudyDomain Conversion Failed ", caEX);
            studyImportOutcome.addErrorMessage("StudyDto to StudyDomain Conversion Failed ", DomainObjectImportOutcome.Severity.ERROR);
            Helper.populateError(caaersServiceResponse, "WS_GEN_000", "StudyDto to StudyDomain Conversion Failed");
            return caaersServiceResponse;
        }

        if (studyImportOutcome == null) {
			studyImportOutcome = studyImportService.importStudy(study);
			List<String> errors = domainObjectValidator.validate(studyImportOutcome.getImportedDomainObject());
			if(studyImportOutcome.isSavable() && errors.size() == 0){
				Study dbStudy = fetchStudy(studyImportOutcome.getImportedDomainObject());

                if(dbStudy == null){
                    Helper.populateError(caaersServiceResponse, "WS_GEN_000", "Study \""
                            +  studyImportOutcome.getImportedDomainObject().getPrimaryIdentifierValue()
                            + "\" does not exist in caAERS");
                    studyImportOutcome.addErrorMessage("Study \""
                            +  studyImportOutcome.getImportedDomainObject().getPrimaryIdentifierValue()
                            + "\" does not exist in caAERS" , DomainObjectImportOutcome.Severity.ERROR);
                    return caaersServiceResponse;
                }
                
                studySynchronizer.migrate(dbStudy, studyImportOutcome.getImportedDomainObject(), studyImportOutcome);
                studyImportOutcome.setImportedDomainObject(dbStudy);

                //check if another study exist?
                Study anotherStudy = checkDuplicateStudyBasedOnProcolAuthorityIdentifier(studyImportOutcome.getImportedDomainObject());
                if(anotherStudy != null){
                    String errorDescription = messageSource.getMessage("WS_STU_001", new Object[]{anotherStudy.getPrimaryIdentifierValue(),
                            studyImportOutcome.getImportedDomainObject().getPrimaryIdentifierValue()},
                            "Another study is using the identifier provided", Locale.getDefault());
                    Helper.populateError(caaersServiceResponse, "WS_GEN_000", errorDescription);
                    studyImportOutcome.addErrorMessage(errorDescription, DomainObjectImportOutcome.Severity.ERROR);
                    return caaersServiceResponse;
                }
                try {
                    Study theStudy =  studyImportOutcome.getImportedDomainObject();
                    studyRepository.synchronizeStudyPersonnel(theStudy);
                    studyRepository.save(theStudy);
                    ProcessingOutcome processingOutcome = Helper.createOutcome(Study.class, theStudy.getFundingSponsorIdentifierValue(), 
                            String.valueOf(theStudy.getId()), false,"Study \""
                            +  studyImportOutcome.getImportedDomainObject().getPrimaryIdentifierValue()
                            + "\" updated in caAERS" ) ;
                    Helper.populateProcessingOutcome(caaersServiceResponse, processingOutcome);
                    Helper.populateMessage(caaersServiceResponse, "Study \""
                            +  studyImportOutcome.getImportedDomainObject().getPrimaryIdentifierValue()
                            + "\" updated in caAERS");
                    caaersServiceResponse.getServiceResponse().getResponsecode();
                    logger.info("Study Updated");
                } catch (Exception e) {
                    logger.error("Error while saving the study :" + e.getMessage(), e);
                    String msg = "Cannot process study : " + e.getMessage();
                    Helper.populateError(caaersServiceResponse, "WS_GEN_000", msg);
                    Helper.populateProcessingOutcome(caaersServiceResponse, Helper.createOutcome(Study.class,
                                        		studyImportOutcome.getImportedDomainObject().getFundingSponsorIdentifierValue(), true, msg));
                }


			}else {

                List<String> messages = new ArrayList<String>();
				for(Message message : studyImportOutcome.getMessages()){
					messages.add(message.getMessage());
				}
				for(String errMsg : errors){
					messages.add(errMsg);
	        	}
                Helper.populateError(caaersServiceResponse, "WS_GEN_000", "Study \""
                        +  studyImportOutcome.getImportedDomainObject().getPrimaryIdentifierValue()
                        + "\" could not be updated in caAERS. " + messages.toString());

            }
		}

		logger.info("Leaving updateStudy() in StudyProcessor");
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
	
	
	/**
	 * Will retrieve from DB a study that is having the same protocol identifier as that of the study parameter. 
	 * @param study
	 * @return
	 */
	public Study checkDuplicateStudyBasedOnProcolAuthorityIdentifier(Study study){
		for(Identifier id : study.getIdentifiers()){
			
			if(!id.getType().equals(OrganizationAssignedIdentifier.SPONSOR_IDENTIFIER_TYPE)){
				continue;
			}
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

    public OrganizationManagementServiceImpl getOrganizationManagementServiceImpl() {
        return oms;
    }

    public void setOrganizationManagementServiceImpl(OrganizationManagementServiceImpl oms) {
        this.oms = oms;
    }

    public OrganizationConverter getOrganizationConverter() {
        return organizationConverter;
    }

    public void setOrganizationConverter(OrganizationConverter organizationConverter) {
        this.organizationConverter = organizationConverter;
    }
}
