package gov.nih.nci.cabig.caaers.web.ae;

import org.springframework.beans.BeanWrapper;
import org.springframework.validation.Errors;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.ListIterator;

import gov.nih.nci.cabig.caaers.dao.PriorTherapyDao;
import gov.nih.nci.cabig.caaers.domain.PriorTherapy;
import gov.nih.nci.cabig.caaers.domain.PreExistingCondition;
import gov.nih.nci.cabig.caaers.domain.SAEReportPriorTherapy;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection;
import gov.nih.nci.cabig.caaers.web.fields.InputField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldAttributes;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory;

/**
 * @author Rhett Sutphin
 */
public class PriorTherapyTab extends AeTab {

	private PriorTherapyDao priorTherapyDao;
	
    public PriorTherapyTab() {
        super("Prior Therapies", ExpeditedReportSection.PRIOR_THERAPIES_SECTION.getDisplayName(), "ae/priorTherapies");
        setAutoPopulateHelpKey(true);
        addHelpKeyExclusion("other","startDate","endDate");
    }

    @Override
    protected void createFieldGroups(AeInputFieldCreator creator, ExpeditedAdverseEventInputCommand command) {
        //InputField priorTherapyField = InputFieldFactory.createAutocompleterField("priorTherapy", "Prior therapy", false);
        InputField priorTherapyField = InputFieldFactory.createSelectField("priorTherapy", "Prior therapy", false, createOptions());
        /*InputFieldAttributes.setDetails(priorTherapyField, "If the appropriate therapy is not listed, type the therapy in the &quot;Comments(prior therapy)&quot; field below.");*/
        //TODO: change the name of other to "comment"
        InputField otherField = InputFieldFactory.createTextArea("other", "Comments", false);
        InputFieldAttributes.setColumns(otherField, 65);
        InputField startDateField = InputFieldFactory.createDateField("startDate", "Therapy start Date", false);
        /*InputFieldAttributes.setDetails(startDateField, "If known, enter start date for prior therapy.");*/
        InputField endDateField = InputFieldFactory.createDateField("endDate", "Therapy end date", false);
        /*InputFieldAttributes.setDetails(endDateField, "If known, enter end date for prior therapy.");*/

        creator.createRepeatingFieldGroup("priorTherapy", "saeReportPriorTherapies",
            new SimpleNumericDisplayNameCreator("Prior therapy"),
            priorTherapyField,
            otherField,
            startDateField,
            endDateField
            
        );

        creator.createRepeatingFieldGroup("ptAgent", "saeReportPriorTherapies",
        		InputFieldFactory.createAutocompleterField("priorTherapyAgents.chemoAgent", "Agent", false)	
        );
    }

    private Map<Object, Object> createOptions() {
        Map<Object, Object> options = new LinkedHashMap<Object, Object>();
        
        List<PriorTherapy> list = priorTherapyDao.getAll();
        options.put("", "Please select");
    	if(list != null){
    		for(PriorTherapy l : list ){
    			options.put(l.getId(), l.getText());
    		}
    	}
    	
        return options;
    }
    
    
    @Override
    protected void validate(
        ExpeditedAdverseEventInputCommand command, BeanWrapper commandBean,
        Map<String, InputFieldGroup> fieldGroups, Errors errors
    ) {
        for (ListIterator<SAEReportPriorTherapy> it = command.getAeReport().getSaeReportPriorTherapies().listIterator(); it.hasNext();) {
            SAEReportPriorTherapy aePriorTherapy = it.next();
            validatePriorTherapy(aePriorTherapy, it.previousIndex(), errors);
        }
    }

    private void validatePriorTherapy(SAEReportPriorTherapy aePriorTherapy, int index, Errors errors) {
        if (aePriorTherapy.getPriorTherapy() == null) {
            errors.rejectValue(
                String.format("aeReport.saeReportPriorTherapies[%d]", index),
                "REQUIRED",
                "Prior Therapy is required"
            );
        }
    }
    @Override
    public ExpeditedReportSection section() {
    	return ExpeditedReportSection.PRIOR_THERAPIES_SECTION;
    }

	public PriorTherapyDao getPriorTherapyDao() {
		return priorTherapyDao;
	}

	public void setPriorTherapyDao(PriorTherapyDao priorTherapyDao) {
		this.priorTherapyDao = priorTherapyDao;
	}
}
