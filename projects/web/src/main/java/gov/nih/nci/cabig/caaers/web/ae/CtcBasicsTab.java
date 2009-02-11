package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.dao.CtcDao;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.Ctc;
import gov.nih.nci.cabig.caaers.domain.CtcTerm;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection;
import gov.nih.nci.cabig.caaers.web.fields.InputField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldAttributes;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.validation.Errors;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Rhett Sutphin
 */
public class CtcBasicsTab extends BasicsTab {
	 private static final Log log = LogFactory.getLog(CtcBasicsTab.class);
	
    private static final String CTC_TERM_FIELD_GROUP = "ctcTerm";

    private static final String CTC_OTHER_FIELD_GROUP = "ctcOther";

    private CtcDao ctcDao;

    public CtcBasicsTab() {
        super("Enter basic AE information", ExpeditedReportSection.BASICS_SECTION.getDisplayName(), "ae/enterBasic");
    }

    @Override
    public Map<String, Object> referenceData(HttpServletRequest request, ExpeditedAdverseEventInputCommand command) {
        Map<String, Object> refdata = super.referenceData(request, command);
        int ctcVersionId = command.getAssignment().getStudySite().getStudy().getAeTerminology().getCtcVersion().getId();
        Ctc ctc = ctcDao.getById(ctcVersionId);
        refdata.put("ctcCategories", ctc.getCategories());
        return refdata;
    }

    @Override
    protected void createFieldGroups(AeInputFieldCreator creator, ExpeditedAdverseEventInputCommand command) {
        super.createFieldGroups(creator, command);
        InputField ctcTermField = InputFieldFactory.createAutocompleterField("adverseEventCtcTerm.ctcTerm", "CTC term", true);
        /*
         * InputFieldAttributes.setDetails(ctcTermField, "Type a portion of the CTC term you are
         * looking for. If you select a category, only terms in that category will be shown.");
         */
        creator.createRepeatingFieldGroup(CTC_TERM_FIELD_GROUP, "adverseEvents", ctcTermField);
        InputField otherVerbatimField = InputFieldFactory.createTextArea("detailsForOther","Other (verbatim)", false);
        InputFieldAttributes.setColumns(otherVerbatimField, 49);
        /*
         * InputFieldAttributes.setDetails(otherVerbatimField,"The CTC term chosen requires a MedDRA
         * based term or a free text entry ");
         */
        InputField otherLowLevelTermField = InputFieldFactory.createAutocompleterField("lowLevelTerm", "Other (MedDRA)", false);
        creator.createRepeatingFieldGroup(CtcBasicsTab.CTC_OTHER_FIELD_GROUP, "adverseEvents", otherLowLevelTermField, otherVerbatimField);
        
        //add the fields for outcomes
        for(InputFieldGroup outcomeFieldGrp : getOutcomeInputFieldGroups(command)){
        	creator.addUnprocessedFieldGroup(outcomeFieldGrp);
        }
    }
    
    
    @Override
    public void postProcess(HttpServletRequest request,	ExpeditedAdverseEventInputCommand command, Errors errors) {
    	super.postProcessOutcomes(command);
    }

    @Override
    protected void validateAdverseEvent(AdverseEvent ae, int index, Map<String, InputFieldGroup> groups, Errors errors) {
        CtcTerm ctcTerm = ae.getAdverseEventCtcTerm().getCtcTerm();
        if (ctcTerm != null && ctcTerm.isOtherRequired() && ae.getDetailsForOther() == null && ae.getLowLevelTerm() == null) {
            InputField field0 = groups.get(CTC_OTHER_FIELD_GROUP + index).getFields().get(0);
            errors.rejectValue(field0.getPropertyName(), "REQUIRED", "Missing " + field0.getDisplayName());

            InputField field1 = groups.get(CTC_OTHER_FIELD_GROUP + index).getFields().get(1);
            errors.rejectValue(field1.getPropertyName(), "REQUIRED", "Missing " + field1.getDisplayName());
        }
        // Inforce business Rule
        if (ctcTerm != null && !ctcTerm.isOtherRequired()) {
            ae.setLowLevelTerm(null);
            ae.setDetailsForOther(null);
        }
    }

    // //// CONFIGURATION

    @Required
    public void setCtcDao(CtcDao ctcDao) {
        this.ctcDao = ctcDao;
    }

    // for testing
    CtcDao getCtcDao() {
        return ctcDao;
    }
}
