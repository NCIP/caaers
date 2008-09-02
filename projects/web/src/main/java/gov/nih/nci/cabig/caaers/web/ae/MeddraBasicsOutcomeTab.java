package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.Errors;

/**
 * @author Krikor Krumlian
 */
public class MeddraBasicsOutcomeTab extends MeddraBasicsTab {
    private static final Log log = LogFactory.getLog(MeddraBasicsOutcomeTab.class);

    public MeddraBasicsOutcomeTab() {
        super();
        super.outcomeHelpKeyExclusion();
    }

    @Override
    protected void createFieldGroups(AeInputFieldCreator creator, ExpeditedAdverseEventInputCommand command) {

        super.createFieldGroups(creator, command);
        for(InputFieldGroup outcomeFieldGrp : getOutcomeInputFieldGroups(command)){
        	creator.addUnprocessedFieldGroup(outcomeFieldGrp);
        }

    }

    @Override
    public void onDisplay(HttpServletRequest request, ExpeditedAdverseEventInputCommand command) {
        command.updateOutcomes();
        log.debug("Outcome Map : " + command.getOutcomes().toString());
        log.debug("Outcome otherDetails :" + command.getOutcomeOtherDetails().toString());

    }

    @Override
    public void postProcess(HttpServletRequest request, ExpeditedAdverseEventInputCommand command,
                    Errors errors) {
        super.postProcessOutcomes(command);
    }
}