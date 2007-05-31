package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.dao.AdverseEventReportDao;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.RoutineAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.CtcCategory;
import gov.nih.nci.cabig.caaers.web.ae.CreateRoutineAdverseEventCommand;
import gov.nih.nci.cabig.ctms.web.tabs.Flow;

import javax.servlet.http.HttpServletRequest;

import org.springframework.validation.BindException;
import org.springframework.validation.Errors;

/**
 * @author Rhett Sutphin
 */
public class CreateRoutineAdverseEventController extends AbstractRoutineAdverseEventInputController<CreateRoutineAdverseEventCommand> {

	public CreateRoutineAdverseEventController() {
        super();
        setCommandClass(CreateRoutineAdverseEventCommand.class);
    }

    @Override
    public void addTabs(Flow<CreateRoutineAdverseEventCommand> flow) {
        flow.addTab(new BeginTab<CreateRoutineAdverseEventCommand>());
        flow.addTab(new CategoriesTab());
        flow.addTab(new RoutineAeTab());
        flow.addTab(new EmptyAeTab<CreateRoutineAdverseEventCommand>("Confirm and save", "Save", "ae/save"));

        //super.addTabs(flow);
    }

    @Override
    protected String getFlowName() {
        return "Create Routine AE";
    }

    @Override
    protected Object formBackingObject(HttpServletRequest request) throws Exception {
    	CreateRoutineAdverseEventCommand caec = new CreateRoutineAdverseEventCommand(assignmentDao, routineReportDao, reportDao, ruleExecutionService, nowFactory);
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
    protected void save(CreateRoutineAdverseEventCommand command, Errors errors) {
        command.save();
    }
}
