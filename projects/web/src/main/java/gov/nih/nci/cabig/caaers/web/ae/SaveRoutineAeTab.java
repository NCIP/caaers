package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.dao.CtcDao;
import gov.nih.nci.cabig.caaers.dao.CtcTermDao;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroupMap;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.validation.Errors;

/**
 * @author Krikor Krumlian
 */
public class SaveRoutineAeTab extends AeRoutTab {

    private CtcDao ctcDao;

    private CtcTermDao ctcTermDao;

    public SaveRoutineAeTab() {
        super("Confirm and save", "Save", "ae/saveRoutine");
    }

    @Override
    public void postProcess(HttpServletRequest request, RoutineAdverseEventInputCommand command,
                    Errors errors) {
        handleTermAction(command, request.getParameter("_action"), request
                        .getParameter("_selected"));

    }

    private void handleTermAction(RoutineAdverseEventInputCommand c, String action, String selected) {
        if ("removeTerm".equals(action)) {
            c.getAeRoutineReport().getAdverseEvents().remove(Integer.parseInt(selected));
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public InputFieldGroupMap createFieldGroups(RoutineAdverseEventInputCommand command) {
        InputFieldGroupMap map = new InputFieldGroupMap();
        return map;
    }

    @Override
    protected void validate(RoutineAdverseEventInputCommand command, BeanWrapper commandBean,
                    Map<String, InputFieldGroup> fieldGroups, Errors errors) {
        if (command.getAeRoutineReport().getAdverseEvents().isEmpty()) {
            errors.rejectValue("aeRoutineReport.adverseEvents", "REQUIRED",
                            "Missing Adverse Events");
        }
    }

    // //// CONFIGURATION

    @Required
    public void setCtcDao(CtcDao ctcDao) {
        this.ctcDao = ctcDao;
    }

    // for testing
    CtcDao getCtcDao() {
        return ctcDao;
    }

    public CtcTermDao getCtcTermDao() {
        return ctcTermDao;
    }

    @Required
    public void setCtcTermDao(CtcTermDao ctcTermDao) {
        this.ctcTermDao = ctcTermDao;
    }

}
