/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.api.impl.DefaultInvestigatorMigratorService;
import gov.nih.nci.cabig.caaers.domain.Investigator;
import gov.nih.nci.cabig.caaers.domain.LocalInvestigator;
import gov.nih.nci.cabig.caaers.domain.repository.InvestigatorRepository;
import gov.nih.nci.cabig.caaers.integration.schema.investigator.InvestigatorType;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome.Severity;
import gov.nih.nci.cabig.caaers.validation.validator.DomainObjectValidator;
import gov.nih.nci.cabig.caaers.web.user.ResetPasswordController;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.mail.MailException;

/**
 * @author Sameer Sawant
 */
public class InvestigatorImporter extends Importer{
	
	private DomainObjectValidator domainObjectValidator;
	private DefaultInvestigatorMigratorService investigatorMigratorService;
	private InvestigatorRepository investigatorRepository;
	private static Logger logger = Logger.getLogger(InvestigatorImporter.class);
	
	public void InvestigatorImporter(){
	}
	
	public void setInvestigatorRepository(InvestigatorRepository investigatorRepository){
		this.investigatorRepository = investigatorRepository;
	}
	
	public DomainObjectValidator getDomainObjectValidator(){
		return domainObjectValidator;
	}
	
	public void setDomainObjectValidator(DomainObjectValidator domainObjectValidator){
		this.domainObjectValidator = domainObjectValidator;
	}
	
	public void setInvestigatorMigratorService(DefaultInvestigatorMigratorService investigatorMigratorService){
		this.investigatorMigratorService = investigatorMigratorService;
	}
	
	public DefaultInvestigatorMigratorService getInvestigatorMigratorService(){
		return investigatorMigratorService;
	}
	
	public void processEntities(File xmlFile,ImportCommand command){
		boolean valid = validateAgainstSchema(xmlFile , command, getXSDLocation(INVESTIGATOR_IMPORT));
        if (!valid) {
        	return;
        }
		try {
			//DefaultInvestigatorMigratorService svc = (DefaultInvestigatorMigratorService) getApplicationContext().getBean("investigatorMigratorService");
			JAXBContext jaxbContext = JAXBContext.newInstance("gov.nih.nci.cabig.caaers.integration.schema.investigator");
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			
			Object importObject = unmarshaller.unmarshal(xmlFile);
			
			if(!validRootElement(importObject, INVESTIGATOR_IMPORT, command))
				return;
			
			gov.nih.nci.cabig.caaers.integration.schema.investigator.Staff staff = (gov.nih.nci.cabig.caaers.integration.schema.investigator.Staff ) importObject;
			for(InvestigatorType xmlInvestigator : staff.getInvestigator()){
				DomainObjectImportOutcome<Investigator> investigatorOutcome = investigatorMigratorService.processInvestigator(xmlInvestigator);
				List<String> errors = domainObjectValidator.validate(investigatorOutcome.getImportedDomainObject());
				if(investigatorOutcome.isSavable() && errors.size() == 0){
					command.addImportableInvestigator(investigatorOutcome);
				}else{
					for(String errMsg : errors){
						investigatorOutcome.addErrorMessage(errMsg, Severity.ERROR);
			    	}
					command.addNonImportableInvestigator(investigatorOutcome);
				}
			}
			//Remove Duplicate Investigators from the ImportableInvestigators List.
			List<DomainObjectImportOutcome<Investigator>> dupList = new ArrayList<DomainObjectImportOutcome<Investigator>>();
			for(int k=0 ; k < command.getImportableInvestigators().size()-1 ; k++){
				Investigator inv1 = command.getImportableInvestigators().get(k).getImportedDomainObject();
				for(int l=k+1 ; l < command.getImportableInvestigators().size() ; l++){
					Investigator inv2 = command.getImportableInvestigators().get(l).getImportedDomainObject();
					if(inv1.equals(inv2)){
						command.getImportableInvestigators().get(l).addErrorMessage("Duplicate Investigator", Severity.ERROR);
						command.addNonImportableInvestigator(command.getImportableInvestigators().get(l));
						dupList.add(command.getImportableInvestigators().get(l));
						logger.debug("Duplicate Investigator :: " + inv2.getFullName());
						break;
					}
				}
			}
			for(DomainObjectImportOutcome<Investigator> obj : dupList){
				command.getImportableInvestigators().remove(obj);
			}
		} catch (JAXBException e) {
			throw new RuntimeException("JAXB Exception", e);
		}
	}

	public void save(ImportCommand command, HttpServletRequest request){
		List<DomainObjectImportOutcome<Investigator>> importableInvestigators = command.getImportableInvestigators();
        for (DomainObjectImportOutcome<Investigator> importOutcome : importableInvestigators) {
        	try{
        		investigatorRepository.save(importOutcome.getImportedDomainObject(), ResetPasswordController.getURL(request
                        .getScheme(), request.getServerName(), request.getServerPort(), request
                        .getContextPath()));
        	}catch(MailException mEx){
        		logger.warn("Exception while sending email to Investigator", mEx);
        	}
        }
        //	CAAERS-4461
        if(CollectionUtils.isNotEmpty(importableInvestigators)) getEventFactory().publishEntityModifiedEvent(new LocalInvestigator(), true);
	}
}
