package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.Errors;

/**
 * @author Krikor Krumlian
 */
public class MeddraBasicsTab extends BasicsTab {
	
	private static final Log log = LogFactory.getLog(MeddraBasicsTab.class);
	
    // Why?
    private static final String CTC_TERM_FIELD_GROUP = "ctcTerm";

    public MeddraBasicsTab() {
        super("Enter basic AE information", ExpeditedReportSection.BASICS_SECTION.getDisplayName(), "ae/enterBasicMeddra");
    }

    @Override
    protected void createFieldGroups(AeInputFieldCreator creator, ExpeditedAdverseEventInputCommand command) {
        super.createFieldGroups(creator, command);
        creator.createRepeatingFieldGroup(CTC_TERM_FIELD_GROUP, "adverseEvents", InputFieldFactory.createAutocompleterField("adverseEventMeddraLowLevelTerm.lowLevelTerm", "MedDRA code", false));
        
        for(InputFieldGroup outcomeFieldGrp : getOutcomeInputFieldGroups(command)){
        	creator.addUnprocessedFieldGroup(outcomeFieldGrp);
        }

    }
    
    @Override
    public void postProcess(HttpServletRequest request, ExpeditedAdverseEventInputCommand command, Errors errors) {
        super.postProcessOutcomes(command);
    }
}
