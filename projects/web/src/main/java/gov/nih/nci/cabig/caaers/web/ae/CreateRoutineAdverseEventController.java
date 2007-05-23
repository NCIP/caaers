package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.domain.CtcCategory;
import gov.nih.nci.cabig.caaers.web.ae.CreateAdverseEventCommand;
import gov.nih.nci.cabig.ctms.web.tabs.Flow;

import javax.servlet.http.HttpServletRequest;

import org.springframework.validation.BindException;
import org.springframework.validation.Errors;

/**
 * @author Rhett Sutphin
 */
public class CreateRoutineAdverseEventController extends AbstractAdverseEventInputController<CreateAdverseEventCommand> {

	public CreateRoutineAdverseEventController() {
        super();
        setCommandClass(CreateAdverseEventCommand.class);
    }

    @Override
    public void addTabs(Flow<CreateAdverseEventCommand> flow) {
        flow.addTab(new BeginTab());
        flow.addTab(new CategoriesTab());
        flow.addTab(new RoutineAeTab());
        flow.addTab(new EmptyAeTab<CreateAdverseEventCommand>("Confirm and save", "Save", "ae/save"));

        //super.addTabs(flow);
    }

    @Override
    protected String getFlowName() {
        return "Create Routine AE";
    }

    @Override
    protected Object formBackingObject(HttpServletRequest request) throws Exception {
    	CreateAdverseEventCommand caec = new CreateAdverseEventCommand(assignmentDao, reportDao, ruleExecutionService, nowFactory);
    	caec.getAeReport().getAdverseEvents().clear();
        return caec;
    }

    protected void onBindAndValidate(HttpServletRequest request, Object command,BindException errors,int page)throws Exception {

    	// populate categories
    	// TODO See if you do the below with binding
    	CreateAdverseEventCommand adverseEventCommand = (CreateAdverseEventCommand)command;

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
    protected void save(CreateAdverseEventCommand command, Errors errors) {
        command.save();
    }
}
