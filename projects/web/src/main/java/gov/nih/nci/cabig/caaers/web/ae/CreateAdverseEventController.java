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
            @Override
            protected void addPreBasicTabs(Flow<ExpeditedAdverseEventInputCommand> flow) {
                flow.addTab(new BeginTab<ExpeditedAdverseEventInputCommand>());
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
