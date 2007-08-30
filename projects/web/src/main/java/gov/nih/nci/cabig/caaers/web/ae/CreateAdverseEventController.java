package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.ctms.web.tabs.Flow;
import gov.nih.nci.cabig.ctms.web.tabs.FlowFactory;
import org.springframework.validation.Errors;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Rhett Sutphin
 */
public class CreateAdverseEventController extends AbstractAdverseEventInputController {
	
	
	
    public CreateAdverseEventController() {
        super();
        setCommandClass(CreateExpeditedAdverseEventCommand.class);
    }

    @Override
    protected FlowFactory<ExpeditedAdverseEventInputCommand> createFlowFactory() {
        return new ExpeditedFlowFactory("Create expedited report") {
        	
        	private String instructions = "In order to create an expedited AE report, you need to first select a participant and a\n" +
            "study. You may start with either one. Once you have selected one, the options\n" +
            "for the other will be automatically constrained.";
        	
            @Override
            protected void addPreBasicTabs(Flow<ExpeditedAdverseEventInputCommand> flow) {
                flow.addTab(new BeginTab<ExpeditedAdverseEventInputCommand>(instructions));
            }
        };
    }

    @Override
    protected Object formBackingObject(HttpServletRequest request) throws Exception {
        return new CreateExpeditedAdverseEventCommand(assignmentDao, reportDao, reportDefinitionDao, studyDao, participantDao, nowFactory);
    }

    @Override
    protected ExpeditedAdverseEventInputCommand save(ExpeditedAdverseEventInputCommand command, Errors errors) {
        command.save();
        return null;
    }

    @Override
    protected boolean displaySummary(int page) {
        return page != 0;
    }
}
