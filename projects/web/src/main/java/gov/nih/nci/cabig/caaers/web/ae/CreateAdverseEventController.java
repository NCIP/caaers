package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.web.ae.CreateAdverseEventCommand;
import gov.nih.nci.cabig.ctms.web.tabs.Flow;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Rhett Sutphin
 */
public class CreateAdverseEventController extends AbstractAdverseEventInputController<CreateAdverseEventCommand> {
    public CreateAdverseEventController() {
        super();
        setCommandClass(CreateAdverseEventCommand.class);
    }

    @Override
    public void addTabs(Flow<AdverseEventInputCommand> flow) {
        flow.addTab(new BeginTab());
        super.addTabs(flow);
    }


    @Override
    protected String getFlowName() {
        return "Create AE";
    }

    @Override
    protected Object formBackingObject(HttpServletRequest request) throws Exception {
        return new CreateAdverseEventCommand(assignmentDao, reportDao, ruleExecutionService);
    }

    @Override
    protected void doSave(CreateAdverseEventCommand command) {
        command.save();
    }
}
