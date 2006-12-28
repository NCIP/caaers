package gov.nih.nci.cabig.caaers.web;

import java.util.Map;
import java.util.Date;
import java.beans.PropertyEditor;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.io.IOException;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.ModelMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Rhett Sutphin
 */
public class ControllerTools {
    private static ThreadLocal<DateFormat> dateFormat = new ThreadLocal<DateFormat>();

    // TODO: make date format externally configurable
    public static PropertyEditor getDateEditor(boolean required) {
        // note that date formats are not threadsafe, so we have to create a new one each time
        return new CustomDateEditor(createDateFormat(), !required);
    }

    // TODO: make date format externally configurable
    public static DateFormat createDateFormat() {
        return new SimpleDateFormat("MM/dd/yyyy");
    }

    public static String formatDate(Date date) {
        if (dateFormat.get() == null) {
            dateFormat.set(createDateFormat());
        }
        return dateFormat.get().format(date);
    }

    /*
    public static void registerDomainObjectEditor(ServletRequestDataBinder binder, String field, StudyCalendarDao<?> dao) {
        binder.registerCustomEditor(dao.domainClass(), field, new DaoBasedEditor(dao));
    }

    public static void addHierarchyToModel(ScheduledEvent event, Map<String, Object> model) {
        model.put("scheduledEvent", event);
        if (event != null) {
            addHierarchyToModel(event.getPlannedEvent(), model);
            addHierarchyToModel(event.getScheduledArm(), model);
        }
    }

    public static void addHierarchyToModel(ScheduledArm arm, Map<String, Object> model) {
        model.put("scheduledArm", arm);
        if (arm != null) {
            addHierarchyToModel(arm.getArm(), model);
            addHierarchyToModel(arm.getScheduledCalendar(), model);
        }
    }

    private static void addHierarchyToModel(ScheduledCalendar calendar, Map<String, Object> model) {
        model.put("scheduledCalendar", calendar);
        addHierarchyToModel(calendar.getAssignment().getStudySite().getStudy().getPlannedCalendar(), model);
        addHierarchyToModel(calendar.getAssignment(), model);
    }

    private static void addHierarchyToModel(StudyParticipantAssignment assignment, Map<String, Object> model) {
        model.put("assignment", assignment);
        model.put("participant", assignment.getParticipant());
    }

    public static void addHierarchyToModel(PlannedEvent event, Map<String, Object> model) {
        model.put("plannedEvent", event);
        addHierarchyToModel(event.getPeriod(), model);
    }

    public static void addHierarchyToModel(Period period, Map<String, Object> model) {
        model.put("period", period);
        if (period != null) addHierarchyToModel(period.getArm(), model);
    }

    public static void addHierarchyToModel(Arm arm, Map<String, Object> model) {
        model.put("arm", arm);
        addHierarchyToModel(arm.getEpoch(), model);
    }

    public static void addHierarchyToModel(Epoch epoch, Map<String, Object> model) {
        model.put("epoch", epoch);
        addHierarchyToModel(epoch.getPlannedCalendar(), model);
    }

    public static void addHierarchyToModel(PlannedCalendar plannedCalendar, Map<String, Object> model) {
        model.put("plannedCalendar", plannedCalendar);
        model.put("study", plannedCalendar.getStudy());
    }

    public static boolean isAjaxRequest(HttpServletRequest request) {
        String header = request.getHeader("X-Requested-With");
        return header != null && "XMLHttpRequest".equals(header);
    }

    public static ModelAndView redirectToCalendarTemplate(int studyId) {
        return redirectToCalendarTemplate(studyId, null);
    }

    public static ModelAndView redirectToCalendarTemplate(int studyId, Integer selectedArmId) {
        ModelMap model = new ModelMap("study", studyId);
        if (selectedArmId != null) model.put("arm", selectedArmId);
        return new ModelAndView("redirectToCalendarTemplate", model);
    }

    // note that if you change the error message here, you need to change it in error-console.js, too
    public static void sendPostOnlyError(HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "POST is the only valid method for this URL");
    }
	*/
    private ControllerTools() { }
}
