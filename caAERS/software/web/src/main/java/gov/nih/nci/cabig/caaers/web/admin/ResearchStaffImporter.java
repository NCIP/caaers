package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.api.impl.DefaultInvestigatorMigratorService;
import gov.nih.nci.cabig.caaers.api.impl.DefaultResearchStaffMigratorService;
import gov.nih.nci.cabig.caaers.api.impl.ParticipantServiceImpl;
import gov.nih.nci.cabig.caaers.api.impl.StudyProcessorImpl;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.caaers.domain.repository.ResearchStaffRepository;
import gov.nih.nci.cabig.caaers.integration.schema.researchstaff.ResearchStaffType;
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

import org.apache.log4j.Logger;
import org.springframework.context.MessageSource;

/**
 * @author Sameer Sawant
 */
public class ResearchStaffImporter extends Importer{
	
	
	private DomainObjectValidator domainObjectValidator;
	private DefaultResearchStaffMigratorService researchStaffMigratorService;
	private ResearchStaffRepository researchStaffRepository;
	private static Logger logger = Logger.getLogger(ResearchStaffImporter.class);
	
	public void ResearchStaffImporter(){
	}
	
	public void setResearchStaffRepository(ResearchStaffRepository researchStaffRepository){
		this.researchStaffRepository = researchStaffRepository;
	}
	
	public DomainObjectValidator getDomainObjectValidator(){
		return domainObjectValidator;
	}
	
	public void setDomainObjectValidator(DomainObjectValidator domainObjectValidator){
		this.domainObjectValidator = domainObjectValidator;
	}
	
	public void setResearchStaffMigratorService(DefaultResearchStaffMigratorService researchStaffMigratorService){
		this.researchStaffMigratorService = researchStaffMigratorService;
	}
	
	public DefaultResearchStaffMigratorService getResearchStaffMigratorService(){
		return researchStaffMigratorService;
	}
	
	public void processEntities(File xmlFile,ImportCommand command){
		boolean valid = validateAgainstSchema(xmlFile , command, getXSDLocation(RESEARCH_STAFF_IMPORT));
        if (!valid) {
        	return;
        }
		try{
			//DefaultResearchStaffMigratorService svc = (DefaultResearchStaffMigratorService) getApplicationContext().getBean("researchStaffMigratorService");
			JAXBContext jaxbContext = JAXBContext.newInstance("gov.nih.nci.cabig.caaers.integration.schema.researchstaff");
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			
			Object importObject = unmarshaller.unmarshal(xmlFile);
			if(!validRootElement(importObject, RESEARCH_STAFF_IMPORT, command))
				return;
			
			gov.nih.nci.cabig.caaers.integration.schema.researchstaff.Staff  staff = (gov.nih.nci.cabig.caaers.integration.schema.researchstaff.Staff ) importObject;
			for(ResearchStaffType researchStaff : staff.getResearchStaff()){
				DomainObjectImportOutcome<ResearchStaff> researchStaffOutcome = researchStaffMigratorService.processResearchStaff(researchStaff);
				List<String> errors = domainObjectValidator.validate(researchStaffOutcome.getImportedDomainObject());
				if(researchStaffOutcome.isSavable() && errors.size() == 0){
					command.addImportableResearchStaff(researchStaffOutcome);
				}else{
					for(String errMsg : errors){
						researchStaffOutcome.addErrorMessage(errMsg, Severity.ERROR);
					}
					command.addNonImportableResearchStaff(researchStaffOutcome);
				}
			}
			//Remove Duplicate Investigators from the ImportableInvestigators List.
			List<DomainObjectImportOutcome<ResearchStaff>> dupList = new ArrayList<DomainObjectImportOutcome<ResearchStaff>>();
			for(int k=0 ; k < command.getImportableResearchStaff().size()-1 ; k++){
				ResearchStaff rStaff1 = command.getImportableResearchStaff().get(k).getImportedDomainObject();
				for(int l=k+1 ; l < command.getImportableResearchStaff().size() ; l++){
					ResearchStaff rStaff2 = command.getImportableResearchStaff().get(l).getImportedDomainObject();
					if(rStaff1.equals(rStaff2)){
						command.getImportableResearchStaff().get(l).addErrorMessage("Duplicate ResearchStaff", Severity.ERROR);
						command.addNonImportableResearchStaff(command.getImportableResearchStaff().get(l));
						dupList.add(command.getImportableResearchStaff().get(l));
						logger.debug("Duplicate Investigator :: " + rStaff2.getFullName());
						break;
					}
				}
			}
			for(DomainObjectImportOutcome<ResearchStaff> obj : dupList){
				command.getImportableResearchStaff().remove(obj);
			}
		}catch (JAXBException e) {
        	throw new RuntimeException("JAXB Exception", e);
		}
	}
	
	public void save(ImportCommand command, HttpServletRequest request){
		List<DomainObjectImportOutcome<ResearchStaff>> importableResearchStaff = command.getImportableResearchStaff();
        for (DomainObjectImportOutcome<ResearchStaff> importOutcome : importableResearchStaff) {
        	//researchStaffDao.save(importOutcome.getImportedDomainObject());
        	researchStaffRepository.save(importOutcome.getImportedDomainObject(), ResetPasswordController.getURL(request
                    .getScheme(), request.getServerName(), request.getServerPort(), request
                    .getContextPath()));
        }
	}
}