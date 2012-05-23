package gov.nih.nci.cabig.caaers.api.impl;

import gov.nih.nci.cabig.caaers.api.CtcService;
import gov.nih.nci.cabig.caaers.api.ProcessingOutcome;
import gov.nih.nci.cabig.caaers.dao.CtcDao;
import gov.nih.nci.cabig.caaers.domain.Ctc;
import gov.nih.nci.cabig.caaers.domain.CtcCategory;
import gov.nih.nci.cabig.caaers.domain.CtcGrade;
import gov.nih.nci.cabig.caaers.domain.CtcTerm;
import gov.nih.nci.cabig.caaers.integration.schema.common.CaaersServiceResponse;
import gov.nih.nci.cabig.caaers.integration.schema.ctc.CtcVersionType;
import gov.nih.nci.cabig.caaers.integration.schema.ctc.Ctcs;
import gov.nih.nci.cabig.caaers.service.migrator.CtcConverter;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class CtcServiceImpl implements CtcService{
	
	private static Log logger = LogFactory.getLog(CtcServiceImpl.class);
	private CtcDao ctcDao;
	private CtcConverter ctcConverter = new CtcConverter();

	public CaaersServiceResponse createOrUpdateCtc(Ctcs ctcs) {
		CaaersServiceResponse response = Helper.createResponse();
		List<ProcessingOutcome> processingOutcomes = new ArrayList<ProcessingOutcome>();
		for(CtcVersionType ctcVersionType : ctcs.getCtc()){
			processingOutcomes.addAll(createOrUpdateCtc(ctcConverter.convert(ctcVersionType)));
		}
		for(ProcessingOutcome processingOutcome : processingOutcomes){
			Helper.populateProcessingOutcome(response, processingOutcome);
		}
		return response;
	}
	
	public List<ProcessingOutcome> createOrUpdateCtc(Ctc ctcInput) {
		ProcessingOutcome processingOutcome = new ProcessingOutcome();
		processingOutcome.setKlassName(Ctc.class.getName());
		List<ProcessingOutcome> processingOutcomes = new ArrayList<ProcessingOutcome>();
		processingOutcomes.add(processingOutcome);
		if(StringUtils.isBlank(ctcInput.getName())){
			processingOutcome.setBusinessId("NA");
	        processingOutcome.setFailed(true);
	        processingOutcome.addMessage("Ctc name/version cannot be blank");
	        return processingOutcomes;
		}
		processingOutcome.setBusinessId(ctcInput.getName());
		Ctc ctc = ctcDao.getByName(ctcInput.getName());
		if(ctc == null){
			ctc = new Ctc();
			ctc.setName(ctcInput.getName());
			try {
				ctcDao.save(ctc);
				processingOutcome.addMessage("Added");
			} catch (Exception e) {
				logger.error(e.getMessage());
		        processingOutcome.setFailed(true);
		        processingOutcome.addMessage(String.valueOf(e.getMessage()));
		        return processingOutcomes;
			}
		}else{
			processingOutcome.addMessage("Updated");
		}
		for(CtcCategory category: ctcInput.getCategories()){
			processingOutcomes.addAll(createOrUpdateCategory(ctc, category));
		}
		return processingOutcomes;
	}
	
	private List<ProcessingOutcome> createOrUpdateCategory(Ctc ctc, CtcCategory category) {
		List<ProcessingOutcome> processingOutcomes = new ArrayList<ProcessingOutcome>();
		String message = "";
		ProcessingOutcome processingOutcome = new ProcessingOutcome();
		processingOutcome.setKlassName(CtcCategory.class.getName());
		processingOutcomes.add(processingOutcome);
		if(StringUtils.isBlank(category.getName())){
			processingOutcome.setBusinessId("NA");
			processingOutcome.addMessage("CtcCategory name cannot be blank");
			processingOutcome.setFailed(true);
			return processingOutcomes;
		}
		processingOutcome.setBusinessId(category.getName());
		boolean found = false;
        for(CtcCategory existingCategory : ctc.getCategories()){
        	if(existingCategory.getName().equalsIgnoreCase(category.getName())){
        		//Update category
        		found = true;
        		message = "Updated";
        		processingOutcomes.addAll(updateTerms(existingCategory, category.getTerms()));
        	}
        }
        //New category
        if(!found){
        	CtcCategory ctcCategory = new CtcCategory();
        	ctcCategory.setName(category.getName());
        	ctc.addCtcCategory(ctcCategory);
        	message = "Added";
        	processingOutcomes.addAll(updateTerms(ctcCategory, category.getTerms()));
        }
		try {
			ctcDao.merge(ctc);
		} catch (Exception e) {
			for(ProcessingOutcome processingOutcomeTerm : processingOutcomes){
				processingOutcomeTerm.setFailed(true);
				processingOutcomeTerm.addMessage("Ctc save failed");
			}
			processingOutcome.setFailed(true);
			message = String.valueOf(e.getMessage());
			logger.error(e.getMessage());
			
		}finally {
			processingOutcome.addMessage(message);
		}
		
		return processingOutcomes;
	}
	
	private List<ProcessingOutcome> updateTerms(CtcCategory ctcCategory, List<CtcTerm> ctcTerms){
		List<ProcessingOutcome> processingOutcomes = new ArrayList<ProcessingOutcome>();
		for(CtcTerm inputTerm: ctcTerms){
			ProcessingOutcome processingOutcome = new ProcessingOutcome();
			processingOutcome.setKlassName(CtcTerm.class.getName());
			processingOutcomes.add(processingOutcome);
			if(StringUtils.isBlank(inputTerm.getCtepCode())){
				processingOutcome.setBusinessId("NA");
				processingOutcome.addMessage("CtcTerm ctep_code cannot be blank");
				processingOutcome.setFailed(true);
				continue;
			}
			boolean found = false;
			processingOutcome.setBusinessId(inputTerm.getCtepCode());
			for(CtcTerm existingTerm: ctcCategory.getTerms()){
				if(inputTerm.getCtepCode().equalsIgnoreCase(existingTerm.getCtepCode())){
					//Update term
					processingOutcome.addMessage("Updated");
					found = true;
					existingTerm.setCtepTerm(inputTerm.getCtepTerm());
					existingTerm.setOtherRequired(isOtherRequired(inputTerm.getCtepTerm()));
					existingTerm.setTerm(inputTerm.getTerm());
					processingOutcomes.addAll(updateCtcGrades(existingTerm, inputTerm.getContextualGrades()));
					
				}
			}
			if(!found){
				//New term
				processingOutcome.addMessage("Added");
				CtcTerm ctcTerm = new CtcTerm();
				ctcTerm.setCtepCode(inputTerm.getCtepCode());
				ctcTerm.setCtepTerm(inputTerm.getCtepTerm());
				ctcTerm.setOtherRequired(isOtherRequired(inputTerm.getCtepTerm()));
				ctcTerm.setTerm(inputTerm.getTerm());
				ctcCategory.addCtcTerm(ctcTerm);
				processingOutcomes.addAll(updateCtcGrades(ctcTerm, inputTerm.getContextualGrades()));
			}
		}
		return processingOutcomes;
	}
	
	private List<ProcessingOutcome> updateCtcGrades(CtcTerm ctcTerm, List<CtcGrade> grades){
		List<ProcessingOutcome> processingOutcomes = new ArrayList<ProcessingOutcome>();
		if(grades.size()>0){
			List<CtcGrade> validGrades = new ArrayList<CtcGrade>();
			for(CtcGrade ctcGrade : grades){
				ProcessingOutcome processingOutcome = new ProcessingOutcome();
				processingOutcome.setKlassName(CtcGrade.class.getName());
				processingOutcomes.add(processingOutcome);
				if(ctcGrade.getGrade() == null || StringUtils.isBlank(ctcGrade.getText())){
					processingOutcome.setBusinessId("NA");
					processingOutcome.addMessage("Ctc grade or grade description cannot be blank");
					processingOutcome.setFailed(true);
				}else{
					processingOutcome.setBusinessId(ctcGrade.getGrade().getDisplayName());
					processingOutcome.addMessage("Added");
					validGrades.add(ctcGrade);
				}
			}
			if(validGrades.size()>0){
				ctcTerm.getContextualGrades().clear();
				for(CtcGrade ctcGrade : validGrades){
					ctcTerm.addCtcGrade(ctcGrade);
				}
			}
		}
		return processingOutcomes;
	}

	public void setCtcDao(CtcDao ctcDao) {
		this.ctcDao = ctcDao;
	}
	
	public boolean isOtherRequired(String term){
		String regex = "(.)*[Oo]ther(\\W)*,(\\W)*[Ss]pecify";
		return term.matches(regex);
	}

}
