package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.domain.CtcCategory;
import gov.nih.nci.cabig.caaers.web.ae.CreateRoutineAdverseEventCommand;
import gov.nih.nci.cabig.ctms.web.tabs.Flow;

import javax.servlet.http.HttpServletRequest;

import org.springframework.validation.BindException;
import org.springframework.validation.Errors;

/**
 * @author Krikor Krumlian
 */
public class CreateRoutineAdverseEventController extends AbstractRoutineAdverseEventInputController {

	public CreateRoutineAdverseEventController() {
        super();
        setCommandClass(CreateRoutineAdverseEventCommand.class);
    }

    @Override
    public void addTabs(Flow<RoutineAdverseEventInputCommand> flow) {
        flow.addTab(new BeginTab<RoutineAdverseEventInputCommand>());
        super.addTabs(flow);
    }
    
    @Override
    public void addMeddraTabs(Flow<RoutineAdverseEventInputCommand> flow) {
        flow.addTab(new BeginTab<RoutineAdverseEventInputCommand>());
        super.addMeddraTabs(flow);
    }

    

    @Override
    protected String getFlowName() {
        return "Create Routine AE";
    }

    @Override
    protected Object formBackingObject(HttpServletRequest request) throws Exception {
    	CreateRoutineAdverseEventCommand caec = new CreateRoutineAdverseEventCommand(assignmentDao, routineReportDao, reportDao, nowFactory);
    	//caec.getAeReport().getAdverseEvents().clear();
        return caec;
    }
    
    protected void onBindAndValidate(HttpServletRequest request, Object command,BindException errors,int page)throws Exception {

    	// populate categories
    	// TODO See if you do the below with binding
    	CreateRoutineAdverseEventCommand adverseEventCommand = (CreateRoutineAdverseEventCommand)command;

    	if (adverseEventCommand.getCtcCatIds() != null && page ==1 ) {
			for (String st : adverseEventCommand.getCtcCatIds()) {
				CtcCategory ctcCategory = ctcCategoryDao.getById(Integer.parseInt(st));
				adverseEventCommand.getCategories().add(ctcCategory);
			}
    	}

    }

    @Override
    protected boolean displaySummary(int page) {
        return page != 0;
    }

    @Override
    protected RoutineAdverseEventInputCommand save(RoutineAdverseEventInputCommand command, Errors errors) {
        command.save();
        return null;
    }
}
