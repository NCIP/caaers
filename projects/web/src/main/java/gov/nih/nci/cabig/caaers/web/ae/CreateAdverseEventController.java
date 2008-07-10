package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection;
import gov.nih.nci.cabig.caaers.validation.validator.WebControllerValidator;
import gov.nih.nci.cabig.ctms.web.tabs.Flow;
import gov.nih.nci.cabig.ctms.web.tabs.FlowFactory;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;

/**
 * @author Rhett Sutphin
 * @author Biju Joseph
 */
public class CreateAdverseEventController extends AbstractAdverseEventInputController {
	
	//validator needs to be called in onBindAndValidate()
	protected WebControllerValidator webControllerValidator;
	
    public CreateAdverseEventController() {
        super();
        setCommandClass(CreateExpeditedAdverseEventCommand.class);
        setBindOnNewForm(true);
    }

    @Override
    protected FlowFactory<ExpeditedAdverseEventInputCommand> createFlowFactory() {
        return new ExpeditedFlowFactory("Create expedited report") {

            private String instructions = "In order to create an expedited AE report, you need to first select a participant and a\n"
                            + "study. You may start with either one. Once you have selected one, the options\n"
                            + "for the other will be automatically constrained.";

            @Override
            protected void addPreBasicTabs(Flow<ExpeditedAdverseEventInputCommand> flow) {
                flow.addTab(new BeginTab<ExpeditedAdverseEventInputCommand>(instructions));
            }
        };
    }

    @Override
    protected Object formBackingObject(HttpServletRequest request) throws Exception {
        return new CreateExpeditedAdverseEventCommand(assignmentDao, reportDao,
                        reportDefinitionDao, studyDao, participantDao, nowFactory,
                        expeditedReportTree);
    }

    @Override
    protected ExpeditedAdverseEventInputCommand save(ExpeditedAdverseEventInputCommand command,
                    Errors errors) {
        command.save();
        return null;
    }

    @Override
    protected boolean displaySummary(int page) {
        return page != 0;
    }
    /**
     * Supress validation when :
     *   - it is an ajax request
     *   - report is not saved yet.
     *   - attribution page, and is going back.
     */
    @Override
    protected boolean suppressValidation(HttpServletRequest request, Object command) {
        if (super.suppressValidation(request, command)) return true;
        CreateExpeditedAdverseEventCommand aeCommand = (CreateExpeditedAdverseEventCommand) command;
        boolean attributionPage = getFlow(aeCommand).getTab(getCurrentPage(request)).getShortTitle().equals(ExpeditedReportSection.ATTRIBUTION_SECTION.getDisplayName()); 
        //special case, if it is attribution page, allow go back.
        if(attributionPage) return  super.getCurrentPage(request) > aeCommand.getNextPage();
        if (aeCommand.getAeReport().getId() != null) return false;
        return super.getCurrentPage(request) > aeCommand.getNextPage();
    }
    
    /**
     * Will call the validate method on web controller.
     */
    @Override
	protected void onBindAndValidate(HttpServletRequest request,
			Object command, BindException errors, int page) throws Exception {
		super.onBindAndValidate(request, command, errors, page);
		webControllerValidator.validate(request, command, errors);
	}
    
    @Required
	public void setWebControllerValidator(WebControllerValidator webControllerValidator) {
	    this.webControllerValidator = webControllerValidator;
	}

}
