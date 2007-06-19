package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.ctms.web.tabs.Flow;
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
    public void addTabs(Flow<ExpeditedAdverseEventInputCommand> flow) {
        flow.addTab(new BeginTab());
        super.addTabs(flow);
    }

    @Override
    protected String getFlowName() {
        return "Create expedited report";
    }

    @Override
    protected Object formBackingObject(HttpServletRequest request) throws Exception {
        return new CreateExpeditedAdverseEventCommand(assignmentDao, reportDao, ruleExecutionService, nowFactory);
    }

    @Override
    protected void save(ExpeditedAdverseEventInputCommand command, Errors errors) {
        command.save();
    }

    @Override
    protected boolean displaySummary(int page) {
        return page != 0;
    }
}
