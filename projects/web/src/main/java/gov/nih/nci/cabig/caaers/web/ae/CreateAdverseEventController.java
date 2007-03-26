package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.dao.CtcDao;
import gov.nih.nci.cabig.caaers.dao.CtcTermDao;
import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.StudyParticipantAssignmentDao;
import gov.nih.nci.cabig.caaers.dao.AdverseEventReportDao;
import gov.nih.nci.cabig.caaers.rules.runtime.RuleExecutionService;
import gov.nih.nci.cabig.caaers.web.tabbedflow.Flow;
import gov.nih.nci.cabig.caaers.web.tabbedflow.AbstractTabbedFlowFormController;
import gov.nih.nci.cabig.caaers.web.ae.CreateAdverseEventCommand;
import gov.nih.nci.cabig.caaers.web.ControllerTools;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.ModelMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;

/**
 * @author Rhett Sutphin
 */
public class CreateAdverseEventController extends AbstractAdverseEventInputController<CreateAdverseEventCommand> {
    private CtcDao ctcDao;
    private AdverseEventReportDao reportDao;
    private RuleExecutionService ruleExecutionService;
    
    
    public void setRuleExecutionService(RuleExecutionService ruleExecutionService) {
		this.ruleExecutionService = ruleExecutionService;
	}

	public CreateAdverseEventController() {
        super();
        setCommandClass(CreateAdverseEventCommand.class);
    }

    @Override
    protected void initFlow() {
        super.initFlow();
        getFlow().addTab(new BeginTab());
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

    ////// CONFIGURATION

    public void setReportDao(AdverseEventReportDao reportDao) {
        this.reportDao = reportDao;
    }

    public void setCtcDao(CtcDao ctcDao) {
        this.ctcDao = ctcDao;
        // TODO: this is a dumb, short-term solution
        ((BasicsTab) getFlow().getTab(1)).setCtcDao(ctcDao);
    }

}
