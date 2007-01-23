package gov.nih.nci.cabig.caaers.web.ae;

import org.springframework.ui.ModelMap;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.ServletRequestDataBinder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

import gov.nih.nci.cabig.caaers.dao.CtcDao;
import gov.nih.nci.cabig.caaers.dao.CaaersDao;
import gov.nih.nci.cabig.caaers.dao.AdverseEventReportDao;
import gov.nih.nci.cabig.caaers.web.ControllerTools;

/**
 * @author Rhett Sutphin
 */
public class EditAdverseEventController extends AbstractAdverseEventInputController<EditAdverseEventCommand> {
    private AdverseEventReportDao adverseEventReportDao;

    public EditAdverseEventController() {
        setCommandClass(EditAdverseEventCommand.class);
        setBindOnNewForm(true);
    }

    @Override
    protected String getFlowName() {
        return "Edit AE";
    }

    @Override
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        super.initBinder(request,binder);
        ControllerTools.registerDomainObjectEditor(binder, "aeReport", adverseEventReportDao);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected ModelAndView processFinish(HttpServletRequest request, HttpServletResponse response, Object oCommand, BindException errors) throws Exception {
        EditAdverseEventCommand command = (EditAdverseEventCommand) oCommand;
        // everything is saved as you move from page to page, so no action required here
        Map<String, Object> model = new ModelMap("participant", command.getParticipant().getId());
        model.put("study", command.getStudy().getId());
        return new ModelAndView("redirectToAeList", model);
    }

    ////// CONFIGURATION

    public void setAdverseEventReportDao(AdverseEventReportDao adverseEventReportDao) {
        this.adverseEventReportDao = adverseEventReportDao;
    }

    public void setCtcDao(CtcDao ctcDao) {
        // TODO: this is a dumb, short-term solution
        ((BasicsTab) getFlow().getTab(0)).setCtcDao(ctcDao);
    }

}
