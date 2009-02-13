package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.domain.CtcCategory;
import gov.nih.nci.cabig.ctms.web.tabs.Flow;
import gov.nih.nci.cabig.ctms.web.tabs.FlowFactory;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.validation.BindException;
import org.springframework.validation.Errors;

/**
 * @author Krikor Krumlian
 */
public class CreateRoutineAdverseEventController extends AbstractRoutineAdverseEventInputController {

    public CreateRoutineAdverseEventController() {
        super();
        setBindOnNewForm(true);
        setCommandClass(CreateRoutineAdverseEventCommand.class);
    }

    @Override
    protected FlowFactory<RoutineAdverseEventInputCommand> createFlowFactory() {
        return new RoutineFlowFactory("Document routine AEs") {

            private String instructions = "In order to document Routine AEs, you need to first select a participant and a\n"
                            + "study. You may start with either one. Once you have selected one, the options\n"
                            + "for the other will be automatically constrained.";

            @Override
            protected void addPreBasicTabs(Flow<RoutineAdverseEventInputCommand> flow) {
                //flow.addTab(new BeginTab<RoutineAdverseEventInputCommand>(instructions));
            }
        };
    }

    @Override
    protected Object formBackingObject(HttpServletRequest request) throws Exception {
        CreateRoutineAdverseEventCommand caec = new CreateRoutineAdverseEventCommand(assignmentDao,
                        routineReportDao, reportDao, nowFactory, evaluationService);
        return caec;
    }

    protected void onBindAndValidate(HttpServletRequest request, Object command,
                    BindException errors, int page) throws Exception {

        // populate categories
        // TODO See if you do the below with binding , the problem is the items are not being
        // checked when go back
        CreateRoutineAdverseEventCommand adverseEventCommand = (CreateRoutineAdverseEventCommand) command;

        if (adverseEventCommand.getCtcCatIds() != null && page == 1) {
            adverseEventCommand.setCategories(new ArrayList<CtcCategory>());
            for (String st : adverseEventCommand.getCtcCatIds()) {
                CtcCategory ctcCategory = ctcCategoryDao.getById(Integer.parseInt(st));
                adverseEventCommand.getCategories().add(ctcCategory);
            }
        }

    }

    @Override
    protected boolean suppressValidation(HttpServletRequest request, Object command) {
        // supress validation when target page is less than current page.
        int curPage = getCurrentPage(request);
        int targetPage = getTargetPage(request, curPage);
        if (targetPage < curPage) return true;
        return super.suppressValidation(request, command);
    }

    @Override
    protected RoutineAdverseEventInputCommand save(RoutineAdverseEventInputCommand command,
                    Errors errors) {
        command.save();
        return null;
    }

    @Override
    protected boolean displaySummary(int page) {
        return page != 0;
    }

}
