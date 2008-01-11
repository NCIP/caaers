package gov.nih.nci.cabig.caaers.web.ae;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.Errors;

/**
 * @author Krikor Krumlian
 */
public class CtcBasicsOutcomeTab extends CtcBasicsTab {
    private static final Log log = LogFactory.getLog(CtcBasicsOutcomeTab.class);
    
    public CtcBasicsOutcomeTab() {
        super();
        super.outcomeHelpKeyExclusion();
    }

    @Override
    protected void createFieldGroups(AeInputFieldCreator creator, ExpeditedAdverseEventInputCommand command) {
    	
    	super.createFieldGroups(creator, command);
        creator.addUnprocessedFieldGroup(super.getOutcomeInputFieldGroup(command));
    }

    @Override
    public void onDisplay(HttpServletRequest request, ExpeditedAdverseEventInputCommand command) {
    	command.updateOutcomes();
    	
    	for (String outcome : command.getOutcomes().keySet()) {
    		log.debug(outcome + "  :   " + command.getOutcomes().get(outcome));
		}
    }
    
	@Override  	
	public void postProcess(HttpServletRequest request, ExpeditedAdverseEventInputCommand command, Errors errors) {	
		super.postProcessOutcomes(command);
	}
}